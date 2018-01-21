/*
* represents a professor for UNF WC scheduling software
* and contains setters and getters that allow a Professor's data to be retrieved and manipulated
*/
public class Professor 
{
	
	private String name;
	private int courseLoad; // maximum number of courses a Prof can teach in a given semester
	private int numPreps; // maximum number of courses a Prof wants to prepare for a given semester
	private String coursePrefOne;// course that Prof most wants to be matched to 
	private String coursePrefTwo;
	private String coursePrefThree;
	private String coursePrefFour;
	private String coursePrefFive; // course that Prof fifth most wants to be matched to
	private String specialtyOne; // course type (prefix only regardless of level) that Prof is best suited to teach (and is used as a secondary matching attribute if they cannot be matched to a specific course in above course preferences)
	private String specialtyTwo;
	private String specialtyThree;
	private String dayAndTimePrefOne;   // days and times that Prof wants to teach on
	private String dayAndTimePrefTwo;
	private String dayAndTimePrefThree;
	private String dayAndTimePrefFour; //one, two, three, four, etc. correspond to specific sets of days, such as Monday/Wednesday, Tuesday/Thursday, Monday/Wednesday/Friday, etc. and are not ranked in any specific order
	private String dayAndTimePrefFive;  
	private String dayAndTimePrefSix;
	private String dayAndTimePrefSeven;
	private String dayAndTimePrefEight;
	private int timePrefOne;  // time at which Prof wants to teach (separate variable from dayAndTimePref to allow for int manipulation)  
	private int timePrefTwo;
	private int timePrefThree;
	private int timePrefFour; // time prefs also correspond to same specific sets of days and are not ranked in any order
	private int timePrefFive;
	private int timePrefSix;
	private int timePrefSeven;
	private int timePrefEight;
	private int timePrefNine;
	private int timePrefTen; 
	private String firstPref;  // preference that matters most to Prof (i.e. course, day, time, number of preps, or online/on campus)
	private String secondPref;
	private String thirdPref;
	private String fourthPref;
	private String fifthPref; // preference that matters least to Prof
	private String courseMatchOne;  // first course that Prof is matched to
	private String courseMatchTwo;
	private String courseMatchThree;
	private String courseMatchFour;  
	private String courseMatchFive;  //fifth course that Prof is matched to
	private double percentPrefMet;    // percentage of Prof's preferences that are met
	

