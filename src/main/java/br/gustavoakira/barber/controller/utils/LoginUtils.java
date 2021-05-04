package br.gustavoakira.barber.controller.utils;

import br.gustavoakira.barber.model.Barber;
import br.gustavoakira.barber.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginUtils {
    @Autowired
    private BarberService service;

    public Barber getLoggedUser(){
        return service.getBarberByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }
}
