package org.shortenlink.dtos.request;

import lombok.Data;

@Data
public class LinkShortenerRequest {
    private String longUrl;
}
