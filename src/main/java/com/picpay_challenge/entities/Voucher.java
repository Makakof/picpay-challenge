package com.picpay_challenge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Voucher
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(nullable = false)
    private Wallet walletPayer;

    @OneToOne
    @JoinColumn(nullable = false)
    private Wallet walletReceiver;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime date;


    public Voucher() {
    }

    public Voucher(Wallet walletPayer, Wallet walletReceiver, BigDecimal amount) {
        this.walletPayer = walletPayer;
        this.walletReceiver = walletReceiver;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

}
