package com.minh.speechmanager.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "speeches")
public class Speech {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id; // Speech ID

    @Column(name = "speechName", nullable = false)
    @JsonProperty("speechName")
    private String speechName;

    @Column(name = "author", nullable = false)
    @JsonProperty("author")
    private String author;

    @Column(name = "text", nullable = false, columnDefinition = "TEXT")
    @JsonProperty("content")
    private String content;

    @Column(name = "date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private LocalDate date;

    @ElementCollection
    @CollectionTable(name = "speechKeywords", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "keyword")
    @JsonProperty("keywords")
    private List<String> keywords;


    public int getId(){
        return id;
    }

    // Speech name
    public void setSpeechName(String speechName){
        this.speechName = speechName;
    }

    public String getSpeechName() {
        return speechName;
    }

    // Author
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    // Content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Date
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Keyword
    public void setKeywords(List<String> keywords){
        this.keywords = keywords;
    }

    public List<String> getKeywords(){
        return keywords;
    }
}
