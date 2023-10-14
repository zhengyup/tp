package seedu.letsgethired.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.commons.util.AppUtil.checkArgument;

/**
 * Represents an InternApplication's company in the intern tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompany(String)}
 */
public class Company {

    public static final String MESSAGE_CONSTRAINTS =
            "Company should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the status must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Company}.
     *
     * @param name A valid company.
     */
    public Company(String name) {
        requireNonNull(name);
        checkArgument(isValidCompany(name), MESSAGE_CONSTRAINTS);
        value = name;
    }

    /**
     * Returns true if a given string is a valid company.
     */
    public static boolean isValidCompany(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Company)) {
            return false;
        }

        Company otherCompany = (Company) other;
        return value.equals(otherCompany.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
