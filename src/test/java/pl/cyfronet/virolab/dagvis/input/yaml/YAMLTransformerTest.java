package pl.cyfronet.virolab.dagvis.input.yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import pl.cyfronet.virolab.dagvis.TransformationException;
import pl.cyfronet.virolab.dagvis.Transformer;
import pl.cyfronet.virolab.dagvis.structure.IGraph;

public class YAMLTransformerTest {
	
	public static final String DIRECTORY = "testfiles/yaml/";
	
	@Test
	public void getGraph() throws FileNotFoundException, TransformationException {
		Transformer t = new YAMLTransformer();
		IGraph graph = t.getGraph(new FileInputStream(DIRECTORY + "loop.yaml"));
		//System.out.println(graph);		
	}

}
