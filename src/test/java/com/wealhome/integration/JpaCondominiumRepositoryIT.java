package com.wealhome.integration;

import com.wealhome.businesslogic.models.Condominium;
import com.wealhome.businesslogic.repositories.CondominiumRepository;
import com.wealhome.infra.client.springboot.ServicesConfiguration;
import com.wealhome.infra.repositories.jpa.JpaCondominiumRepository;
import com.wealhome.infra.repositories.jpa.jpaentities.CondominiumJpaEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = ServicesConfiguration.class)
public class JpaCondominiumRepositoryIT extends BaseIntegration {

    @Autowired
    private CondominiumRepository condominiumRepository;

    @Test
    void canFindACondominiumById() {
        UUID aCondominiumId = UUID.fromString("cdef8cc9-0fcd-42ca-b9c9-caff3acd61a5");
        JpaCondominiumRepository jpaCondominiumRepository = (JpaCondominiumRepository) condominiumRepository;
        jpaCondominiumRepository.getSpringCondominiumRepository().save(
          new CondominiumJpaEntity(
                  aCondominiumId,
                  valueOf(10000)
          )
        );
        assertThat(condominiumRepository.findById(aCondominiumId).get().takeSnapshot()).isEqualTo(
                new Condominium.CondominiumStateSnapshot(
                        aCondominiumId,
                        valueOf(10000)
                )
        );
    }

}
