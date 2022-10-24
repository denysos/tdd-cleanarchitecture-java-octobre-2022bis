package com.wealhome.infra.repositories.jpa;

import com.wealhome.businesslogic.models.Condominium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringCondominiumRepository extends JpaRepository<Condominium, UUID> {
}
