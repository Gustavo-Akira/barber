package br.gustavoakira.barber.service.impl;

import br.gustavoakira.barber.exception.ResourceNotFoundException;
import br.gustavoakira.barber.model.Barber;
import br.gustavoakira.barber.model.Role;
import br.gustavoakira.barber.repository.BarberRepository;
import br.gustavoakira.barber.repository.RoleRepository;
import br.gustavoakira.barber.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BarberServiceImpl implements BarberService {

    @Autowired
    private BarberRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Barber> getBarbers() {
        return repository.findAll();
    }

    @Override
    public Barber getBarber(Long id) {
        return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Barber not found with id = "+id));
    }

    @Override
    public Barber saveBarber(Barber barber) {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2L).get());
        barber.setPassword(new BCryptPasswordEncoder().encode(barber.getPassword()));
        barber.setRoles(roles);
        return repository.save(barber);
    }

    @Override
    public Barber updateBarber(Long id, Barber barber) {
        Barber old = this.getBarber(id);
        old.setName(barber.getName());
        old.setColor(barber.getColor());
        old.setUsername(barber.getUsername());
        old.setPassword(barber.getPassword());
        return repository.save(old);
    }

    @Override
    public Barber getBarberByUsername(String username) {
        return repository.getBarberByUsername(username).orElseThrow(()->new ResourceNotFoundException("Barber with username " + username + "not found"));
    }

    @Override
    public Barber removeBarber(Long id) {
        Barber barber = getBarber(id);
        repository.delete(barber);
        return barber;
    }
}
