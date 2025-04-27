package org.skypro.skyshop.exeption;

import org.skypro.skyshop.model.dto.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProduct(NoSuchProductException ex) {
        ShopError error = new ShopError(
                "PRODUCT_NOT_FOUND",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ShopError> handleAllExceptions(Exception ex) {
        ShopError error = new ShopError(
                "INTERNAL_SERVER_ERROR",
                "Произошла непредвиденная ошибка"
        );
        return ResponseEntity.internalServerError().body(error);
    }
}