package br.gustavoakira.barber.service.impl;

import br.gustavoakira.barber.model.Barber;
import br.gustavoakira.barber.repository.BarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private BarberRepository repository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Barber barber = repository.getBarberByUsername(s).get();
        return new User(barber.getUsername(), barber.getPassword(), barber.getAuthorities());
    }
}
