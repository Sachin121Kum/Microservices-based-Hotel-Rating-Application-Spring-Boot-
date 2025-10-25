package com.lwcd.user.service.exception;

public class ResourceNotFOundException extends  RuntimeException{

    public ResourceNotFOundException(){
        super("Resource not found on Server");
    }

    public ResourceNotFOundException(String message){
        super(message);
    }


}
