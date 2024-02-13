//package br.gov.pnae.agaat.application.cecanes.retrieve.list;
//
//import br.gov.pnae.agaat.domain.cecanes.CecaneFactory;
//import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@Tag("unitTest")
//class RetrieveCecanesByFilterOutputTest {
//    @Test
//    void shouldCreateCecaneListOutput() {
//        // given
//        CecaneId cecaneId = Mockito.mock(CecaneId.class);
//        final var expectedNome = "nome";
//
//        // when
//        final var cecaneListOutput = new RetrieveCecanesByFilterOutput(cecaneId, expectedNome);
//
//        // then
//        assertNotNull(cecaneListOutput);
//        assertEquals(cecaneListOutput, cecaneListOutput);
//        assertEquals(cecaneId, cecaneListOutput.id());
//        assertEquals(expectedNome, cecaneListOutput.nome());
//    }
//
//    @Test
//    void shouldFromAggregate() {
//        // given
//        final var cecaneId = CecaneId.from(1L);
//        final var cecane = CecaneFactory.create(cecaneId, "Cecane 1").right();
//        var cecaneListOutput = RetrieveCecanesByFilterOutput.fromAggregate(cecane);
//
//        // when
//        assertEquals(CecaneId.from(1L), cecaneListOutput.id());
//        assertEquals("Cecane 1", cecaneListOutput.nome());
//
//        // then
//        assertNotEquals(2L, cecaneListOutput.id());
//
//
//    }
//}