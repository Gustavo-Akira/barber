package br.gustavoakira.barber.service;

import br.gustavoakira.barber.model.Service;

import java.util.List;

public interface ServiceService {
    List<Service> getAllService();
    Service getService(Long id);
    Service createNewService(Service service);
    Service updateService(Long id,Service service);
    Service deleteService(Long id);
    Service exists(String name);
}
