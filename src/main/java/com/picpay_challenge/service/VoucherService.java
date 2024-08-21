package com.picpay_challenge.service;

import com.picpay_challenge.dto.VoucherDto;
import com.picpay_challenge.entities.Person;
import com.picpay_challenge.entities.Voucher;
import com.picpay_challenge.repository.VoucherRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


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
        voucherRepository.save(voucher);

        return voucher;
    }

    public List<Voucher> createPayerVouchers(Person payer)
    {
        List<Voucher> vouchers = voucherRepository.findAll();
        vouchers.removeIf(voucherNow -> !voucherNow.getWalletPayer().getOwner().getDocument().equalsIgnoreCase(payer.getDocument()));

        return vouchers;
    }

    public List<Voucher> createReceiverVouchers(Person receiver)
    {
        List<Voucher> vouchers = voucherRepository.findAll();
        vouchers.removeIf(voucherNow -> !voucherNow.getWalletReceiver().getOwner().getDocument().equalsIgnoreCase(receiver.getDocument()));

        return vouchers;
    }
}
