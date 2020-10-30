package geometry;

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
		
		double x = r * Math.cos(theta) * Math.sin(phi);
		double y = r * Math.sin(theta) * Math.sin(phi);
		double z = r * Math.cos(phi);
		
		this.v = Vector3.XYZ(x, y, z);
	}
	
	public static Spherical3 RTP(double r, double theta, double phi) {
		return new Spherical3(r, theta, phi);
	}
	
	public static Spherical3 fromV3(Vector3 v) {
		
		double x = v.x();	double y = v.y();	double z = v.z();
		
		double r = Math.sqrt(x*x + y*y + z*z);
		double phi = Transform.rad2Turn(Math.atan2(y, x));
		double theta = Transform.rad2Turn(Math.atan2(Math.sqrt(x*x + y*y), z));
		
		return new Spherical3(r, theta, phi);
	}
	
	public String toString() {
		return String.format("( r %.2f, θ %.2f, φ %.2f )", r, theta, phi);
	}
	
	public void print() {
		System.out.println(this);
	}
	
	public Vector3 v() 		{ return v; 	}
	public double r() 		{ return r; 	}
	public double theta() 	{ return theta; }
	public double phi() 	{ return phi; 	}
}
