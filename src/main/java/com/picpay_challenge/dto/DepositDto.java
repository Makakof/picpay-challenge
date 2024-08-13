package com.picpay_challenge.dto;

import lombok.NonNull;

public record DepositDto(@NonNull String documentPayer,
                         @NonNull String documentReceiver,
                         @NonNull double amount) {
}
