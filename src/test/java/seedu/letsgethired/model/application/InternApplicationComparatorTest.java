package seedu.letsgethired.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.SortOrder.ASCENDING;
import static seedu.letsgethired.logic.parser.SortOrder.DESCENDING;
import static seedu.letsgethired.model.application.InternApplicationComparator.createCompositeComparator;
import static seedu.letsgethired.model.application.InternApplicationComparator.getComparator;

import java.util.List;

import org.junit.jupiter.api.Test;

public class InternApplicationComparatorTest {
    @Test
    public void equals() {
        InternApplicationComparator companyAscending = getComparator(PREFIX_COMPANY, ASCENDING);
        InternApplicationComparator companyDescending = getComparator(PREFIX_COMPANY, DESCENDING);
        InternApplicationComparator cycleAscending = getComparator(PREFIX_CYCLE, ASCENDING);
        assertNotNull(companyAscending);
        assertNotNull(companyDescending);
        assertNotNull(cycleAscending);

        // Same prefix and order -> returns true
        assertEquals(companyAscending, getComparator(PREFIX_COMPANY, ASCENDING));

        // Same prefix different order -> returns false
        assertNotEquals(companyAscending, companyDescending);

        // Different prefix same order -> returns false
        assertNotEquals(companyAscending, cycleAscending);

        // Composite comparator in same order -> returns true
        InternApplicationComparator firstComposite = createCompositeComparator(
                List.of(companyAscending, cycleAscending));
        InternApplicationComparator firstCompositeSame = createCompositeComparator(
                List.of(companyAscending, cycleAscending));
        assertEquals(firstComposite, firstCompositeSame);

        InternApplicationComparator secondComposite = createCompositeComparator(
                List.of(companyAscending));
        // Different lengths -> returns false
        assertNotEquals(firstComposite, secondComposite);

        InternApplicationComparator thirdComposite = createCompositeComparator(
                List.of(cycleAscending, companyAscending));
        // Different order -> returns false
        assertNotEquals(firstComposite, thirdComposite);
    }
}
