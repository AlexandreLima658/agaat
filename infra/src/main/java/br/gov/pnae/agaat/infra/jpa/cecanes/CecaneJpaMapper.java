package br.gov.pnae.agaat.infra.jpa.cecanes;

import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneFactory;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;

public interface CecaneJpaMapper {

    static CecaneJpaEntity toJpaEntity(final Cecane cecane) {
        return new CecaneJpaEntity()
                .setId(cecane.id().value())
                .setNome(cecane.nome());
    }

    static Cecane toAggregate(final CecaneJpaEntity cecaneJpaEntity) {
        return CecaneFactory.create(
                CecaneId.from(cecaneJpaEntity.getId()),
                cecaneJpaEntity.getNome()
        );
    }

}
