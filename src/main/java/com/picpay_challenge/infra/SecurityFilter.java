package com.picpay_challenge.infra;

import com.picpay_challenge.service.PersonService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final PersonService personService;

    public SecurityFilter(TokenService tokenService, PersonService personService) {
        this.tokenService = tokenService;
        this.personService = personService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);

        if(token != null){
            String login = tokenService.getSubject(token);

            UserDetails person = personService.findByMail(login);

            // Pegando todas as informações que o Spring Security vai precisa para fazer as próximas validações de requisição
            var authentication = new UsernamePasswordAuthenticationToken(person,null, person.getAuthorities());

            // Salvando no contexto da autenticação esse usuário
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response); // To chamando o próximo filtro, o que tinha para fazer ja foi feito
    }

    private String recoverToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null)
            return null;

        return authHeader.replace("Bearer ",""); // Substitui pelo espaço para ficar mais fácil pegar só o token direto
    }
}
