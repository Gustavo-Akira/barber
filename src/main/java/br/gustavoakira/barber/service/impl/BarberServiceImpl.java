package br.gustavoakira.barber.service.impl;

import br.gustavoakira.barber.model.Barber;
import br.gustavoakira.barber.repository.BarberRepository;
import br.gustavoakira.barber.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BarberServiceImpl implements BarberService {

    @Autowired
    private BarberRepository repository;

    @Override
    public List<Barber> getBarbers() {
        return repository.findAll();
    }

    @Override
    public Barber getBarber(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Barber saveBarber(Barber barber) {
        return repository.save(barber);
    }

    @Override
    public Barber updateBarber(Long id, Barber barber) {
        Barber old = this.getBarber(id);
        old.setNome(barber.getNome());
        old.setColor(barber.getColor());
        old.setUsername(barber.getUsername());
        old.setPassword(barber.getPassword());
        return repository.save(old);
    }

    @Override
    public Barber removeBarber(Long id) {
        Barber barber = getBarber(id);
        repository.delete(barber);
        return barber;
    }
}
