package algebra;

public class Algebra {
	
	public static Matrix gauss(final Matrix matrix, final Matrix rhs) {
		/*
		 * for k = 1 ... m:
   				Find pivot for column k:
   				i_max := argmax (i = k ... m, abs(A[i, k]))
   				if A[i_max, k] = 0
     				error "Matrix is singular!"
   				swap rows(k, i_max)
   				Do for all rows below pivot:
   				for i = k + 1 ... m:
     				Do for all remaining elements in current row:
     				for j = k + 1 ... n:
       					A[i, j] := A[i, j] - A[k, j] * (A[i, k] / A[k, k])
     				Fill lower triangular matrix with zeros:
     				A[i, k] := 0
		 
		 */
		int debug = 1;
		int m = matrix.getNumRows();
		int n = matrix.getNumCols();
		Matrix A = new Matrix(matrix.getData());
		Matrix b = new Matrix(rhs.getData());
		
		for (int k = 0; k < m ;k++) {
			int index_max = A.max(k, k);
			if (A.getElement(index_max, k)== new Rational(0,1)) {
				throw new IllegalArgumentException( "Not solvable." );
			}
			A.swap(k, index_max);
			b.swap(k, index_max);
			
			for (int i = k+1; i < m; i++) {
				Rational tmp = Rational.div(A.getElement(i, k),A.getElement(k, k));
				b.setElement(Rational.sub(b.getElement(k, 1),Rational.mult(b.getElement(i, 1),tmp)), i, 1);
				for (int j = k+1; j < n; j++) { //A[i, j] := A[i, j] - A[k, j] * (A[i, k] / A[k, k])
					A.setElement(Rational.sub(A.getElement(k, j),Rational.mult(A.getElement(i, j),tmp)), i, j);
				}
				A.setElement(new Rational(0,1), i, k);
			}
		}
		return A;
		
	}
	
	
	
	
	public static Matrix gaussJordan(final Matrix matrix, final Matrix vector){
		int n = matrix.getNumRows();
		int l;
		boolean null_row;
		
		if(n != matrix.getNumCols() || n != vector.getNumRows()) return new Matrix();

		Matrix A_k = matrix.clone();
		Matrix b_k = vector.clone();
		for(int k=1;k<=n;k++){
			l=k;
			for(int j=k;j<=n;j++){
				if(A_k.getElement(l,k).compareTo(A_k.getElement(j,k)) == -1) {
					l=j;
				}
			}
			A_k.swap(k,l);
			b_k.swap(k,l);
			for(int i=1;i<=n;i++){
				if(i==k) continue;
				null_row = true;
				Rational sub = Rational.div(A_k.getElement(i, k), A_k.getElement(k, k));
				for(int j=k;j<=n;j++){
					A_k
						.getElement(i, j)
						.sub(new Rational(A_k.getElement(k, j)).mult(sub));
					if(!A_k.getElement(i, j).equals(0)) null_row = false;
				}
				if(null_row && i>k) throw new IllegalArgumentException( "Not solvable." );
				b_k
					.getElement(0,i)
					.sub(new Rational(b_k.getElement(0,k)).mult(sub));
			}
		}
		for(int k=1;k<=n;k++){
			b_k
				.getElement(0,k)
				.div(A_k.getElement(k, k));
		}
		return b_k;
	}
}
