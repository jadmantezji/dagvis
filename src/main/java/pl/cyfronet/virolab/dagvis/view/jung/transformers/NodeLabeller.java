package pl.cyfronet.virolab.dagvis.view.jung.transformers;

import org.apache.commons.collections15.Transformer;

import pl.cyfronet.virolab.dagvis.structure.INode;

/**
 * 
 * @author Krzysztof Nirski
 *
 */
public class NodeLabeller implements Transformer<INode, String> {

	public String transform(INode input) {
		String label = input.getLabel();
		return label == null ? input.getName() : label; 
	}
	
}
