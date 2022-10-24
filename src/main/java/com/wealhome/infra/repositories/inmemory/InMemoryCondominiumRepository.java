package com.wealhome.infra.repositories.inmemory;

import com.wealhome.businesslogic.models.Condominium;
import com.wealhome.businesslogic.repositories.CondominiumRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryCondominiumRepository implements CondominiumRepository {

    private final List<Condominium> condominiums = new ArrayList<>();

    @Override
    public Optional<Condominium> findById(UUID condominiumId) {
        return condominiums.stream().filter(condominium -> condominium.getId().equals(condominiumId)).findFirst();
    }

    public void feedWith(Condominium condominium) {
        condominiums.add(condominium);
    }
}