	Professor (String startName, int startCourseLoad, int startNumPreps, String startCoursePrefOne, String startCoursePrefTwo, String startCoursePrefThree, String startCoursePrefFour, String startCoursePrefFive, 
		String startDayAndTimePrefOne, String startDayAndTimePrefTwo, String startDayAndTimePrefThree, String startDayAndTimePrefFour, String startDayAndTimePrefFive, String startDayAndTimePrefSix, String startDayAndTimePrefSeven, String startDayAndTimePrefEight, int startTimePrefOne, int startTimePrefTwo, int startTimePrefThree, 
		int startTimePrefFour, int startTimePrefFive, int startTimePrefSix, int startTimePrefSeven, int startTimePrefEight, int startTimePrefNine, int startTimePrefTen, String startFirstPref, String startSecondPref,	String startThirdPref, String startFourthPref, String startFifthPref, String startCourseMatchOne, String startCourseMatchTwo, String startCourseMatchThree, String startCourseMatchFour, String startCourseMatchFive, double startPercentPrefMet)
	{
	
		name = startName;
		courseLoad = startCourseLoad;
		numPreps = startNumPreps;
		coursePrefOne = startCoursePrefOne;
		coursePrefTwo = startCoursePrefTwo;
		coursePrefThree = startCoursePrefThree;
		coursePrefFour = startCoursePrefFour;
		coursePrefFive = startCoursePrefFive;
		specialtyOne = coursePrefOne.substring(0, 3);
		//finding course type (prefix) for specialtyTwo that has  not already been stored in specialtyOne
		if (!coursePrefTwo.substring(0, 3).equalsIgnoreCase(coursePrefOne.substring(0, 3)))
			specialtyTwo = coursePrefTwo.substring(0, 3);
		else if (!coursePrefTwo.substring(0, 3).equalsIgnoreCase(coursePrefThree.substring(0, 3)))
			specialtyTwo = coursePrefThree.substring(0, 3);
		else if (!coursePrefTwo.substring(0, 3).equalsIgnoreCase(coursePrefFour.substring(0, 3)))
			specialtyTwo = coursePrefFour.substring(0, 3);
		else if (!coursePrefTwo.substring(0, 3).equalsIgnoreCase(coursePrefFive.substring(0, 3)))
			specialtyTwo = coursePrefFive.substring(0, 3);
		else 
			specialtyTwo = coursePrefOne.substring(0, 3);
		//finding course type (prefix) for specialtyThree that has not already been stored in specialtyOne AND has not already been stored in specialtyTwo
		if (!coursePrefThree.substring(0, 3).equalsIgnoreCase(specialtyOne) && !coursePrefThree.substring(0, 3).equalsIgnoreCase(specialtyTwo))
			specialtyThree = coursePrefThree.substring(0, 3);
		else if (!coursePrefFour.substring(0, 3).equalsIgnoreCase(specialtyOne) && !coursePrefFour.substring(0, 3).equalsIgnoreCase(specialtyTwo))
			specialtyThree = coursePrefFour.substring(0, 3);
		else if (!coursePrefFive.substring(0, 3).equalsIgnoreCase(specialtyOne) && !coursePrefFive.substring(0, 3).equalsIgnoreCase(specialtyTwo))
			specialtyThree = coursePrefFive.substring(0, 3);
		else 
			specialtyThree = coursePrefOne.substring(0, 3);
			
		dayAndTimePrefOne = startDayAndTimePrefOne;
		dayAndTimePrefTwo = startDayAndTimePrefTwo;
		dayAndTimePrefThree = startDayAndTimePrefThree;
		dayAndTimePrefFour = startDayAndTimePrefFour;
		dayAndTimePrefFive = startDayAndTimePrefFive;
		dayAndTimePrefSix = startDayAndTimePrefSix;
		dayAndTimePrefSeven = startDayAndTimePrefSeven;
		dayAndTimePrefEight = startDayAndTimePrefEight;
		timePrefOne = startTimePrefOne;
		timePrefTwo = startTimePrefTwo;
		timePrefThree = startTimePrefThree;
		timePrefFour = startTimePrefFour;
		timePrefFive = startTimePrefFive;
		timePrefSix = startTimePrefSix;
		timePrefSeven = startTimePrefSeven;
		timePrefEight = startTimePrefEight;
		timePrefNine = startTimePrefNine;
		timePrefTen = startTimePrefTen;
		firstPref = startFirstPref;
		secondPref = startSecondPref;
		thirdPref = startThirdPref;
		fourthPref = startFourthPref;
		fifthPref = startFifthPref;
		courseMatchOne = startCourseMatchOne;
		courseMatchTwo = startCourseMatchTwo;
		courseMatchThree = startCourseMatchThree;
		courseMatchFour = startCourseMatchFour;
		courseMatchFive = startCourseMatchFive;
		percentPrefMet = startPercentPrefMet;
	}
	

	public String getName ()
	{
		return name;
	}
	
	public void changeName(String updateName)
	{
		name = updateName;
	}
	
	public int getCourseLoad ()
	{
		return courseLoad;
	}
	
	public void changeCourseLoad(int updateCourseLoad)
	{
		courseLoad = updateCourseLoad;
	}
	
	public int getNumPreps()
	{
		return numPreps;
	}
	
	public void changeNumPreps(int updateNumPreps)
	{
		numPreps = updateNumPreps;
	}

	public String getCoursePrefOne()
	{
		return coursePrefOne;
	}

	public void changeCoursePrefOne(String updateCoursePrefOne)
	{
		coursePrefOne = updateCoursePrefOne;
	}
	
	public String getCoursePrefTwo()
	{
		return coursePrefTwo;
	}

	public void changeCoursePrefTwo(String updateCoursePrefTwo)
	{
		coursePrefTwo = updateCoursePrefTwo;
	}
	
	public String getCoursePrefThree()
	{
		return coursePrefThree;
	}
	
	public void changeCoursePrefThree(String updateCoursePrefThree)
	{
		coursePrefThree = updateCoursePrefThree;
	}
	
	public String getCoursePrefFour()
	{
		return coursePrefFour;
	}

	public void changeCoursePrefFour(String updateCoursePrefFour)
	{
		coursePrefFour = updateCoursePrefFour;
	}
	
	public String getCoursePrefFive()
	{
		return coursePrefFive;
	}

	public void changeCoursePrefFive(String updateCoursePrefFive)
	{
		coursePrefFive = updateCoursePrefFive;
	}
	
	public String getSpecialtyOne()
	{
		return specialtyOne;
	}

	public void changeSpecialtyOne(String updateSpecialtyOne)
	{
		specialtyOne = updateSpecialtyOne;
	}
	
	public String getSpecialtyTwo()
	{
		return specialtyTwo;
	}
	
	public void changeSpecialtyTwo(String updateSpecialtyTwo)
	{
		specialtyTwo = updateSpecialtyTwo;
	}
	
