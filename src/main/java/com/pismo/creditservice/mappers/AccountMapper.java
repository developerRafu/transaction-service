package com.pismo.creditservice.mappers;

import com.pismo.creditservice.domain.Account;
import com.pismo.creditservice.vo.responses.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(source = "id", target = "accountId")
    AccountResponse toAccountResponse(final Account account);
}
