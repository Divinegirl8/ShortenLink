package org.shortenlink.service;

import com.opsmatters.bitly.Bitly;
import com.opsmatters.bitly.api.model.v4.CreateBitlinkResponse;
import jakarta.annotation.PostConstruct;
import org.shortenlink.dtos.request.LinkShortenerRequest;
import org.shortenlink.exception.InvalidUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


@Service
public class LinkShortenerService {
//    @Value("${BITLY_TOKEN}")
//    private String BITLY_TOKEN;
//
//    private Bitly client;
//
//    @PostConstruct
//    public void setUp() {
//        client = new Bitly(BITLY_TOKEN);
//    }
//
//    public String getShortUrl(String longUrl) {
//        longUrl = formatUrlWithScheme(longUrl);
//
//        String link;
//        try {
//            CreateBitlinkResponse response = client.bitlinks().shorten(longUrl).get();
//            link = response.getLink();
//        } catch (Exception e) {
//            return "Error: " + e.getMessage();
//        }
//        return link;
//    }
//
//    private String formatUrlWithScheme(String url) {
//        try {
//            URI uri = new URI(url);
//            if (uri.getScheme() == null) {
//                url = "http://" + url;
//            }
//        } catch (URISyntaxException e) {
//
//        }
//        return url;
//    }
//
    @Value("${BITLY_TOKEN}")
    private String BITLY_TOKEN;

    private Bitly client;

    @PostConstruct
    public void setUp() {
        client = new Bitly(BITLY_TOKEN);
    }

    public String getShortUrl(String longUrl) {

        try {
            throwError(longUrl);
        } catch (Exception e) {
            return e.getMessage();
        }
         longUrl = formatUrlWithScheme(longUrl);
        String link;
        try {
            CreateBitlinkResponse response = client.bitlinks().shorten(longUrl).get();
            link = response.getLink();
        } catch (Exception e) {
            return e.getMessage();
        }
        return link;
    }

    private String formatUrlWithScheme(String url) {
        try {
            URI uri = new URI(url);
            if (uri.getScheme() == null) {
                url = "http://" + url;
            }
        } catch (URISyntaxException e) {

        }
        return url;
    }
private static void throwError(String url){
        if (!verifyUrl(url)){
            throw new InvalidUrl(url + " does not exist or not accessible");
        }
}

    private static boolean verifyUrl(String url){
        try {
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://" + url;
            }

            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("HEAD");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }
}
