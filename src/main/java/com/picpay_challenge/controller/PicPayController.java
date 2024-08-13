package com.picpay_challenge.controller;


import com.picpay_challenge.dto.DocumentDto;
import com.picpay_challenge.dto.PersonDto;
import com.picpay_challenge.entities.Person;
import com.picpay_challenge.service.PicpayService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/picpay")
@RestController
public class PicPayController
{
    private final PicpayService picpayService;

    public PicPayController(PicpayService picpayService) {
        this.picpayService = picpayService;
    }


    /*A diferenciação entre cada requisição vem pela tipo de requisição que está sendo enviado
     e isso deve ser especificado na documentação*/
    @PostMapping("/user")
    public ResponseEntity<Person> save(@RequestBody @Validated PersonDto request)
    {
        Person person = picpayService.save(request);
        return ResponseEntity.ok(person);
    }

    @GetMapping("/user")
    public ResponseEntity<Person> getByDocument(@RequestBody @Validated DocumentDto request)
    {
        Person person = picpayService.getByDocument(request.document());
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteByDocument(@RequestBody @Validated DocumentDto request)
    {
        picpayService.deleteByDocument(request.document());
        return ResponseEntity.ok("delete success");
    }


    @PostMapping("/deposit")
    public ResponseEntity<>

    @PutMapping("/deposit")
    public String olaMundo(){
        return "Olá Mundo";
    }
}
