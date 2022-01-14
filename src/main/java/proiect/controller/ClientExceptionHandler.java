package proiect.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import proiect.service.ClientException;

@ControllerAdvice
public class ClientExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<String> handleClientException(ClientException clientException, WebRequest request){
        HttpStatus status=HttpStatus.INTERNAL_SERVER_ERROR;
        if(clientException.getErrorCodeClient()== ClientException.ErrorCodeClient.CLIENT_NOT_FOUND){
            status=HttpStatus.UNAUTHORIZED;
        }else if(clientException.getErrorCodeClient()== ClientException.ErrorCodeClient.DOES_NOT_HAVE_PERMISSION){
            status=HttpStatus.FORBIDDEN;
        }else if(clientException.getErrorCodeClient()== ClientException.ErrorCodeClient.NOT_ENOUGH_MONEY){
            status=HttpStatus.INSUFFICIENT_STORAGE;
        }else if (clientException.getErrorCodeClient()== ClientException.ErrorCodeClient.NOT_ELIGIBLE_FOR_DISCOUNT){
            status=HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity<>(clientException.getErrorCodeClient().name(),status);
    }
}
