package com.picpay_challenge.controller;

import com.picpay_challenge.dto.AuthenticationDto;
import com.picpay_challenge.dto.LoginDto;
import com.picpay_challenge.dto.PersonDto;
import com.picpay_challenge.entities.Person;
import com.picpay_challenge.infra.TokenService;
import com.picpay_challenge.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final PersonService personService;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, PersonService personService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.personService = personService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> login(@RequestBody @Validated AuthenticationDto request){
        UsernamePasswordAuthenticationToken userNamePassword = new UsernamePasswordAuthenticationToken(request.login(), request.password());
        Authentication auth = this.authenticationManager.authenticate(userNamePassword);

        String token = tokenService.generateToken(((Person)auth.getPrincipal())); // auth.getPrincipal() pega o objeto principal

        return ResponseEntity.ok(new LoginDto(token));
    }

    @PostMapping("/registry")
    public ResponseEntity<Person> register(@RequestBody @Validated PersonDto request) throws Exception {
        Person person = personService.registryPerson(request);
        return ResponseEntity.ok(person);
    }
}
