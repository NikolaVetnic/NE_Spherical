package geometry;

import javafx.scene.paint.Color;
import mars.drawingx.drawing.View;

public class Draw {

	public static void line(View view, Color c, double lineWidth, Vector3 v0, Vector3 v1) {
		
		view.setStroke(c);
		view.setLineWidth(lineWidth);
		view.strokeLine(v0.project(), v1.project());
	}
}
