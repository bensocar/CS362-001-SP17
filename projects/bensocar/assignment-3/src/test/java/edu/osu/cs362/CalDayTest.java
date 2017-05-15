package edu.osu.cs362;

import org.junit.Test; 
import static org.junit.Assert.*; 
import java.util.EmptyStackException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalDayTest {

    @Test
    public void test0() throws Throwable {
    	CalDay cal = new CalDay();
		assertEquals(false, cal.isValid());
		assertEquals(null, cal.getAppts());
		assertEquals(null, cal.iterator());
		
		cal.toString();
    }
	
	@Test
	public void test1() throws Throwable{
		Calendar currCal = Calendar.getInstance();
		
		CalDay cal = new CalDay((GregorianCalendar)currCal);
		
		assertEquals(true, cal.isValid());
		assertEquals(0, cal.getSizeAppts());
		
		assertEquals(currCal.get(Calendar.DAY_OF_MONTH), cal.getDay());
		assertEquals(currCal.get(Calendar.MONTH), cal.getMonth());
		assertEquals(currCal.get(Calendar.YEAR), cal.getYear());
		
		int startHour = 10;
		int startMinute = 30;
		int startDay = 15;
		int startMonth = 5;
		int startYear = 2017;
		String title = "Event!";
		String desc = "Description!";
		
		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, desc);
		
		cal.addAppt(appt);
		assertEquals(1, cal.getSizeAppts());
		
		startHour = 15;
		Appt appt2 = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, desc);
		
		cal.addAppt(appt2);
		
		startHour = 99;
		Appt appt3 = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, desc);
		
		cal.addAppt(appt3);
		
		startHour = 1;
		Appt appt4 = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, desc);
		
		cal.addAppt(appt4);
		
		assertNotNull(cal.toString());
		assertNotNull(cal.iterator());
	}

}