package com.devconnect.DevConnect.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.devconnect.DevConnect.models.Developer;

@RequestMapping("/api/v1/developers")
public class DeveloperController {

    // In Memory Storage
    // ConcurrentHashMap is thread-safe — safe for concurrent requests
    
    private Map<Long, Developer> developers = new ConcurrentHashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);
    
    @GetMapping("/") 
    public List<Developer> getDevelopers() {
        return new ArrayList<>(developers.values());
    }

    @GetMapping("/{id}")
    public Developer getDeveloperById(@PathVariable Long id) {
        return developers.get(id);
    }

    @GetMapping("/search")
    public List<Developer> getDevelopersBySkill(
        @RequestParam String skill,
        @RequestParam(required = false) String name,
        @RequestParam(defaultValue = "0") int cnt
    ) {
        List<Developer> developersWithSkill = new ArrayList<>();
        for (Developer developer : developers.values()) {
            if (developer.getSkills().contains(skill)) {
                developersWithSkill.add(developer);
            }
        }
        return developersWithSkill;
    }

    // @PostMapping
    // public Developer addDeveloper(@RequestBody Developer developer) {
    //     developer.setId(idCounter.getAndIncrement());
    //     developers.put(developer.getId(), developer);
    //     return developer;
    // }

    @PostMapping
    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer developer) {
        developer.setId(idCounter.getAndIncrement());
        developers.put(developer.getId(), developer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
