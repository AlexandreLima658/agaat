package br.gov.pnae.agaat.domain.commons.exceptions;

import br.gov.pnae.agaat.domain.UnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DomainExceptionTest extends UnitTest {
    @Test
    @DisplayName("Deve criar uma DomainException quando passado uma mensagem")
    void shouldDomainException() {
        //given
        final String message = "Teste";
        //when
        final DomainException domainException = DomainException.with(message);
        //then
        assert domainException.getMessage().equals(message);

    }

    @Test
    @DisplayName("Deve criar uma DomainException quando passado uma mensagem e uma causa")
    void shouldDomainExceptionWithCause() {
        //given
        final String message = "Teste";
        //when
        final DomainException domainException = DomainException.with(message);
        //then
        assert domainException.getMessage().equals(message);
        assert domainException.getCause() == null;
    }

    @Test
    @DisplayName("Deve criar uma DomainException")
    void shouldWithSigleError() {

        //given
        final var errorInfo = new ErrorInfo("Teste single error");

        //when
        final var domainException = DomainException.with(errorInfo);

        //then
        assertEquals(errorInfo, domainException.errorInfo());

    }

}