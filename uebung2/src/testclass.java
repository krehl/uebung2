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
	
	
	
	
	
	/*private static void readFile(String filename){
		try{
			FileInputStream fStream = new FileInputStream(testclass.class.getProtectionDomain().getCodeSource().getLocation().getFile() + filename);
			DataInputStream fInputStream = new DataInputStream(fStream);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fInputStream));
			String[] line_numbers;
			
			//Read first line  
			String line = bufferedReader.readLine();
			String[] firstLine = line.split(" ");
			Rational[] data_vector = new Rational[firstLine.length];
			//result vector numbers
			for(int i=0;i<data_vector.length;i++){
				data_vector[i] = new Rational(Long.parseLong(firstLine[i]),1);
			}
			
			//Read rest of file
			Rational[][] data_matrix = new Rational[firstLine.length][firstLine.length];
			int i = 0;
			while((line=bufferedReader.readLine()) != null){
				//don't read to much
				if(i>=firstLine.length) break;
				line.replace("\t", " ");
				while(line.contains("  ")) line = line.replace("  ", " ");
				line_numbers = line.split(" ");
				if(line_numbers.length!=firstLine.length) {
					data_matrix = null;
					fInputStream.close();
					bufferedReader.close();
					throw new IllegalArgumentException( "File Format incorrect" );
				}
				for(int j=0;j<line_numbers.length;j++){
					data_matrix[i][j] = new Rational(Long.parseLong(line_numbers[j]),1);
				}
				i++;
			}
			if(i!=firstLine.length) {
				data_matrix = null;
				fInputStream.close();
				bufferedReader.close();
				throw new IllegalArgumentException( "File Format incorrect" );
			}
			fInputStream.close();
			bufferedReader.close();
		}catch(FileNotFoundException e){
			System.out.println("Datei nicht gefunden: "+e.getMessage());
			return;
		}catch(IllegalArgumentException e){
			System.out.println("Datei Fehler: "+e.getMessage());
			return;
		}catch(IOException e){
			System.out.println("Lese Fehler: "+e.getMessage());
			return;
		}
	}*/


}
