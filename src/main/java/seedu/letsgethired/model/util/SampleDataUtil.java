package seedu.letsgethired.model.util;

import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.model.ReadOnlyInternTracker;
import seedu.letsgethired.model.application.Company;
import seedu.letsgethired.model.application.Cycle;
import seedu.letsgethired.model.application.Deadline;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.Note;
import seedu.letsgethired.model.application.Role;
import seedu.letsgethired.model.application.Status;

/**
 * Contains utility methods for populating {@code InternTracker} with sample data.
 */
public class SampleDataUtil {

    public static InternApplication[] getSampleInternApplications() {
        return new InternApplication[] {
            new InternApplication(
                    new Company("Apple"),
                    new Role("DevOps"),
                    new Cycle("Summer 2024"),
                    new Note("Need to revise Rust before the Apple interview"),
                    new Status("Pending"),
                    new Deadline("24 Oct 2023")),
            new InternApplication(
                    new Company("Bytedance"),
                    new Role("Systems Integrator"),
                    new Cycle("Summer 2023"),
                    new Note("Bytedance requires back end developers to know the MERN stack"),
                    new Status("Rejected"),
                    new Deadline("24 Oct 2023")),
            new InternApplication(
                    new Company("Grab"),
                    new Role("Data intern"),
                    new Cycle("Summer 2023"),
                    new Note("Grab pays their data interns 2000 SGD per month"),
                    new Status("Pending"),
                    new Deadline("24 Oct 2023")),
            new InternApplication(
                    new Company("Shoppee"),
                    new Role("Full Stack Developer"),
                    new Cycle("Off-cycle 2024"),
                    new Note("Shoppee online assessment has a difficult mental math section"),
                    new Status("Rejected"),
                    new Deadline("24 Oct 2023")),
            new InternApplication(
                    new Company("Stripe"),
                    new Role("Cybersecurity Intern"),
                    new Cycle("Winter 2024"),
                    new Note("Stripe requires previous experience with SQL"),
                    new Status("Pending"),
                    new Deadline("24 Oct 2023")),
            new InternApplication(
                    new Company("Jane Street"),
                    new Role("Economist"),
                    new Cycle("Summer 2022"),
                    new Note("Jane Street is the leading market maker in the APAC region"),
                    new Status("Pending"),
                    new Deadline("24 Oct 2023"))};
    }

    public static ReadOnlyInternTracker getSampleInternTracker() {
        InternTracker sampleAb = new InternTracker();
        for (InternApplication sampleInternApplication : getSampleInternApplications()) {
            sampleAb.addApplication(sampleInternApplication);
        }
        return sampleAb;
    }
}
