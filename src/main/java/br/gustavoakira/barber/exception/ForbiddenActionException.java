package br.gustavoakira.barber.exception;

public class ForbiddenActionException extends RuntimeException{
    public ForbiddenActionException(String msg){
        super(msg);
    }
}
