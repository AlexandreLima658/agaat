package br.gov.pnae.agaat.application.cecanes;

import br.gov.pnae.agaat.application.UseCase;
import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneFactory;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneNome;
import jakarta.inject.Named;

@Named
public class CreateCecaneUseCase extends UseCase<CreateCecaneUseCase.Input, CreateCecaneUseCase.Output> {

    private final CecaneRepository repository;

    public CreateCecaneUseCase(final CecaneRepository repository) {
        this.repository = repository;
    }

    public Output execute(final Input input) {

        final var cecane = input.toAggregate();

        repository.persist(cecane);

        return Output.fromAggregate(cecane);
    }

    public record Input(String nome) {

        public Cecane toAggregate() {

            final var nome = new CecaneNome(this.nome);

            return CecaneFactory.create(nome);
        }

    }

    public record Output(Long id) {

        public static Output fromAggregate(final Cecane cecane) {
            final var id = cecane.id().value();

            return new Output(
                    id
            );
        }

    }

}
