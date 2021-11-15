package testBase;

import java.util.Random;

public class FunctionUtils {

	public static int GenerateRandomNumber(int withinBound)
	{
		Random random = new Random();
		return random.nextInt(withinBound);	 
	}
}
