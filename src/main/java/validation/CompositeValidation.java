package validation;

public abstract class CompositeValidation<T> implements Composition<T> {

    @Override
    public abstract boolean isSatisfiedBy(T t);

    public Composition<T> and(Validation<T> other) {
        return new AndValidation(this, other);
    }

    public Composition<T> or(Validation<T> other) {
        return new OrValidation(this, other);
    }

    public Composition<T> not() {
        return new NotValidation(this);
    }

    class AndValidation extends CompositeValidation<T> {

        private final Validation<T> validation1;
        private final Validation<T> validation2;

        public AndValidation(Validation<T> validation1, Validation<T> validation2) {
            this.validation1 = validation1;
            this.validation2 = validation2;
        }

        @Override
        public boolean isSatisfiedBy(T t) {
            return validation1.isSatisfiedBy(t) && validation2.isSatisfiedBy(t);
        }
    }

    class NotValidation extends CompositeValidation<T> {

        private final Validation<T> validation;

        public NotValidation(Validation<T> v) {
            this.validation = v;
        }

        @Override
        public boolean isSatisfiedBy(T t) {
            return !validation.isSatisfiedBy(t);
        }
    }

    class OrValidation extends CompositeValidation<T> {

        private final Validation<T> validation1;
        private final Validation<T> validation2;

        public OrValidation(Validation<T> validation1, Validation<T> validation2) {
            this.validation1 = validation1;
            this.validation2 = validation2;
        }

        @Override
        public boolean isSatisfiedBy(T o) {
            return validation1.isSatisfiedBy(o) || validation2.isSatisfiedBy(o);
        }
    }

}
