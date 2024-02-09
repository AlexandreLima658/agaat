package br.gov.pnae.agaat.domain.commons;

import java.util.function.Function;

public class Either<L, R> {
    private L left = null;
    private R right = null;

    private Either(final L left, final R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Either<L, R> left(L left) {
        return new Either<>(left, null);
    }

    public L left() {
        return this.left;
    }

    public boolean isLeft() {
        return this.left != null;
    }

    public boolean isRight() {
        return this.right != null;
    }

    public R right() {
        return this.right;
    }

    public static <L, R> Either<L, R> right(final R right) {
        return new Either<>(null, right);
    }

    public final <U> U fold(Function<? super L, ? extends U> leftMapper, Function<? super R, ? extends U> rightMapper) {
        if (isRight()) {
            return rightMapper.apply(this.right);
        }

        return leftMapper.apply(this.left);

    }
}