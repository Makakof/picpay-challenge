package com.picpay_challenge.controller;
import com.picpay_challenge.dto.VoucherDto;
import com.picpay_challenge.entities.Voucher;
import com.picpay_challenge.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/transaction")
    public String createPerson()
    {
        return "Hellor World";
    }
}
