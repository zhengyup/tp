package seedu.letsgethired.model.application;

import static seedu.letsgethired.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.letsgethired.commons.util.ToStringBuilder;
import seedu.letsgethired.model.tag.Tag;

/**
 * Represents an Intern Application in the intern tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class InternApplication {

    // Identity fields
    private final Company company;
    private final Role role;
    private final Cycle cycle;

    // Data fields
    private final Status status;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public InternApplication(Company company, Role role, Cycle cycle, Status status, Set<Tag> tags) {
        requireAllNonNull(company, role, cycle, status, tags);
        this.company = company;
        this.role = role;
        this.cycle = cycle;
        this.status = status;
        this.tags.addAll(tags);
    }

    public Company getCompany() {
        return company;
    }

    public Role getRole() {
        return role;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both applications have the same name.
     * This defines a weaker notion of equality between two applications.
     */
    public boolean isSameApplication(InternApplication otherInternApplication) {
        if (otherInternApplication == this) {
            return true;
        }

        return otherInternApplication != null
                && otherInternApplication.getCompany().equals(getCompany());
    }

    /**
     * Returns true if both applications have the same identity and data fields.
     * This defines a stronger notion of equality between two applications.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternApplication)) {
            return false;
        }

        InternApplication otherInternApplication = (InternApplication) other;
        return company.equals(otherInternApplication.company)
                && role.equals(otherInternApplication.role)
                && cycle.equals(otherInternApplication.cycle)
                && status.equals(otherInternApplication.status)
                && tags.equals(otherInternApplication.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(company, role, cycle, status, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("company", company)
                .add("role", role)
                .add("cycle", cycle)
                .add("status", status)
                .add("tags", tags)
                .toString();
    }

}
