package cz.sio2.bib2rdf.bibtex;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationException extends Exception {

    public ValidationException() {
    }

    public ValidationException(final String id, List<String> messages) {
        super(messages.stream().map((s) -> id+" : "+s).collect(Collectors.joining(",\n")));
    }
}
