package validation;

public interface Composition<T> extends Validation<T> {

    Composition<T> and(Validation<T> other);

    Composition<T> or(Validation<T> other);

    Composition<T> not();
}
