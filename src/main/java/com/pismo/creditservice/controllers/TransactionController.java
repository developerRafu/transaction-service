package com.pismo.creditservice.controllers;

import com.pismo.creditservice.mappers.TransactionMapper;
import com.pismo.creditservice.services.ITransactionService;
import com.pismo.creditservice.vo.requests.TransactionRequest;
import com.pismo.creditservice.vo.responses.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/transactions")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionController {
    private final ITransactionService service;
    private final TransactionMapper mapper;

    @PostMapping
    public ResponseEntity<TransactionResponse> post(@RequestBody @NotNull @Valid final TransactionRequest request) {
        final var transaction = mapper.toTransaction(request);
        final var response = mapper.toResponse(service.create(transaction));
        return ResponseEntity.ok().body(response);
    }
}
