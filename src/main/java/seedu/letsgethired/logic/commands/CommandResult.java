package seedu.letsgethired.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.letsgethired.commons.util.ToStringBuilder;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    private final String notesToUser;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, String notesToUser) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.notesToUser = notesToUser;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code notesToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, String notesToUser) {
        this(feedbackToUser, false, false, notesToUser);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, "");
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public String getNotesToUser() {
        return notesToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && notesToUser.equals(otherCommandResult.notesToUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showHelp", showHelp)
                .add("exit", exit)
                .add("notesToUser", notesToUser)
                .toString();
    }
}
