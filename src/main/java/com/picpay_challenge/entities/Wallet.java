package com.picpay_challenge.entities;


import com.picpay_challenge.exceptions.PicPayException;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

public class Wallet
{
    @ManyToOne
    private Person owner;
    private Transfer List<Transfer> listOfTransfers;
    private BigDecimal balance;

    Wallet(Person owner)
    {
        this.owner = owner;
    }

    public void withdraw()
    // this.wallet.deposit(Eduardo, 1500);


    public void deposit(double amount, Person receiver)
    {
        if(this.balance <= amount)
            throw new PicPayException("insufficient balance");

        if(receiver == null)
            throw new PicPayException("recipient dont exist");

        this.balance -= amount; // vai virar o metodo de saque
        //this.wallet.deposit(Eduardo, 1500);
        receiver.wallet.balance.subtract() += amount;
    }
}
