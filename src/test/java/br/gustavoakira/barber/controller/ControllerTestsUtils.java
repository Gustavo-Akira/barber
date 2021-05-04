package br.gustavoakira.barber.controller;

import br.gustavoakira.barber.model.Barber;
import br.gustavoakira.barber.model.Role;
import br.gustavoakira.barber.repository.RoleRepository;
import br.gustavoakira.barber.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControllerTestsUtils {
    @Autowired
    private RoleRepository repository;

    @Autowired
    private BarberService barberService;

    public void setup(){
        Role role = new Role();
        role.setAuthority("ROLE_ADMIN");
        repository.save(role);
        Role role2 = new Role();
        role2.setAuthority("ROLE_BARBER");
        repository.save(role2);

        Barber barber = new Barber();
        barber.setUsername("akirinha");
        barber.setPassword("kadeira");
        barberService.saveBarber(barber);
    }
}
