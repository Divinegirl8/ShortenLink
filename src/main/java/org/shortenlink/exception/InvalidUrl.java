package org.shortenlink.exception;

public class InvalidUrl extends RuntimeException {
    public InvalidUrl(String message) {
        super(message);
    }
}
