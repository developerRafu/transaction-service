package com.pismo.creditservice.repositories;

import com.pismo.creditservice.domain.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationTypeRepository extends JpaRepository<OperationType, Long> {
}
