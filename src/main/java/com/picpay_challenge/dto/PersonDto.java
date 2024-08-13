package com.picpay_challenge.dto;

import com.picpay_challenge.enums.AccountType;
import lombok.NonNull;



public record PersonDto(@NonNull String name,
                        @NonNull String mail,
                        @NonNull String password,
                        @NonNull String document,
                        @NonNull AccountType accountType,
                        double balance){};
