package com.picpay_challenge.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.picpay_challenge.exceptions.PicPayException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Wallet
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @JsonIgnore
    @OneToOne(mappedBy = "wallet")
    private Person owner;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.valueOf(0);

    @OneToMany(mappedBy = "walletPayer")
    private List<Voucher> vouchersPayers;

    @OneToMany(mappedBy = "walletReceiver")
    private List<Voucher> vouchersReceivers;

    private Wallet() {
    }

    Wallet(Person owner, BigDecimal amount)
    {
        this.owner = owner;
        this.balance = amount;
    }

    public void withdraw(BigDecimal amount)
    {
        if(this.balance.compareTo(amount) < 0)
            throw new PicPayException("insufficient balance");

        this.balance = this.balance.subtract(amount);
        //
    }

    public void deposit(BigDecimal amount)
    {
        this.balance = this.balance.add(amount);
    }

}
