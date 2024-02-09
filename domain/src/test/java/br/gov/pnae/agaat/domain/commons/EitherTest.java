package br.gov.pnae.agaat.domain.commons;

import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneFactory;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneNome;
import br.gov.pnae.agaat.domain.commons.exceptions.DomainException;
import br.gov.pnae.agaat.domain.commons.exceptions.ErrorInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unitTest")
class EitherTest {
    @Test
    void shouldCreateEitherWithLeftValue() {
        // given
        var exception = new DomainException(new ErrorInfo("Nome do Cecane não pode ser nulo ou vazio"));

        // when
        Either<DomainException, Cecane> either = Either.left(exception);

        // then
        assertNotNull(either);
        assertTrue(either.isLeft());
        assertFalse(either.isRight());
        assertNull(either.right());
        assertEquals(exception.errorInfo().message(), either.left().errorInfo().message());
    }

    @Test
    void shouldCreateEitherWithRightValue() {
        // given
        var cecane = CecaneFactory.create(new CecaneNome("UFC Russas"));

        // when
        Either<DomainException, Cecane> either = Either.right(cecane);

        // then
        assertNotNull(either);
        assertNull(either.left());
        assertFalse(either.isLeft());
        assertTrue(either.isRight());
        assertEquals(cecane.nome(), either.right().nome());
    }
}