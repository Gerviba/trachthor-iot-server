package hu.trackthor.server.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hu.trackthor.server.model.Segment;

@Repository
public interface SegmentRepository extends CrudRepository<Segment, Long> {

    void deleteByPoligon(long longValue);

    List<Segment> findAllByPoligon(Long id);

}
