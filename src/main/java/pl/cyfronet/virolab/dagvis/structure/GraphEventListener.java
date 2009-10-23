package pl.cyfronet.virolab.dagvis.structure;

/**
 * A listener interface to graph state events.
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
