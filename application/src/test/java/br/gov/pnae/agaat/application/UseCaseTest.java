package br.gov.pnae.agaat.application;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unitTest")
class UseCaseTest {
    @Test
    void givenUseCase_whenExecute_thenShouldExecute() {
      //given
        UseCase<String, String> useCase = new UseCase<>() {
            @Override
            public String execute(String input) {
                return input;
            }
        };
        //when
        final String result = useCase.execute("testInput");
        //then
        assertEquals("testInput", result);

    }
}