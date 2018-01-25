package cz.sio2.bib2rdf.bibtex.validators;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.sio2.bib2rdf.bibtex.ValidationException;
import cz.sio2.bib2rdf.generated.Vocabulary;
import cz.sio2.bib2rdf.generated.model.Article;
import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static void validate(final EntityManager em, final String id)
        throws ValidationException {
        final Article a = em.find(Article.class, id);

        final List<String> warnings = new ArrayList<>();

        ValidationUtils.checkNonEmpty(Vocabulary.s_p_hasAuthor, a.getHasAuthor(), warnings);
        ValidationUtils.checkNonEmpty(Vocabulary.s_p_hasJournal, a.getHasJournal(), warnings);
        ValidationUtils.checkNonEmpty(Vocabulary.s_p_hasTitle, a.getHasTitle(), warnings);
        ValidationUtils.checkNonEmpty(Vocabulary.s_p_hasYear, a.getHasYear(), warnings);

        if (!warnings.isEmpty()) {
            throw new ValidationException(a.getId(),warnings);
        }
    }
}
