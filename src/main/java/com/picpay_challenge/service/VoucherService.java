package com.picpay_challenge.service;

import com.picpay_challenge.dto.VoucherDto;
import com.picpay_challenge.entities.Person;
import com.picpay_challenge.entities.Voucher;
import com.picpay_challenge.repository.VoucherRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class VoucherService
{
    private final VoucherRepository voucherRepository;

    VoucherService(VoucherRepository voucherRepository)
    {
        this.voucherRepository = voucherRepository;
    }

    public Voucher createDeposit(Person payer, Person receiver, BigDecimal amount)
    {
        payer.getWallet().withdraw(amount);
        receiver.getWallet().deposit(amount);

        Voucher voucher = new Voucher(payer.getWallet(), receiver.getWallet(), amount);
        //payer.getWallet().getListOfTransfers().add(voucher);
        //receiver.getWallet().getListOfTransfers().add(voucher);
        voucherRepository.save(voucher);

        return voucher;
    }
}
