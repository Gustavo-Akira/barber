package br.gustavoakira.barber.controller;

import br.gustavoakira.barber.model.Service;
import br.gustavoakira.barber.service.ServiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ServiceController.class)
@AutoConfigureMockMvc
class ServiceControllerTest {
    private Service service = new Service();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private String json;

    @MockBean
    private ServiceService serviceService;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        service.setName("Cut");
        service.setValue(20.00);
        service.setId(1L);
        json = mapper.writeValueAsString(service);
    }


    @Test
    void getAllServicesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/services"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getServiceTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/service/1"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveServiceTest() throws Exception{

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/service")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void updateServiceTest() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/service/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void removeServiceTest()  throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1/service/1")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}