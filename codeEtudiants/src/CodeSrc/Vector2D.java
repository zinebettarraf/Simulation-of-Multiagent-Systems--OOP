package CodeSrc;
import java.util.Arrays;
import java.lang.Math;

public class Vector2D {
	/** The Class of 2D-Vectors with integer Components*/
	private int[] vector; // An array containing the two components of the Vector
	
	
	public Vector2D(int x,int y) {
		/** Constructor of the 2D - Vector */
		this.vector=new int[] {x,y};
	}
	public void setX(int x) {
		/** Setter of the x component*/
		this.vector[0] = x;
	}
	public void setY(int y) {
		/** Setter of the y component*/
		this.vector[1] = y;
	}
	
	public int[] getVector() {
		/** The Getter of the Vector*/
		return this.vector;
	}
	
	public Vector2D add(Vector2D other) {
		/** add a vector to another */
		return new Vector2D(this.vector[0] + other.vector[0],this.vector[1]+other.vector[1]);  		
	}
	public Vector2D mult(Vector2D other) {
		/** multiply a vector by another*/
		return new Vector2D(this.vector[0] * other.vector[0],this.vector[1]*other.vector[1]);  
	}
	public Vector2D mult_nbr(int nbr) {
		/** multiply a vector by an integer*/
		return new Vector2D(this.vector[0] * nbr,this.vector[1]*nbr);  
	}
	public Vector2D substract(Vector2D other) {
		/**substract a vector from another */
		return new Vector2D(this.vector[0]-other.vector[0],this.vector[1]-other.vector[1]); 
	}
	public Vector2D divide(int nbr) {
		/** divide a vector by an integer*/
		if (nbr ==0) {
			 throw new ArithmeticException("Division by zero !!");
		}
		Vector2D result= new Vector2D( this.vector[0]/nbr, this.vector[1]/nbr);
        return result;

	}
	public double norm(Vector2D other) {
		/** Used to calculate the  distance or the norm*/
		Vector2D result = this.substract(other);
		return Math.sqrt(Math.pow(result.vector[0], 2)+ Math.pow(result.vector[1],2));  
	}

	
	@Override
	public String toString() {
		/** Defines a way of printing the components of the Vector in a presentable way*/
		return "Vector2D [vector=" + Arrays.toString(vector) + "]";
	}
	
	public void limitVector (double vlim) {
		/** limits the values the components of the vector can take, mainly used to limit the velocity and the maximum of a force applied*/
		if (this.getVector()[0] >= vlim) {
			this.setX((int) vlim);
		}
		
		if (this.getVector()[0] <= -vlim) {
			this.setX((int) -vlim);
		}
		if (this.getVector()[1] >= vlim) {
			this.setY((int) vlim);
		}
		if (this.getVector()[1] <= -vlim) {
			this.setY((int) -vlim);
		}
		
	}
	
	public void normalize () {
		/** normalize the vector */
		double norm = this.norm(this);
		this.setX((int) (this.vector[0]/norm));
		this.setY((int) (this.vector[1]/norm));
		
	}
	
	public int scalarProd (Vector2D vector) {
		/** Dot Product of two Vectors*/
		return (this.vector[0] * vector.getVector()[0] + this.vector[1] * vector.getVector()[1]);
	}
	
    public Vector2D getNormalPoint (Vector2D point1, Vector2D point2) {
    	
    	/** Gets the point normal to the segment [point1, point2] from the vector*/
    	Vector2D p1p = this.substract(point1);
    	Vector2D p1p2 = point2.substract(point1);
    	p1p2.normalize();
    	p1p2.mult_nbr(p1p.scalarProd(p1p2));
    	
    	Vector2D normalPoint = point1.add(p1p2);
    	return normalPoint;
    }
}