package br.gov.pnae.agaat.application;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@Tag("unitTest")
class NullaryUseCaseTest {
    @Test
    void givenNullaryUseCase_whenExecute_thenShouldExecute() {
      //given
        NullaryUseCase<String> useCase = new NullaryUseCase<>() {
            @Override
            public String execute() {
                return "testInput";
            }
        };
        //when
        String result = useCase.execute();
        //then
        assertEquals("testInput", result);

    }

}