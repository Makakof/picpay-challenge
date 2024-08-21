package com.picpay_challenge.controller;
import com.picpay_challenge.dto.DocumentDto;
import com.picpay_challenge.dto.TransactionDto;
import com.picpay_challenge.dto.VoucherDto;
import com.picpay_challenge.entities.Voucher;
import com.picpay_challenge.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/picpayTransaction")
@RestController
public class TransactionController
{
    private final PersonService personService;

    public TransactionController(PersonService personService) {
        this.personService = personService;
    }

    @PutMapping("/deposit")
    public ResponseEntity<Voucher> deposit(@RequestBody @Validated VoucherDto request)
    {
        Voucher voucher = personService.deposit(request);
        //
        return ResponseEntity.ok(voucher);
    }
    @PostMapping("/voucher/payer")
    public ResponseEntity<List<Voucher>> findPayerVouchers(@RequestBody @Validated DocumentDto request)
    {
        List<Voucher> vouchers = personService.getListPayerVouchers(request);
        return ResponseEntity.ok(vouchers);
    }

    @PostMapping("/voucher/receiver")
    public ResponseEntity<List<Voucher>> findReceiverVouchers(@RequestBody @Validated DocumentDto request)
    {
        List<Voucher> vouchers = personService.getListReceiverVouchers(request);
        return ResponseEntity.ok(vouchers);
    }
}
