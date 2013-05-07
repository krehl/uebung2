package algebra;

public class algebra {

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
