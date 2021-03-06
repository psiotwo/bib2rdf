package cz.sio2.bib2rdf;

import cz.cvut.kbss.jopa.Persistence;
import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.model.EntityManagerFactory;
import cz.cvut.kbss.jopa.model.JOPAPersistenceProperties;
import cz.cvut.kbss.jopa.model.JOPAPersistenceProvider;
import cz.cvut.kbss.ontodriver.config.OntoDriverProperties;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersistenceFactory {

    private static final Logger LOG = LoggerFactory.getLogger(PersistenceFactory.class);

    private static final String REPOSITORY_FILE_NAME = "repository.owl";
    private static final String FILE_SCHEMA = "file://";

    private static boolean initialized = false;

    private static EntityManagerFactory emf;

    private PersistenceFactory() {
        throw new AssertionError();
    }

    public static void init(String ontologyFile, Map<String, String> propsToOverride) {
        final Map<String, String> props = new HashMap<>();
        // Here we set up basic storage access properties - driver class, physical location of
        // the storage
        props.put(JOPAPersistenceProperties.ONTOLOGY_PHYSICAL_URI_KEY,
            setupRepository(ontologyFile));
        props.put(JOPAPersistenceProperties.DATA_SOURCE_CLASS,
            "cz.cvut.kbss.ontodriver.owlapi.OwlapiDataSource");
        // View transactional changes during transaction
        props.put(OntoDriverProperties.USE_TRANSACTIONAL_ONTOLOGY, Boolean.TRUE.toString());
        // Ontology language
        props.put(JOPAPersistenceProperties.LANG, "en");
        // Where to look for entity classes
        props.put(OntoDriverProperties.OWLAPI_REASONER_FACTORY_CLASS,
            StructuralReasonerFactory.class.getName());
        // Persistence provider name
        props.put(JOPAPersistenceProperties.JPA_PERSISTENCE_PROVIDER,
            JOPAPersistenceProvider.class.getName());
        props.put(JOPAPersistenceProperties.DISABLE_IC_VALIDATION_ON_LOAD, Boolean.TRUE + "");

        props.putAll(propsToOverride);
        // Use Pellet for reasoning
        emf = Persistence.createEntityManagerFactory("BibtexPU", props);
        initialized = true;
    }

    private static String setupRepository(String ontologyFile) {
        LOG.debug("Setting up repository...");
        final String ontologyFileAbsolute = resolveAbsolutePath(ontologyFile);
        final String repoFolder =
            ontologyFileAbsolute.substring(0, ontologyFileAbsolute.lastIndexOf(File.separatorChar));
        final File repoFile = new File(repoFolder + File.separator + REPOSITORY_FILE_NAME);
        if (repoFile.exists()) {
            LOG.debug("Repository already exists. Removing it...");
            final boolean res = repoFile.delete();
            assert res;
        }
        try {
            LOG.debug("Copying ontology to the repository...");
            Files.copy(new File(ontologyFileAbsolute).toPath(), repoFile.toPath());
        } catch (IOException e) {
            LOG.error("Unable to copy ontology into the repository", e);
            System.exit(1);
        }
        return URI.create(FILE_SCHEMA + repoFile.getAbsolutePath()).toString();
    }

    private static String resolveAbsolutePath(String ontologyFile) {
        final File file = new File(ontologyFile);
        assert file.exists();
        return file.getAbsolutePath();
    }

    public static EntityManager createEntityManager() {
        if (!initialized) {
            throw new IllegalStateException("PersistenceFactory has not been initialized.");
        }
        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();
    }
}