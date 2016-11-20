package validation;

public interface Validation<T> {

    boolean isSatisfiedBy(T t);

}
