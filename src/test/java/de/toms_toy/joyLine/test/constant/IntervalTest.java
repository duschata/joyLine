package de.toms_toy.joyLine.test.constant;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import de.toms_toy.joyLine.constant.Interval;

public class IntervalTest
{

	@Test
	public void getByName()
	{
		Interval interval_1 = Interval.getByName("#5");
		Assert.assertEquals(Interval.s5, interval_1);
		
		Logger.getLogger(getClass()).debug("interval: " + "#5" + " ist " + interval_1);
		
		Interval interval_2 = Interval.getByName("blafasel");
		Assert.assertNull(interval_2);
		
		
	}
}
