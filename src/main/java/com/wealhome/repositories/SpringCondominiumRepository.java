package com.wealhome.repositories;

import com.wealhome.models.Condominium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringCondominiumRepository extends JpaRepository<Condominium, UUID> {
}
