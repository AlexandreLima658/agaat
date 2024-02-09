package br.gov.pnae.agaat.infra.rest.cecanes.models;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unitTest")
class CecaneListResponseTest {
    @Test
    void shouldCecaneListResponse() {
        CecaneListResponse cecaneListResponse = new CecaneListResponse(1L, "nome");
        assertEquals(1L, cecaneListResponse.id());
        assertEquals("nome", cecaneListResponse.nome());
    }
}