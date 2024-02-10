package br.gov.pnae.agaat.application.cecanes.create;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@Tag("unitTest")
class InputTest {
    @Test
    void shouldInput(){
        //given
        String nome = "Cecane 1";
        //when
        CreateCecaneInput input = new CreateCecaneInput(nome);
        //then
        assertNotNull(input);
    }
    @Test
    void shouldToAggregate(){
        //given
        String nome = "Cecane 1";
        CreateCecaneInput input = new CreateCecaneInput(nome);
        //when
        var cecane = input.toAggregate();
        //then
        assertNotNull(cecane);
    }
}