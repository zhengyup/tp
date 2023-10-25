package seedu.letsgethired.model.application;

import static seedu.letsgethired.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.letsgethired.commons.util.ToStringBuilder;

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
    private final Note note;
    private final Status status;
    private final Deadline deadline;

    /**
     * Every field must be present and not null.
     */
    public InternApplication(Company company, Role role, Cycle cycle, Note note, Status status, Deadline deadline) {
        requireAllNonNull(company, role, cycle, note, status, deadline);
        this.company = company;
        this.role = role;
        this.cycle = cycle;
        this.note = note;
        this.status = status;
        this.deadline = deadline;
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

    public Note getNote() {
        return note;
    }

    public Status getStatus() {
        return status;
    }

    public Deadline getDeadline() {
        return deadline;
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
                && otherInternApplication.getCompany().equals(getCompany())
                && otherInternApplication.getRole().equals(getRole())
                && otherInternApplication.getCycle().equals(getCycle());
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

        // No comparing notes because notes is not a strict differentiating factor
        InternApplication otherInternApplication = (InternApplication) other;
        return company.equals(otherInternApplication.company)
                && role.equals(otherInternApplication.role)
                && cycle.equals(otherInternApplication.cycle);
    }

    @Override
    public int hashCode() {
        // No comparing notes because notes is not a strict differentiating factor
        return Objects.hash(company, role, cycle);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("company", company)
                .add("role", role)
                .add("cycle", cycle)
                .add("note", note)
                .add("status", status)
                .add("deadline", deadline)
                .toString();
    }

    /**
     * Creates a copy of the current InternApplication object.
     *
     * @return A new InternApplication object with the same company, role, cycle, note, and status.
     */
    public InternApplication clone() {
        return new InternApplication(this.company, this.role, this.cycle, this.note, this.status);
    }
}
