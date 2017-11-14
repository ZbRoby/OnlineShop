package ro.msg.learning.shop.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ro.msg.learning.shop.exceptions.NoSingleLocationFoundException;
import ro.msg.learning.shop.exceptions.ProductNotFoundException;
import ro.msg.learning.shop.exceptions.QuantityExceedsStockException;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 * Rest Controller Advice for handling the exceptions raised by the services
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NoSingleLocationFoundException.class)
    protected ResponseEntity<Object> noSingleLocationFoundHandler(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "No Single Location found ... " + ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    protected ResponseEntity<Object> productNotFoundHandler(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Product not found ... " + ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = QuantityExceedsStockException.class)
    protected ResponseEntity<Object> quantityExceedsStockHandler(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Quantity exceeds stock ... " + ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
