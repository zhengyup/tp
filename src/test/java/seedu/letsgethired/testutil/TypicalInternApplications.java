package seedu.letsgethired.testutil;

import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_COMPANY_A;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_COMPANY_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_CYCLE_A;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_CYCLE_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_A;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_STATUS_A;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_STATUS_B;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.model.application.InternApplication;

/**
 * A utility class containing a list of {@code InternApplication} objects to be used in tests.
 */
public class TypicalInternApplications {

    public static final InternApplication JANE_STREET = new InternApplicationBuilder().withCompany("Jane Street")
            .withStatus("Pending").withCycle("Summer 2024")
            .withRole("SWE Intern").build();
    public static final InternApplication OPTIVER = new InternApplicationBuilder().withCompany("Optiver")
            .withStatus("Pending")
            .withCycle("Summer 2023").withRole("Data Engineering Intern").build();
    public static final InternApplication GOOGLE = new InternApplicationBuilder().withCompany("Google")
            .withRole("Full Stack Intern")
            .withCycle("Summer 2021").withStatus("Pending").build();
    public static final InternApplication META = new InternApplicationBuilder().withCompany("Meta")
            .withRole("Back End Intern")
            .withCycle("Off-cycle 2020").withStatus("Pending").build();
    public static final InternApplication BYTEDANCE = new InternApplicationBuilder().withCompany("Bytedance")
            .withRole("Front End Intern")
            .withCycle("Summer 2021").withStatus("Pending").build();
    public static final InternApplication GRAB = new InternApplicationBuilder().withCompany("Grab")
            .withRole("Web Dev Intern")
            .withCycle("Summer 2021").withStatus("Pending").build();
    public static final InternApplication STRIPE = new InternApplicationBuilder().withCompany("Stripe")
            .withRole("DevOps Intern")
            .withCycle("Summer 2021").withStatus("Pending").build();

    // Manually added
    public static final InternApplication HOON = new InternApplicationBuilder().withCompany("Hoon Meier")
            .withRole("8482424")
            .withCycle("Summer 2021").withStatus("little india").build();
    public static final InternApplication IDA = new InternApplicationBuilder().withCompany("Ida Mueller")
            .withRole("8482131")
            .withCycle("Summer 2021").withStatus("chicago ave").build();

    // Manually added - Intern Application's details found in {@code CommandTestUtil}
    public static final InternApplication A = new InternApplicationBuilder().withCompany(VALID_COMPANY_A)
            .withRole(VALID_ROLE_A)
            .withCycle(VALID_CYCLE_A).withStatus(VALID_STATUS_A).withTags(VALID_TAG_FRIEND).build();
    public static final InternApplication B = new InternApplicationBuilder().withCompany(VALID_COMPANY_B)
            .withRole(VALID_ROLE_B)
            .withCycle(VALID_CYCLE_B).withStatus(VALID_STATUS_B).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalInternApplications() {
    } // prevents instantiation

    /**
     * Returns an {@code InternTracker} with all the typical intern applications.
     */
    public static InternTracker getTypicalInternTracker() {
        InternTracker ab = new InternTracker();
        for (InternApplication internApplication : getTypicalInternApplications()) {
            ab.addApplication(internApplication);
        }
        return ab;
    }

    public static List<InternApplication> getTypicalInternApplications() {
        return new ArrayList<>(Arrays.asList(JANE_STREET, OPTIVER, GOOGLE, META, BYTEDANCE, GRAB, STRIPE));
    }
}
