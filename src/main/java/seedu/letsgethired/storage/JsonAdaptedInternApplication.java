package seedu.letsgethired.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.letsgethired.commons.exceptions.IllegalValueException;
import seedu.letsgethired.model.application.Company;
import seedu.letsgethired.model.application.Cycle;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.Note;
import seedu.letsgethired.model.application.Role;
import seedu.letsgethired.model.application.Status;

/**
 * Jackson-friendly version of {@link InternApplication}.
 */
class JsonAdaptedInternApplication {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Intern Application's %s field is missing!";
    private final String company;
    private final String role;
    private final String cycle;
    private final String status;
    private final String note;

    /**
     * Constructs a {@code JsonAdaptedInternApplication} with the given intern application details.
     */
    @JsonCreator
    public JsonAdaptedInternApplication(@JsonProperty("company") String company,
                                        @JsonProperty("role") String role,
                                        @JsonProperty("cycle") String cycle,
                                        @JsonProperty("note") String note,
                                        @JsonProperty("status") String status) {
        this.company = company;
        this.role = role;
        this.cycle = cycle;
        this.note = note;
        this.status = status;
    }

    /**
     * Converts a given {@code InternApplication} into this class for Jackson use.
     */
    public JsonAdaptedInternApplication(InternApplication source) {
        company = source.getCompany().value;
        role = source.getRole().value;
        cycle = source.getCycle().value;
        note = source.getNote().value;
        status = source.getStatus().value;
    }

    /**
     * Converts this Jackson-friendly adapted intern application object
     * into the model's {@code InternApplication} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted intern application.
     */
    public InternApplication toModelType() throws IllegalValueException {
        if (company == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName()));
        }
        if (!Company.isValidCompany(company)) {
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

        if (note == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Note.class.getSimpleName()));
        }
        if (!Note.isValidNote(note)) {
            throw new IllegalValueException(Note.MESSAGE_CONSTRAINTS);
        }
        final Note modelNote = new Note(note);

        return new InternApplication(modelCompany, modelRole, modelCycle, modelNote, modelStatus);
    }
}
