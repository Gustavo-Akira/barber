package br.gustavoakira.barber.controller;

import br.gustavoakira.barber.model.Client;
import br.gustavoakira.barber.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ClientController.class)
@AutoConfigureMockMvc
class ClientControllerTest {
    private Client client = new Client();
    private String json;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ClientService service;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        client.setId(1L);
        client.setName("Gustavo Akira Uekita");
        json = mapper.writeValueAsString(client);
    }

    @Test
    void getClientsTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getClientTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/client/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveClientTest() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void updateClientTest() throws Exception{
        mockMvc.perform(
          MockMvcRequestBuilders.put("/api/v1/client/1")
          .contentType(MediaType.APPLICATION_JSON)
          .content(json)
        );
    }

    @Test
    void removeClientTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/client/1")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}