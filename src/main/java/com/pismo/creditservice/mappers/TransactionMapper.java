package com.pismo.creditservice.mappers;

import com.pismo.creditservice.domain.Account;
import com.pismo.creditservice.domain.Transaction;
import com.pismo.creditservice.domain.enums.OperationType;
import com.pismo.creditservice.vo.requests.TransactionRequest;
import com.pismo.creditservice.vo.responses.TransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    default TransactionResponse toResponse(final Transaction transaction){
        if ( transaction == null ) {
            return null;
        }

        TransactionResponse.TransactionResponseBuilder transactionResponse = TransactionResponse.builder();

        transactionResponse.amount( transaction.getAmount() );
        transactionResponse.transactionId(transaction.getId());
        transactionResponse.operationTypeId(transaction.getType().getId());
        transactionResponse.accountId(transaction.getAccount().getId());

        return transactionResponse.build();
    }

    default Transaction toTransaction(final TransactionRequest request) {
        if (request == null) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setAmount(request.getAmount());
        transaction.setAccount(accountIdToAccount(request.getAccountId()));
        transaction.setType(operationTypeIdToOperationType(request.getOperationTypeId()));
        return transaction;
    }

    default Account accountIdToAccount(Long accountId) {
        if (accountId == null) {
            return null;
        }
        final var account = new Account();
        account.setId(accountId);
        return account;
    }

    default OperationType operationTypeIdToOperationType(final Integer operationTypeId) {
        if (operationTypeId == null) {
            return null;
        }
        return OperationType.of(operationTypeId);
    }
}