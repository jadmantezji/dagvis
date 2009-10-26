package pl.cyfronet.virolab.dagvis.input;

import java.io.InputStream;

import pl.cyfronet.virolab.dagvis.structure.IGraph;

/**
 * Transformers: more than meets the eye.
 * Interface for input data to abstract data structure transformer.
 * 
 * @author Krzysztof Nirski
 *
 */
public interface Transformer {
	
	/**
	 * Transforms source to IGraph instance.
	 * 
	 * @param source Stream of input data.
	 * @return Abstract Graph representation.
	 * @throws TransformationException When the input contains error. 
	 */
	IGraph getGraph(InputStream source) throws TransformationException;
	
}
