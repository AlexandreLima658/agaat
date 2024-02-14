package br.gov.pnae.agaat.infra.jpa.cecanes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "cecane")
public class CecaneJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String nome;

    public UUID getId() {
        return id;
    }

    public CecaneJpaEntity setId(final UUID id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public CecaneJpaEntity setNome(final String nome) {
        this.nome = nome;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (CecaneJpaEntity) o;
        return id.equals(that.id);
    }

    public int hashCode() {
        return id.hashCode();
    }
}
