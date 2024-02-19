package br.gov.pnae.agaat.infra;

import br.gov.pnae.agaat.Application;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.AbstractEnvironment;


public class ApplicationTests extends E2eTest {
    @Test
    public void testMain() {
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "test");
        Application.main(new String[]{});
    }
}
