package pl.cyfronet.virolab.dagvis.structure;

/**
 * Listener interface, listens to graph state events.
 * 
 * @author Krzysztof Nirski
 *
 */
public interface GraphEventListener {

	/**
	 * Called when an event occured.
	 * @param e Graph event.
	 */
	void stateChanged(AbstractGraphEvent e);

}
