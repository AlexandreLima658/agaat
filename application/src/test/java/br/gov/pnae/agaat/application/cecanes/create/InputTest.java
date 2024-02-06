package br.gov.pnae.agaat.application.cecanes.create;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@Tag("unitTest")
class InputTest {
    @Test
    void shouldInput(){
        //given
        String nome = "Cecane 1";
        //when
        Input input = new Input(nome);
        //then
        assertNotNull(input);
    }
    @Test
    void shouldToAggregate(){
        //given
        String nome = "Cecane 1";
        Input input = new Input(nome);
        //when
        var cecane = input.toAggregate();
        //then
        assertNotNull(cecane);
    }
}