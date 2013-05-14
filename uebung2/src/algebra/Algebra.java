package algebra;

public class Algebra {
	//Berechnet Ax = b. gibt x zurück bei gegebenem A und b
	public static Matrix gauss(final Matrix matrix, final Matrix rhs) { //A, b sollten unveränderlich sein, daher final
		int m = matrix.getNumRows();
		int n = matrix.getNumCols(); //Dimension der Matrix abfragen
		Matrix A = matrix.clone(); //Kopien der Matrizen anlegen
		//System.out.println(A);
		Matrix b = rhs.clone();
		
		for (int k = 0; k < m ;k++) {
			int max = A.max(k, k);
			if (A.getElement(max, k)== new Rational(0,1)) {
				throw new IllegalArgumentException( "Not solvable." ); //Falls das maximale Element Null ist, ist die Gleichung nicht lösbar
			}
			A.swap(k, max); //Zeilenvertauschen
			b.swap(k, max);
			
			for (int i = 0; i < m; i++) {
				if(i==k) continue;
				Rational tmp = Rational.div(A.getElement(i, k),A.getElement(k, k)); //Zwischenspeichern, um sich Arbeit zu ersparen
				for (int j = k; j < n; j++) {
					A.setElement(Rational.sub(A.getElement(i, j),Rational.mult(A.getElement(k, j),tmp)), i, j);
				}
				b.setElement(Rational.sub(b.getElement(i, 0),Rational.mult(b.getElement(k, 0),tmp)), i, 0); //b hat nur eine Spalte, daher nicht innerhalb der Schleife
			}
			//System.out.println(A);
			//System.out.println(b);
		}
		for(int k = 0; k < n; k++){ //Zurückrechnen
			b.setElement(Rational.div(b.getElement(k, 0),A.getElement(k, k)),k,0);
		}
		return b; //b enthält die Lösung
		
	}
}
