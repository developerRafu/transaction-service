package com.rafu.creditservice.services;

import com.rafu.creditservice.domain.OperationType;

import java.util.List;
import java.util.Optional;

public interface IOperationTypeService {
    List<OperationType> saveAll(final List<OperationType> operations);
    Optional<OperationType> findById(final Long id);
}
