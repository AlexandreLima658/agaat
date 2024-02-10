package br.gov.pnae.agaat.infra.rest.cecanes.models;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Tag("unitTest")
class CreateCecaneRequestTest {

        @Test
        void testCreateCecaneRequest() {
            //given
            String name = "Cecane";
            //when
            CreateCecaneRequest createCecaneRequest = new CreateCecaneRequest(name);
            //then
            assertEquals(name, createCecaneRequest.name());
        }
}