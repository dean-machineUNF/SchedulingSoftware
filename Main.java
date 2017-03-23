import java.io.IOException;

import jxl.read.biff.BiffException;

public class Main 
{
	DoublyLinkedList profDoublyLinkedList;

	public static void main(String[] args) throws IOException, BiffException 
	{
	
		
		/* 
		Professor testProf = new Professor("Smith", 4, 2, "CRW 3220", "CRW 2130", "CRW 4050", "CRW 2010", "AML 1040", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, "Course", "Day", "Time", "ENG 2020", "ENL 4010", "LIT 3530", "CRW 2010", "LIT 3130", .75);
		
		testProf.toString();
		
		//test setters
		testProf.changeName("name");
		testProf.changeCourseLoad(0);
		testProf.changeNumPreps(1);
		testProf.changeCoursePrefOne("CP1");
		testProf.changeCoursePrefTwo("CP2");
		testProf.changeCoursePrefThree("CP3");
		testProf.changeCoursePrefFour("Cp4");
		testProf.changeCoursePrefFive("CP5");
		testProf.changeSpecialtyOne("S1");
		testProf.changeSpecialtyTwo("S2");
		testProf.changeSpecialtyThree("S3");
		testProf.changeDayPrefOne("DP1");
		testProf.changeDayPrefTwo("DP2");
		testProf.changeDayPrefThree("DP3");
		testProf.changeDayPrefFour("DP4");
		testProf.changeDayPrefFive("DP5");
		testProf.changeTimePrefOne(2);
		testProf.changeTimePrefTwo(3);
		testProf.changeTimePrefThree(4);
		testProf.changeTimePrefFour(5);
		testProf.changeTimePrefFive(6);
		testProf.changeTimePrefSix(7);
		testProf.changeTimePrefSeven(8);
		testProf.changeTimePrefEight(9);
		testProf.changeTimePrefNine(10);
		testProf.changeTimePrefTen(11);
		testProf.changeFirstPref("FP");
		testProf.changeSecondPref("SP");
		testProf.changeThirdPref("TP");
		testProf.changeCourseMatchOne("CM1");
		testProf.changeCourseMatchTwo("CM2");
		testProf.changeCourseMatchThree("CM3");
		testProf.changeCourseMatchFour("CM4");
		testProf.changeCourseMatchFive("CM5");
		testProf.changePercentPrefMet(0.5);
		
		testProf.toString();
		
		//test getters
		System.out.println("Test Getters: " +
				testProf.getName() + 
		testProf.getCourseLoad() +
		testProf.getNumPreps() +
		testProf.getCoursePrefOne() +
		testProf.getCoursePrefTwo() +
		testProf.getCoursePrefThree() + 
		testProf.getCoursePrefFour() +
		testProf.getCoursePrefFive() +
		testProf.getSpecialtyOne() + 
		testProf.getSpecialtyTwo() +
		testProf.getSpecialtyThree() + 
		testProf.getDayPrefOne() +
		testProf.getDayPrefTwo() +
		testProf.getDayPrefThree() +
		testProf.getDayPrefFour() +
		testProf.getDayPrefFive() +
		testProf.getTimePrefOne() +
		testProf.getTimePrefTwo() +
		testProf.getTimePrefThree() + 
		testProf.getTimePrefFour() +
		testProf.getTimePrefFive() +
		testProf.getTimePrefSix() + 
		testProf.getTimePrefSeven() +
		testProf.getTimePrefEight() +
		testProf.getTimePrefNine() +
		testProf.getTimePrefTen() +
		testProf.getFirstPref() +
		testProf.getSecondPref() +
		testProf.getThirdPref() +
		testProf.getCourseMatchOne() +
		testProf.getCourseMatchTwo() +
		testProf.getCourseMatchThree() +
		testProf.getCourseMatchFour() +
		testProf.getCourseMatchFive() +
		testProf.getPercentPrefMet());
		
		*/
		
		/*
		System.out.println("Test TTF: ");
		
		TenureTrackFaculty testTenureTrackFaculty = new TenureTrackFaculty ("Smith", 4, 2, "CRW 3220", "CRW 2130", "CRW 4050", "CRW 2010", "AML 1040", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, "Course", "Day", "Time", "ENG 2020", "ENL 4010", "LIT 3530", "CRW 2010", "LIT 3130", .75);
		
		testTenureTrackFaculty.toString();
		
		testTenureTrackFaculty.changeCourseLoad(0);
		
		System.out.println("New Course Load: " + testTenureTrackFaculty.getCourseLoad());
		*/
		/*
		System.out.println("Test NTTF: ");
		
		NonTenureTrackFaculty testNonTenureTrackFaculty = new NonTenureTrackFaculty ("Smith", 4, 2, "CRW 3220", "CRW 2130", "CRW 4050", "CRW 2010", "AML 1040", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, "Course", "Day", "Time", "ENG 2020", "ENL 4010", "LIT 3530", "CRW 2010", "LIT 3130", .75);
		
		testNonTenureTrackFaculty.toString();
		
		testNonTenureTrackFaculty.changeCourseLoad(0);
		
		System.out.println("New Course Load: " + testNonTenureTrackFaculty.getCourseLoad());
		*/
		
		
		
		Input testInput = new Input();
		
		System.out.println("MAIN");
		
		DoublyLinkedList profDoublyLinkedList = new DoublyLinkedList(testInput.readProfessorData()); 
		
		System.out.println("MAIN");
		
		profDoublyLinkedList.displayForward();
		
		profDoublyLinkedList.displayBackward();
		
		//write Node and DoublyLinkedList classes
		
		//need to add Javadoc for all methods
	}

}
