package com.example.demo.pckg1;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class MeetingRepository {
	@Autowired
	IMeetingRepository meetingRepo;
	
	long DAY_IN_MS = 1000 * 60 * 60 * 24;
	/**
	 * 
	 * @param new meeting
	 * @return meeting object
	 */
	public Meeting addNewMeeting(Meeting meeting) {
		return meetingRepo.save(meeting);
	}
	
	/**
	 * 
	 * @return All Meetings
	 */
	public ArrayList<Meeting> getAllMeetings(){
		return (ArrayList<Meeting>) meetingRepo.findAll();
	}
	
	/**
	 * 
	 * @return All Meetings number
	 */
	public int getAllMeetingsNumbers(){
		return getAllMeetings().size();
	}
	
	
	////////////////// CHANGE IT TO CRITERIA YA MAALIM
	/**
	 * 
	 * @param courseId of the course 
	 * @return getting all the meeting of course
	 */
	public ArrayList<Meeting> getAllCourseMeetings(String courseId) {
		ArrayList<Meeting> meetings = (ArrayList<Meeting>) meetingRepo.findAll();
		ArrayList<Meeting> toReturn = new ArrayList<Meeting>();
		//meetings.stream().map(null);
		for (Meeting m : meetings) {
			if(m.getCourseId().equals(courseId)) toReturn.add(m);
		}
		return meetings;
	}
	/**
	 * 
	 * @param period
	 * @return
	 */
	public HashMap<String, Double> getTotalCancelledMeetingsTime(int period) {
		if(period != 1 && period !=2 && period !=3) {
			new ResponseEntity<>("Input: 1- For week 2- For month 3- For year.", HttpStatus.NOT_ACCEPTABLE);
			return null;
		}
		Date d;
		if(period == 1) {
			d = new Date((new Date()).getTime()- 7*DAY_IN_MS);
		}else if(period == 2) {
			d = new Date((new Date()).getTime()-35*DAY_IN_MS);
		}
		else {
			d = new Date((new Date()).getTime()- 365*DAY_IN_MS);
		}
		double totalTime = 0;
		double doneTime = 0;
		
		for(Meeting meeting : getAllMeetings()) {
			if(meeting.getMeetingDateTime().after(d)) {
				totalTime += meeting.getActualMeetingDuration();
				if(!meeting.isCancelled()) {
					doneTime += meeting.getActualMeetingDuration();
				}
			}
		}
		HashMap<String, Double> toReturn = new HashMap<String,Double>();
		toReturn.put("totalTime", totalTime);
		toReturn.put("activityTime", doneTime);
		return toReturn;
	}
	
	
	
	public int getAllMeetingsForDate(Date startDate, Date endDate){
		int all = 0;
		
		 for (Meeting m: getAllMeetings()) {
			 if (m.getMeetingDateTime().after(startDate) && m.getMeetingDateTime().before(endDate)) {
				 all++;
				
			 }
		 }	
		 return all;
	 }
	
	public double getAllMeetingsPerPeriod(int period){
		double all = 0;
		Date endDate=new Date();
		Date startDate=new Date();
		if(period==1) {
			 startDate=new Date(endDate.getTime() - 7*(DAY_IN_MS));
		}
		if(period==2) {
			 startDate=new Date(endDate.getTime() - 30*(DAY_IN_MS));
		}
		if(period==3) {
			 startDate=new Date(endDate.getTime() - 365*(DAY_IN_MS));

		}
	 for (Meeting m: getAllMeetings()) {
			 if (m.getMeetingDateTime().after(startDate) && m.getMeetingDateTime().before(endDate)) {
				 all++;
				
			 }
		 }	
		 return all;
	 }
	
	public int getHappenedMeetingsNumber(Date startDate, Date endDate){
		int happend = 0;
		 for (Meeting m: getAllMeetings()) {
			 if (m.getMeetingDateTime().after(startDate) && m.getMeetingDateTime().before(endDate)) {
				 if (m.isCancelled() == false) {
					 happend++;
				 }
			 }
		 }
		 return happend;
	 }
	
	public double getHappenedMeetingsNumberPerPeriod(int period){
		double happend = 0;
		Date endDate=new Date();
		Date startDate=new Date();
		if(period==1) {
			 startDate=new Date(endDate.getTime() - 7*(DAY_IN_MS));
		}
		if(period==2) {
			 startDate=new Date(endDate.getTime() - 30*(DAY_IN_MS));
		}
		if(period==3) {
			 startDate=new Date(endDate.getTime() - 365*(DAY_IN_MS));

		}
		 for (Meeting m: getAllMeetings()) {
			 if (m.getMeetingDateTime().after(startDate) && m.getMeetingDateTime().before(endDate)) {
				 if (m.isCancelled() == false) {
					 happend++;
				 }
			 }
		 }
		 return happend;
	 }
	
	
	public double getMeetingPercent(int period) {
		double happened=getHappenedMeetingsNumberPerPeriod(period);
		double all=getAllMeetingsPerPeriod(period);
		return (happened/all)*100;
	}
	
	
	public double[] meetingsChart(int period) {
		double[] results= {0,0,0};
		results[0]=getAllMeetingsPerPeriod(period);
		results[1]=getHappenedMeetingsNumberPerPeriod(period);
		results[2]=getMeetingPercent(period);
		
		return results;
		}
}
