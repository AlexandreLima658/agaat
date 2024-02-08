package br.gov.pnae.agaat.application.cecanes.retrieve;

import br.gov.pnae.agaat.application.cecanes.retrieve.get.GetCecaneByIdOutput;
import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneNome;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@Tag("unitTest")
class GetCecaneByIdOutputTest {
    @Test
    void shouldGetCecaneByIdOutput(){
        //given
        final var cecaneId = CecaneId.from(1L);
        var getCecaneByIdOutput = new GetCecaneByIdOutput(cecaneId, "Cecane 1");

        // when
        assertEquals(CecaneId.from(1L), getCecaneByIdOutput.id());
        assertEquals("Cecane 1", getCecaneByIdOutput.nome());

        // then
        assertNotEquals(2L, getCecaneByIdOutput.id());

    }

    @Test
    void shouldGetCecaneByIdOutputFromAggregate(){
        //given
        final var cecaneId = CecaneId.from(1L);
        final var cecane = new Cecane(cecaneId, new CecaneNome("Cecane 1"));
        var getCecaneByIdOutput = GetCecaneByIdOutput.fromAggregate(cecane);

        // when
        assertEquals(CecaneId.from(1L), getCecaneByIdOutput.id());
        assertEquals("Cecane 1", getCecaneByIdOutput.nome());

        // then
        assertNotEquals(2L, getCecaneByIdOutput.id());

    }
}