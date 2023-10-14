package seedu.letsgethired.model.application;

import java.util.List;
import java.util.function.Predicate;

import seedu.letsgethired.commons.util.StringUtil;
import seedu.letsgethired.commons.util.ToStringBuilder;

/**
 * Tests that a {@code InternApplication}'s {@code Name} matches any of the keywords given.
 */
public class CompanyContainsKeywordsPredicate implements Predicate<InternApplication> {
    private final List<String> keywords;

    public CompanyContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(InternApplication internApplication) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil
                        .containsWordIgnoreCase(internApplication.getCompany().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompanyContainsKeywordsPredicate)) {
            return false;
        }

        CompanyContainsKeywordsPredicate otherCompanyContainsKeywordsPredicate =
                (CompanyContainsKeywordsPredicate) other;
        return keywords.equals(otherCompanyContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
