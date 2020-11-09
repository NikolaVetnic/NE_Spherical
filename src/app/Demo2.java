package app;

import geometry.Space;
import geometry.Vector3;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.utils.Numeric;

public class Demo2 implements Drawing {
	
	@GadgetAnimation(speed = 0.5)
	double time = 0.0;
	
	@GadgetDouble(min = 0.0, max = 500.0)
	double r = 25.0;
	
//	@GadgetDouble(min = 0.0, max = 1.0)
	double theta = .125;
	
//	@GadgetDouble(min = 0.0, max = 1.0)
	double phi = 0.0;

//	@GadgetDouble(min = 0.0, max = 1.0)
	double beta = 0.05;

//	@GadgetInteger(min = 0, max = 30)
	int n = 10;

	@GadgetBoolean
	boolean drawLines = true;

	@Override
	public void draw(View view) {
		
		if (time == 0.0) DrawingUtils.clear(view, Color.gray(0.125));
		
		Space s = Space.r(r);
		s.drawGrid(view, Color.gray(0.5), .5, n, 100, drawLines);
		
		s.addPoint(
				theta 	+ Numeric.cosT(time * .125), 
				phi 	+ Numeric.sinT(time * .075));
		
		s.axisRotate(
				0, 
				Vector3.xyz(
						Numeric.cosT(time * 0.500), 
						Numeric.cosT(time * .250), 
						Numeric.sinT(time * .125)).norm(), 
				beta);
		
		s.fillPoint(view, 
				Color.hsb(
						Numeric.cosT(time * .125) * 30 + 60, 
						Math.abs(Numeric.cosT(time * .375)) % .25 * 3 + .15, 
						Math.abs(Numeric.cosT(time * .375)) % .25 * 3 + .15), 
				0, Numeric.sinT(time * .125) * 15);
	}

	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
}
