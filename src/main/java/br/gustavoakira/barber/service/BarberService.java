package br.gustavoakira.barber.service;

import br.gustavoakira.barber.model.Barber;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BarberService {
    List<Barber> getBarbers();
    Barber getBarber(Long id);
    Barber saveBarber(Barber barber);
    Barber updateBarber(Long id,Barber barber);
    Barber removeBarber(Long id);
    Barber getBarberByUsername(String username);

    boolean isUsernameAvailable(String username);
}
