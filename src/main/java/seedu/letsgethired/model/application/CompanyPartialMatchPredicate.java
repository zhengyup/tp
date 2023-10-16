package seedu.letsgethired.model.application;

import java.util.function.Predicate;

import seedu.letsgethired.commons.util.ToStringBuilder;

/**
 * Tests that a {@code InternApplication}'s {@code Company} matches any of the keywords given.
 */
public class CompanyPartialMatchPredicate implements Predicate<InternApplication> {
    private final String searchString;

    public CompanyPartialMatchPredicate(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public boolean test(InternApplication internApplication) {
        // Matches partially if the company name contains the search string
        // E.g. "ggle" matches "Google"
        String company = internApplication.getCompany().value;
        int companyIndex = 0;
        int searchIndex = 0;
        while (companyIndex < company.length() && searchIndex < searchString.length()) {
            if (Character.toLowerCase(company.charAt(companyIndex))
                    == Character.toLowerCase(searchString.charAt(searchIndex))) {
                searchIndex++;
            }
            companyIndex++;
        }
        return searchIndex == searchString.length();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompanyPartialMatchPredicate)) {
            return false;
        }

        CompanyPartialMatchPredicate otherCompanyPartialMatchPredicate =
                (CompanyPartialMatchPredicate) other;
        return searchString.equals(otherCompanyPartialMatchPredicate.searchString);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("searchString", searchString).toString();
    }
}
