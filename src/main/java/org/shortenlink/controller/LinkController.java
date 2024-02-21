package org.shortenlink.controller;

import lombok.extern.slf4j.Slf4j;
import org.shortenlink.dtos.request.LinkShortenerRequest;
import org.shortenlink.service.LinkShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/processLink")
    public String processLink(@RequestBody LinkShortenerRequest linkShortenerRequest){
        return linkShortenerService.getShortUrl(linkShortenerRequest.getLongUrl());
    }
}
