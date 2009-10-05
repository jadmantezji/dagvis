package pl.cyfronet.virolab.dagvis.view.jung.transformers;

import java.awt.Color;
import java.awt.Paint;

import pl.cyfronet.virolab.dagvis.structure.INode;

public class FillPaintTransformer extends PaintTransformer {
	
	@Override
	public Paint transform(INode input) {
		return Color.WHITE;
//		String style = input.getAttribute("style");
//		boolean filled = style == null ? false : style.equals("filled");
//		//return super.transform(input);
//		return filled ? super.transform(input) : Color.WHITE;
	}

}
