package br.gustavoakira.barber.service;

import br.gustavoakira.barber.model.Service;
import br.gustavoakira.barber.repository.ServiceRepository;
import br.gustavoakira.barber.service.impl.ServiceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith({SpringExtension.class})
class ServiceServiceTest {

    @Autowired
    private ServiceService serviceService;

    @MockBean
    private ServiceRepository repository;

    private Service service = new Service();

    private List<Service> services = new ArrayList<>();

    @TestConfiguration
    static class ServiceServiceTestConfiguration{
        @Bean
        public ServiceService serviceService(){
            return new ServiceServiceImpl();
        }
    }

    @BeforeEach
    void setUp() {
        service.setId(1L);
        service.setValue(22.00);
        service.setName("Cut");
        services.add(service);

        Mockito.when(repository.findAll()).thenReturn(services);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(service));
        Mockito.when(repository.save(service)).thenReturn(service);
    }

    @Test
    void getAllService() {
        assertEquals(services,serviceService.getAllService());
    }

    @Test
    void getService() {
        assertEquals(service,serviceService.getService(1L));
    }

    @Test
    void createNewService() {
        assertEquals(service,serviceService.createNewService(service));
    }

    @Test
    void updateService() {
        assertEquals(service,serviceService.updateService(1L,service));
    }

    @Test
    void deleteService() {
        assertEquals(service,serviceService.deleteService(1L));
    }
}