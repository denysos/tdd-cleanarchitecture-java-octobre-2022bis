package com.wealhome.infra.repositories.jpa;

import com.wealhome.infra.repositories.jpa.jpaentities.CallForFundsJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringCallForFundsRepository extends JpaRepository<CallForFundsJpaEntity, UUID> {
}
