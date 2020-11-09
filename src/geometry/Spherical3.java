package geometry;

import mars.utils.Numeric;

public class Spherical3 {

	/** Cartesian coordinates. */
	private Vector3 v;
	
	/** Radius. */
	private double r;
	/** Azimuthal angle in the x-y plane &theta;. */
	private double theta;
	/** Polar angle (co-latitude) &Phi;. */
	private double phi;
	
	private Spherical3(double r, double theta, double phi) {
		
		this.r 		= r;
		this.theta 	= theta;
		this.phi 	= phi;
		
//		double x = r * Numeric.cosT(theta) * Numeric.sinT(phi);
//		double y = r * Numeric.sinT(theta) * Numeric.sinT(phi);
//		double z = r * Numeric.cosT(phi);

		double x = r * Numeric.cosT(phi) * Numeric.sinT(theta);
		double y = r * Numeric.sinT(phi) * Numeric.sinT(theta);
		double z = r * Numeric.cosT(theta);
		
		this.v = Vector3.xyz(x, y, z);
	}
	
	public static Spherical3 rtp(double r, double theta, double phi) {
		return new Spherical3(r, theta, phi);
	}
	
	public static Spherical3 fromV3(Vector3 v) {
		
		double x = v.x();	double y = v.y();	double z = v.z();
		
		double r = Math.sqrt(x*x + y*y + z*z);
		double phi = Numeric.atan2T(y, x);
		double theta = Numeric.atan2T(Math.sqrt(x*x + y*y), z);
		
		return new Spherical3(r, theta, phi);
	}
	
	public String toString() {
		return String.format("( r %.2f, θ %.2f, φ %.2f )", r, theta, phi);
	}
	
	public void print() {
		System.out.println(this);
	}
	
	public void rotateTheta(double ang) 	{ this.theta += ang; 	}
	public void rotatePhi(double ang) 		{ this.phi += ang; 		}
	
	public Vector3 v() 		{ return v; 	}
	public double r() 		{ return r; 	}
	public double theta() 	{ return theta; }
	public double phi() 	{ return phi; 	}
}
