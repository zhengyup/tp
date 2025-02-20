package seedu.intern.testutil;

import static seedu.intern.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.intern.logic.commands.AddCommand;
import seedu.intern.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.intern.model.application.InternApplication;
import seedu.intern.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(InternApplication internApplication) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(internApplication);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(InternApplication internApplication) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_COMPANY + internApplication.getCompany().companyName + " ");
        sb.append(PREFIX_ROLE + internApplication.getRole().value + " ");
        sb.append(PREFIX_CYCLE + internApplication.getCycle().value + " ");
        sb.append(PREFIX_STATUS + internApplication.getStatus().value + " ");
        internApplication.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getCompany().ifPresent(name -> sb.append(PREFIX_COMPANY).append(name.companyName).append(" "));
        descriptor.getRole().ifPresent(role -> sb.append(PREFIX_ROLE).append(role.value).append(" "));
        descriptor.getCycle().ifPresent(cycle -> sb.append(PREFIX_CYCLE).append(cycle.value).append(" "));
        descriptor.getStatus().ifPresent(status -> sb.append(PREFIX_STATUS).append(status.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
