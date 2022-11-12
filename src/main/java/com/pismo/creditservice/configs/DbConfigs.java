package com.pismo.creditservice.configs;

import com.pismo.creditservice.domain.Transaction;
import com.pismo.creditservice.domain.enums.OperationType;
import com.pismo.creditservice.services.IAccountService;
import com.pismo.creditservice.services.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DbConfigs {
    private final IAccountService accountService;
    private final ITransactionService transactionService;

    @Bean
    public boolean fillDatabase() {
        final var accountOne = accountService.create("12345678900");
        final var transactionOne = Transaction.builder().account(accountOne).type(OperationType.PAYMENT).build();
        transactionService.create(transactionOne);
        return Boolean.TRUE;
    }
}
