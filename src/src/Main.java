package src;

public class Main {

	public static void main(String[] args) {
		SIRModel x = new SIRModel(0.43, 0.18);
		x.createData();
		x.printGraph();
	}

}
