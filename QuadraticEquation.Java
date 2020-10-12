public class QuadraticEquation {
	public static Roots findRoots(double a, double b, double c) throws NoRootsExpection {
		int SqrtClac = b*b -4*a*c;
		if(SqrtClac < 0)
			throw new NoRootsExpection(“roots are complex”);
		else{
			int SqrtValue = Math.sqrt(SqrtClac);
			int root1 =( -b+ SqrtValue)/2*a;
			int root2 = (-b – SqrtValue)/2*a;
			Roots roots = new Roots(root1,root2);
			return roots;
		}
	}

public static void main(String[] args){
	Roots roots = QuadraticEquation.findRoots(2, 10, 8);
	System.out.println("Roots: " + roots.x1 + ", " + roots.x2);
}
}
