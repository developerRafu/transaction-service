package com.rafu.creditservice.vo.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponse {
    @JsonProperty(value = "account_id")
    private Long accountId;
    private String documentNumber;
}
