package br.gov.pnae.agaat.infra.jpa.cecanes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface CecaneJpaRepository extends JpaRepository<CecaneJpaEntity, UUID>, JpaSpecificationExecutor<CecaneJpaEntity> {
}
