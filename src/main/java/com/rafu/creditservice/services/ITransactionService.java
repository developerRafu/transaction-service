package com.rafu.creditservice.services;

import com.rafu.creditservice.domain.Transaction;

public interface ITransactionService {
    Transaction create(final Transaction transaction);
}
