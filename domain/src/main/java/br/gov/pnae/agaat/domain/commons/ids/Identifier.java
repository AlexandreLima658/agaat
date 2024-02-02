package br.gov.pnae.agaat.domain.commons.ids;

public abstract class Identifier<T> {

    private final T value;

    protected Identifier(final T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        final var that = (Identifier<?>) other;

        return value.equals(that.value);
    }

}
