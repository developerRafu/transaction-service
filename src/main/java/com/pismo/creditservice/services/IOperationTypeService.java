package com.pismo.creditservice.services;

import com.pismo.creditservice.domain.OperationType;

import java.util.List;
import java.util.Optional;

public interface IOperationTypeService {
    List<OperationType> saveAll(final List<OperationType> operations);
    Optional<OperationType> findById(final Long id);
}
