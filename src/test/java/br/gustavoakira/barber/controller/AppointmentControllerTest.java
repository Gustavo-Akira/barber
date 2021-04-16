package br.gustavoakira.barber.controller;

import br.gustavoakira.barber.model.Appointment;
import br.gustavoakira.barber.service.AppointmentService;
import br.gustavoakira.barber.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(AppointmentController.class)
@AutoConfigureMockMvc
class AppointmentControllerTest {

    private String json;
    private Appointment appointment = new Appointment();

    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private ClientService clientService;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        appointment.setId(1L);
        appointment.setDate(LocalDateTime.now());
        appointment.setService(new ArrayList<>());
        json = mapper.writeValueAsString(appointment);
    }

    @Test
    void getAppointmentsTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/appointments"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getAppointmentTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/appointment/1"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveAppointmentTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/appointment/client/1").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void updateAppointmentTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/appointment/1").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void removeAppointmentTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/appointment/1"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}