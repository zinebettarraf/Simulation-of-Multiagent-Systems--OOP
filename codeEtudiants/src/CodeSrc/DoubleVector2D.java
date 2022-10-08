package CodeSrc;
import java.util.Arrays;
import java.lang.Math;

public class DoubleVector2D {
private double[] vector;
	
	
	public DoubleVector2D(double x,double y) {
		/** Constructs Vectors whose components are of type double */
		this.vector=new double[] {x,y};
	}
	public void setX(double x) {
		/** Setter of the x component*/
		this.vector[0] = x;
	}
	public void setY(double y) {
		/** Setter of the y component*/
		this.vector[1] = y;
	}
	public DoubleVector2D add(DoubleVector2D other) {
		/** add a vector to another */
		return new DoubleVector2D(this.vector[0] + other.vector[0],this.vector[1]+other.vector[1]);  		
	}
	public DoubleVector2D mult(DoubleVector2D other) {
		/** multiply a vector by another*/
		return new DoubleVector2D(this.vector[0] * other.vector[0],this.vector[1]*other.vector[1]);  
	}
	public DoubleVector2D mult_nbr(double nbr) {
		/** multiply a vector by a double*/
		return new DoubleVector2D(this.vector[0] * nbr,this.vector[1]*nbr);  
	}
	public DoubleVector2D substract(DoubleVector2D other) {
		/**substract a vector from another */
		return new DoubleVector2D(this.vector[0]-other.vector[0],this.vector[1]-other.vector[1]); 
	}
	public DoubleVector2D divide(double nbr) {
		/** divide a vector by an integer*/
		if (nbr ==0) {
			 throw new ArithmeticException("Division by zero !!");
		}
		DoubleVector2D result= new DoubleVector2D( this.vector[0]/nbr, this.vector[1]/nbr);
        return result;

	}
	public double norm(DoubleVector2D other) {
		/** Used to calculate the  distance or the norm*/
		DoubleVector2D result = this.substract(other);
		return Math.sqrt(Math.pow(result.vector[0],2)+ Math.pow(result.vector[1],2));  
	}
	

	public double[] getVector() {
		return this.vector;
	}
	@Override
	public String toString() {
		/** Defines a way of printing the components of the Vector in a presentable way*/
		return "DoubleVector2D [vector=" + Arrays.toString(vector) + "]";
	}
	
	public void limitVector (double vlim) {
		/** limits the values the components of the vector can take, mainly used to limit the velocity and the maximum of a force applied*/
		if (this.getVector()[0] >= vlim) {
			this.setX((double) vlim);
		}
		if (this.getVector()[1] >= vlim) {
			this.setY((double) vlim);
		}
	}
	
       
}