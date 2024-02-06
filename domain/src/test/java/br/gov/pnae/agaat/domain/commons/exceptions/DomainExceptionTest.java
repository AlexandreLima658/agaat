package br.gov.pnae.agaat.domain.commons.exceptions;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unitTest")
class DomainExceptionTest {
    @Test
    void shouldDomainException() {
        //given
        final String message = "Teste";
        //when
        final DomainException domainException = new DomainException(message);
        //then
        assert domainException.getMessage().equals(message);

    }

    @Test
    void shouldDomainExceptionWithCause() {
        //given
        final String message = "Teste";
        final Throwable cause = new Throwable();
        //when
        final DomainException domainException = new DomainException(message, cause);
        //then
        assert domainException.getMessage().equals(message);
        assert domainException.getCause().equals(cause);
    }

}