package br.gustavoakira.barber.controller;

import br.gustavoakira.barber.model.Service;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServiceControllerTest {
    private Service service = new Service();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private JWTTokenAuthenticationService jwtTokenAuthenticationService;

    @Autowired
    private ControllerTestsUtils utils;

    private String json;

    private String token = "";

    @BeforeAll
    void setUp() throws JsonProcessingException {
        utils.setup();
        service.setName("Cut");
        service.setValue(20.00);
        service.setId(1L);
        json = mapper.writeValueAsString(service);

        token = jwtTokenAuthenticationService.createToken("akirinha");
        token = token.replace("Bearer","");
    }


    @Test
    void getAllServicesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/services").header("Authorization",token))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getServiceTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/service/1").header("Authorization",token))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveServiceTest() throws Exception{

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/service")
                        .header("Authorization",token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void updateServiceTest() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/service/1")
                        .header("Authorization",token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void removeServiceTest()  throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1/service/1").header("Authorization",token)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
