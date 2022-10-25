package com.wealhome.integration;

import com.wealhome.businesslogic.models.CallForFunds;
import com.wealhome.businesslogic.repositories.CallForFundsRepository;
import com.wealhome.infra.client.springboot.ServicesConfiguration;
import com.wealhome.infra.repositories.jpa.JpaCallForFundsRepository;
import com.wealhome.infra.repositories.jpa.jpaentities.CallForFundsJpaEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = ServicesConfiguration.class)
public class JpaCallForFundsRepositoryIT extends BaseIntegration {

    @Autowired
    private CallForFundsRepository callForFundsRepository;

    @Test
    void canSaveACallForFunds() {
        CallForFunds aCallForFunds = aCallForFunds();
        callForFundsRepository.save(aCallForFunds);
        JpaCallForFundsRepository jpaCallForFundsRepository = (JpaCallForFundsRepository) callForFundsRepository;
        assertThat(
                jpaCallForFundsRepository.springCallForFundsRepository().findById(
                                UUID.fromString("699915cd-056a-41e2-ba3d-cea478d4c0a2")
                        )).isEqualTo(Optional.of(new CallForFundsJpaEntity(
                UUID.fromString("699915cd-056a-41e2-ba3d-cea478d4c0a2"),
                UUID.fromString("cdef8cc9-0fcd-42ca-b9c9-caff3acd61a5"),
                BigDecimal.valueOf(2500),
                1,
                LocalDateTime.of(2018, 3, 5, 4, 5)
        )));
    }

    @Test
    void shouldDetectThatACallForFundsExist() {
        CallForFunds aCallForFunds = aCallForFunds();
        callForFundsRepository.save(aCallForFunds);
        assertThat(callForFundsRepository.doSomePastCallForFundsExist()).isTrue();
    }

    @Test
    void shouldDetectThatACallForFundsDoesNOTExist() {
        assertThat(callForFundsRepository.doSomePastCallForFundsExist()).isFalse();
    }

    private CallForFunds aCallForFunds() {
        return CallForFunds.fromSnapshot(new CallForFunds.CallForFundsStateSnapshot(
                UUID.fromString("699915cd-056a-41e2-ba3d-cea478d4c0a2"),
                UUID.fromString("cdef8cc9-0fcd-42ca-b9c9-caff3acd61a5"),
                BigDecimal.valueOf(2500),
                1,
                LocalDateTime.of(2018, 3, 5, 4, 5)
        ));
    }

}
