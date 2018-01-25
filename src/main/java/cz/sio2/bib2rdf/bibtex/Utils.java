package cz.sio2.bib2rdf.bibtex;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Utils {

    public static void checkNonEmpty(final String attributeId, final String s,
                                     final List<String> validationErrors) {
        if (!Objects.nonNull(s)) {
            validationErrors.add("Attribute " + attributeId + " is null ");
        }
    }

    public static void checkNonEmpty(final String attributeId, final Collection<?> s,
                                     final List<String> validationErrors) {
        if (!Objects.nonNull(s)) {
            validationErrors.add("Attribute " + attributeId + " is null ");
        } else if (s.isEmpty()) {
            validationErrors.add("Attribute " + attributeId + " is empty ");
        }
    }
}
