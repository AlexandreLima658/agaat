package br.gov.pnae.agaat.domain.commons.exceptions;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("unitTest")
class DomainExceptionTest {
    @Test
    void shouldDomainException() {
        //given
        final String message = "Teste";
        //when
        final DomainException domainException = DomainException.with(message);
        //then
        assert domainException.getMessage().equals(message);

    }

    @Test
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
    void shouldWithSigleError() {

        //given
        ErrorInfo errorInfo = new ErrorInfo("Teste single error");

        //when
        final var domainException = DomainException.with(errorInfo);

        //then
        assertEquals(errorInfo, domainException.errorInfo());

    }

}