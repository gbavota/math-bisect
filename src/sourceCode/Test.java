package sourceCode;

public class Test {

	public String testString;
	public boolean isDelta;
	
	@Override
	public boolean equals(Object obj) {
		return this.testString.equals(((Test)obj).testString);
	}
	
}
