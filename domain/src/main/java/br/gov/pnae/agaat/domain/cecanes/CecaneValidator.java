package br.gov.pnae.agaat.domain.cecanes;

import br.gov.pnae.agaat.domain.commons.exceptions.DomainException;
import br.gov.pnae.agaat.domain.commons.validation.Error;
import br.gov.pnae.agaat.domain.commons.validation.ValidationHandler;
import br.gov.pnae.agaat.domain.commons.validation.Validator;

public class CecaneValidator extends Validator {
    private Cecane cecane;

    protected CecaneValidator(final Cecane cecane, final ValidationHandler anHandler) {
        super(anHandler);
        this.cecane = cecane;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        try {
            this.cecane.nome.validate();
        } catch (final DomainException ex) {
            this.validationHandler().append(new Error(ex.getMessage()));
        }
    }
}
