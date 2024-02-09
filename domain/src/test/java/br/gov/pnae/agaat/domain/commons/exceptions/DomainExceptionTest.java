package br.gov.pnae.agaat.domain.commons.exceptions;

import br.gov.pnae.agaat.domain.commons.validation.Error;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @Test
    void shouldWithSigleError(){
      //given
        Error error = new Error("Teste single error");

        //when
        DomainException domainException = DomainException.with(error);

        //then
        assertEquals(1,domainException.getErrors().size());
        assertEquals(error, domainException.getErrors().get(0));
    }

    @Test
    void shouldWithListError(){
        //given
        Error error1 = new Error("Teste single error 1");
        Error error2 = new Error("Teste single error 2");

        //when
        DomainException domainException = DomainException.with(List.of(error1, error2));

        //then
        assertEquals(2,domainException.getErrors().size());
        assertEquals(error1, domainException.getErrors().get(0));
        assertEquals(error2, domainException.getErrors().get(1));
    }

}