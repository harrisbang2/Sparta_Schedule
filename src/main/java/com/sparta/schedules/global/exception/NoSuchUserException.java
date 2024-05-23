package com.sparta.schedules.global.exception;

public class NoSuchUserException extends IllegalArgumentException{
    public NoSuchUserException(){
        super("해당 유저를 찾을수 없습니다");
    }
    public NoSuchUserException(String s){
        super(s);
    }
}
