package com.wealhome.infra.repositories.jpa;

import com.wealhome.infra.repositories.jpa.jpaentities.CondominiumJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringCondominiumRepository extends JpaRepository<CondominiumJpaEntity, UUID> {
}
