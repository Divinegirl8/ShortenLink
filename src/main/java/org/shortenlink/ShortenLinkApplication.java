package org.shortenlink;


import com.sun.tools.javac.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShortenLinkApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortenLinkApplication.class,args);
    }
}