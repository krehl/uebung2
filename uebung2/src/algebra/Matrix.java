package algebra;

public class Matrix {

	private Rational[][] data;
	
	private int m; //rows
	private int n; //columns
	
	public Matrix(Rational[][] data) {
		this.data = data;
		this.m = data.length;
		this.n = data[0].length;
	}
	
	public Matrix(Rational[] data) {
		this.data = new Rational[1][data.length];
		this.data[0] = data;
		this.m = 1;
		this.n = data.length;
	}
	
	public Matrix() {
		this.data = null;
		this.m = 0;
		this.n = 0;
	}
	
	public Matrix(int m, int n) {
		this.m = m;
		this.n = n;
		data = new Rational[m][n];
	}
	
	public Matrix(int n) {
		this.m = 1;
		this.n = n;
		data = new Rational[1][n];
	}
	
	public Matrix(int m, int n, Rational r) {
		this.m = m;
		this.n = n;
		this.data = new Rational[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				this.data[i][j] = r;
			}
		}
	}
	
	public Rational[][] getData() {
		return data;
	}
	
	public Rational getElement(int row, int column) {
		return data[row][column];
	}

	public Matrix setData(Rational[][] data) {
		this.data = data;
		this.m = data.length;
		this.n = data[0].length;
		return this;
	}
	
	public int getNumRows() {
		return this.m;
	}
	
	public int getNumCols() {
		return this.n;
	}
	
	public Matrix swap(int a, int b) {
		Rational[] tmp = this.data[a];
		this.data[a] = this.data[b];
		this.data[b] = tmp;
		return this;
	}
	
	public Matrix multrow(int row, Rational r) {
		for (int j = 0; j < n; j++) {
			this.data[row][j].mult(r);
		}
		return this;
	}
	
	public int max(int column, int start) {
		int max_index = start;
		Rational max = this.data[start][column].abs();
		for (int i = start + 1; i < this.m; i++) {
			if (max.compareTo(this.data[i][column].abs()) == -1) {// ***************
				max = this.data[i][column].abs();
				max_index = i;
			}
		}
		return max_index;
	}
	
	@Override
	public Matrix clone() {
		Matrix clone = new Matrix(m,n);
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            clone.data[i][j] = this.data[i][j];
	         }
	      }
	      clone.m = this.m;
	      clone.n = this.n;
	      return clone;
	}
	
	public boolean equals(Matrix matrix){
			for(int i=0;i<this.m;i++){
					for(int j=0;j<this.n;j++){
							if(!this.data[i][j].equals(matrix.data[i][j])) return false;
					}
			}
			return true;
	}
	
	@Override
	public String toString() {
		String s = "[\n";
		for (int i = 0; i < m; i++) {
			s += "[ ";
			for (int j = 0; j < n; j++) {
				s += this.data[i][j];
				s += " ";
			}
			s += "]\n";
		}
		s += "]";
		return s;
	}
	
	public Matrix setElement(Rational r, int row, int column) {
		data[row][column] = r;
		return this;
	}

	
	public static void main(String[] args) {
		Rational[][] A = {{new Rational(1,2), new Rational(2,3)},{new Rational(1,2), new Rational(2,3)},{new Rational(1,2), new Rational(2,3)}};
		Matrix B = new Matrix(A);
		System.out.println(A);
		System.out.println(B);
	}

}
