package com.picpay_challenge.entities;

import com.picpay_challenge.enums.AccountType;
import com.picpay_challenge.dto.PersonDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Person implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String mail;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String document;

    @OneToOne(cascade = CascadeType.ALL)
    private Wallet wallet;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public Person(){};

    public Person(String name, String mail, String password, String document, AccountType accountType, BigDecimal amount) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.document = document;
        this.accountType = accountType;
        this.wallet = new Wallet(this, amount);
    }

    public Person(PersonDto request) {
        this(request.name(), request.mail(), request.password(), request.document(),request.accountType(), request.balance());
    }

    public Person(PersonDto request, String encryptedPassword) {
        this(request.name(), request.mail(), encryptedPassword, request.document(),request.accountType(), request.balance());
    }

    /*
    Métodos criados pela implementação da interface UserDetails para fazer a validação dos dados
    */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.accountType == AccountType.LOJIST)
            return List.of(new SimpleGrantedAuthority("ROLE_LOJIST"));

        return List.of(new SimpleGrantedAuthority("ROLE_COMMUM"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.mail;
    }
}
