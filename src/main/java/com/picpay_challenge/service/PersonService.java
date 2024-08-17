package com.picpay_challenge.service;

import com.picpay_challenge.dto.PersonDto;
import com.picpay_challenge.dto.VoucherDto;
import com.picpay_challenge.entities.Person;
import com.picpay_challenge.entities.Voucher;
import com.picpay_challenge.repository.PersonRepository;
import com.picpay_challenge.repository.VoucherRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService
{
    private final PersonRepository personRepository;
    private final VoucherService voucherService;
    public PersonService(PersonRepository personRepository, VoucherService voucherService) {
        this.personRepository = personRepository;
        this.voucherService = voucherService;
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
}
