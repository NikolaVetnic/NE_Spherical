package geometry;

import mars.utils.Numeric;

public class Transform {

	/** Compute rotation of vector in space around the axis by angle of
	 *  rotation using Rodrigues' formula.
	 * @param v vector to rotate
     * @param w axis of rotation
     * @param beta rotation angle (in turns)
	 */
	public static Vector3 rodrigues(Vector3 v, Vector3 w, double beta) {

		Vector3 k = w.norm();
		
		double sin = Numeric.sinT(beta);
		double cos = Numeric.cosT(beta);
		
		Vector3 u = v.scale(cos).add(k.cross(v).scale(sin)).add(k.scale(k.dot(v) * (1 - cos)));
		
		if (((Double) u.x()).equals(Double.NaN)) u.setX(0.0);
		if (((Double) u.y()).equals(Double.NaN)) u.setY(0.0);
		if (((Double) u.z()).equals(Double.NaN)) u.setZ(0.0);
		
		return u;
	}
}
