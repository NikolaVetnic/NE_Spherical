package app;

import geometry.Transform;
import geometry.Vector3;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetBoolean;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;

public class Demo1 implements Drawing {
	
	@GadgetAnimation(speed = 0.25)
	double time = 0;
	
	@GadgetDouble(min = 0.0, max = 500.)
	double xx = 0.0;
	
	@GadgetDouble(min = 0.0, max = 500.)
	double rr = 10.0;
	
	@GadgetBoolean
	boolean drawTopView = false;
	
	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		double r = Math.sin(time) * rr;
		
		for (int j = 0; j < 20; j++) {
			for (int i = 0; i < 100; i++) {
				
				// red circles
				Vector3 k = Vector3.xyz(
						Math.sin(time) * 2., 
						Math.sin(time) * 4., 
						Math.cos(time) * 1.);
				
				Vector3 v = Vector3.xyz(
						0, 
						100 + r * Math.sin(time) * (50.0 * j / 10), 
						0);
				
				// stereographic azimuthal projection
				Vector3 t0 = Transform.rodrigues(v, k, time + (1.0 * i / 100));	
				view.setFill(Color.RED);
				if (drawTopView) view.fillCircleCentered(new Vector(t0.x(), t0.y()), .75);
				view.fillCircleCentered(t0.scale(.25).project(), .75);
				
				// top view
				Vector3 t1 = Transform.rodrigues(v, k, time);
				view.setFill(Color.GREENYELLOW);
				if (drawTopView) view.fillCircleCentered(new Vector(t1.x(), t1.y()), 1.5);
				view.fillCircleCentered(t1.scale(.25).project(), 2.5);
				
				// blue circles
				Vector3 l = Vector3.xyz(
						Math.sin(time) * 1.,
						Math.sin(time) * 2.,
						Math.cos(time) * 4.);
				
				Vector3 w = Vector3.xyz(
						xx, 
						100 + r * Math.sin(time) * (25.0 * j / 10), 
						-25);
	
				// stereographic azimuthal projection
				Vector3 u0 = Transform.rodrigues(w, l, time + (1.0 * i / 100));
				view.setFill(Color.CORNFLOWERBLUE);
				if (drawTopView) view.fillCircleCentered(new Vector(u0.x(), u0.y()), .75);
				view.fillCircleCentered(u0.scale(.25).project(), .75);
				
				// top view
				Vector3 u1 = Transform.rodrigues(w, l, time);
				view.setFill(Color.ORANGE);
				if (drawTopView) view.fillCircleCentered(new Vector(u1.x(), u1.y()), 1.5);
				view.fillCircleCentered(u1.scale(.25).project(), 2.5);
			}
		}
	}

	public static void main(String[] args) {
		
		DrawingApplication.launch(640, 480);
	}
}
