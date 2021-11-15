package Enums;

public class Enums {

	public enum SelectBy{
		VISIBLETEXT, INDEX, VALUE;
	}
	
	public enum TimeOutInSeconds{
		FIVE(5), TEN(10), FIFTEEN(15), TWENTY(20);
		 private int numVal;
		 TimeOutInSeconds(int numVal) {
		        this.numVal = numVal;
		    }

		    public int getNumVal() {
		        return numVal;
		    }
	}
}
