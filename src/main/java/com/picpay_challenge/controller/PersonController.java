package com.picpay_challenge.controller;


import com.picpay_challenge.dto.DocumentDto;
import com.picpay_challenge.dto.PersonDto;
import com.picpay_challenge.dto.VoucherDto;
import com.picpay_challenge.entities.Person;
import com.picpay_challenge.entities.Voucher;
import com.picpay_challenge.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/picpayUser")
@RestController
public class PersonController
{
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    /*A diferenciação entre cada requisição vem pela tipo de requisição que está sendo enviado
     e isso deve ser especificado na documentação*/
    @PostMapping("/user")
    public ResponseEntity<Person> createPerson(@RequestBody @Validated PersonDto request)
    {
        Person person = personService.save(request);
        return ResponseEntity.ok(person);
    }

    @GetMapping("/user")
    public ResponseEntity<Person> findPerson(@RequestBody @Validated DocumentDto request)
    {
        Person person = personService.getByDocument(request.document());
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deletePerson(@RequestBody @Validated DocumentDto request)
    {
        personService.deleteByDocument(request.document());
        return ResponseEntity.ok("delete success");
    }
}
