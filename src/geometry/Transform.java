package geometry;

public class Transform {
	
	/** Convert angle in radians to turns.
	 * @param x angle in radians 
	 */
	public static double rad2Turn(double x) { return x / (2 * Math.PI); }
	
	/** Convert angle in turns to radians.
	 * @param x angle in turns 
	 */
	public static double turn2Rad(double x) { return x * (2 * Math.PI); }

	/** Compute rotation of vector in space around the axis by angle of
	 *  rotation using Rodrigues' formula.
	 * @param v vector to rotate
     * @param w axis of rotation
     * @param beta rotation angle (in turns)
	 */
	public static Vector3 rodrigues(Vector3 v, Vector3 w, double beta) {

		Vector3 k = w.norm();
		
		double sin = Math.sin(turn2Rad(beta));
		double cos = Math.cos(turn2Rad(beta));
		
		Vector3 u = v.scale(cos).add(k.cross(v).scale(sin)).add(k.scale(k.dot(v) * (1 - cos)));
		
		if (((Double) u.x()).equals(Double.NaN)) u.setX(0.0);
		if (((Double) u.y()).equals(Double.NaN)) u.setY(0.0);
		if (((Double) u.z()).equals(Double.NaN)) u.setZ(0.0);
		
		return u;
	}
}
