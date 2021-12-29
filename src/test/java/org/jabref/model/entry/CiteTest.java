package org.jabref.model.entry;

import org.jabref.model.entry.field.StandardField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CiteTest {
        private BibEntry entry1 = new BibEntry().withField(StandardField.CITE,"title1");
        private BibEntry entry2 = new BibEntry().withField(StandardField.CITE,"title1");

    @Test
    void citeConsideredEqualIfSame() {
        assertEquals(StandardField.CITE, StandardField.CITE);
    }
    @Test
    void differentEntries() {
        assertEquals(entry1, entry2);
    }
    @Test
    void compareToNullEntry() throws Exception {
        assertFalse(entry1.equals(null));
    }

    @Test
    void sameCiteEntries() {
        String citeEntry1 = entry1.getFieldValues().iterator().next();
        String citeEntry2 = entry2.getFieldValues().iterator().next();
        assertEquals(citeEntry1, citeEntry2);
    }
}
