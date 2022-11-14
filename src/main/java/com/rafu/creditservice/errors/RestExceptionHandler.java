package com.rafu.creditservice.errors;

import com.rafu.creditservice.errors.helpers.MessagesEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class RestExceptionHandler {

    @ExceptionHandler(InvalidDocumentNumberException.class)
    public ResponseEntity<DefaultError> handleInvalidDocumentNumberException(
            final InvalidDocumentNumberException ex,
            final WebRequest request
    ) {
        final var error = DefaultError
                .builder()
                .message(MessagesEnum.INVALID_NUMBER_DOCUMENT.getFormattedMessage(ex.getMessage()))
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<DefaultError> handleAccountNotFoundException(
            final AccountNotFoundException ex,
            final WebRequest request
    ) {
        final var error = DefaultError
                .builder()
                .message(MessagesEnum.ACCOUNT_NOT_FOUND.getFormattedMessage(ex.getAccountId()))
                .code(HttpStatus.NOT_FOUND.value())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultError> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException ex,
            final WebRequest request
    ) {
        final var error = DefaultError
                .builder()
                .message(MessagesEnum.INVALID_REQUEST.getFormattedMessage())
                .details(getMessages(ex))
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    @ExceptionHandler(TransactionTypeNotFound.class)
    public ResponseEntity<DefaultError> handleInvalidDocumentNumberException(
            final TransactionTypeNotFound ex,
            final WebRequest request
    ) {
        final var error = DefaultError
                .builder()
                .message(MessagesEnum.INVALID_TRANSACTION_TYPE.getFormattedMessage(ex.getTransactionTypeId()))
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    @ExceptionHandler(InvalidBankOperation.class)
    public ResponseEntity<DefaultError> handleInvalidBankOperation(
            final InvalidBankOperation ex,
            final WebRequest request
    ) {
        final var error = DefaultError
                .builder()
                .message(MessagesEnum.INVALID_BANK_OPERATION.getFormattedMessage
                        (
                                ex.operationTypeEnum.getDescription(),
                                ex.operationTypeEnum.getOperationAmountDescription()
                        )
                )
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    private List<String> getMessages(MethodArgumentNotValidException exception) {
        return exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> MessagesEnum.INVALID_VALUE.getFormattedMessage(f.getField(), f.getRejectedValue()))
                .collect(Collectors.toList());
    }
}
