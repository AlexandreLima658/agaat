package br.gov.pnae.agaat.application.cecanes.create;

import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.commons.ids.Identifier;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("unitTest")
class OutputTest {
    @Test
    void shouldOutput(){
        //given
        Long id = UUID.randomUUID().getMostSignificantBits();
        //when
        Output output = new Output(id);
        //then
        assertNotNull(output);
    }

    @Test
    void shouldFromAggregate(){
        //given
        Cecane cecane = mock(Cecane.class);
        CecaneId cecaneId = mock(CecaneId.class);
        //when
        when(cecane.id()).thenReturn(cecaneId);
        when(cecaneId.value()).thenReturn(UUID.randomUUID().getMostSignificantBits());

        Output output = Output.fromAggregate(cecane);
        //then
        assertNotNull(output);
    }
}