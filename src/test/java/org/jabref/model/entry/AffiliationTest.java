package org.jabref.model.entry;

import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.StandardField;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class AffiliationTest {
    private BibEntry entry1 = new BibEntry()
            .withField(StandardField.AFFILIATION, "FCT");

    private BibEntry entry2 = new BibEntry()
            .withField(StandardField.AFFILIATION, "FCT");

    @Test
    void affiliationConsideredEqualIfSame() {
        assertEquals(StandardField.AFFILIATION, StandardField.AFFILIATION);
    }

    @Test
    void SameAffiliationEntries() {
        String affiliationOfEntry1 = entry1.getFieldValues().iterator().next();
        String affiliationOfEntry2 = entry2.getFieldValues().iterator().next();
        assertEquals(affiliationOfEntry1, affiliationOfEntry2);
    }

    @Test
    void changeInValueOfAffiliationEntries() {
        entry1.setField(StandardField.AFFILIATION, "Tecnico");
        String affiliationOfEntry1 = entry1.getField(StandardField.AFFILIATION).orElse("");
        String affiliationOfEntry2 = entry2.getField(StandardField.AFFILIATION).orElse("");
        assertNotEquals(affiliationOfEntry1, affiliationOfEntry2);
    }

    @Test
    void differentEntries() {
        assertEquals(entry1, entry2);
    }



}
