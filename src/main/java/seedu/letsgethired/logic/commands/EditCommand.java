package seedu.letsgethired.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.letsgethired.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.commons.util.CollectionUtil;
import seedu.letsgethired.commons.util.ToStringBuilder;
import seedu.letsgethired.logic.Messages;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.application.Company;
import seedu.letsgethired.model.application.Cycle;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.Role;
import seedu.letsgethired.model.application.Status;
import seedu.letsgethired.model.tag.Tag;

/**
 * Edits the details of an existing intern application in the interntracker.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the intern application "
            + "identified by the index number used in the displayed intern application list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_COMPANY + "COMPANY] "
            + "[" + PREFIX_ROLE + "ROLE] "
            + "[" + PREFIX_CYCLE + "CYCLE] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ROLE + "91234567 "
            + PREFIX_CYCLE + "Summer 2024";

    public static final String MESSAGE_EDIT_INTERN_APPLICATION_SUCCESS = "Edited Intern Application: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_INTERN_APPLICATION =
            "This intern application already exists in the intern tracker.";

    private final Index index;
    private final EditInternApplicationDescriptor editInternApplicationDescriptor;

    /**
     * @param index of the intern application in the filtered intern application list to edit
     * @param editInternApplicationDescriptor details to edit the intern application with
     */
    public EditCommand(Index index, EditInternApplicationDescriptor editInternApplicationDescriptor) {
        requireNonNull(index);
        requireNonNull(editInternApplicationDescriptor);

        this.index = index;
        this.editInternApplicationDescriptor = new EditInternApplicationDescriptor(editInternApplicationDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternApplication> lastShownList = model.getFilteredInternApplicationList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERN_APPLICATION_DISPLAYED_INDEX);
        }

        InternApplication internApplicationToEdit = lastShownList.get(index.getZeroBased());
        InternApplication editedInternApplication =
                createEditedInternApplication(internApplicationToEdit, editInternApplicationDescriptor);

        if (!internApplicationToEdit.isSameApplication(editedInternApplication)
                && model.hasInternApplication(editedInternApplication)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERN_APPLICATION);
        }

        model.setInternApplication(internApplicationToEdit, editedInternApplication);
        model.updateFilteredInternApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        return new CommandResult(String.format(MESSAGE_EDIT_INTERN_APPLICATION_SUCCESS,
                                               Messages.format(editedInternApplication)));
    }

    /**
     * Creates and returns a {@code InternApplication} with the details of {@code internApplicationToEdit}
     * edited with {@code editInternApplicationDescriptor}.
     */
    private static InternApplication createEditedInternApplication(
            InternApplication internApplicationToEdit,
            EditInternApplicationDescriptor editInternApplicationDescriptor) {
        assert internApplicationToEdit != null;

        Company updatedCompany = editInternApplicationDescriptor.getCompany()
                .orElse(internApplicationToEdit.getCompany());
        Role updatedRole = editInternApplicationDescriptor.getRole()
                .orElse(internApplicationToEdit.getRole());
        Cycle updatedCycle = editInternApplicationDescriptor.getCycle()
                .orElse(internApplicationToEdit.getCycle());
        Status updatedStatus = editInternApplicationDescriptor.getStatus()
                .orElse(internApplicationToEdit.getStatus());
        Set<Tag> updatedTags = editInternApplicationDescriptor.getTags()
                .orElse(internApplicationToEdit.getTags());

        return new InternApplication(updatedCompany, updatedRole, updatedCycle, updatedStatus, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editInternApplicationDescriptor.equals(otherEditCommand.editInternApplicationDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editInternApplicationDescriptor", editInternApplicationDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the intern application with. Each non-empty field value will replace the
     * corresponding field value of the intern application.
     */
    public static class EditInternApplicationDescriptor {
        private Company company;
        private Role role;
        private Cycle cycle;
        private Status status;
        private Set<Tag> tags;

        public EditInternApplicationDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditInternApplicationDescriptor(EditInternApplicationDescriptor toCopy) {
            setCompany(toCopy.company);
            setRole(toCopy.role);
            setCycle(toCopy.cycle);
            setStatus(toCopy.status);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(company, role, cycle, status, tags);
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public Optional<Company> getCompany() {
            return Optional.ofNullable(company);
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public Optional<Role> getRole() {
            return Optional.ofNullable(role);
        }

        public void setCycle(Cycle cycle) {
            this.cycle = cycle;
        }

        public Optional<Cycle> getCycle() {
            return Optional.ofNullable(cycle);
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditInternApplicationDescriptor)) {
                return false;
            }

            EditInternApplicationDescriptor otherEditInternApplicationDescriptor =
                    (EditInternApplicationDescriptor) other;
            return Objects.equals(company, otherEditInternApplicationDescriptor.company)
                    && Objects.equals(role, otherEditInternApplicationDescriptor.role)
                    && Objects.equals(cycle, otherEditInternApplicationDescriptor.cycle)
                    && Objects.equals(status, otherEditInternApplicationDescriptor.status)
                    && Objects.equals(tags, otherEditInternApplicationDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("company", company)
                    .add("role", role)
                    .add("cycle", cycle)
                    .add("status", status)
                    .add("tags", tags)
                    .toString();
        }
    }
}
