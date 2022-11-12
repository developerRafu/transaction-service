package com.pismo.creditservice.errors;

import com.pismo.creditservice.errors.helpers.MessagesEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
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

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<DefaultError> handleInvalidDocumentNumberException(final AccountNotFoundException ex, final WebRequest request) {
        final var error = DefaultError
                .builder()
                .message(MessagesEnum.ACCOUNT_NOT_FOUND.getFormattedMessage(ex.getAccountId()))
                .code(HttpStatus.NOT_FOUND.value())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }
}
