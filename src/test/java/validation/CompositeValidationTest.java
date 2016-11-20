package validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CompositeValidationTest {

    Validation<String> satisfaction = (s) -> true;
    Validation<String> unSatisfaction = (s) -> false;

    final CompositeValidation<String> satisfied = new CompositeValidation<String>() {
        @Override
        public boolean isSatisfiedBy(String s) {
            return satisfaction.isSatisfiedBy(s);
        }
    };
    final CompositeValidation<String> unsatisfied = new CompositeValidation<String>() {
        @Override
        public boolean isSatisfiedBy(String s) {
            return unSatisfaction.isSatisfiedBy(s);
        }
    };

    @Test
    public void satisfied_and_satisfied_returns_true() throws Exception {
        assertTrue(satisfied.and(satisfaction).isSatisfiedBy(null));
    }

    @Test
    public void satisfied_and_unsatisfied_returns_false() throws Exception {
        assertFalse(satisfied.and(satisfaction).and(unSatisfaction).isSatisfiedBy(null));
    }

    @Test
    public void satisfied_or_satisfied_returns_true() throws Exception {
        assertTrue(satisfied.or(satisfaction).isSatisfiedBy(null));
    }

    @Test
    public void satisfied_or_unsatisfied_returns_true() throws Exception {
        assertTrue(satisfied.or(satisfaction).or(unSatisfaction).isSatisfiedBy(null));
    }

    @Test
    public void unsatisfied_or_unsatisfied_returns_false() throws Exception {
        assertFalse(unsatisfied.or(unSatisfaction).or(unSatisfaction).isSatisfiedBy(null));
    }

    @Test
    public void validation_is_reversed_when_not() throws Exception {
        assertTrue(unsatisfied.not().isSatisfiedBy(null));
        assertTrue(satisfied.and(unSatisfaction).not().isSatisfiedBy(null));
        assertFalse(satisfied.not().isSatisfiedBy(null));
        assertFalse(satisfied.or(unSatisfaction).not().isSatisfiedBy(null));
    }

}