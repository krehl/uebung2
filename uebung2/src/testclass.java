import algebra.*;
import java.io.*;

/**
 * @author Konstantin Krehl
 */
public class testclass {

	public static void main(String[] args) {
    	try {
    		BufferedReader in = new BufferedReader(new FileReader("C:/Users/Konstantin Krehl/git/uebung2/uebung2/src/data.dat"));
    		try {
    			String[] b = in.readLine().split(" ");
    			String[][] A = new String[b.length][b.length];
    			for (int j = 0; j<b.length;j++) {
    				A[j] = in.readLine().split(" ");
    			}
    			
    			Matrix BB = new Matrix(b.length,1);
    			for (int i = 0; i < b.length; i++){
    				BB.setElement(new Rational(Long.parseLong(b[i])), i, 0);
    			}
    			
    			Matrix AA = new Matrix(b.length, b.length);
    			for (int i = 0; i < b.length; i++){
    				for (int j = 0; j < b.length; j++){
    					AA.setElement(new Rational(Long.parseLong(A[i][j])), i, j);
    				}
    			}
    			
    			System.out.println(AA);
    			System.out.println(BB);
    			
    			System.out.println(Algebra.gauss(AA, BB));
    			
    			Rational x, y, z;

    	        // 1/2 + 1/3 = 5/6
    	        x = new Rational(1, 2);
    	        y = new Rational(1, 3);
    	        z = x.add(y);
    	        System.out.println(z);

    	        // 8/9 + 1/9 = 1
    	        x = new Rational(8, 9);
    	        y = new Rational(1, 9);
    	        z = x.add(y);
    	        System.out.println(z);

    	        // 1/200000000 + 1/300000000 = 1/120000000
    	        x = new Rational(1, 200000000);
    	        y = new Rational(1, 300000000);
    	        z = x.add(y);
    	        System.out.println(z);

    	        // 1073741789/20 + 1073741789/30 = 1073741789/12
    	        x = new Rational(1073741789, 20);
    	        y = new Rational(1073741789, 30);
    	        z = x.add(y);
    	        System.out.println(z);

    	        //  4/17 * 17/4 = 1
    	        x = new Rational(4, 17);
    	        y = new Rational(17, 4);
    	        z = x.mult(y);
    	        System.out.println(z);

    	        // 3037141/3247033 * 3037547/3246599 = 841/961 
    	        x = new Rational(3037141, 3247033);
    	        y = new Rational(3037547, 3246599);
    	        z = x.mult(y);
    	        System.out.println(z);

    	        // 1/6 - -4/-8 = -1/3
    	        x = new Rational( 1,  6);
    	        y = new Rational(-4, -8);
    	        z = x.sub(y);
    	        System.out.println(z);
    	    
    		}
    		finally {
    		in.close(); }
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
}
