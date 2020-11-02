package app;

import geometry.Spherical3;
import geometry.Transform;
import geometry.Vector3;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;

public class Demo2 implements Drawing {
	
	@GadgetAnimation(speed = .25)
	double time = 0.0;
	
	@GadgetDouble(min = 0.0, max = 10000.0)
	double r = 1000.0;
	
	@GadgetDouble(min = -1., max = 1.0)
	double x = 0.0;

	@GadgetDouble(min = -1., max = 1.0)
	double y = 0.0;

	@GadgetDouble(min = 0.0, max = 1.0)
	double z = 0.0;

	@Override
	public void draw(View view) {
		
		DrawingUtils.clear(view, Color.gray(0.125));
		
		int numLines = 5;
		
		Spherical3[] sx = new Spherical3[11];
		Spherical3[] sy = new Spherical3[11];
		
		for (int i = -numLines; i <= numLines; i++) {
			sx[i+numLines] = Spherical3.RTP(r,  0.00,  1.0 * i / sx.length);
			sy[i+numLines] = Spherical3.RTP(r,  1.0 * i / sy.length,  0.25);
		}
		
		int grain = 500;
		
		for (int i = 0; i < sx.length; i++) {
			for (int j = 0; j < grain; j++) {
				
				if (i >= 0) {
					
					// circles parallel to xy plane
					Vector3 v2 = Transform.rodrigues(
							Vector3.fromSpherical(sx[i]), 
							Vector3.PLUS_K, (1.0 * j / 100));	
					view.setFill(Color.DARKRED);
					view.fillCircleCentered(v2.scale(.25).project(), .50);
				}
				
				// circles parallel to xz plane
				Vector3 v0 = Transform.rodrigues(
						Vector3.fromSpherical(sx[i]), 
						Vector3.PLUS_I, (1.0 * j / 100));	
				view.setFill(Color.RED);
				view.fillCircleCentered(v0.scale(.25).project(), .50);
				
				// circles parallel to yz plane
				Vector3 v1 = Transform.rodrigues(
						Vector3.fromSpherical(sy[i]), 
						Vector3.PLUS_J, (1.0 * j / 100));	
				view.setFill(Color.ORANGERED);
				view.fillCircleCentered(v1.scale(.25).project(), .50);
			}
		}
	}

	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
}
