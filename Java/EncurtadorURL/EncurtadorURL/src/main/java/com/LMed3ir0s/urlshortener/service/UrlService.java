package com.LMed3ir0s.urlshortener.service;

import com.LMed3ir0s.urlshortener.model.Url;
import com.LMed3ir0s.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.Optional; // => tratativa de erro .orElseThrow(...)

@Service
public class UrlService {

    private final UrlRepository repository;

    public UrlService(UrlRepository repository){
        this.repository = repository;
    }

    public Url shortenUrl(String originalUrl){
        String shortUrl = UUID.randomUUID().toString().substring(0, 6);
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortCode(shortUrl);
        return repository.save((url));
    }

    public String getOriginalUrl(String shortCode){
        Url urlEnity = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL encurtada não encontrada"));
        return urlEnity.getOriginalUrl();
    }

    public void deleteByShortCode(String shortCode){
        Url urlEnity = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL encurtada não encontrada"));
        repository.delete(urlEnity);
    }
}