	public String getSpecialtyThree()
	{
		return specialtyThree;
	}
	
	public void changeSpecialtyThree(String updateSpecialtyThree)
	{
		specialtyThree = updateSpecialtyThree;
	}
	
	public String getDayAndTimePrefOne()
	{
		return dayAndTimePrefOne;
	}
	
	public void changeDayAndTimePrefOne(String updateDayAndTimePrefOne)
	{
		dayAndTimePrefOne = updateDayAndTimePrefOne;
	}
	
	public String getDayAndTimePrefTwo()
	{
		return dayAndTimePrefTwo;
	}
	
	public void changeDayAndTimePrefTwo(String updateDayAndTimePrefTwo)
	{
		dayAndTimePrefTwo = updateDayAndTimePrefTwo;
	}
	
	public String getDayAndTimePrefThree()
	{
		return dayAndTimePrefThree;
	}
	
	public void changeDayAndTimePrefThree(String updateDayAndTimePrefThree)
	{
		dayAndTimePrefThree = updateDayAndTimePrefThree;
	}
	
	public String getDayAndTimePrefFour()
	{
		return dayAndTimePrefFour;
	}
	
	public void changeDayAndTimePrefFour(String updateDayAndTimePrefFour)
	{
		dayAndTimePrefFour = updateDayAndTimePrefFour;
	}
	
	public String getDayAndTimePrefFive()
	{
		return dayAndTimePrefFive;
	}
	
	public void changeDayAndTimePrefFive(String updateDayAndTimePrefFive)
	{
		dayAndTimePrefFive = updateDayAndTimePrefFive;
	}
	
	public String getDayAndTimePrefSix() 
	{	
		return dayAndTimePrefSix;
	}

	public void changeDayAndTimePrefSix(String updateDayAndTimePrefSix) 
	{
		dayAndTimePrefSix = updateDayAndTimePrefSix;
	}

	public String getDayAndTimePrefSeven() 
	{	
		return dayAndTimePrefSeven;
	}

	public void changeDayAndTimePrefSeven(String updateDayAndTimePrefSeven) 
	{
		dayAndTimePrefSeven = updateDayAndTimePrefSeven;
	}
	
	public String getDayAndTimePrefEight() 
	{	
		return dayAndTimePrefEight;
	}

	public void changeDayAndTimePrefEight(String updateDayAndTimePrefEight) 
	{
		dayAndTimePrefEight = updateDayAndTimePrefEight;
	}
	
	public int getTimePrefOne()
	{
		return timePrefOne;
	}
	
	public void changeTimePrefOne(int updateTimePrefOne)
	{
		timePrefOne = updateTimePrefOne;
	}
	
	public int getTimePrefTwo()
	{
		return timePrefTwo;
	}
	
	public void changeTimePrefTwo(int updateTimePrefTwo)
	{
		timePrefTwo = updateTimePrefTwo;
	}
	
	public int getTimePrefThree()
	{
		return timePrefThree;
	}
	
	public void changeTimePrefThree(int updateTimePrefThree)
	{
		timePrefThree = updateTimePrefThree;
	}
	
	public int getTimePrefFour()
	{
		return timePrefFour;
	}
	
	public void changeTimePrefFour(int updateTimePrefFour)
	{
		timePrefFour = updateTimePrefFour;
	}
	
	public int getTimePrefFive()
	{
		return timePrefFive;
	}
	
	public void changeTimePrefFive(int updateTimePrefFive)
	{
		timePrefFive = updateTimePrefFive;
	}
	
	public int getTimePrefSix()
	{
		return timePrefSix;
	}
	
	public void changeTimePrefSix(int updateTimePrefSix)
	{
		timePrefSix = updateTimePrefSix;
	}
	
	public int getTimePrefSeven()
	{
		return timePrefSeven;
	}
	
	public void changeTimePrefSeven(int updateTimePrefSeven)
	{
		timePrefSeven = updateTimePrefSeven;
	}
	
	public int getTimePrefEight()
	{
		return timePrefEight;
	}
	
	public void changeTimePrefEight(int updateTimePrefEight)
	{
		timePrefEight = updateTimePrefEight;
	}
	
	public int getTimePrefNine()
	{
		return timePrefNine;
	}
	
	public void changeTimePrefNine(int updateTimePrefNine)
	{
		timePrefNine = updateTimePrefNine;
	}
	
	public int getTimePrefTen()
	{
		return timePrefTen;
	}
	
	public void changeTimePrefTen(int updateTimePrefTen)
	{
		timePrefTen = updateTimePrefTen;
	}

	public String getFirstPref()
	{
		return firstPref;
	}
	
