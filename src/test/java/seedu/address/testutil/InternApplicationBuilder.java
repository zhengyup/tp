package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.application.Address;
import seedu.address.model.application.Cycle;
import seedu.address.model.application.InternApplication;
import seedu.address.model.application.Name;
import seedu.address.model.application.Role;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building InternApplication objects.
 */
public class InternApplicationBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_ROLE = "Intern";
    public static final String DEFAULT_CYCLE = "Summer 2021";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Role role;
    private Cycle cycle;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code InternApplicationBuilder} with the default details.
     */
    public InternApplicationBuilder() {
        name = new Name(DEFAULT_NAME);
        role = new Role(DEFAULT_ROLE);
        cycle = new Cycle(DEFAULT_CYCLE);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the InternApplicationBuilder with the data of {@code internApplicationToCopy}.
     */
    public InternApplicationBuilder(InternApplication internApplicationToCopy) {
        name = internApplicationToCopy.getName();
        role = internApplicationToCopy.getRole();
        cycle = internApplicationToCopy.getCycle();
        address = internApplicationToCopy.getAddress();
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
     * Sets the {@code Address} of the {@code InternApplication} that we are building.
     */
    public InternApplicationBuilder withAddress(String address) {
        this.address = new Address(address);
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
        return new InternApplication(name, role, cycle, address, tags);
    }

}
