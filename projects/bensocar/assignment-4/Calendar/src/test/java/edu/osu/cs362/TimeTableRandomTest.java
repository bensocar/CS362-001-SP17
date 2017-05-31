package edu.osu.cs362;

import java.util.Calendar;
import java.util.Random;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Test;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Random Test Generator  for TimeTable class.
 */

public class TimeTableRandomTest {
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;
    /**
     * Generate Random Tests that tests TimeTable Class.
     */
	@Test
	public void testRandom()  throws Throwable  {
		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		System.out.println("Start testing...");
		
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed = System.currentTimeMillis();
				Random random = new Random(randomseed);
				
				TimeTable tt = new TimeTable();
				
				LinkedList<Appt> appts = new LinkedList<Appt>();
				LinkedList<Appt> removedAppts = new LinkedList<Appt>();
				
				int amtAppts = ValuesGenerator.getRandomIntBetween(random, 1, 10);
				for(int i = 0; i < amtAppts; i++){
					int startHour = ValuesGenerator.getRandomIntBetween(random, -10, 30);
					int startMinute = ValuesGenerator.getRandomIntBetween(random, -10, 100);
					int startDay = ValuesGenerator.getRandomIntBetween(random, 0, 100);
					int startMonth = ValuesGenerator.getRandomIntBetween(random, 0, 100);
					int startYear = ValuesGenerator.getRandomIntBetween(random, 0, 3000);
					String title = ValuesGenerator.getString(random);
					String description = ValuesGenerator.getString(random);
					
					//Construct a new Appointment object with the initial data	 
					Appt appt = new Appt(startHour,
				        startMinute ,
				        startDay ,
				        startMonth ,
				        startYear ,
				        title,
				        description);
					
					appts.add(appt);
				}
				
				for(int i = 0; i < appts.size(); i++){
					int index = ValuesGenerator.getRandomIntBetween(random, -3, appts.size()-1);
					if(index == -3){
						removedAppts = tt.deleteAppt(null, appts.get(0));
					}
					else if(index == -2){
						removedAppts = tt.deleteAppt(appts, null);
					}
					else if(index == -1){
						int startHour = ValuesGenerator.getRandomIntBetween(random, -10, 30);
						int startMinute = ValuesGenerator.getRandomIntBetween(random, -10, 100);
						int startDay = ValuesGenerator.getRandomIntBetween(random, 0, 100);
						int startMonth = ValuesGenerator.getRandomIntBetween(random, 0, 100);
						int startYear = ValuesGenerator.getRandomIntBetween(random, 0, 3000);
						String title = ValuesGenerator.getString(random);
						String description = ValuesGenerator.getString(random);
						
						//Construct a new Appointment object with the initial data	 
						Appt newAppt = new Appt(startHour,
							startMinute ,
							startDay ,
							startMonth ,
							startYear ,
							title,
							description);
						removedAppts = tt.deleteAppt(appts, newAppt);
					}
					else{
						removedAppts = tt.deleteAppt(appts, appts.get(index));
					}
				}
				
				int startHour = ValuesGenerator.getRandomIntBetween(random, -10, 30);
				int startMinute = ValuesGenerator.getRandomIntBetween(random, -10, 100);
				int startDay = ValuesGenerator.getRandomIntBetween(random, 0, 100);
				int startMonth = ValuesGenerator.getRandomIntBetween(random, 0, 100);
				int startYear = ValuesGenerator.getRandomIntBetween(random, 0, 3000);
				String title = ValuesGenerator.getString(random);
				String description = ValuesGenerator.getString(random);
				
				 //Construct a new Appointment object with the initial data	 
				 Appt appt = new Appt(startHour,
				          startMinute ,
				          startDay ,
				          startMonth ,
				          startYear ,
				          title,
				         description);
				for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = ApptRandomTest.RandomSelectMethod(random);
					if (methodName.equals("setDescription")){
						String newDesc =(String) ValuesGenerator.getString(random);
						appt.setDescription(newDesc);						   
					}
				}
				
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			    if((iteration%10000)==0 && iteration!=0 )
					System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			 
			}
	 
	 
		 System.out.println("Done testing...");
	
			
	}	
}
