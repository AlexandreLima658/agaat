package br.gov.pnae.agaat.application.cecanes.update;

public record UpdateCecaneCommand(Long id, String name) {
    public static UpdateCecaneCommand with(final Long id, final String name) {
        return new UpdateCecaneCommand(
                id,
                name
        );
    }
}
