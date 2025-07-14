package com.instamart.shopping_delivery.exceptions;

public class UserNotExistException extends RuntimeException{
    public UserNotExistException(String msg){
        super(msg);
    }
}
