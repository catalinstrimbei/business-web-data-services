package org.demo.services;

import java.math.BigDecimal;
import java.util.Random;

public class DataFactoryUtils {
	public static Integer nextIntInRange(int min, int max, Random rng) {
		if (min > max) {
			throw new IllegalArgumentException(
					"Cannot draw random int from invalid range [" + min + ", "
							+ max + "].");
		}
		int diff = max - min;
		if (diff >= 0 && diff != Integer.MAX_VALUE) {
			return (min + rng.nextInt(diff + 1));
		}
		int i;
		do {
			i = rng.nextInt();
		} while (i < min || i > max);
		return i;
	}

	public static Double truncateDecimal(double x,int numberofDecimals){
	    if ( x > 0) {
	        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR).doubleValue();
	    } else {
	        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING).doubleValue();
	    }
	}
}
