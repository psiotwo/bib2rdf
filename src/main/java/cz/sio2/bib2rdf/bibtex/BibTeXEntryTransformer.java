package cz.sio2.bib2rdf.bibtex;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.sio2.bib2rdf.bibtex.validators.ArticleValidator;
import cz.sio2.bib2rdf.bibtex.validators.BookValidator;
import cz.sio2.bib2rdf.bibtex.validators.BookletValidator;
import cz.sio2.bib2rdf.bibtex.validators.ConferenceValidator;
import cz.sio2.bib2rdf.bibtex.validators.InbookValidator;
import cz.sio2.bib2rdf.bibtex.validators.IncollectionValidator;
import cz.sio2.bib2rdf.bibtex.validators.InproceedingsValidator;
import cz.sio2.bib2rdf.bibtex.validators.ManualValidator;
import cz.sio2.bib2rdf.bibtex.validators.MastersthesisValidator;
import cz.sio2.bib2rdf.bibtex.validators.MiscValidator;
import cz.sio2.bib2rdf.bibtex.validators.PhdthesisValidator;
import cz.sio2.bib2rdf.bibtex.validators.ProceedingsValidator;
import cz.sio2.bib2rdf.bibtex.validators.TechReportValidator;
import cz.sio2.bib2rdf.bibtex.validators.UnpublishedValidator;
import cz.sio2.bib2rdf.generated.Vocabulary;
import cz.sio2.bib2rdf.generated.model.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jbibtex.BibTeXEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BibTeXEntryTransformer {

    private static final Logger LOG = LoggerFactory.getLogger(BibTeXEntryTransformer.class);

    /**
     * Computes bibtex prop iri for bibtex attribute
     *
     * @param localName
     */
    private static String p(final String localName) {
        return Vocabulary.ONTOLOGY_IRI_bibtex + "#" + "has" + Character
            .toUpperCase(localName.charAt(0)) + localName.substring(1).toLowerCase();
    }

    /**
     * Computes bibtex class iri for bibtex attribute
     *
     * @param localName
     */
    private static String c(final String localName) {
        return Vocabulary.ONTOLOGY_IRI_bibtex + "#" + Character.toUpperCase(localName.charAt(0))
               + localName.substring(1).toLowerCase();
    }

    public static void parse(final EntityManager em, BibTeXEntry e) {
        final Entry i = new Entry();

        final Map<String, Set<String>> p = new HashMap<>();
        final Set<String> t = new HashSet<>();
        t.add(c(e.getType().getValue()));
        e.getFields().forEach((k, v) -> {
            p.put(p(k.getValue()), Collections.singleton(v.toUserString()));
        });

        p.put(p("key"), Collections.singleton(e.getKey().getValue()));

        //        i.setName(e.getField(BibTeXEntry.KEY_KEY).toUserString());
        i.setProperties(p);
        i.setTypes(t);
        em.getTransaction().begin();
        em.persist(i);
        em.getTransaction().commit();

        List<ValidationException> warnings = new ArrayList<>();

        try {
            validate(em, i.getId(), i.getTypes().iterator().next());
        } catch (ValidationException ex) {
            warnings.add(ex);
            LOG.warn(ex.getMessage());
        }
    }

    private static void validate(final EntityManager em, final String id, final String type)
        throws ValidationException {
        switch (type) {
            case Vocabulary.s_c_Article:
                ArticleValidator.validate(em, id);
                break;
            case Vocabulary.s_c_Book:
                BookValidator.validate(em, id);
                break;
            case Vocabulary.s_c_Booklet:
                BookletValidator.validate(em, id);
                break;
            case Vocabulary.s_c_Conference:
                ConferenceValidator.validate(em, id);
                break;
            case Vocabulary.s_c_Inbook:
                InbookValidator.validate(em, id);
                break;
            case Vocabulary.s_c_Incollection:
                IncollectionValidator.validate(em, id);
                break;
            case Vocabulary.s_c_Inproceedings:
                InproceedingsValidator.validate(em, id);
                break;
            case Vocabulary.s_c_Manual:
                ManualValidator.validate(em, id);
                break;
            case Vocabulary.s_c_Mastersthesis:
                MastersthesisValidator.validate(em, id);
                break;
            case Vocabulary.s_c_Misc:
                MiscValidator.validate(em, id);
                break;
            case Vocabulary.s_c_Phdthesis:
                PhdthesisValidator.validate(em, id);
                break;
            case Vocabulary.s_c_Proceedings:
                ProceedingsValidator.validate(em, id);
                break;
            case Vocabulary.s_c_Techreport:
                TechReportValidator.validate(em, id);
                break;
            case Vocabulary.s_c_Unpublished:
                UnpublishedValidator.validate(em, id);
                break;
            default:
                throw new ValidationException(id,
                    Collections.singletonList("Unknown type " + type));
        }
    }
}
