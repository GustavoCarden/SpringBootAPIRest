package com.gustavo.apirest;

import com.gustavo.apirest.controller.ClienteController;
import com.gustavo.apirest.model.entity.Cliente;
import com.gustavo.apirest.service.ClienteService;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.mockito.BDDMockito.given;

@WebMvcTest(ClienteController.class)
class ApiRestApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    private Cliente cliente = new Cliente(2, "Carlos", "Miranda", "mirandaTr98@gmail.com", null);

    private static final String expectedResult = "{\n" +
            "    \"mensaje\": \"Cliente encontrado\",\n" +
            "    \"object\": {\n" +
            "        \"idCliente\": 2,\n" +
            "        \"nombre\": \"Carlos\",\n" +
            "        \"apellido\": \"Miranda\",\n" +
            "        \"correo\": \"mirandaTr98@gmail.com\"\n" +
            "    }\n" +
            "}";

    @Test
    void contextLoads() throws Exception {
        given(clienteService.existById(2)).willReturn(true);
        given(clienteService.findById(2)).willReturn(new Cliente(2, "Carlos", "Miranda", "mirandaTr98@gmail.com", new Date()));

        String requestURI = "/api/v1/cliente/2";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(requestURI).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println("RESPUESTA:===============================================\n" + result.getResponse().getContentAsString());
        JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), true);
    }

}
