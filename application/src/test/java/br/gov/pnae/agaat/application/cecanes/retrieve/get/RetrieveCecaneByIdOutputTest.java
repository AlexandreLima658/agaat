//package br.gov.pnae.agaat.application.cecanes.retrieve.get;
//
//import br.gov.pnae.agaat.domain.cecanes.CecaneFactory;
//import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//@Tag("unitTest")
//class RetrieveCecaneByIdOutputTest {
//    @Test
//    void shouldGetCecaneByIdOutput(){
//        //given
//        final var cecaneId = 1L;
//        var getCecaneByIdOutput = new RetrieveCecaneByIdOutput(cecaneId, "Cecane 1");
//
//        // when
//        assertEquals(1L, getCecaneByIdOutput.id());
//        assertEquals("Cecane 1", getCecaneByIdOutput.nome());
//
//        // then
//        assertNotEquals(2L, getCecaneByIdOutput.id());
//
//    }
//
//    @Test
//    void shouldGetCecaneByIdOutputFromAggregate(){
//        //given
//        final var cecaneId = CecaneId.from(1L);
//        final var cecane = CecaneFactory.create(cecaneId, "Cecane 1").right();
//        var getCecaneByIdOutput = RetrieveCecaneByIdOutput.fromAggregate(cecane);
//
//        // when
//        assertEquals(1L, getCecaneByIdOutput.id());
//        assertEquals("Cecane 1", getCecaneByIdOutput.nome());
//
//        // then
//        assertNotEquals(2L, getCecaneByIdOutput.id());
//
//    }
//}