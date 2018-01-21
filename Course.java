/*
 * represents a course for UNF WC scheduling software
 */
public class Course {

	private String name;
	private String prefix;
	private int level;
	private boolean isDup;
	private int numTimesOffered;
	private boolean hasBeenMatched;
	private String profMatchedTo;
	private String dayOffered;
	private int timeOffered;
	
	Course (String startName, String startPrefix, int startLevel, boolean startIsDup, int startnumTimesOffered, boolean startHasBeenMatched, String startProfMatchedTo, String startDayOffered, int startTimeOffered)
	{
		name = startName; // name of course
		prefix = startPrefix; // course type prefix
		level = startLevel; // level at which course is being taught
		isDup = startIsDup; // whether or not there are duplicates of course
		numTimesOffered = startnumTimesOffered; // reflects number of times the course is offered
		hasBeenMatched = startHasBeenMatched; //whether or not the course has been matched to a prof
		profMatchedTo = startProfMatchedTo; //prof course has been matched to
		dayOffered = startDayOffered; //day(s) the course is being offered
		timeOffered = startTimeOffered; //time(s) the course is being offered
	}
	
	public void changeName(String updateName)
	{
		name = updateName;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void changePrefix(String updatePrefix)
	{
		prefix = updatePrefix;
	}
	
	public String getPrefix()
	{
		return prefix;
	}
	
	public void changeLevel(int updateLevel)
	{
		level = updateLevel;
	}
	
	public int getLevel()
	{
		return level;
	}

	public void changeIsDup(boolean updateIsDup)
	{
		isDup = updateIsDup; 
	}
	
	public boolean getIsDup()
	{
		return isDup;
	}
	
	public void changeNumTimesOffered(int updateNumTimesOffered)
	{
		numTimesOffered = updateNumTimesOffered;
	}
	
	public int getNumTimesOffered()
	{
		return numTimesOffered;
	}
	
	public void changeHasBeenMatched(boolean updateHasBeenMatched)
	{
		hasBeenMatched = updateHasBeenMatched;
	}
	
	public boolean getHasBeenMatched()
	{
		return hasBeenMatched;
	}
	
	public void changeProfMatchedTo(String updateProfMatchedTo)
	{
		profMatchedTo = updateProfMatchedTo;
	}
	
	public String getProfMatchedTo()
	{
		return profMatchedTo;
	}
	
	public void changeDayOffered(String updateDayOffered)
	{
		dayOffered = updateDayOffered;
	}
	
	public String getDayOffered()
	{
		return dayOffered;
	}
	
	public void changeTimeOffered(int updateTimeOffered)
	{
		timeOffered = updateTimeOffered;
	}
	
	public int getTimeOffered()
	{
		return timeOffered;
	}
	
	public String toString()
	{
		return ("Name: " + name + "\nPrefix: " + prefix + "\nLevel: " + level + "\nIs a Duplicate: " + isDup + "\nNumber of times offered: " + numTimesOffered + "\nHas Been Matched: " + hasBeenMatched + "\nprofMatchedTo: " + profMatchedTo + "\nDay Offered: " + dayOffered + "\nTime Offered: " + timeOffered + "\n");
	}

}
