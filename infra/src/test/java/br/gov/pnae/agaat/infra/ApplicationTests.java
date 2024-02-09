package br.gov.pnae.agaat.infra;

import br.gov.pnae.agaat.Application;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTests {
    @Test
    public void testMain() {
        Assertions.assertNotNull(new Application());
        Application.main(new String[]{});
    }
}
