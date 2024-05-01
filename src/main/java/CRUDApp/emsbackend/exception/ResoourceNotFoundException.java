package CRUDApp.emsbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResoourceNotFoundException extends RuntimeException{

    public ResoourceNotFoundException(String message){
        super(message);
    }
}
