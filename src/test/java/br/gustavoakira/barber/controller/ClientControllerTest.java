package br.gustavoakira.barber.controller;

import br.gustavoakira.barber.model.Client;
import br.gustavoakira.barber.security.JWTTokenAuthenticationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClientControllerTest {
    private Client client = new Client();
    private String json;
    private String token;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private JWTTokenAuthenticationService service;

    @Autowired
    private ControllerTestsUtils utils;

    @BeforeAll
    void setUp() throws JsonProcessingException {
        utils.setup();

        client.setId(1L);
        client.setName("Gustavo Akira Uekita");
        json = mapper.writeValueAsString(client);


        token = service.createToken("akirinha");
        token = token.replace("Bearer","");

    }

    @Test
    void getClientsTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients")
                .header("Authorization",token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getClientTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/client/1").header("Authorization",token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveClientTest() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/client")
                        .header("Authorization",token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void updateClientTest() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/client/1")
                        .header("Authorization",token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void removeClientTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/client/1").header("Authorization",token)).andExpect(MockMvcResultMatchers.status().isOk());
    }


}
