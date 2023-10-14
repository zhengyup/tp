package seedu.letsgethired.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.letsgethired.model.application.Company;
import seedu.letsgethired.model.application.Cycle;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.Role;
import seedu.letsgethired.model.application.Status;
import seedu.letsgethired.model.tag.Tag;
import seedu.letsgethired.model.util.SampleDataUtil;

/**
 * A utility class to help with building InternApplication objects.
 */
public class InternApplicationBuilder {

    public static final String DEFAULT_COMPANY = "Amy Bee";
    public static final String DEFAULT_ROLE = "Intern";
    public static final String DEFAULT_CYCLE = "Summer 2021";
    public static final String DEFAULT_STATUS = "Pending";

    private Company company;
    private Role role;
    private Cycle cycle;
    private Status status;
    private Set<Tag> tags;

    /**
     * Creates a {@code InternApplicationBuilder} with the default details.
     */
    public InternApplicationBuilder() {
        company = new Company(DEFAULT_COMPANY);
        role = new Role(DEFAULT_ROLE);
        cycle = new Cycle(DEFAULT_CYCLE);
        status = new Status(DEFAULT_STATUS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the InternApplicationBuilder with the data of {@code internApplicationToCopy}.
     */
    public InternApplicationBuilder(InternApplication internApplicationToCopy) {
        company = internApplicationToCopy.getCompany();
        role = internApplicationToCopy.getRole();
        cycle = internApplicationToCopy.getCycle();
        status = internApplicationToCopy.getStatus();
        tags = new HashSet<>(internApplicationToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code InternApplication} that we are building.
     */
    public InternApplicationBuilder withCompany(String name) {
        this.company = new Company(name);
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
    public InternApplicationBuilder withCycle(String email) {
        this.cycle = new Cycle(email);
        return this;
    }

    public InternApplication build() {
        return new InternApplication(company, role, cycle, status, tags);
    }

}
