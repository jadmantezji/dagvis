package pl.cyfronet.virolab.dagvis;

public class TransformationException extends Exception {

	protected String message;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3371254323815758406L;

	public TransformationException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
