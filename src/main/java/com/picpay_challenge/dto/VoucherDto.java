package com.picpay_challenge.dto;

import lombok.NonNull;

import java.math.BigDecimal;

public record VoucherDto(@NonNull String documentPayer,
                         @NonNull String documentReceiver,
                         @NonNull BigDecimal amount) {
}
