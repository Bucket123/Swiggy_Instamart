package com.instamart.shopping_delivery.exceptions;

public class InvalidOperationException extends RuntimeException{
    public InvalidOperationException(String msg){
        super(msg);
    }
}
