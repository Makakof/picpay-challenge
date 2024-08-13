package com.picpay_challenge.repository;

import com.picpay_challenge.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PicpayRepository extends JpaRepository<Person, Long>
{
    Person findByDocument(String document);
}
