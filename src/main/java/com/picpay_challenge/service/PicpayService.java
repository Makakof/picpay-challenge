package com.picpay_challenge.service;

import com.picpay_challenge.dto.PersonDto;
import com.picpay_challenge.entities.Person;
import com.picpay_challenge.repository.PicpayRepository;
import org.springframework.stereotype.Service;

@Service
public class PicpayService
{
    private final PicpayRepository picpayRepository;
    public PicpayService(PicpayRepository picpayRepository) {
        this.picpayRepository = picpayRepository;
    }


    public Person save(PersonDto request)
    {
        Person person = new Person(request);
        picpayRepository.save(person);
        return person;
    }

    public Person getByDocument(String document)
    {
        return picpayRepository.findByDocument(document);
    }

    public void deleteByDocument(String document)
    {
        Person person = picpayRepository.findByDocument(document);
        picpayRepository.delete(person);
    }
}
