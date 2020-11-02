package app;

import geometry.Draw;
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
		
		int 	linesNum = 10;		
		double 	linesWdt = .5;
		
		int grain = 200;
		
		boolean drawZ = false;
		
		Spherical3[] sx = new Spherical3[linesNum * 2 + 1];
		Spherical3[] sy = new Spherical3[linesNum * 2 + 1];
		
		for (int i = -linesNum; i <= linesNum; i++) {
			sx[i+linesNum] = Spherical3.RTP(r,  0.00,  0.5 * i / sx.length);
			sy[i+linesNum] = Spherical3.RTP(r,  0.5 * i / sy.length,  0.25);
		}
		
		for (int i = 0; i < sx.length; i++) {
			
			double c = 1.0 - (.5 * Math.abs(i - sx.length / 2) / (sx.length / 2) + .35);
			Color cx = Color.gray(c);
			Color cz = Color.gray(0.5 * (1.0 - c));
			
			for (int j = 0; j < grain; j++) {
				
				// circles parallel to xy plane
				if (i >= 0 && drawZ) {
					
					Vector3 vz0 = Transform.rodrigues(
							Vector3.fromSpherical(sx[i]), 
							Vector3.PLUS_K, (1.0 * j / 100));	
					Vector3 vz1 = Transform.rodrigues(
							Vector3.fromSpherical(sx[i]), 
							Vector3.PLUS_K, (1.0 * (j+1) / 100));
					
					view.setFill(cz);
					view.fillCircleCentered(vz0.scale(.25).project(), 0.5);
					Draw.line(view, cz, linesWdt, vz0.scale(.25), vz1.scale(.25));
				}
				
				// circles parallel to xz plane
				Vector3 vy0 = Transform.rodrigues(
						Vector3.fromSpherical(sx[i]), 
						Vector3.PLUS_I, (1.0 * j / 100));
				Vector3 vy1 = Transform.rodrigues(
						Vector3.fromSpherical(sx[i]), 
						Vector3.PLUS_I, (1.0 * (j+1) / 100));
				
				view.setFill(cx);
				view.fillCircleCentered(vy0.scale(.25).project(), 0.5);
				Draw.line(view, cx, linesWdt, vy0.scale(.25), vy1.scale(.25));
				
				// circles parallel to yz plane
				Vector3 vx0 = Transform.rodrigues(
						Vector3.fromSpherical(sy[i]), 
						Vector3.PLUS_J, (1.0 * j / 100));
				Vector3 vx1 = Transform.rodrigues(
						Vector3.fromSpherical(sy[i]), 
						Vector3.PLUS_J, (1.0 * (j+1) / 100));
				
				view.setFill(cx);
				view.fillCircleCentered(vx0.scale(.25).project(), 0.5);
				Draw.line(view, cx, linesWdt, vx0.scale(.25), vx1.scale(.25));
			}
		}
	}

	public static void main(String[] args) {
		DrawingApplication.launch(600, 600);
	}
}
