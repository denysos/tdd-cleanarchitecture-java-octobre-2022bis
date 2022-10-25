package com.wealhome.infra.repositories.jpa;

import com.wealhome.businesslogic.models.Condominium;
import com.wealhome.businesslogic.repositories.CondominiumRepository;

import java.util.Optional;
import java.util.UUID;

public class JpaCondominiumRepository implements CondominiumRepository {

    private final SpringCondominiumRepository springCondominiumRepository;

    public JpaCondominiumRepository(SpringCondominiumRepository springCondominiumRepository) {
        this.springCondominiumRepository = springCondominiumRepository;
    }

    @Override
    public Optional<Condominium> findById(UUID condominiumId) {
        return springCondominiumRepository.findById(condominiumId).map(c ->
                Condominium.fromSnapshot(c.getId(), c.getYearlyBudget()));
    }
}
