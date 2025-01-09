package com.picpay_challenge.dto;

import lombok.NonNull;

public record AuthenticationDto(@NonNull String login,
                                @NonNull String password) {
}
