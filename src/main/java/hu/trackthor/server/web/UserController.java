package hu.trackthor.server.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.trackthor.server.service.WarningService;

@Controller
@RequestMapping("/control")
public class UserController {

    @Autowired
    WarningService warnings;
    
    @GetMapping
    String index(Map<String, Object> model) {
        model.put("warnings", warnings.getAll());
        System.out.println(warnings.getAll());
        return "dashboard";
    }
 
    @GetMapping("/devices")
    String devices() {
        return "devices";
    }

    @GetMapping("/device/{id}")
    String device(@PathVariable String id) {
        return "device";
    }
    
    @GetMapping("/device/{id}/map")
    String map(@PathVariable String id) {
        return "map";
    }
    
    @GetMapping("/map")
    String mapAll() {
        return "map";
    }
    
    // Post -> Add device 
    // Post -> MOdify device
    
}
