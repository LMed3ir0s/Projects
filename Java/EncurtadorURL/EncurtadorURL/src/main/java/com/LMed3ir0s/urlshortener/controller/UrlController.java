package com.LMed3ir0s.urlshortener.controller;

import com.LMed3ir0s.urlshortener.model.Url;
import com.LMed3ir0s.urlshortener.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/shorten")
public class UrlController {

    private final UrlService service;

    public UrlController(UrlService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Url> shorten(@RequestBody String originalUrl) {
        Url shorted = service.shortenUrl(originalUrl);
        return ResponseEntity.ok(shorted);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirect(@PathVariable String shortCode){
        String originalUrl = service.getOriginalUrl(shortCode);
        return ResponseEntity.status(302)
                .header("Location", originalUrl)
                .build();
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<Void> delete(@PathVariable String shortCode){
        service.deleteByShortCode(shortCode);
        return ResponseEntity.noContent().build();
    }


}
