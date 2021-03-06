package cz.sio2.bib2rdf.bibtex.validators;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.sio2.bib2rdf.bibtex.Utils;
import cz.sio2.bib2rdf.bibtex.ValidationException;
import cz.sio2.bib2rdf.generated.Vocabulary;
import cz.sio2.bib2rdf.generated.model.Mastersthesis;
import java.util.ArrayList;
import java.util.List;

public class MastersthesisValidator {

    public static void validate(final EntityManager em, final String id)
        throws ValidationException {
        final Mastersthesis a = em.find(Mastersthesis.class, id);

        final List<String> warnings = new ArrayList<>();

        Utils.checkNonEmpty(Vocabulary.s_p_hasTitle, a.getHasTitle(), warnings);
        Utils.checkNonEmpty(Vocabulary.s_p_hasAuthor, a.getHasAuthor(), warnings);
        Utils.checkNonEmpty(Vocabulary.s_p_hasSchool, a.getHasSchool(), warnings);
        Utils.checkNonEmpty(Vocabulary.s_p_hasYear, a.getHasYear(), warnings);

        if (!warnings.isEmpty()) {
            throw new ValidationException(a.getId(), warnings);
        }
    }
}
