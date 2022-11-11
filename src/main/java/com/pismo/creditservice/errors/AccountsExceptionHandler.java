package com.pismo.creditservice.errors;

import com.pismo.creditservice.controllers.AccountController;
import com.pismo.creditservice.errors.helpers.MessagesEnum;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice(assignableTypes = AccountController.class)
@RestController
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class AccountsExceptionHandler {

    @ExceptionHandler(InvalidDocumentNumberException.class)
    public ResponseEntity<DefaultError> handleInvalidDocumentNumberException(final InvalidDocumentNumberException ex, final WebRequest request) {
        final var error = DefaultError
                .builder()
                .message(MessagesEnum.INVALID_NUMBER_DOCUMENT.getFormattedMessage(ex.getMessage()))
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }
}
