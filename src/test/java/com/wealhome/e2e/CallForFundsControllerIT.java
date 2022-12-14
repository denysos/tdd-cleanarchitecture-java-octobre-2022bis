package com.wealhome.e2e;

import com.wealhome.businesslogic.models.DateProvider;
import com.wealhome.businesslogic.models.DeterministicDateProvider;
import com.wealhome.infra.repositories.jpa.SpringCondominiumRepository;
import com.wealhome.infra.repositories.jpa.jpaentities.CondominiumJpaEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class CallForFundsControllerIT extends BaseE2E {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    private SpringCondominiumRepository condominiumRepository;

    @Autowired
    @Qualifier("deterministicDateProvider")
    private DateProvider dateProvider;

    @Test
    void shouldLaunchTheFirstCallForFundsOfTheFiscalYear() throws Exception {
        ((DeterministicDateProvider)dateProvider).setTime(LocalDateTime.of(2022, 1, 3, 14, 15, 3));
        UUID condominiumId = UUID.fromString("699915cd-056a-41e2-ba3d-cea478d4c0a2");
        UUID callForFundsId = UUID.fromString("cdef8cc9-0fcd-42ca-b9c9-caff3acd61a5");
        condominiumRepository.save(new CondominiumJpaEntity(
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
                .andExpect(jsonPath("$.quarter").value("T1"))
                .andExpect(jsonPath("$.occurredOn").value(dateProvider.timeNow().toString()));
    }

}
