package gameproject.io;

public class Console implements IO {
	
	public static void printCurrentThreadName(String startSentence) {
		System.out.println(startSentence + Thread.currentThread().getName());
	}
	
	@Override
	public double time() {
		return System.currentTimeMillis();
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
