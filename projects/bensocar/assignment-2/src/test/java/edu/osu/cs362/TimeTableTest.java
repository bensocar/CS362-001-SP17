package edu.osu.cs362;

import org.junit.Test; 
import static org.junit.Assert.*; 
import java.util.EmptyStackException;
import java.util.*;


public class TimeTableTest {

	@Test
	public void test0() throws Throwable{
		TimeTable tt = new TimeTable();
		LinkedList<Appt> appts = new LinkedList<Appt>();
		LinkedList<Appt> nullAppts = null;
		int startHour = 10;
		int startMinute = 30;
		int startDay = 15;
		int startMonth = 5;
		int startYear = 2017;
		String title = "Event!";
		String desc = "Description!";
		Appt nullAppt = null;
		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, desc);
		
		Calendar currCal = Calendar.getInstance();
		Calendar future = new GregorianCalendar(2017, 8, 8);
		Calendar future2 = new GregorianCalendar(2018, 8, 8);
		Calendar past1 = new GregorianCalendar(1990, 1, 1);
		Calendar past2 = new GregorianCalendar(1991, 1, 1);
		
		try{
			tt.getApptRange(appts, (GregorianCalendar)currCal, (GregorianCalendar)currCal);
		} catch(IllegalArgumentException e){}
		
		assertNull(tt.deleteAppt(appts, nullAppt));
		assertNull(tt.deleteAppt(nullAppts, appt));
		
		appt.setStartHour(100);
		assertNull(tt.deleteAppt(appts, appt));
		
		for(int i = 0; i < 5; i++){		
			appt.setStartHour(startHour + i);
			appts.add(appt);
		}

		assertNull(tt.deleteAppt(appts, nullAppt));
		
		tt.getApptRange(appts, (GregorianCalendar)currCal, (GregorianCalendar)future);
		assertNotNull(tt.deleteAppt(appts, appt));
		
		Appt fakeAppt = new Appt(1, 1, 1, 1, 1, "Fake", "Event");
		assertNull(tt.deleteAppt(appts, fakeAppt));
		
		tt.getApptRange(appts, (GregorianCalendar)past1, (GregorianCalendar)past2);
		tt.getApptRange(appts, (GregorianCalendar)future, (GregorianCalendar)future2);
	}
	
}