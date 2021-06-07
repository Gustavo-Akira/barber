package br.gustavoakira.barber.service.impl;

import br.gustavoakira.barber.exception.ResourceNotFoundException;
import br.gustavoakira.barber.model.Barber;
import br.gustavoakira.barber.model.Service;
import br.gustavoakira.barber.repository.ServiceRepository;
import br.gustavoakira.barber.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository repository;

    @Override
    public List<Service> getAllService() {
        return repository.findAll();
    }

    @Override
    public Service getService(Long id) {
        return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Service not found with id = "+id));
    }

    @Override
    public Service createNewService(Service service) {
        return repository.save(service);
    }

    @Override
    public Service updateService(Long id, Service service) {
        Service old = getService(id);
        service.setId(old.getId());
        return repository.save(service);
    }

    @Override
    public Service deleteService(Long id) {
        Service old = getService(id);
        repository.delete(old);
        return old;
    }

    @Override
    public Service exists(String name, Barber barber) {
        Service service = getAllService().stream().filter(x->x.getName().equals(name)).findFirst().orElse(null);
        if(service.getBarber().equals(barber)) {
            return  service;
        }
        return null;
    }
}
