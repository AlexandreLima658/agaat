package br.gov.pnae.agaat.application.cecanes.create;

import br.gov.pnae.agaat.application.UseCase;
import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.commons.validation.Either;
import br.gov.pnae.agaat.domain.commons.validation.handler.Notification;
import jakarta.inject.Named;

import java.util.Objects;

@Named
public class CreateCecaneUseCase extends UseCase<Input, Either<Notification, Output>> {
    private final CecaneRepository repository;

    public CreateCecaneUseCase(final CecaneRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public Either<Notification, Output> execute(final Input input) {
        final var notification = Notification.create();
        final var cecane = input.toAggregate();

        cecane.validate(notification);

        return notification.hasErrors() ? Either.left(notification) : create(cecane);
    }

    private Either<Notification, Output> create(final Cecane cecane) {
        this.repository.persist(cecane);

        return Either.right(Output.fromAggregate(cecane));
    }
}
