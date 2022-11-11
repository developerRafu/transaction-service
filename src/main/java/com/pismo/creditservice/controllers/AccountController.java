package com.pismo.creditservice.controllers;

import com.pismo.creditservice.mappers.AccountMapper;
import com.pismo.creditservice.services.IAccountService;
import com.pismo.creditservice.vo.requests.AccountRequest;
import com.pismo.creditservice.vo.responses.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountController {
    private final IAccountService service;
    private final AccountMapper mapper;

    public ResponseEntity<AccountResponse> post(@RequestBody AccountRequest request) {
        final var accountCreated = mapper.toAccountResponse(service.create(request.getDocumentNumber()));
        return ResponseEntity.ok().body(accountCreated);
    }
}
