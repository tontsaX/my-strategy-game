package gameproject.io;

public interface IO<T> {
	
	public double time();
	
	public void userInput(T input);
	public T getUserInput();
	
	public void print(String line);
	public void exitSystem();
}
