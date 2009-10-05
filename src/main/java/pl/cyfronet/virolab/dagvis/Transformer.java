package pl.cyfronet.virolab.dagvis;

import java.io.InputStream;

import pl.cyfronet.virolab.dagvis.structure.IGraph;

/**
 * More than meets the eye.
 * 
 * @author Krzysztof Nirski
 *
 */
public interface Transformer {

	IGraph getGraph(InputStream source) throws TransformationException;
	
}
