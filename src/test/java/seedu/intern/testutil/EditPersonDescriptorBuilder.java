package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.intern.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.intern.model.application.Email;
import seedu.intern.model.application.InternApplication;
import seedu.intern.model.application.Name;
import seedu.intern.model.application.Role;
import seedu.intern.model.application.Status;
import seedu.intern.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(InternApplication internApplication) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(internApplication.getName());
        descriptor.setRole(internApplication.getRole());
        descriptor.setCycle(internApplication.getCycle());
        descriptor.setStatus(internApplication.getStatus());
        descriptor.setTags(internApplication.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withRole(String role) {
        descriptor.setRole(new Role(role));
        return this;
    }

    /**
     * Sets the {@code Cycle} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withCycle(String cycle) {
        descriptor.setCycle(new Cycle(cycle));
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(new Status(status));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
