package com.minh.speechmanager.service;

import com.minh.speechmanager.model.Speech;
import com.minh.speechmanager.repository.SpeechRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SpeechService {
    private final SpeechRepository speechRepository;

    public SpeechService(SpeechRepository speechRepository) {
        this.speechRepository = speechRepository;
    }

    public Speech createSpeech(Speech speech) {
        return speechRepository.save(speech);
    }

    public List<Speech> getAllSpeech() {
        return speechRepository.findAll();
    }

    public Optional<Speech> searchByID(Long id) {
        Optional<Speech> result = speechRepository.findById(id);
        if (result.isPresent()) {
            return result;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Speech not found");
        }
    }

    public List<Speech> searchByName(String name) {
        List<Speech> result = speechRepository.findBySpeechNameContaining(name);
        if (!result.isEmpty()) {
            return result;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Speech not found");
        }
    }

    public List<Speech> searchByAuthorAndKeyword(String author, String keyword) {
        List<Speech> result = null;
        if (author != null && keyword != null) {
            result = speechRepository.findByAuthorAndKeywordsContaining(author, keyword);
        } else if (keyword != null) {
            result = speechRepository.findByKeywordsContainingIgnoreCase(keyword);
        } else {
            result = speechRepository.findByAuthorContaining(author);
        }

        if (!result.isEmpty()) {
            return result;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Speech not found");
        }
    }

    public Speech update(Long id, Speech newSpeech) {
        Optional<Speech> speechOpt = speechRepository.findById(id);
        if (speechOpt.isPresent()) {
            Speech oldSpeech = speechOpt.get();
            // Name
            if (newSpeech.getSpeechName() != null) {
                oldSpeech.setSpeechName(newSpeech.getSpeechName());
            }
            // Author
            if (newSpeech.getAuthor() != null) {
                oldSpeech.setAuthor(newSpeech.getAuthor());
            }
            // Content
            if (newSpeech.getContent() != null) {
                oldSpeech.setContent(newSpeech.getContent());
            }
            // Date
            if (newSpeech.getDate() != null) {
                oldSpeech.setDate(newSpeech.getDate());
            }
            // Keyword
            if (newSpeech.getKeywords() != null && !newSpeech.getKeywords().isEmpty()) {
                oldSpeech.setKeywords(newSpeech.getKeywords());
            }
            return speechRepository.save(oldSpeech);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Speech not found");
        }
    }

    public void delete(Long id) {
        speechRepository.deleteById(id);
    }
}
