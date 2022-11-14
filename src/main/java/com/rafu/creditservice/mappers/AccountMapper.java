package com.rafu.creditservice.mappers;

import com.rafu.creditservice.domain.Account;
import com.rafu.creditservice.vo.responses.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(source = "id", target = "accountId")
    AccountResponse toAccountResponse(final Account account);
}
