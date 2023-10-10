package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.application.Address;
import seedu.address.model.application.Cycle;
import seedu.address.model.application.InternApplication;
import seedu.address.model.application.Name;
import seedu.address.model.application.Role;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link InternApplication}.
 */
class JsonAdaptedInternApplication {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String role;
    private final String cycle;
    private final String address;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedInternApplication} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedInternApplication(@JsonProperty("name") String name, @JsonProperty("role") String role,
                                        @JsonProperty("cycle") String cycle, @JsonProperty("address") String address,
                                        @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.role = role;
        this.cycle = cycle;
        this.address = address;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code InternApplication} into this class for Jackson use.
     */
    public JsonAdaptedInternApplication(InternApplication source) {
        name = source.getName().fullName;
        role = source.getRole().value;
        cycle = source.getCycle().value;
        address = source.getAddress().value;
        tags.addAll(source.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code InternApplication} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public InternApplication toModelType() throws IllegalValueException {
        final List<Tag> applicationTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            applicationTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (role == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName()));
        }
        if (!Role.isValidRole(role)) {
            throw new IllegalValueException(Role.MESSAGE_CONSTRAINTS);
        }
        final Role modelRole = new Role(role);

        if (cycle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Cycle.class.getSimpleName()));
        }
        if (!Cycle.isValidCycle(cycle)) {
            throw new IllegalValueException(Cycle.MESSAGE_CONSTRAINTS);
        }
        final Cycle modelCycle = new Cycle(cycle);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(applicationTags);
        return new InternApplication(modelName, modelRole, modelCycle, modelAddress, modelTags);
    }
}
