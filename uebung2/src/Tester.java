import algebra.*;

import java.io.*;

public class Tester {

	private static Rational[] data_vector = null;
	private static Rational[][] data_matrix = null;

	public static void main(String[] args) {

		Matrix gauss_vector = null;
		Matrix gauss_matrix = null;
		
		//Test Arguments and Test Data
		if(args.length==0) return;
		else {Tester.readFile(args[0]);}
		if(data_vector==null || data_matrix==null) return;
		
		/*
		 * Rational Class
		 */
		System.out.println("\n\nRational Class Tests\n\n");
		//Constructor test without arguments
		Rational num1 = new Rational();
		//Constructor test with arguments
		Rational num2 = new Rational(10,3);
		Rational num3 = new Rational(5,3);
		Rational num4 = new Rational(10,3);
		Rational num5 = new Rational(21,6);
		Rational num6 = new Rational(20,6);
		//Constructor test with invalid arguments
		try{
			Rational num7 = new Rational(10,0);
		}catch(IllegalArgumentException e){
			System.out.println("Num7 Falsches Argument: "+ e.getMessage());			
		}

		//toString test
		System.out.println("Num1: " + num1);
		System.out.println("Num2 10/3: " + num2);
		System.out.println("Num3  5/3: " + num3);
		System.out.println("Num4 10/3: " + num4);
		System.out.println("Num5 21/6: " + num5);
		System.out.println("Num6 20/6: " + num6);
		
		//equal test
		System.out.println("Num2=Num3 should be false: " + num2.equals(num3));
		System.out.println("Num2=Num4 Should be true: " + num2.equals(num4));
		System.out.println("Num2=Num5 Should be false: " + num2.equals(num5));
		System.out.println("Num2=Num6 Should be true: " + num2.equals(num6));
		
		
		
		/*
		 * Matrix Class
		 */
		System.out.println("\n\n\n\nMatrix Class Tests\n\n");
		//Constructor test without arguments
		Matrix mat1 = new Matrix();
		//Constructor test with arguments
		Matrix mat2 = new Matrix(3,3);
		Matrix mat3 = new Matrix(3,3);
		//Constructor test with invalid arguments
		try{
			Matrix mat4 = new Matrix(0,0);
		}catch(IllegalArgumentException e){
			System.out.println("Mat4 Falsches Argument: "+ e.getMessage());			
		}
		try{
			gauss_matrix = new Matrix(data_matrix);
		}catch(IllegalArgumentException e){
			System.out.println("Datafile Matrix: "+ e.getMessage());			
		}

		//toString test
		System.out.println("Mat1: \n" + mat1);
		System.out.println("Mat2: \n" + mat2);
		System.out.println("Mat3: \n" + mat3);
		

		//set test
		mat2.setElement( new Rational(20,4),2,2);
		//set invalid test
		try{
			mat2.setElement(new Rational(20,4),4,4);
		}catch(IllegalArgumentException e){
			System.out.println("Mat2 Falsches Argument: "+ e.getMessage());			
		}
		System.out.println("Swap Test\nMat2: \n" + mat2);
		//swap test
		mat2.swap(2,1);
		System.out.println("Mat2: \n" + mat2);
		
		//equals test
		System.out.println("Mat2=Mat3 should be false: " + mat2.equals(mat3));
		mat3.setElement(new Rational(20,4),1,2);
		System.out.println("Mat3: \n" + mat3);
		System.out.println("Mat2=Mat3 should be true: " + mat2.equals(mat3));
		
		//clone test
		Matrix mat5 = mat3.clone();
		mat5.setElement(new Rational(14,4),1,1);
		System.out.println("Clone Test:\nMat3: \n" + mat3);
		System.out.println("Mat5 added 1,1:14/4: \n" + mat5);
		
		
		/*
		 * Vector Class
		 */
		System.out.println("\n\n\n\nVector Class Tests\n\n");
		//Constructor Test
		Matrix vek1 = new Matrix(3);
		vek1.setElement(new Rational(3,7),0,0);
		try{
			gauss_vector = new Matrix(data_vector);
		}catch(IllegalArgumentException e){
			System.out.println("Datafile Matrix: "+ e.getMessage());			
		}
		System.out.println("Vek1: \n" + vek1);
		
		
		/*
		 * Gauss
		 */
		if(gauss_matrix==null || gauss_vector==null) {
			System.out.println("Kein Daten für den Gaußtest");
			return;
		}
		System.out.println("\n\n\n\nGauß Tests\n\n");
		System.out.println("Vec:\n"+gauss_vector.toString());
		System.out.println("Mat:\n"+gauss_matrix.toString());
		try{
			System.out.println("Gauß:\n"+Algebra.gaussJordan(gauss_matrix, gauss_vector));
		}catch(IllegalArgumentException e){
			System.out.println("Matrix nicht lösbar: "+ e.getMessage());			
		}
		
	}

	private static void readFile(String filename){
		try{
			FileInputStream fStream = new FileInputStream(Tester.class.getProtectionDomain().getCodeSource().getLocation().getFile() + filename);
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
	}

}