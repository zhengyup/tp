package seedu.letsgethired.logic.parser;

import static seedu.letsgethired.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.letsgethired.logic.commands.CommandTestUtil.CYCLE_DESC_A;
import static seedu.letsgethired.logic.commands.CommandTestUtil.CYCLE_DESC_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.INVALID_CYCLE_DESC;
import static seedu.letsgethired.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.letsgethired.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.letsgethired.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.letsgethired.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.letsgethired.logic.commands.CommandTestUtil.COMPANY_DESC_A;
import static seedu.letsgethired.logic.commands.CommandTestUtil.ROLE_DESC_A;
import static seedu.letsgethired.logic.commands.CommandTestUtil.ROLE_DESC_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.STATUS_DESC_A;
import static seedu.letsgethired.logic.commands.CommandTestUtil.STATUS_DESC_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.letsgethired.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_COMPANY_A;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_CYCLE_A;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_A;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_STATUS_A;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.letsgethired.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.letsgethired.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_THIRD_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.logic.Messages;
import seedu.letsgethired.logic.commands.EditCommand;
import seedu.letsgethired.logic.commands.EditCommand.EditInternApplicationDescriptor;
import seedu.letsgethired.model.application.Company;
import seedu.letsgethired.model.application.Cycle;
import seedu.letsgethired.model.application.Role;
import seedu.letsgethired.model.application.Status;
import seedu.letsgethired.model.tag.Tag;
import seedu.letsgethired.testutil.EditInternApplicationDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_COMPANY_A, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + COMPANY_DESC_A, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + COMPANY_DESC_A, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_COMPANY_DESC, Company.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_ROLE_DESC, Role.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_CYCLE_DESC, Cycle.MESSAGE_CONSTRAINTS); // invalid cycle
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC, Status.MESSAGE_CONSTRAINTS); // invalid status
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid role followed by valid cycle
        assertParseFailure(parser, "1" + INVALID_ROLE_DESC + CYCLE_DESC_A, Role.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_COMPANY_DESC + INVALID_CYCLE_DESC
                        + VALID_STATUS_A + VALID_ROLE_A, Company.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_APPLICATION;
        String userInput = targetIndex.getOneBased() + ROLE_DESC_B + TAG_DESC_HUSBAND
                + CYCLE_DESC_A + STATUS_DESC_A + COMPANY_DESC_A + TAG_DESC_FRIEND;

        EditInternApplicationDescriptor descriptor = new EditInternApplicationDescriptorBuilder().withCompany(VALID_COMPANY_A)
                .withRole(VALID_ROLE_B).withCycle(VALID_CYCLE_A).withStatus(VALID_STATUS_A)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + ROLE_DESC_B + CYCLE_DESC_A;

        EditInternApplicationDescriptor descriptor = new EditInternApplicationDescriptorBuilder().withRole(VALID_ROLE_B)
                .withCycle(VALID_CYCLE_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_APPLICATION;
        String userInput = targetIndex.getOneBased() + COMPANY_DESC_A;
        EditInternApplicationDescriptor descriptor = new EditInternApplicationDescriptorBuilder().withCompany(VALID_COMPANY_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // role
        userInput = targetIndex.getOneBased() + ROLE_DESC_A;
        descriptor = new EditInternApplicationDescriptorBuilder().withRole(VALID_ROLE_A).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // cycle
        userInput = targetIndex.getOneBased() + CYCLE_DESC_A;
        descriptor = new EditInternApplicationDescriptorBuilder().withCycle(VALID_CYCLE_A).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // status
        userInput = targetIndex.getOneBased() + STATUS_DESC_A;
        descriptor = new EditInternApplicationDescriptorBuilder().withStatus(VALID_STATUS_A).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditInternApplicationDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + INVALID_ROLE_DESC + ROLE_DESC_B;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + ROLE_DESC_B + INVALID_ROLE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // mulltiple valid fields repeated
        userInput = targetIndex.getOneBased() + ROLE_DESC_A + STATUS_DESC_A + CYCLE_DESC_A
                + TAG_DESC_FRIEND + ROLE_DESC_A + STATUS_DESC_A + CYCLE_DESC_A + TAG_DESC_FRIEND
                + ROLE_DESC_B + STATUS_DESC_B + CYCLE_DESC_B + TAG_DESC_HUSBAND;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE, PREFIX_CYCLE, PREFIX_STATUS));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_ROLE_DESC + INVALID_STATUS_DESC + INVALID_CYCLE_DESC
                + INVALID_ROLE_DESC + INVALID_STATUS_DESC + INVALID_CYCLE_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE, PREFIX_CYCLE, PREFIX_STATUS));
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_APPLICATION;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditInternApplicationDescriptor descriptor = new EditInternApplicationDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
