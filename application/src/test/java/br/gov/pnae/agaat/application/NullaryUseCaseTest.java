package br.gov.pnae.agaat.application;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void givenNullaryUseCase_whenExecute_thenShouldExecuteWithPresenter() {
        //given
        NullaryUseCase<String> useCase = new NullaryUseCase<>() {
            @Override
            public String execute() {
                return "Mock output";
            }
        };
        //when
        String result = useCase.execute(new Presenter<>() {
            @Override
            public String present(final String output) {
                return output;
            }

            @Override
            public String present(final Throwable throwable) {
                return "Error";
            }

        });

        //then
        assertEquals("Mock output", result);

    }

    @Test
    void givenNullaryUseCase_whenExecute_thenShouldExecuteWithPresenterAndThrowException() {
        //given
        NullaryUseCase<String> useCase = new NullaryUseCase<>() {
            @Override
            public String execute() {
                throw new RuntimeException("Mock exception");
            }
        };
        //when
        String result = useCase.execute(new Presenter<>() {
            @Override
            public String present(final String output) {
                return output;
            }

            @Override
            public String present(final Throwable throwable) {
                return "Error";
            }

        });

        //then
        assertEquals("Error", result);

    }

}