package com.wealhome.e2e;

import com.wealhome.models.Condominium;
import com.wealhome.repositories.SpringCondominiumRepository;
import com.wealhome.springboot.WealHomeAppLauncher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


public class CallForFundsControllerIT extends BaseE2E {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    private SpringCondominiumRepository condominiumRepository;

    @Test
    void shouldLaunchTheLastCallForFundsOfTheFiscalYear() throws Exception {
        UUID condominiumId = UUID.fromString("699915cd-056a-41e2-ba3d-cea478d4c0a2");
        UUID callForFundsId = UUID.fromString("cdef8cc9-0fcd-42ca-b9c9-caff3acd61a5");
        condominiumRepository.save(new Condominium(
                condominiumId,
                BigDecimal.valueOf(10000)
        ));
        mvc.perform(post("/condominiums/{condominiumId}/callsforfunds/{callForFundsId}",
                        condominiumId, callForFundsId
                ).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value(callForFundsId.toString()))
                .andExpect(jsonPath("$.condominiumId").value(condominiumId.toString()))
                .andExpect(jsonPath("$.amount").value(2500))
                .andExpect(jsonPath("$.quarter").value(4));
    }

}
