package com.minh.speechmanager.repository;

import com.minh.speechmanager.model.Speech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeechRepository extends JpaRepository<Speech, Long> {
    List<Speech> findByKeywordsContainingIgnoreCase(String keyword);

    @Query("SELECT s FROM Speech s WHERE LOWER(s.speechName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Speech> findBySpeechNameContaining(String name);

    @Query("SELECT s FROM Speech s WHERE LOWER(s.author) LIKE LOWER(CONCAT('%', :author, '%'))")
    List<Speech> findByAuthorContaining(String author);

    @Query("SELECT s FROM Speech s JOIN s.keywords k WHERE LOWER(s.author) LIKE LOWER(CONCAT('%', :author, '%')) AND LOWER(k) = LOWER(:keyword)")
    List<Speech> findByAuthorAndKeywordsContaining(String author, String keyword);
}
