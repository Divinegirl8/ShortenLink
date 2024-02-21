package org.shortenlink.service;

import com.opsmatters.bitly.Bitly;
import com.opsmatters.bitly.api.model.v4.CreateBitlinkResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LinkShortenerService {
    @Value("${BITLY_TOKEN}")
    String BITLY_TOKEN;
    private Bitly client;
  @PostConstruct
  public void setUp(){
      client = new Bitly(BITLY_TOKEN);
  }
    public String getShortUrl(String longUrl){
      String link;

      try{
          CreateBitlinkResponse response = client.bitlinks().shorten(longUrl).get();
          link = response.getLink();
      }
      catch (Exception e){
return e + " error";
      }
   return link;
    }
}
