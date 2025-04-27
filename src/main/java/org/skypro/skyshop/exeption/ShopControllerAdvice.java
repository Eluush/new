package org.skypro.skyshop.exeption;
import org.skypro.skyshop.model.dto.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProduct(NoSuchProductException ex) {

        ShopError error = new ShopError(
                "PRODUCT_NOT_FOUND",
                "Товар не найден: " + ex.getMessage()
        );


        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
}