package seedu.intern.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.intern.commons.exceptions.IllegalValueException;
import seedu.intern.model.application.*;
import seedu.intern.model.application.Status;
import seedu.intern.model.tag.Tag;

/**
 * Jackson-friendly version of {@link InternApplication}.
 */
class JsonAdaptedInternApplication {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String role;
    private final String email;
    private final String status;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedInternApplication} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedInternApplication(@JsonProperty("name") String name,
                                        @JsonProperty("role") String role,
                                        @JsonProperty("email") String email,
                                        @JsonProperty("status") String status,
                                        @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.status = status;
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
        email = source.getEmail().value;
        status = source.getStatus().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
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

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(status);

        final Set<Tag> modelTags = new HashSet<>(applicationTags);
        return new InternApplication(modelName, modelRole, modelEmail, modelStatus, modelTags);
    }
}
