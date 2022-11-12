package com.pismo.creditservice.services;

import com.pismo.creditservice.domain.OperationType;
import com.pismo.creditservice.repositories.OperationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OperationTypeServiceImpl implements IOperationTypeService {
    private final OperationTypeRepository repository;

    @Override
    public List<OperationType> saveAll(final List<OperationType> operations) {
        return repository.saveAll(operations);
    }

    @Override
    public Optional<OperationType> findById(final Long id) {
        return repository.findById(id);
    }
}
