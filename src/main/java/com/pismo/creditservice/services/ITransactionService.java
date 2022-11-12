package com.pismo.creditservice.services;

import com.pismo.creditservice.domain.Transaction;

public interface ITransactionService {
    Transaction create(final Transaction transaction);
}
