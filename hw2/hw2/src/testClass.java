
public class testClass {
	public static void main(String[] args) {
		
		Set<String> s  = new SetArray<String>();
		s.add("21");
		s.add("22");
		s.add("12");
		s.add("java");
		s.add("school");
		System.out.println(s.toString()); // test add
		s.remove("21");
		s.remove("10");
		System.out.println(s.toString()); // test remove
		if (s.isEmpty() == false){
			System.out.println("It is not empty"); // test isEmpty
		}
		if (s.contains("22") == true){
			System.out.println("The set contains it"); // test contains
		}
		Set<String> a  = new SetArray<String>();
		a.add("13");
		a.add("22");
		a.add("45");
		a.add("21");
		a.add("java");
		a.add("school");
		System.out.println(a.toString()); // test intersect
		System.out.println(s.toString());
			a.intersect(s);
			System.out.println("?");
		System.out.println(a.toString());
		System.out.println(s.toString());// test union
		
		a.union(s);
		System.out.println(a.toString()); // test intersect
		System.out.println(s.toString());
		
		
		
		
	}
}
