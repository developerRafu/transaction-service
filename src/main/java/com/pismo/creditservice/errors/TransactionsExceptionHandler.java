package com.pismo.creditservice.errors;

import com.pismo.creditservice.controllers.TransactionController;
import com.pismo.creditservice.errors.helpers.MessagesEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice(assignableTypes = TransactionController.class)
@RestController
public class TransactionsExceptionHandler {
    @ExceptionHandler(TransactionTypeNotFound.class)
    public ResponseEntity<DefaultError> handleInvalidDocumentNumberException(final TransactionTypeNotFound ex, final WebRequest request) {
        final var error = DefaultError
                .builder()
                .message(MessagesEnum.INVALID_TRANSACTION_TYPE.getFormattedMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }
}
