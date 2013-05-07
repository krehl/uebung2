package algebra;

public class Rational implements Comparable<Rational> {

	public static long ggt(long a, long b) {
		long h;
		while (!(b == 0)) {
			h = a%b;
			a=b;
			b=h;
		}
		return a;
	}
	
	private long zaehler = 0;
	private long nenner = 1;
	
	//private static Rational zero = new Rational(0,1);
	
	public long getZaehler() {
		return this.zaehler;
	}
	
	public long getNenner() {
		return this.nenner;
	}
	
	public Rational setZaehler(long l) {
		this.zaehler = l;
		return this;
	}
	
	public Rational setNenner(long l) {
		this.nenner = l;
		return this;
	}
	
	public Rational() {
		this.zaehler = 0;
		this.nenner = 1;
	}

	public Rational abs() {
		if (zaehler < 0) {
			return new Rational(-this.zaehler,this.nenner);
		}
		return new Rational(this.zaehler,this.nenner);
	}
	
	public Rational(long z) {
		this();
		this.zaehler = z;
	}
	
	public Rational(long z, long n) {
		this();
		long g = ggt(z,n);
		this.zaehler = z / g;
		this.nenner = n / g;
		if (nenner < 0) { nenner = -nenner; zaehler = -zaehler; }
	}
	
	public Rational (Rational r) {
		this();
		this.zaehler = r.zaehler;
		this.nenner = r.nenner;
	}
	
	public Rational set(Rational r) {
		this.zaehler = r.zaehler;
		this.nenner = r.nenner;
		return this;
	}
	
	public String toString() {
		if (this.zaehler == 0) {
			return "0";
		} if (this.nenner == 1) {
			return Long.toString(this.zaehler);
		}
		return Long.toString(this.zaehler) + "/" + Long.toString(this.nenner); 
	}
	
	public boolean equals(Rational r) { //muss noch überarbeitet werden
		return (this.zaehler*r.nenner) == (r.zaehler*this.nenner);
	}
	
	public Rational add(Rational r) {
		this.zaehler = this.zaehler * r.nenner + this.nenner * r.zaehler;
		this.nenner = this.nenner * r.nenner;
		long g = ggt(this.zaehler,this.nenner);
		this.zaehler = zaehler / g;
		this.nenner = nenner / g;
		if (nenner < 0) { nenner = -nenner; zaehler = -zaehler; }
		return this;
	}
	
	public static Rational add(Rational a, Rational b) {
		return new Rational(a).add(b);
	}
	
	public Rational sub(Rational r) {
		this.add(new Rational(-r.zaehler,r.nenner));
		return this;
	}
	
	public static Rational sub(Rational a, Rational b) {
		return new Rational(a).sub(b);
	}
	
	public Rational mult(Rational r) {
		this.zaehler *= r.zaehler;
		this.nenner *= r.nenner;
		long g = ggt(this.zaehler,this.nenner);
		this.zaehler = zaehler / g;
		this.nenner = nenner / g;
		if (nenner < 0) { nenner = -nenner; zaehler = -zaehler; }
		return this;
	}
	
	public static Rational mult (Rational a, Rational b) {
		return (new Rational(a)).mult(b);
	}
	
	public Rational div(Rational r) {
		this.zaehler *= r.nenner;
		this.nenner *= r.zaehler;
		long g = ggt(this.zaehler,this.nenner);
		this.zaehler = zaehler / g;
		this.nenner = nenner / g;
		if (nenner < 0) { nenner = -nenner; zaehler = -zaehler; }
		return this;
	}
	
	public static Rational div(Rational a, Rational b) {
		return (new Rational(a)).div(b);
	}
	
	
	public int compareTo(Rational b) {
        Rational a = this;
        long links = a.zaehler * b.nenner;
        long rechts = a.nenner * b.zaehler;
        if (links < rechts) return -1;
        if (links > rechts) return +1;
        return 0;
	}
	
	public static int compare(Rational a, Rational b) {
		return (new Rational(a)).compareTo(b);
	}
	
	public Rational clone() {
		return new Rational(this.zaehler, this.nenner);
	}
	
	public Rational kuerzen() {
		long a = ggt(this.zaehler, this.nenner);
		this.zaehler = this.zaehler / a;
		this.nenner = this.nenner / a;
		return this;
	}
	
	public static void main(String[] args) { //Test
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

        x = new Rational( 841, 961);
        y = new Rational( 3037547, 3246599);
        z = Rational.div(x,y);
        System.out.println(z);
        
        
        // 1/6 - -4/-8 = -1/3
        x = new Rational( 1,  6);
        y = new Rational(-4, -8);
        z = x.sub(y);
        System.out.println(z);
        
        //1/4
        x = new Rational( 1, 2);
        y = new Rational( 2, 1);
        z = x.div(y);
        System.out.println(z);
        //1/4
        x = new Rational( 1, 2);
        y = new Rational( 2, 1);
        z = Rational.div(x,y);
        System.out.println(z);
        //4
        x = new Rational( 2, 1);
        y = new Rational( 1, 2);
        z = x.div(y);
        System.out.println(z);
        //4
        x = new Rational( 2, 1);
        y = new Rational( 1, 2);
        z = Rational.div(x,y);
        System.out.println(z);
	}
}
