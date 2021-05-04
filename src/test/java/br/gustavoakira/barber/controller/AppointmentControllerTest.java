package br.gustavoakira.barber.controller;

import br.gustavoakira.barber.model.Appointment;
import br.gustavoakira.barber.model.Barber;
import br.gustavoakira.barber.model.Client;
import br.gustavoakira.barber.model.Role;
import br.gustavoakira.barber.repository.RoleRepository;
import br.gustavoakira.barber.security.JWTTokenAuthenticationService;
import br.gustavoakira.barber.service.AppointmentService;
import br.gustavoakira.barber.service.BarberService;
import br.gustavoakira.barber.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AppointmentControllerTest {

    private String json;
    private Appointment appointment = new Appointment();
    private String token;

    @Autowired
    private JWTTokenAuthenticationService service = new JWTTokenAuthenticationService();

    @Autowired
    private ClientService clientService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private BarberService barberService;

    @Autowired
    private RoleRepository repository;


    @Autowired
    private ControllerTestsUtils utils;

    @BeforeAll
    void setUp() throws JsonProcessingException {

        utils.setup();

        appointment.setDate(LocalDateTime.now());
        appointment.setService(new ArrayList<>());
        json = mapper.writeValueAsString(appointment);

        token = service.createToken("akirinha");
        token = token.replace("Bearer","");

        Client client = new Client();
        client.setName("akirasa");
        client.setId(1L);
        clientService.createNewClient(client);

    }

    @Test
    void getAppointmentsTest() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/appointments")
                .header("Authorization",token))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getAppointmentNotFoundTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/appointment/999").header("Authorization",token))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void saveAppointmentTestWithClientNotFound() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/appointment/client/999")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }



    @Test
    void updateAppointmentTestWithAppointmentNotFound() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/appointment/999")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }



    @Test
    void removeAppointmentTestNotFound() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/appointment/999")
                .header("Authorization",token))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    void saveNewAppointmentSuccessfullyTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/appointment/client/1")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void removeAppointmentSuccessfullyTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/appointment/1")
                .header("Authorization",token))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void updateAppointmentSuccessfully() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/appointment/1")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getAppointmentSuccessfullyTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/appointment/1").header("Authorization",token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void shouldGenerateAuthToken() throws Exception{
        assertNotNull(token);
    }
}