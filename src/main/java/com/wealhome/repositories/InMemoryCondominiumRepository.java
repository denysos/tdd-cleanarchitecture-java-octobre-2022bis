package com.wealhome.repositories;

import com.wealhome.models.Condominium;

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
