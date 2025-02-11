package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.intern.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.intern.commons.core.index.Index;
import seedu.intern.commons.util.CollectionUtil;
import seedu.intern.commons.util.ToStringBuilder;
import seedu.intern.logic.Messages;
import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.Model;
import seedu.intern.model.application.Company;
import seedu.intern.model.application.Cycle;
import seedu.intern.model.application.InternApplication;
import seedu.intern.model.application.Role;
import seedu.intern.model.application.Status;
import seedu.intern.model.tag.Tag;

/**
 * Edits the details of an existing person in the interntracker.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
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

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the intern tracker.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternApplication> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        InternApplication internApplicationToEdit = lastShownList.get(index.getZeroBased());
        InternApplication editedInternApplication = createEditedPerson(internApplicationToEdit, editPersonDescriptor);

        if (!internApplicationToEdit.isSameApplication(editedInternApplication)
                && model.hasPerson(editedInternApplication)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(internApplicationToEdit, editedInternApplication);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedInternApplication)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static InternApplication createEditedPerson(InternApplication internApplicationToEdit,
                                                        EditPersonDescriptor editPersonDescriptor) {
        assert internApplicationToEdit != null;

        Company updatedCompany = editPersonDescriptor.getCompany().orElse(internApplicationToEdit.getCompany());
        Role updatedRole = editPersonDescriptor.getRole().orElse(internApplicationToEdit.getRole());
        Cycle updatedCycle = editPersonDescriptor.getCycle().orElse(internApplicationToEdit.getCycle());
        Status updatedStatus = editPersonDescriptor.getStatus().orElse(internApplicationToEdit.getStatus());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(internApplicationToEdit.getTags());

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
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Company company;
        private Role role;
        private Cycle cycle;
        private Status status;
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
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
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
            return Objects.equals(company, otherEditPersonDescriptor.company)
                    && Objects.equals(role, otherEditPersonDescriptor.role)
                    && Objects.equals(cycle, otherEditPersonDescriptor.cycle)
                    && Objects.equals(status, otherEditPersonDescriptor.status)
                    && Objects.equals(tags, otherEditPersonDescriptor.tags);
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
