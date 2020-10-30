package geometry;

import mars.geometry.Vector;

public class Vector3 {
	
	/** Null vector (coordinates: 0, 0, 0). */
	public static final Vector3 ZERO = Vector3.XYZ(0, 0, 0);
	
	/** First canonical vector (coordinates: 1, 0, 0). */
	public static final Vector3 PLUS_X = Vector3.XYZ(1, 0, 0);
	
	/** Opposite of the first canonical vector (coordinates: -1, 0, 0). */
	public static final Vector3 MINUS_X = Vector3.XYZ(-1, 0, 0);

	/** Second canonical vector (coordinates: 1, 0, 0). */
	public static final Vector3 PLUS_Y = Vector3.XYZ(1, 0, 0);
	
	/** Opposite of the second canonical vector (coordinates: -1, 0, 0). */
	public static final Vector3 MINUS_Y = Vector3.XYZ(-1, 0, 0);

	/** Third canonical vector (coordinates: 0, 0, 1). */
	public static final Vector3 PLUS_Z = Vector3.XYZ(0, 0, 1);
	
	/** Opposite of the third canonical vector (coordinates: 0, 0, -1). */
	public static final Vector3 MINUS_Z = Vector3.XYZ(0, 0, -1);
	
	/** Abscissa. */
	private double x; 
	/** Ordinate. */
	private double y;
	/** Height. */
	private double z;
	
	private Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static Vector3 XYZ(double x, double y, double z) {
		return new Vector3(x, y, z);
	}
	
	/** Spherical to spatial Cartesian coordinates. */
	public static Vector3 fromSpherical(Spherical3 s) {
		return s.v().cop();
	}
	
	/** Copy this vector to a new instance of Vector3. */
	public Vector3 cop() {
		return XYZ(this.x(), this.y(), this.z());
	}
	
	public Vector3 neg() {
		return XYZ(-this.x(), -this.y(), -this.z());
	}
	
	public Vector3 add(Vector3 v) {
		return XYZ(this.x + v.x(), this.y + v.y(), this.z + v.z());
	}

	public Vector3 sub(Vector3 v) {
		return XYZ(this.x - v.x(), this.y - v.y(), this.z - v.z());
	}
	
	public Vector3 scale(double a) {
		return XYZ(this.x * a, this.y * a, this.z * a);
	}
	
	public double dot(Vector3 v) {
		return this.x * v.x() + this.y * v.y() + this.z * v.z();
	}
	
	public Vector3 cross(Vector3 v) {
		return XYZ(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
	}
	
	public Vector3 norm() {
		return XYZ(x / mag(), y / mag(), z / mag());
	}
	
	public double mag() {
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	/** Returns angle between vectors in turns.
	 * @param v Vector3   
	 */
	public double angle(Vector3 v) {
		return Transform.rad2Turn(Math.acos(dot(v) / (mag() * v.mag())));
	}
	
	/** Performs stereographic azimuthal projection onto plane parallel
	 * to XY plane at Z of negative R.
	 */
	public Vector project() {
		
		double r 		= Spherical3.fromV3(this).r();
		double phi 		= Spherical3.fromV3(this).phi();
		double theta 	= Spherical3.fromV3(this).theta();
		
		return Vector.polar(
				2.0 * r * Math.tan(Transform.turn2Rad((0.5 - theta) * 0.5)), phi);
	}
	
	/** Returns distance between vectors. */
	public double dist(Vector3 v) {
		double dx = x - v.x(), dy = y - v.y(), dz = z - v.z();
		return Math.sqrt(dx*dx + dy*dy + dz*dz);
	}
	
	public String toString() {
		return String.format("( %.2f, %.2f, %.2f )", x, y, z);
	}
	
	public void print() {
		System.out.println(this);
	}
	
	public double x() { return x; }
	public double y() { return y; }
	public double z() { return z; }
	
	/** Setter methods. */
	public void setX(double x) { this.x = x; }
	public void setY(double y) { this.y = y; }
	public void setZ(double z) { this.z = z; }
}
