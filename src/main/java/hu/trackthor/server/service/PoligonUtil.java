package hu.trackthor.server.service;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.trackthor.server.model.Poligon;
import hu.trackthor.server.model.Segment;
import hu.trackthor.server.repo.PoligonRepository;
import hu.trackthor.server.repo.SegmentRepository;

@Service
public class PoligonUtil {

    @Autowired
    PoligonRepository poligons;
    
    @Autowired
    SegmentRepository segments;
    
    public boolean isInPoligon(double latitude, double longnitude, List<Segment> segments) {
        Line2D baseline = new Line2D.Double(latitude, longnitude, latitude + 1, longnitude);
        
        return segments.stream().map(seg -> new Line2D.Double(
                    seg.getLat1(), seg.getLong1(),
                    seg.getLat2(), seg.getLong2()))
                .filter(baseline::intersectsLine)
                .count() % 2 == 1;
    }
    
    @Transactional
    public void addPoligon(String name, String color, boolean goZone, String coordinates) {
        Poligon p = new Poligon(null, color, name, goZone);
        p = poligons.save(p);
        
        List<Segment> segments = new ArrayList<>();
        List<String> coords = Arrays.asList(coordinates.split("\n"));
        for (int i = 0; i < coords.size() - 1; ++i) {
            double lat1 = Double.parseDouble(coords.get(i).split("[ \t]*")[0]);
            double long1 = Double.parseDouble(coords.get(i).split("[ \t]*")[1]);
            double lat2 = Double.parseDouble(coords.get(i + 1).split("[ \t]*")[0]);
            double long2 = Double.parseDouble(coords.get(i + 1).split("[ \t]*")[1]);
            segments.add(new Segment(null, p.getId(), lat1, long1, lat2, long2, i));
        }
        
        double lat1 = Double.parseDouble(coords.get(0).split("[ \t]*")[0]);
        double long1 = Double.parseDouble(coords.get(0).split("[ \t]*")[1]);
        double lat2 = Double.parseDouble(coords.get(coords.size() - 1).split("[ \t]*")[0]);
        double long2 = Double.parseDouble(coords.get(coords.size() - 1).split("[ \t]*")[1]);
        segments.add(new Segment(null, p.getId(), lat1, long1, lat2, long2, coords.size() - 1));
        
        segments.forEach(this.segments::save);
    }
    
    @Transactional
    public void removePolgon(Long id) {
        poligons.deleteById(id);
        segments.deleteByPoligon(id.longValue());
    }

    @Transactional
    public void togglePoligon(Long id) {
        Poligon p = poligons.findById(id).get();
        p.setGoZone(!p.isGoZone());
        poligons.save(p);
    }
    
    @Transactional
    public String getGeofenceStatus(double latitude, double longnitude) {
        boolean go = false;
        for (Poligon p : poligons.findAll()) {
            if (isInPoligon(latitude, longnitude, segments.findAllByPoligon(p.getId()))) {
                if (!p.isGoZone()) {
                    return "WRONG";
                } else {
                    go = true;
                }
            }
        }
        return go ? "GOOD" : "NOTSET";
    }
    
}
