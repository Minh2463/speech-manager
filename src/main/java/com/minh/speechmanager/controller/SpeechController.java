package com.minh.speechmanager.controller;

import com.minh.speechmanager.model.Speech;
import com.minh.speechmanager.service.SpeechService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/speech/")
public class SpeechController {
    private final SpeechService speechService;

    public SpeechController(SpeechService speechService) {
        this.speechService = speechService;
    }

    // Create
    @PostMapping("/create")
    public ResponseEntity<String> createSpeech(@RequestBody Speech speech) {
        Speech newSpeech = speechService.createSpeech(speech);
        return ResponseEntity.ok("Speech created successfully. ID is " + newSpeech.getId());
    }

    // Search
    @GetMapping("search/all")
    public List<Speech> getAll() {
        return speechService.getAllSpeech();
    }

    @GetMapping("/search/extra")
    public List<Speech> search(@RequestParam(required = false) String author,
                               @RequestParam(required = false) String keyword) {
        return speechService.searchByAuthorAndKeyword(author, keyword);
    }

    @GetMapping("/search/name")
    public List<Speech> searchByName(@RequestParam(required = false) String name) {
        return speechService.searchByName(name);
    }

    @GetMapping("/search/id")
    public Optional<Speech> searchByID(@RequestParam(required = false) Long id) {
        return speechService.searchByID(id);
    }

    // Edit
    @PutMapping("/edit")
    public ResponseEntity<Speech> update(@RequestParam Long id, @RequestBody Speech newSpeechInfo) {
        Speech speech = speechService.update(id, newSpeechInfo);
        return ResponseEntity.ok(speech);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        speechService.delete(id);
        return ResponseEntity.ok("Speech deleted successfully");
    }

}
