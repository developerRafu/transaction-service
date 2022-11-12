package com.pismo.creditservice.configs;

import com.pismo.creditservice.domain.OperationType;
import com.pismo.creditservice.domain.Transaction;
import com.pismo.creditservice.domain.enums.OperationTypeEnum;
import com.pismo.creditservice.services.IAccountService;
import com.pismo.creditservice.services.IOperationTypeService;
import com.pismo.creditservice.services.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DbConfigs {
    private final IAccountService accountService;
    private final ITransactionService transactionService;
    private final IOperationTypeService operationTypeService;

    @Bean
    public boolean fillDatabase() {
        final var accountOne = accountService.create("12345678900");
        final var operations = saveAllOperations();
        final var transactionOne = Transaction.builder().account(accountOne).operationType(operations.get(0)).build();
        transactionService.create(transactionOne);
        return Boolean.TRUE;
    }

    private List<OperationType> saveAllOperations() {
        final var operations = Arrays.stream(OperationTypeEnum.values()).map(this::toOperationType).collect(Collectors.toList());
        return operationTypeService.saveAll(operations);
    }

    private OperationType toOperationType(final OperationTypeEnum typeEnum) {
        return OperationType
                .builder()
                .id(typeEnum.getId())
                .description(typeEnum)
                .build();
    }
}
