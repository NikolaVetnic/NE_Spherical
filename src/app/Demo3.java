package app;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetInteger;

public class Demo3 implements Drawing {
	
	@GadgetDouble(min = 1.0, max = 500.0)
	double r = 100.0;
	
	@GadgetDouble(min = 0.0, max = 1.0)
	double theta = 10.0;
	
	@GadgetInteger(min = 0, max = 30)
	int n = 4;
	
	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		
	}

	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
}
