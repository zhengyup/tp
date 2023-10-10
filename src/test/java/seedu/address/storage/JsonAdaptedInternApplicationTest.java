package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedInternApplication.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternApplications.BENSON;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.application.Address;
import seedu.address.model.application.Cycle;
import seedu.address.model.application.Name;
import seedu.address.model.application.Role;

public class JsonAdaptedInternApplicationTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ROLE = " ";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_CYCLE = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_ROLE = BENSON.getRole().toString();
    private static final String VALID_CYCLE = BENSON.getCycle().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validInternApplicationDetails_returnsInternApplication() throws Exception {
        JsonAdaptedInternApplication application = new JsonAdaptedInternApplication(BENSON);
        assertEquals(BENSON, application.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(INVALID_NAME, VALID_ROLE, VALID_CYCLE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedInternApplication application = new JsonAdaptedInternApplication(null, VALID_ROLE,
                VALID_CYCLE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidRole_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(VALID_NAME, INVALID_ROLE, VALID_CYCLE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Role.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullRole_throwsIllegalValueException() {
        JsonAdaptedInternApplication application = new JsonAdaptedInternApplication(VALID_NAME, null,
                VALID_CYCLE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidCycle_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(VALID_NAME, VALID_ROLE, INVALID_CYCLE, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Cycle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullCycle_throwsIllegalValueException() {
        JsonAdaptedInternApplication application = new JsonAdaptedInternApplication(VALID_NAME, VALID_ROLE,
                null, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Cycle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(VALID_NAME, VALID_ROLE, VALID_CYCLE, INVALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedInternApplication application = new JsonAdaptedInternApplication(VALID_NAME, VALID_ROLE,
                VALID_CYCLE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(VALID_NAME, VALID_ROLE, VALID_CYCLE, VALID_ADDRESS, invalidTags);
        assertThrows(IllegalValueException.class, application::toModelType);
    }

}
