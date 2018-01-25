package cz.sio2.bib2rdf.bibtex.validators;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.sio2.bib2rdf.bibtex.Utils;
import cz.sio2.bib2rdf.bibtex.ValidationException;
import cz.sio2.bib2rdf.generated.Vocabulary;
import cz.sio2.bib2rdf.generated.model.Unpublished;
import java.util.ArrayList;
import java.util.List;

public class UnpublishedValidator {

    public static void validate(final EntityManager em, final String id)
        throws ValidationException {
        final Unpublished a = em.find(Unpublished.class, id);

        final List<String> warnings = new ArrayList<>();

        Utils.checkNonEmpty(Vocabulary.s_p_hasNote, a.getHasNote(), warnings);
        Utils.checkNonEmpty(Vocabulary.s_p_hasAuthor, a.getHasAuthor(), warnings);
        Utils.checkNonEmpty(Vocabulary.s_p_hasTitle, a.getHasTitle(), warnings);

        if (!warnings.isEmpty()) {
            throw new ValidationException(a.getId(), warnings);
        }
    }
}
