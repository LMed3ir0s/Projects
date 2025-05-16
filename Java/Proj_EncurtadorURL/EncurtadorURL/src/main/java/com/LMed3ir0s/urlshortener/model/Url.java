package com.LMed3ir0s.urlshortener.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originalUrl;

    @Column(nullable = false, unique = true)
    private String shortCode;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

// => Getters e Setters

    public String getOriginalUrl() {
        return originalUrl;
    }
    public void setOriginalUrl(String originalUrl){
        this.originalUrl = originalUrl;
    }


    public String getShortCode() {
        return shortCode;
    }
    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
        }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }
}



