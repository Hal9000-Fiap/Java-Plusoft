package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.security;


import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository.CustomerRepository;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.security.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var tokenJwt = request.getHeader("Authorization");
        if (tokenJwt != null) {
            tokenJwt = tokenJwt.replace("Bearer ", "");
            var subject = tokenService.getSubject(tokenJwt);
            var user = customerRepository.findByEmail(subject);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
