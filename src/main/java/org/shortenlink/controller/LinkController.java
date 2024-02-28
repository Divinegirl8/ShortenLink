package org.shortenlink.controller;

import lombok.extern.slf4j.Slf4j;
import org.shortenlink.dtos.request.LinkShortenerRequest;
import org.shortenlink.dtos.response.ApiResponse;
import org.shortenlink.dtos.response.ShortenLinkMessage;
import org.shortenlink.service.LinkShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class LinkController {
    @Autowired
    LinkShortenerService linkShortenerService;

//    @PostMapping("/processLink")
//    public String processLink(@RequestBody LinkShortenerRequest linkShortenerRequest){
//        return linkShortenerService.getShortUrl(linkShortenerRequest.getLongUrl());
//    }


    @PostMapping("/processLink")
    public ResponseEntity<?> processLink(@RequestBody LinkShortenerRequest linkShortenerRequest){
        ShortenLinkMessage shortenLinkMessage = new ShortenLinkMessage();

        try{
           String message = linkShortenerService.getShortUrl(linkShortenerRequest.getLongUrl());
           shortenLinkMessage.setMessage(message);
           return ResponseEntity.ok().body(message);
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

    }
}
