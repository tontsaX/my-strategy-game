package gameproject.io;

public class Machine<T> implements IO<T> {
	
	T input;
	
	@Override
	public double time() {
		return System.currentTimeMillis();
	}

	@Override
	public void userInput(T input) {
		this.input = input;
	}
	
	@Override
	public T getUserInput() {
		return input;
	}
	
	@Override
	public void print(String line) {
		System.out.println(line);
	}
	
	@Override
	public void exitSystem() {
		System.exit(0);
	}
}
