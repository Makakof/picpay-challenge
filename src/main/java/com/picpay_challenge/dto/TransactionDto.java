package com.picpay_challenge.dto;

import lombok.NonNull;

public record TransactionDto(@NonNull String documentPayer,
                             @NonNull String documentReceiver) {
}
