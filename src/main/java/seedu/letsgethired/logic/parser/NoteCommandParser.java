package seedu.letsgethired.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_NOTE_DELETE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_NOTE_INSERT;

import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.logic.commands.NoteCommand;
import seedu.letsgethired.logic.commands.NoteDeleteCommand;
import seedu.letsgethired.logic.commands.NoteInsertCommand;
import seedu.letsgethired.logic.parser.exceptions.ParseException;
import seedu.letsgethired.model.application.Note;

/**
 * Parses input arguments and creates a new {@code NoteCommand} object
 */
public class NoteCommandParser implements Parser<NoteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code NoteCommand}
     * and returns a {@code NoteCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NOTE_INSERT, PREFIX_NOTE_DELETE);

        Index index;

        try {
            index = ParserUtil.parseApplicationIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE), pe);
        }

        String maybeInsert = argMultimap.getValue(PREFIX_NOTE_INSERT).orElse(null);
        String maybeDelete = argMultimap.getValue(PREFIX_NOTE_DELETE).orElse(null);

        if (maybeInsert != null && maybeDelete != null) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.DUAL_NOTE_ERROR));
        } else if (maybeInsert == null && maybeDelete == null) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Note.MESSAGE_CONSTRAINTS));
        } else if (maybeInsert != null) {
            Note note = ParserUtil.parseNote(maybeInsert);
            return new NoteInsertCommand(index, note);
        } else {
            Integer pos = ParserUtil.parseNoteIndex(maybeDelete);
            return new NoteDeleteCommand(index, pos);
        }


    }
}
