package seedu.intern.logic.parser;

import static seedu.intern.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intern.logic.commands.CommandTestUtil.CYCLE_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.CYCLE_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_CYCLE_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.intern.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.intern.logic.commands.CommandTestUtil.ROLE_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.ROLE_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.STATUS_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.intern.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_CYCLE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.intern.testutil.TypicalInternApplications.AMY;
import static seedu.intern.testutil.TypicalInternApplications.BOB;

import org.junit.jupiter.api.Test;

import seedu.intern.logic.Messages;
import seedu.intern.logic.commands.AddCommand;
import seedu.intern.model.application.Company;
import seedu.intern.model.application.Cycle;
import seedu.intern.model.application.InternApplication;
import seedu.intern.model.application.Role;
import seedu.intern.model.application.Status;
import seedu.intern.model.tag.Tag;
import seedu.intern.testutil.InternApplicationBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        InternApplication expectedInternApplication = new InternApplicationBuilder(BOB).withTags(VALID_TAG_FRIEND)
                .build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + ROLE_DESC_BOB + CYCLE_DESC_BOB
                + STATUS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedInternApplication));


        // multiple tags - all accepted
        InternApplication expectedInternApplicationMultipleTags = new InternApplicationBuilder(BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser,
                NAME_DESC_BOB + ROLE_DESC_BOB + CYCLE_DESC_BOB + STATUS_DESC_BOB + TAG_DESC_HUSBAND
                        + TAG_DESC_FRIEND,
                new AddCommand(expectedInternApplicationMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + ROLE_DESC_BOB + CYCLE_DESC_BOB
                + STATUS_DESC_BOB + TAG_DESC_FRIEND;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY));

        // multiple role
        assertParseFailure(parser, ROLE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // multiple cycles
        assertParseFailure(parser, CYCLE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_CYCLE));

        // multiple statuses
        assertParseFailure(parser, STATUS_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + ROLE_DESC_AMY + CYCLE_DESC_AMY + NAME_DESC_AMY + STATUS_DESC_AMY
                        + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY, PREFIX_CYCLE, PREFIX_STATUS, PREFIX_ROLE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY));

        // invalid cycle
        assertParseFailure(parser, INVALID_CYCLE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_CYCLE));

        // invalid role
        assertParseFailure(parser, INVALID_ROLE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // invalid status
        assertParseFailure(parser, INVALID_STATUS_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY));

        // invalid cycle
        assertParseFailure(parser, validExpectedPersonString + INVALID_CYCLE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_CYCLE));

        // invalid role
        assertParseFailure(parser, validExpectedPersonString + INVALID_ROLE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // invalid status
        assertParseFailure(parser, validExpectedPersonString + INVALID_STATUS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        InternApplication expectedInternApplication = new InternApplicationBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + ROLE_DESC_AMY + CYCLE_DESC_AMY + STATUS_DESC_AMY,
                new AddCommand(expectedInternApplication));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing role prefix
        assertParseFailure(parser, VALID_COMPANY_BOB + ROLE_DESC_BOB + CYCLE_DESC_BOB + STATUS_DESC_BOB,
                expectedMessage);

        // missing role prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_ROLE_BOB + CYCLE_DESC_BOB + STATUS_DESC_BOB,
                expectedMessage);

        // missing cycle prefix
        assertParseFailure(parser, NAME_DESC_BOB + ROLE_DESC_BOB + VALID_CYCLE_BOB + STATUS_DESC_BOB,
                expectedMessage);

        // missing status prefix
        assertParseFailure(parser, NAME_DESC_BOB + ROLE_DESC_BOB + CYCLE_DESC_BOB + VALID_STATUS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_COMPANY_BOB + VALID_ROLE_BOB + VALID_CYCLE_BOB + VALID_STATUS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid company
        assertParseFailure(parser, INVALID_NAME_DESC + ROLE_DESC_BOB + CYCLE_DESC_BOB + STATUS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Company.MESSAGE_CONSTRAINTS);

        // invalid role
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_ROLE_DESC + CYCLE_DESC_BOB + STATUS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Role.MESSAGE_CONSTRAINTS);

        // invalid cycle
        assertParseFailure(parser, NAME_DESC_BOB + ROLE_DESC_BOB + INVALID_CYCLE_DESC + STATUS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Cycle.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, NAME_DESC_BOB + ROLE_DESC_BOB + CYCLE_DESC_BOB + INVALID_STATUS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Status.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + ROLE_DESC_BOB + CYCLE_DESC_BOB + STATUS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + ROLE_DESC_BOB + CYCLE_DESC_BOB + INVALID_STATUS_DESC,
                Company.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + ROLE_DESC_BOB + CYCLE_DESC_BOB
                        + STATUS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
