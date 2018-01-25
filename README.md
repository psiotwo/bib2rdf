Bib2RDF
=======

This tool allows you to transform a [Bibtex](http://www.bibtex.org/Format) file into [RDF](https://www.w3.org/RDF/)

The tool uses:

* [JOPA](https://github.com/kbss-cvut/jopa) for BibTeX model generation and persisting it into RDF
* [jbibtex](https://github.com/jbibtex/jbibtex) for parsing BibTeX

Features
--------

* Compliance with [BibTeX RDF Schema](http://purl.org/net/nknouf/ns/bibtex)
* Validates constraints on empty BibTeX attributes for individual bibtex types (Article, Misc, etc.)

Example
-------
Make sure you compile the project with AspectJ support - JOPA uses aspects.
```
new Transformer().transform(reader, writer)
```
where ``reader`` denotes contains the bibtex source and writer denotes the output RDF stream. 

The tool is licensed under [LGPL v3](https://www.gnu.org/licenses/lgpl-3.0.en.html).