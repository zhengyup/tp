package seedu.letsgethired.logic.parser;

import static seedu.letsgethired.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.letsgethired.logic.parser.CliSyntax.*;

import java.util.stream.Stream;

import seedu.letsgethired.logic.commands.AddCommand;
import seedu.letsgethired.logic.parser.exceptions.ParseException;
import seedu.letsgethired.model.application.*;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY, PREFIX_ROLE, PREFIX_CYCLE, PREFIX_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_COMPANY, PREFIX_STATUS, PREFIX_ROLE, PREFIX_CYCLE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMPANY, PREFIX_ROLE, PREFIX_CYCLE, PREFIX_STATUS);
        Company company = ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get());
        Role role = ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get());
        Cycle cycle = ParserUtil.parseCycle(argMultimap.getValue(PREFIX_CYCLE).get());
        Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        Note note = ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).get());

        InternApplication internApplication = new InternApplication(company, role, cycle, note, status);

        return new AddCommand(internApplication);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
