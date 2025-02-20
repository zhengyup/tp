package seedu.intern.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.intern.commons.exceptions.IllegalValueException;
import seedu.intern.model.InternTracker;
import seedu.intern.model.ReadOnlyInternTracker;
import seedu.intern.model.application.InternApplication;

/**
 * An Immutable InternTracker that is serializable to JSON format.
 */
@JsonRootName(value = "internTracker")
class JsonSerializableInternTracker {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedInternApplication> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableInternTracker} with the given persons.
     */
    @JsonCreator
    public JsonSerializableInternTracker(@JsonProperty("persons") List<JsonAdaptedInternApplication> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyInternTracker} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableInternTracker}.
     */
    public JsonSerializableInternTracker(ReadOnlyInternTracker source) {
        persons.addAll(source.getApplicationList().stream().map(JsonAdaptedInternApplication::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this intern tracker into the model's {@code InternTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public InternTracker toModelType() throws IllegalValueException {
        InternTracker internTracker = new InternTracker();
        for (JsonAdaptedInternApplication jsonAdaptedPerson : persons) {
            InternApplication internApplication = jsonAdaptedPerson.toModelType();
            if (internTracker.hasApplication(internApplication)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            internTracker.addApplication(internApplication);
        }
        return internTracker;
    }
}
