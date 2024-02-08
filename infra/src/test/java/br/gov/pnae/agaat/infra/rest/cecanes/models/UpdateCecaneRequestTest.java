package br.gov.pnae.agaat.infra.rest.cecanes.models;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@Tag("unitTest")
class UpdateCecaneRequestTest {
    @Test
    void testUpdateCecaneRequest() {
        //given
        String name = "Cecane";
        //when
        UpdateCecaneRequest updateCecaneRequest = new UpdateCecaneRequest(name);
        //then
        assertEquals(name, updateCecaneRequest.name());
    }
}