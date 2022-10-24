package com.wealhome.e2e;

import com.wealhome.repositories.SpringCondominiumRepository;
import com.wealhome.models.Condominium;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.UUID;

import static java.util.UUID.fromString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CallForFundsControllerIT extends BaseE2E {

    @Autowired
    private SpringCondominiumRepository condominiumRepository;

    @Test
    void shouldLaunchTheFirstCallForFundsOfTheFiscalYear() throws Exception {
        UUID condominiumId = fromString("d8c68699-24e2-43a5-bb3d-be522628a1ec");
        UUID callForFundsId = fromString("a6c68699-24e2-43a5-bb3d-be522628a1e2");
        condominiumRepository.save(
                new Condominium(
                        condominiumId,
                        BigDecimal.valueOf(10000)
                )
        );
        mvc.perform(
                        post("/condominiums/{condominiumId}/callsforfunds/{callForFundsId}",
                                condominiumId,
                                callForFundsId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("UTF-8"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value(callForFundsId.toString()))
                .andExpect(jsonPath("$.condominiumId").value(condominiumId.toString()))
                .andExpect(jsonPath("$.amount").value(2500))
                .andExpect(jsonPath("$.quarter").value(4));
    }

}
