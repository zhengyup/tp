package seedu.intern.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.intern.commons.exceptions.IllegalValueException;
import seedu.intern.model.application.Company;
import seedu.intern.model.application.Cycle;
import seedu.intern.model.application.InternApplication;
import seedu.intern.model.application.Role;
import seedu.intern.model.application.Status;
import seedu.intern.model.tag.Tag;

/**
 * Jackson-friendly version of {@link InternApplication}.
 */
class JsonAdaptedInternApplication {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String company;
    private final String role;
    private final String cycle;
    private final String status;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedInternApplication} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedInternApplication(@JsonProperty("company") String company,
                                        @JsonProperty("role") String role,
                                        @JsonProperty("cycle") String cycle,
                                        @JsonProperty("status") String status,
                                        @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.company = company;
        this.role = role;
        this.cycle = cycle;
        this.status = status;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code InternApplication} into this class for Jackson use.
     */
    public JsonAdaptedInternApplication(InternApplication source) {
        company = source.getCompany().companyName;
        role = source.getRole().value;
        cycle = source.getCycle().value;
        status = source.getStatus().value;
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

        if (company == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName()));
        }
        if (!Company.isValidName(company)) {
            throw new IllegalValueException(Company.MESSAGE_CONSTRAINTS);
        }
        final Company modelCompany = new Company(company);

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

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(status);

        final Set<Tag> modelTags = new HashSet<>(applicationTags);
        return new InternApplication(modelCompany, modelRole, modelCycle, modelStatus, modelTags);
    }
}
