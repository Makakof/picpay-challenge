package com.picpay_challenge.service;

import com.picpay_challenge.dto.DocumentDto;
import com.picpay_challenge.dto.PersonDto;
import com.picpay_challenge.dto.TransactionDto;
import com.picpay_challenge.dto.VoucherDto;
import com.picpay_challenge.entities.Person;
import com.picpay_challenge.entities.Voucher;
import com.picpay_challenge.repository.PersonRepository;
import com.picpay_challenge.repository.VoucherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService
{
    private final PersonRepository personRepository;
    private final VoucherService voucherService;
    private final VoucherRepository voucherRepository;

    public PersonService(PersonRepository personRepository, VoucherService voucherService,
                         VoucherRepository voucherRepository) {
        this.personRepository = personRepository;
        this.voucherService = voucherService;
        this.voucherRepository = voucherRepository;
    }


    public Person save(PersonDto request)
    {
        Person person = new Person(request);
        //Sempre que possivel ja retorna direto sem a criação de um novo objeto para
        // evitar consumo desnecessário de memória
        return personRepository.save(person);
    }

    public Person getByDocument(String document)
    {
        return personRepository.findByDocument(document);
    }

    public void deleteByDocument(String document)
    {
        Person person = personRepository.findByDocument(document);
        personRepository.delete(person);
    }

    public Voucher deposit(VoucherDto request)
    {
        Person payer = this.getByDocument(request.documentPayer());
        Person receiver = this.getByDocument(request.documentReceiver());

        return voucherService.createDeposit(payer, receiver, request.amount());
    }

    public List<Voucher> getListPayerVouchers(DocumentDto request)
    {
        Person payer = this.getByDocument(request.document());
        return voucherService.createPayerVouchers(payer);
    }

    public List<Voucher> getListReceiverVouchers(DocumentDto request)
    {
        Person receiver = this.getByDocument(request.document());
        return voucherService.createReceiverVouchers(receiver);
    }
}
