package pl.cyfronet.virolab.dagvis.view.jung.transformers;

import java.awt.Paint;

import org.apache.commons.collections15.Transformer;
import org.apache.log4j.Logger;

import pl.cyfronet.virolab.dagvis.structure.INode;

public class PaintTransformer implements Transformer<INode, Paint> {

	private static Logger log = Logger.getLogger(PaintTransformer.class);

	@Override
	public Paint transform(INode input) {
		Paint paint = input.getColor();
		log.debug("Paint initialized: " + paint);
		return paint;
	}

}