	public void changeFirstPref(String updateFirstPref)
	{
		firstPref = updateFirstPref;
	}

	public String getSecondPref()
	{
		return secondPref;
	}
	
	public void changeSecondPref(String updateSecondPref)
	{
		secondPref = updateSecondPref;
	}

	public String getThirdPref()
	{
		return thirdPref;
	}
	
	public void changeThirdPref(String updateThirdPref)
	{
		thirdPref = updateThirdPref;
	}
	
	public String getFourthPref()
	{
		return fourthPref;
	}
	
	public void changeFourthPref(String updateFourthPref)
	{
		fourthPref = updateFourthPref;
	}
	
	public String getFifthPref()
	{
		return fifthPref;
	}
	
	public void changeFifthPref(String updateFifthPref)
	{
		fifthPref = updateFifthPref;
	}

	public String getCourseMatchOne()
	{
		return courseMatchOne;
	}
	
	public void changeCourseMatchOne(String updateCourseMatchOne)
	{
		courseMatchOne = updateCourseMatchOne;
	}
	
	public String getCourseMatchTwo()
	{
		return courseMatchTwo;
	}
	
	public void changeCourseMatchTwo(String updateCourseMatchTwo)
	{
		courseMatchTwo = updateCourseMatchTwo;
	}
	
	public String getCourseMatchThree()
	{
		return courseMatchThree;
	}

	public void changeCourseMatchThree(String updateCourseMatchThree)
	{
		courseMatchThree = updateCourseMatchThree;
	}

	public String getCourseMatchFour()
	{
		return courseMatchFour;
	}

	public void changeCourseMatchFour(String updateCourseMatchFour)
	{
		courseMatchFour = updateCourseMatchFour;
	}

	public String getCourseMatchFive()
	{
		return courseMatchFive;
	}

	public void changeCourseMatchFive(String updateCourseMatchFive)
	{
		courseMatchFive = updateCourseMatchFive;
	}
	
	public double getPercentPrefMet()
	{
		return percentPrefMet;
	}

	public void changePercentPrefMet(double updatePercentPrefMet)
	{
		percentPrefMet = updatePercentPrefMet;
	}
	
	/*
	 * method primarily used in Professor class' subclasses to distinguish between TTF and NTTF when they are grouped together as Professor objects
	 */
	public boolean isTenureTrack()
	{
		return false;
	}
	
	/*
	 * displays all values of Professor's attributes
	 * 
	 */
	public String toString()
	{
		return ("Name: " + name + "\nCourse Load: " + courseLoad + "\nNumber of Preps: " + numPreps + "\nCourse Preference One: " + coursePrefOne + 
				"\nCourse Preference Two: " + coursePrefTwo + "\nCourse Preference Three: " + coursePrefThree + "\nCourse Preference Four: " + coursePrefFour + 
				"\nCourse Preference Five: " + coursePrefFive + "\nSpecialty One: " + specialtyOne + "\nSpecialty Two: " + specialtyTwo + "\nSpecialtyThree: " +specialtyThree +
				"\nDay Preference One: " + dayAndTimePrefOne + "\nDay Preference Two: " + dayAndTimePrefTwo + "\nDay Preference Three: " + dayAndTimePrefThree + "\nDay Preference Four: " + dayAndTimePrefFour +
				"\nDay Preference Five: " + dayAndTimePrefFive + "\nDay Preference Six: " + dayAndTimePrefSix + "\nDay Preference Seven: " + dayAndTimePrefSeven + "\nDay Preference Eight: " + dayAndTimePrefEight +"\nTime Preference One: " + timePrefOne + "\nTime Preference Two: " + timePrefTwo +
				"\nTime Preference Three: " + timePrefThree + "\nTime Preference Four: " + timePrefFour + "\nTime Preference Five: " + timePrefFive + 
				"\nTime Preference Six: " + timePrefSix + "\nTime Preference Seven: " + timePrefSeven + "\nTime Preference Eight: " + timePrefEight + 
				"\nTime Preference Nine: " + timePrefNine + "\nTime Preference Ten: " + timePrefTen + "\nFirst Preference: " + firstPref + "\nSecond Preference: " + secondPref +
				"\nThird Preference: " + thirdPref + "\nFourthPreference: " + fourthPref + "\nFifthPreference: " + fifthPref + "\nCourse Match One: " + courseMatchOne + "\nCourse Match Two: " + courseMatchTwo + "\nCourse Match Three: " + courseMatchThree +
				"\nCourse Match Four: " + courseMatchFour + "\nCourse Match Five: " + courseMatchFive + "\nPercent of Preferences Met: " + percentPrefMet + "\n");
		
	}// end toString()

}// end Professor class






