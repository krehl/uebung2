package algebra;

public class Algebra {
	
	public static Matrix gauss(final Matrix matrix, final Matrix rhs) {
		int m = matrix.getNumRows();
		int n = matrix.getNumCols();
		Matrix A = matrix.clone();
		System.out.println(A);
		Matrix b = rhs.clone();
		
		for (int k = 0; k < m ;k++) {
			int max = A.max(k, k);
			if (A.getElement(max, k)== new Rational(0,1)) {
				throw new IllegalArgumentException( "Not solvable." );
			}
			A.swap(k, max);
			b.swap(k, max);
			
			for (int i = 0; i < m; i++) {
				if(i==k) continue;
				Rational tmp = Rational.div(A.getElement(i, k),A.getElement(k, k));
				for (int j = k; j < n; j++) {
					A.setElement(Rational.sub(A.getElement(i, j),Rational.mult(A.getElement(k, j),tmp)), i, j);
				}
				b.setElement(Rational.sub(b.getElement(i, 0),Rational.mult(b.getElement(k, 0),tmp)), i, 0);
			}
			System.out.println(A);
			System.out.println(b);
		}
		for(int k = 0; k < n; k++){
			b.setElement(Rational.div(b.getElement(k, 0),A.getElement(k, k)),k,0);
		}
		return b;
		
	}
}
