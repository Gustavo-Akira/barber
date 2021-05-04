package br.gustavoakira.barber;

import br.gustavoakira.barber.controller.AppointmentController;
import br.gustavoakira.barber.controller.ClientController;
import br.gustavoakira.barber.controller.ServiceController;
import br.gustavoakira.barber.security.JWTTokenAuthenticationService;
import br.gustavoakira.barber.service.AppointmentService;
import br.gustavoakira.barber.service.ClientService;
import br.gustavoakira.barber.service.ServiceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BarberApplicationTests {

	@Autowired
	private ServiceController serviceController;

	@Autowired
	private ClientController clientController;

	@Autowired
	private AppointmentController appointmentController;

	@MockBean
	private ClientService clientService;

	@MockBean
	private ServiceService serviceService;

	@MockBean
	private AppointmentService appointmentService;

	@Autowired
	private JWTTokenAuthenticationService service;

	@Test
	void contextLoads() {
		assertNotNull(serviceController);
		assertNotNull(clientController);
		assertNotNull(appointmentController);
	}

	@Test
	void shouldGenerateAuthToken() throws Exception{

		String token = service.createToken("akira");
		assertNotNull(token);

	}

}
