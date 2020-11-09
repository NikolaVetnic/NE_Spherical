package geometry;

import javafx.scene.paint.Color;
import mars.drawingx.drawing.View;

public class Draw {

	protected static void drawGrid(View view, Color c, double lineWidth, int density, int grain, double r, boolean drawLines) {
		
		int m = density * 2;
		
		for (int i = 0; i < m; i++) {
			Spherical3 sx = Spherical3.rtp(r, .25, (1.0 * (i 	    ) / (m * 2)));
			Spherical3 sy = Spherical3.rtp(r, .25, (1.0 * (i - m / 2) / (m * 2)));
			Spherical3 sz = Spherical3.rtp(r, (1.0 * i) / (m * 2	), 0.0		);
			
			for (int j = 0; j < grain; j++) {
				
				Vector3 vx0 = Transform.rodrigues(
						Vector3.fromSpherical(sx), 
						Vector3.PLUS_I, (1.0 * ( j    )          / grain));
				Vector3 vx1 = Transform.rodrigues(
						Vector3.fromSpherical(sx), 
						Vector3.PLUS_I, (1.0 * ((j + 1) % grain) / grain));
				
				Vector3 vy0 = Transform.rodrigues(
						Vector3.fromSpherical(sy), 
						Vector3.PLUS_J, (1.0 * ( j    )          / grain));
				Vector3 vy1 = Transform.rodrigues(
						Vector3.fromSpherical(sy), 
						Vector3.PLUS_J, (1.0 * ((j + 1) % grain) / grain));

				Vector3 vz0 = Transform.rodrigues(
						Vector3.fromSpherical(sz), 
						Vector3.PLUS_K, (1.0 * ( j    )          / grain));
				Vector3 vz1 = Transform.rodrigues(
						Vector3.fromSpherical(sz), 
						Vector3.PLUS_K, (1.0 * ((j + 1) % grain) / grain));
				
				if (!drawLines) {
					
					view.setFill(Color.hsb(c.getHue(), c.getSaturation(), c.getBlue() * .5));
					view.fillCircleCentered(vz0.project(), .5);
					
					view.setFill(c);
					view.fillCircleCentered(vx0.project(), .5);
					view.fillCircleCentered(vy0.project(), .5);
				} else {
					
					view.setLineWidth(.25);					
					view.setStroke(Color.hsb(c.getHue(), c.getSaturation(), c.getBlue() * .5));
					view.strokeLine(vz0.project(), vz1.project());
					
					view.setStroke(c);
					view.strokeLine(vx0.project(), vx1.project());
					view.strokeLine(vy0.project(), vy1.project());
				}
			}
		}
	}
}
