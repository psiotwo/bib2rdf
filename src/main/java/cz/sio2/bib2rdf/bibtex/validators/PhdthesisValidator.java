package cz.sio2.bib2rdf.bibtex.validators;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.sio2.bib2rdf.bibtex.ValidationException;
import cz.sio2.bib2rdf.generated.Vocabulary;
import cz.sio2.bib2rdf.generated.model.Phdthesis;
import java.util.ArrayList;
import java.util.List;

public class PhdthesisValidator {

    public static void validate(final EntityManager em, final String id)
        throws ValidationException {
        final Phdthesis a = em.find(Phdthesis.class, id);

        final List<String> warnings = new ArrayList<>();

        ValidationUtils.checkNonEmpty(Vocabulary.s_p_hasSchool, a.getHasSchool(), warnings);
        ValidationUtils.checkNonEmpty(Vocabulary.s_p_hasAuthor, a.getHasAuthor(), warnings);
        ValidationUtils.checkNonEmpty(Vocabulary.s_p_hasTitle, a.getHasTitle(), warnings);
        ValidationUtils.checkNonEmpty(Vocabulary.s_p_hasYear, a.getHasYear(), warnings);

        if (!warnings.isEmpty()) {
            throw new ValidationException(a.getId(),warnings);
        }
    }
}
