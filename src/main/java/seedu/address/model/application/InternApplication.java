package seedu.address.model.application;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents an Intern Application in the intern tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class InternApplication {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Cycle cycle;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public InternApplication(Name name, Phone phone, Cycle cycle, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, cycle, address, tags);
        this.name = name;
        this.phone = phone;
        this.cycle = cycle;
        this.address = address;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public Address getAddress() {
        return address;
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
                && otherInternApplication.getName().equals(getName());
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
        return name.equals(otherInternApplication.name)
                && phone.equals(otherInternApplication.phone)
                && cycle.equals(otherInternApplication.cycle)
                && address.equals(otherInternApplication.address)
                && tags.equals(otherInternApplication.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, cycle, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("cycle", cycle)
                .add("address", address)
                .add("tags", tags)
                .toString();
    }

}
