
package cz.sio2.bib2rdf.generated.model;

import cz.cvut.kbss.jopa.CommonVocabulary;
import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLAnnotationProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraint;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraints;
import cz.cvut.kbss.jopa.model.annotations.Properties;
import cz.cvut.kbss.jopa.model.annotations.Types;
import cz.sio2.bib2rdf.generated.Vocabulary;
import java.util.Map;
import java.util.Set;


/**
 * This class was generated by the OWL2Java tool version $VERSION$
 * 
 */
@OWLClass(iri = Vocabulary.s_c_Book)
public class Book
    extends Entry
{

    @OWLAnnotationProperty(iri = CommonVocabulary.RDFS_LABEL)
    protected String name;
    @OWLAnnotationProperty(iri = CommonVocabulary.DC_DESCRIPTION)
    protected String description;
    @Types
    protected Set<String> types;
    @Id(generated = true)
    protected String id;
    @Properties
    protected Map<String, Set<String>> properties;
    @OWLDataProperty(iri = Vocabulary.s_p_hasPublisher)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", min = 1)
    })
    protected Set<String> hasPublisher;
    @OWLDataProperty(iri = Vocabulary.s_p_hasYear)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#int", min = 1)
    })
    protected Set<Integer> hasYear;
    @OWLDataProperty(iri = Vocabulary.s_p_humanCreator)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#PlainLiteral", min = 1)
    })
    protected Set<String> humanCreator;
    @OWLDataProperty(iri = Vocabulary.s_p_hasTitle)
    @ParticipationConstraints({
        @ParticipationConstraint(owlObjectIRI = "http://www.w3.org/2001/XMLSchema#string", min = 1)
    })
    protected Set<String> hasTitle;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTypes(Set<String> types) {
        this.types = types;
    }

    public Set<String> getTypes() {
        return types;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setProperties(Map<String, Set<String>> properties) {
        this.properties = properties;
    }

    public Map<String, Set<String>> getProperties() {
        return properties;
    }

    public void setHasPublisher(Set<String> hasPublisher) {
        this.hasPublisher = hasPublisher;
    }

    public Set<String> getHasPublisher() {
        return hasPublisher;
    }

    public void setHasYear(Set<Integer> hasYear) {
        this.hasYear = hasYear;
    }

    public Set<Integer> getHasYear() {
        return hasYear;
    }

    public void setHumanCreator(Set<String> humanCreator) {
        this.humanCreator = humanCreator;
    }

    public Set<String> getHumanCreator() {
        return humanCreator;
    }

    public void setHasTitle(Set<String> hasTitle) {
        this.hasTitle = hasTitle;
    }

    public Set<String> getHasTitle() {
        return hasTitle;
    }

}