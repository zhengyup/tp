package seedu.intern.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.intern.model.application.Email;
import seedu.intern.model.application.InternApplication;
import seedu.intern.model.application.Name;
import seedu.intern.model.application.Role;
import seedu.intern.model.application.Status;
import seedu.intern.model.tag.Tag;
import seedu.intern.model.util.SampleDataUtil;

/**
 * A utility class to help with building InternApplication objects.
 */
public class InternApplicationBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_ROLE = "Intern";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_STATUS = "Pending";

    private Name name;
    private Role role;
    private Email email;
    private Status status;
    private Set<Tag> tags;

    /**
     * Creates a {@code InternApplicationBuilder} with the default details.
     */
    public InternApplicationBuilder() {
        name = new Name(DEFAULT_NAME);
        role = new Role(DEFAULT_ROLE);
        email = new Email(DEFAULT_EMAIL);
        status = new Status(DEFAULT_STATUS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the InternApplicationBuilder with the data of {@code internApplicationToCopy}.
     */
    public InternApplicationBuilder(InternApplication internApplicationToCopy) {
        name = internApplicationToCopy.getName();
        role = internApplicationToCopy.getRole();
        email = internApplicationToCopy.getEmail();
        status = internApplicationToCopy.getStatus();
        tags = new HashSet<>(internApplicationToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code InternApplication} that we are building.
     */
    public InternApplicationBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code InternApplication} that we are building.
     */
    public InternApplicationBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code InternApplication} that we are building.
     */
    public InternApplicationBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code InternApplication} that we are building.
     */
    public InternApplicationBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code InternApplication} that we are building.
     */
    public InternApplicationBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public InternApplication build() {
        return new InternApplication(name, role, email, status, tags);
    }

}
