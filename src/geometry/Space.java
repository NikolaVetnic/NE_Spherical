package geometry;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import mars.drawingx.drawing.View;

public class Space {

	private final double r;
	private final List<Spherical3> points;
	
	private Space(double r) {
		this.r = r;
		this.points = new ArrayList<Spherical3>();
	}
	
	public static Space r(double r) 	{ return new Space(r); 	}
	public Spherical3 point(int i) 		{ return points.get(i); }
	
	public void drawGrid(View view, Color c, double lineWidth, int density, int grain, boolean drawLines) {
		Draw.drawGrid(view, c, lineWidth, density, grain, this.r, drawLines);
	}
	
	public void addPoint(double theta, double phi) {
		points.add(Spherical3.rtp(this.r, theta, phi));
	}
	
	public void axisRotate(int i, Vector3 w, double beta) {
		Vector3 v = Vector3.fromSpherical(points.get(i));
		points.set(i, Spherical3.fromV3(Transform.rodrigues(v, w, beta)));
	}
	
	public void fillPoint(View view, Color c, int i, double rr) {
		view.setFill(c);
		view.fillCircleCentered(Vector3.fromSpherical(points.get(i)).project(), rr);
	}
}
