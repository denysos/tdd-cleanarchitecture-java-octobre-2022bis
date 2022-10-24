package com.wealhome.repositories;

import com.wealhome.models.Condominium;

import java.util.Optional;
import java.util.UUID;

public interface CondominiumRepository {
    Optional<Condominium> findById(UUID condominiumId);
}
