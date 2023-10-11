package seedu.intern.testutil;

import static seedu.intern.logic.commands.CommandTestUtil.VALID_CYCLE_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_CYCLE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.intern.model.InternTracker;
import seedu.intern.model.application.InternApplication;

/**
 * A utility class containing a list of {@code InternApplication} objects to be used in tests.
 */
public class TypicalInternApplications {

    public static final InternApplication ALICE = new InternApplicationBuilder().withCompany("Alice Pauline")
            .withStatus("Pending").withCycle("Summer 2024")
            .withRole("SWE Intern")
            .withTags("friends").build();
    public static final InternApplication BENSON = new InternApplicationBuilder().withCompany("Benson Meier")
            .withStatus("Pending")
            .withCycle("Summer 2023").withRole("Data Engineering Intern")
            .withTags("owesMoney", "friends").build();
    public static final InternApplication CARL = new InternApplicationBuilder().withCompany("Google")
            .withRole("Full Stack Intern")
            .withCycle("Summer 2021").withStatus("Pending").build();
    public static final InternApplication DANIEL = new InternApplicationBuilder().withCompany("Daniel Meier")
            .withRole("Back End Intern")
            .withCycle("Off-cycle 2020").withStatus("Pending").withTags("friends").build();
    public static final InternApplication ELLE = new InternApplicationBuilder().withCompany("Elle Meyer")
            .withRole("Front End Intern")
            .withCycle("Summer 2021").withStatus("Pending").build();
    public static final InternApplication FIONA = new InternApplicationBuilder().withCompany("Fiona Kunz")
            .withRole("Web Dev Intern")
            .withCycle("Summer 2021").withStatus("Pending").build();
    public static final InternApplication GEORGE = new InternApplicationBuilder().withCompany("George Best")
            .withRole("DevOps Intern")
            .withCycle("Summer 2021").withStatus("Pending").build();

    // Manually added
    public static final InternApplication HOON = new InternApplicationBuilder().withCompany("Hoon Meier")
            .withRole("8482424")
            .withCycle("Summer 2021").withStatus("little india").build();
    public static final InternApplication IDA = new InternApplicationBuilder().withCompany("Ida Mueller")
            .withRole("8482131")
            .withCycle("Summer 2021").withStatus("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final InternApplication AMY = new InternApplicationBuilder().withCompany(VALID_NAME_AMY)
            .withRole(VALID_ROLE_AMY)
            .withCycle(VALID_CYCLE_AMY).withStatus(VALID_STATUS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final InternApplication BOB = new InternApplicationBuilder().withCompany(VALID_NAME_BOB)
            .withRole(VALID_ROLE_BOB)
            .withCycle(VALID_CYCLE_BOB).withStatus(VALID_STATUS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalInternApplications() {
    } // prevents instantiation

    /**
     * Returns an {@code InternTracker} with all the typical persons.
     */
    public static InternTracker getTypicalInternTracker() {
        InternTracker ab = new InternTracker();
        for (InternApplication internApplication : getTypicalInternApplications()) {
            ab.addApplication(internApplication);
        }
        return ab;
    }

    public static List<InternApplication> getTypicalInternApplications() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
