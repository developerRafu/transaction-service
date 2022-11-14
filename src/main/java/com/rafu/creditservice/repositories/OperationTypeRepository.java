package com.rafu.creditservice.repositories;

import com.rafu.creditservice.domain.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationTypeRepository extends JpaRepository<OperationType, Long> {
}
