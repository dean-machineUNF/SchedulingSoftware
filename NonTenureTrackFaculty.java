
public class NonTenureTrackFaculty extends Professor 
{
	public NonTenureTrackFaculty(String startName, int startCourseLoad, int startNumPreps, String startCoursePrefOne, String startCoursePrefTwo, String startCoursePrefThree, String startCoursePrefFour, String startCoursePrefFive, 
			String startDayAndTimePrefOne, String startDayAndTimePrefTwo, String startDayAndTimePrefThree, String startDayAndTimePrefFour, String startDayAndTimePrefFive, String startDayAndTimePrefSix, String startDayAndTimePrefSeven, String startDayAndTimePrefEight, int startTimePrefOne, int startTimePrefTwo, int startTimePrefThree, 
			int startTimePrefFour, int startTimePrefFive, int startTimePrefSix, int startTimePrefSeven, int startTimePrefEight, int startTimePrefNine, int startTimePrefTen, String startFirstPref, String startSecondPref,	String startThirdPref, String startFourthPref, String startFifthPref, String startCourseMatchOne, String startCourseMatchTwo, String startCourseMatchThree, String startCourseMatchFour, String startCourseMatchFive, double startPercentPrefMet)
	{
		super (startName, startCourseLoad, startNumPreps, startCoursePrefOne, startCoursePrefTwo, startCoursePrefThree, startCoursePrefFour, startCoursePrefFive, 
				startDayAndTimePrefOne, startDayAndTimePrefTwo, startDayAndTimePrefThree, startDayAndTimePrefFour, startDayAndTimePrefFive, startDayAndTimePrefSix, startDayAndTimePrefSeven, startDayAndTimePrefEight, startTimePrefOne, startTimePrefTwo, startTimePrefThree, 
				startTimePrefFour, startTimePrefFive, startTimePrefSix, startTimePrefSeven, startTimePrefEight, startTimePrefNine, startTimePrefTen, startFirstPref, startSecondPref, startThirdPref, startFourthPref, startFifthPref, startCourseMatchOne, startCourseMatchTwo, startCourseMatchThree, startCourseMatchFour, startCourseMatchFive, startPercentPrefMet);
	}//end constructor
	
	public boolean isTenureTrack()
	{
		return false;
	}// end isTenureTrack()
	

}//end NonTenureTrackFaculty class
