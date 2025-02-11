package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.intern.commons.util.ToStringBuilder;
import seedu.intern.logic.Messages;
import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.Model;
import seedu.intern.model.application.InternApplication;

/**
 * Adds a person to the intern tracker.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a application to the intern tracker. "
            + "Parameters: "
            + PREFIX_COMPANY + "COMPANY "
            + PREFIX_ROLE + "ROLE "
            + PREFIX_CYCLE + "CYCLE "
            + PREFIX_STATUS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY + "Jane Street "
            + PREFIX_ROLE + "98765432 "
            + PREFIX_CYCLE + "Summer 2024"
            + PREFIX_STATUS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This application already exists in the intern tracker";

    private final InternApplication toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(InternApplication internApplication) {
        requireNonNull(internApplication);
        toAdd = internApplication;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
