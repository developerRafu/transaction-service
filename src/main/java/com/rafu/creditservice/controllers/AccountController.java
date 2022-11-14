package com.rafu.creditservice.controllers;

import com.rafu.creditservice.errors.AccountNotFoundException;
import com.rafu.creditservice.mappers.AccountMapper;
import com.rafu.creditservice.services.IAccountService;
import com.rafu.creditservice.vo.requests.AccountRequest;
import com.rafu.creditservice.vo.responses.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountController {
    private final IAccountService service;
    private final AccountMapper mapper;

    @Validated
    @PostMapping
    public ResponseEntity<AccountResponse> post(@RequestBody @Valid AccountRequest request) {
        final var accountCreated = mapper.toAccountResponse(service.create(request.getDocumentNumber()));
        return ResponseEntity.ok().body(accountCreated);
    }

    @Validated
    @GetMapping
    public ResponseEntity<AccountResponse> getById(@RequestParam(value = "accountId") final Long accountId) {
        return service
                .findById(accountId)
                .map(account -> ResponseEntity.ok().body(mapper.toAccountResponse(account)))
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }
}
