package seedu.intern.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.intern.storage.JsonAdaptedInternApplication.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.intern.testutil.Assert.assertThrows;
import static seedu.intern.testutil.TypicalInternApplications.OPTIVER;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.intern.commons.exceptions.IllegalValueException;
import seedu.intern.model.application.Company;
import seedu.intern.model.application.Cycle;
import seedu.intern.model.application.Role;
import seedu.intern.model.application.Status;

public class JsonAdaptedInternApplicationTest {
    private static final String INVALID_NAME = "J@ne";
    private static final String INVALID_ROLE = " ";
    private static final String INVALID_STATUS = " ";
    private static final String INVALID_CYCLE = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = OPTIVER.getCompany().toString();
    private static final String VALID_ROLE = OPTIVER.getRole().toString();
    private static final String VALID_CYCLE = OPTIVER.getCycle().toString();
    private static final String VALID_STATUS = OPTIVER.getStatus().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS =
            OPTIVER.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList());

    @Test
    public void toModelType_validInternApplicationDetails_returnsInternApplication() throws Exception {
        JsonAdaptedInternApplication application = new JsonAdaptedInternApplication(OPTIVER);
        assertEquals(OPTIVER, application.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(INVALID_NAME, VALID_ROLE, VALID_CYCLE, VALID_STATUS, VALID_TAGS);
        String expectedMessage = Company.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(null, VALID_ROLE, VALID_CYCLE, VALID_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidRole_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(VALID_NAME, INVALID_ROLE, VALID_CYCLE, VALID_STATUS, VALID_TAGS);
        String expectedMessage = Role.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullRole_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(VALID_NAME, null, VALID_CYCLE, VALID_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidCycle_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(VALID_NAME, VALID_ROLE, INVALID_CYCLE, VALID_STATUS, VALID_TAGS);
        String expectedMessage = Cycle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullCycle_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(VALID_NAME, VALID_ROLE, null, VALID_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Cycle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(VALID_NAME, VALID_ROLE, VALID_CYCLE, INVALID_STATUS, VALID_TAGS);
        String expectedMessage = Status.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(VALID_NAME, VALID_ROLE, VALID_CYCLE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(VALID_NAME, VALID_ROLE, VALID_CYCLE, VALID_STATUS, invalidTags);
        assertThrows(IllegalValueException.class, application::toModelType);
    }

}
