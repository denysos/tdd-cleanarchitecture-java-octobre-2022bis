package com.wealhome.businesslogic.repositories;

import com.wealhome.businesslogic.models.Condominium;

import java.util.Optional;
import java.util.UUID;

public interface CondominiumRepository {
    Optional<Condominium> findById(UUID condominiumId);
}
