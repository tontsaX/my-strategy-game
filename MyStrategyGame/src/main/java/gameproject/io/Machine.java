package gameproject.io;

public class Machine implements IO {
	
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
