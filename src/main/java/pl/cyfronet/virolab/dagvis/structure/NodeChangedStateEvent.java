package pl.cyfronet.virolab.dagvis.structure;

/**
 * 
 * @author Krzysztof Nirski
 *
 */
public class NodeChangedStateEvent extends AbstractGraphEvent {
	
	private INode node;
	private boolean active;
	
	public NodeChangedStateEvent(INode node, boolean active) {
		super();
		this.node = node;
		this.active = active;
	}
	
	/**
	 * When node is null, deactivate all nodes.
	 * 
	 * @param node
	 */
	public NodeChangedStateEvent(INode node) {
		this(node, false);
	}
	
	public INode getNode() {
		return node;
	}

	public boolean isHighlited() {
		return active;
	}
	
	@Override
	public String toString() {
		String result;
		if (node == null) {
			result = "Deactivate all nodes";
		} else {
			result = (active ? "Activate" : "Deactivate") + " node " + node;  
		}
		return result;
	}

}
