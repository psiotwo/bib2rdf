package cz.sio2.bib2rdf;

import cz.sio2.bib2rdf.bibtex.Transformer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import org.junit.Assert;
import org.junit.Test;

public class TransformerTest {

    private final String bibTeXEntry="@ARTICLE{kk2012eoqdev, author = {K{\\v r}emen, P. and Kostov, B.}, title = {{Expressive OWL Queries: Design, Evaluation, Visualization}}, journal = {International Journal on Semantic Web and Information Systems}, year = {2012}, volume = {8}, pages = {57--79}, number = {4}, issn = {1552-6283}, language = {English} }";

    @Test
    public void testSingleRecord() {
		InputStream stream;
		try {
			stream = new ByteArrayInputStream(bibTeXEntry.getBytes("UTF-8"));
			new Transformer().transform(new InputStreamReader(stream), new PrintWriter(System.out));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
