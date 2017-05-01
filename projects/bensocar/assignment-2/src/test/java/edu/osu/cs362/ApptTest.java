package edu.osu.cs362;

import org.junit.Test; 
import static org.junit.Assert.*; 
import java.util.EmptyStackException;


public class ApptTest {

    @Test
    public void test01() throws Throwable {
    	int startHour=10;
		int startMinute=15;
		int startDay=1;
		int startMonth=5;
		int startYear=2017;
		String title="Assignment Due";
		String description="Assignment 2";

		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
		
		assertTrue(appt.getValid());
		assertEquals(10, appt.getStartHour());
		assertEquals(15, appt.getStartMinute());
		assertEquals(1, appt.getStartDay());
		assertEquals(5, appt.getStartMonth());
		assertEquals(2017, appt.getStartYear());
		assertEquals("Assignment Due", appt.getTitle());
		assertEquals("Assignment 2", appt.getDescription()); 
    }
	
	@Test
	public void test02() throws Throwable{
		int startHour=10;
		int startMinute=15;
		int startDay=1;
		int startMonth=5;
		int startYear=2017;
		String title="Assignment Due";
		String description="Assignment 2";

		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
		
		//Test out of bounds with setStartHour
		appt.setStartHour(40);
		assertEquals(false, appt.getValid());
		appt.setStartHour(-10);
		assertEquals(false, appt.getValid());
	
		//Test tostring with invalid
		assertEquals(null, appt.toString());
		
		//Set back to valid entry
		appt.setStartHour(3);
		assertEquals(true, appt.getValid());
		
		//Test toString
		appt.setStartHour(13);
		appt.toString();
		
		appt.setStartHour(0);
		appt.toString();
		
		//Test out of bounds with setStartMinute
		appt.setStartMinute(90);
		assertEquals(false, appt.getValid());
		appt.setStartMinute(-10);
		assertEquals(false, appt.getValid());
		//Set back to valid entry
		appt.setStartMinute(3);
		assertEquals(true, appt.getValid());
		
		//Test out of bounds with setStartDay
		appt.setStartDay(40);
		assertEquals(false, appt.getValid());
		appt.setStartDay(-10);
		assertEquals(false, appt.getValid());
		//Set back to valid entry
		appt.setStartDay(3);
		assertEquals(true, appt.getValid());
		
		//Test out of bounds with setStartMonth
		appt.setStartMonth(40);
		assertEquals(false, appt.getValid());
		appt.setStartMonth(-10);
		assertEquals(false, appt.getValid());
		//Set back to valid entry
		appt.setStartMonth(3);
		assertEquals(true, appt.getValid());

		//Test out of bounds with setStartYear
		appt.setStartYear(2020);
		assertEquals(true, appt.getValid());
		
		//Test out of bounds with setTitle
		appt.setTitle(null);
		assertEquals("", appt.getTitle());
		appt.setTitle("Title");
		
		//Test empty description setDescription
		appt.setDescription(null);
		assertEquals("", appt.getDescription());
		appt.setDescription("Description");		
		
		
	}
}