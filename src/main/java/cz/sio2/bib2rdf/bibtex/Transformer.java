package cz.sio2.bib2rdf.bibtex;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.model.JOPAPersistenceProperties;
import cz.sio2.bib2rdf.PersistenceFactory;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.BibTeXObject;
import org.jbibtex.ParseException;

public class Transformer {

    public void transform(final Reader reader, final Writer w) {
        org.jbibtex.BibTeXParser bibtexParser;
        org.jbibtex.BibTeXDatabase database = null;
        try {
            bibtexParser = new org.jbibtex.BibTeXParser();
            database = bibtexParser.parse(reader);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Map<String, String> props = new HashMap<>();
        props.put(JOPAPersistenceProperties.ONTOLOGY_URI_KEY,
            "http://purl.org/net/nknouf/ns/bibtex");
        props.put(JOPAPersistenceProperties.SCAN_PACKAGE, "cz.sio2.bib2rdf.generated.model");

        PersistenceFactory.init(getClass().getResource("/bibtex.rdf").getFile(), props);
        EntityManager em = PersistenceFactory.createEntityManager();

        database.getObjects().forEach((o) -> {
            transformBibtexObject(em, o);
        });
        em.close();
    }

    private void transformBibtexObject(final EntityManager em, final BibTeXObject o) {
        if (o instanceof BibTeXEntry) {
            BibTeXEntryTransformer.parse(em, (BibTeXEntry) o);
        }
    }
}
