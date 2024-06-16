package dom.com.thesisoauth2resourceserver.controllers;

import dom.com.thesisoauth2resourceserver.exceptions.CategoryException;
import dom.com.thesisoauth2resourceserver.exceptions.ImageException;
import dom.com.thesisoauth2resourceserver.exceptions.SubCategoryException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ CategoryException.class, SubCategoryException.class, ImageException.class})
    public ResponseEntity<Object> handleRoleException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleRuntimeException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
