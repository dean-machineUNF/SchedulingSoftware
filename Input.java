import java.io.File; 
import java.io.IOException;

import jxl.*; 
import jxl.read.biff.BiffException;

/*
 * Dean Rice
 * reads and parses input necessary for UNF WC Scheduling Software 
 */
public class Input 
{
		
	private TenureTrackFaculty newTTF;
	private NonTenureTrackFaculty newNTTF;
	private Professor[] tempProfArray;
	private int numOfProfsCount = 0;
	private Course newCourse;
	private Course[] tempCourseArray;
	private int numOfCoursesCount = 0;
	
	Workbook workbook;
	Sheet sheet; 
	
	public Course[] readCourseData() throws BiffException, IOException
	{
		workbook = Workbook.getWorkbook(new File("Courses_to_be_offered.xls"));
		sheet = workbook.getSheet(0);
		int tempArrayIndex = 0;
		int numRowsOfCourseData = 0; //used to iterate through the Course data
		int x = 1;// these x and y coordinates are where the course data starts in the file
		int y = 1;// x denotes column and y denotes row
		//Cell tempCell;
		/*
		Cell testCell = sheet.getCell(x,y);
		String test = tempCell.getContents();
		System.out.println(test);
		*/
		
		/*loop through entire data set to find total number of courses*/
		while (true)
		{
			/*test to make sure haven't reached end of data*/
			try
			{
				sheet.getCell(x,y);
				//System.out.println(tempCell.getContents());
			}// end try
			catch (Exception ArrayIndexOutofBoundsException)
			{
				System.out.println("DONE");
				break;
			}// end catch
			numRowsOfCourseData++;
			numOfCoursesCount += Integer.parseInt(sheet.getCell(3, y).getContents()); // allow room for duplicate courses in course array 
			System.out.println("number of courses " + numOfCoursesCount);
			y++;
		}// end while
		
		tempCourseArray = new Course[numOfCoursesCount];
		System.out.println("Array size " + tempCourseArray.length);
		
		//reset y to 1 to start at first row of Course data, only do loop statements for number of Courses
		for (y = 1; (y - 1) < numRowsOfCourseData; y++)
		{
			//System.out.println("inside for");
			x = 1;// reset x to first column of Course data for each iteration of loop
			
			//System.out.println("buildCourse called");
			tempCourseArray[tempArrayIndex] = buildCourse(x,y);// call method that builds Course and stores returned Course into array 
			
			
			/*test to see if newly inserted Course is offered more the once and has duplicates, then build duplicates*/
			if (tempCourseArray[tempArrayIndex].getNumTimesOffered() > 1)
			{
				
				//System.out.println("inside if");
				tempCourseArray[tempArrayIndex].changeIsDup(true);
				
				/*counter used to iterate through while loop X times and 
				build X duplicate courses for any course that is offered more than once*/
				int tempCounterOfDups = tempCourseArray[tempArrayIndex].getNumTimesOffered(); 
				while (/*tempCourseArray[tempArrayIndex + i] != null && */tempCourseArray[tempArrayIndex].getIsDup())
				{
					//System.out.println("inside while");
					tempArrayIndex++;
					tempCourseArray[tempArrayIndex] = buildCourse(x,y);
					tempCourseArray[tempArrayIndex].changeIsDup(true);//set new course as a duplicate
					tempCounterOfDups--;
					if (tempCounterOfDups <= 1)
					{
						tempCourseArray[tempArrayIndex].changeIsDup(false);//change to false to break out of while loop
						//System.out.println("Inside nested if");
					}
					
					//System.out.println("out of nested if");
					
				}// end while
				
				//System.out.println("out of while");
				
				tempCourseArray[tempArrayIndex].changeIsDup(true);// change back to true
				
			
			}// end if
			
			//System.out.println("out of if");
			tempArrayIndex++;
				
		}// end for	
		
		//System.out.println("out of for");
		
		for (int i = 0; i < numOfCoursesCount; i++)
		{
			System.out.println("\n" + "TEST OF Courses" + "\n" + tempCourseArray[i]);
		}// end for
		
	
		return tempCourseArray;
		
	}// end readCourseData()
	
	/*
	 * accepts two integers, these integers represent x and y coordinates within the excel file 
	 * reads and parses data necessary to build to a Course object from "Courses_To_Be_Offered.xls"
	 * returns one Course object
	 */
	public Course buildCourse(int passX, int passY)
	{
		int x = passX;
		int y = passY;
		newCourse = new Course(sheet.getCell(1,y).getContents() + " " + sheet.getCell(2,y).getContents(), sheet.getCell(1,y).getContents(), 
				Integer.parseInt(sheet.getCell(2,y).getContents()), false, Integer.parseInt(sheet.getCell(3,y).getContents()), false, "Not Matched Yet",
				sheet.getCell(4,y).getContents(), Integer.parseInt(sheet.getCell(5,y).getContents())); 
		
		System.out.println("Course Built in buildCourse");
		return newCourse;
	}//end Course()
	
	
	/*
	 * reads and parses TTF and NTTF data from "English+Faculty+Survey.xls" and calls appropriate method for building a TenureTrackFaculty or a NonTenureTrackFaculty
	 * returns one array containing Professor objects (some are TenureTrackFaculty objects and some are NonTenureTrackFaculty objects)
	 */
	public Professor[] readProfessorData() throws IOException, BiffException 
	{
		
		workbook = Workbook.getWorkbook(new File("English+Faculty+Survey.xls"));
		sheet = workbook.getSheet(0);
		int tempArrayIndex = 0;
		int x = 14;// these x and y coordinates are where the professor data starts in the file
		int y = 3;// x denotes column and y denotes row
		//Cell tempCell;
		/*
		Cell testCell = sheet.getCell(x,y);
		String test = tempCell.getContents();
		System.out.println(test);
		*/
		
		/*loop through entire data set to find total number of Profs*/
		while (true)
		{
			/*test to make sure haven't reached end of data*/
			try
			{
				sheet.getCell(x,y);
				//System.out.println(tempCell.getContents());
			}// end try
			catch (Exception ArrayIndexOutofBoundsException)
			{
				System.out.println("DONE");
				break;
			}// end catch
			numOfProfsCount++;
			y++;
		}// end while
		//System.out.println("Number of iterations of while " + numOfProfsCount);
		tempProfArray = new Professor[numOfProfsCount];
		
		//reset y to 3 to start at first row of Prof data, only do loop statements for number of Profs
		for (y = 3; (y - 3) < numOfProfsCount; y++)
		{
			x = 14;// reset x to first column of Prof data for each iteration of loop
			//test to see if data in this row is for a TTF
			if (sheet.getCell(x,y).getContents().equalsIgnoreCase("TTF") || sheet.getCell(x, y).getContents().contains(" TTF") ||
					sheet.getCell(x,y).getContents().startsWith("T"))
			{
				tempProfArray[tempArrayIndex] = buildTTF(x,y);// call method that builds TTF and store returned TTF into array 
				tempArrayIndex++;
				//newTTF.toString();	
			}// end if
			//test to see if row contains data for a NTTF
			else if (sheet.getCell(x,y).getContents().contains("NTTF") || sheet.getCell(x,y).getContents().startsWith("N") || 
					sheet.getCell(x,y).getContents().contains("Non"))
			{
				tempProfArray[tempArrayIndex] = buildNTTF(x,y);// call method that builds TTF and stores returned TTF into array 
				tempArrayIndex++;
				//newNTTF.toString();
			}// end else if
				
		}// end for	
		
		/*
		for (int i = 0; i < 3; i++)
		{
			System.out.println("TEST OF TTF's");
			tempProfArray[i].toString();
		}// end for
		*/
		
		return tempProfArray;
		
	}// end readProfessor()
	
	
	
	/*
	 * accepts two integers, these integers represent x and y coordinates within the excel file 
	 * reads and parses data necessary to build to a TenureTrackFaculty object from "English+Faculty+Survey.xls"
	 * returns one TenureTrackFaculty object
	 */
	public TenureTrackFaculty buildTTF(int passX, int passY)
	{
		int x = passX;
		int y = passY;
	
		/*build new TTF object and pass some Professor data as parameters of TTF object*/
		newTTF = new TenureTrackFaculty(sheet.getCell(16,y).getContents(), Integer.parseInt(sheet.getCell(15,y).getContents()), 
				Integer.parseInt(sheet.getCell(70,y).getContents()), sheet.getCell(17,y).getContents(), sheet.getCell(18,y).getContents(),
				sheet.getCell(19,y).getContents(), sheet.getCell(20,y).getContents(), sheet.getCell(21,y).getContents(), 
				"blank", "blank", "blank", "blank", "blank", "blank", "blank", "blank", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
				"71", "72", "73", "74", "75", "blank", "blank", "blank", "blank", "blank", 0.0);
		
		/*Test print*/
		/*
		System.out.println("TTF built");
		System.out.println(newTTF.getCourseLoad());
		System.out.println(newTTF.getName());
		System.out.println(newTTF.getNumPreps());
		System.out.println(newTTF.getCoursePrefOne());
		System.out.println(newTTF.getCoursePrefTwo());
		System.out.println(newTTF.getCoursePrefThree());
		System.out.println(newTTF.getCoursePrefFour());
		System.out.println(newTTF.getCoursePrefFive());
		8/
		
		/*parse and store Professor data that could not be passed as param, starting with day and time preferences*/
		String yes = "answer"; // per clients data sample, yes is represented as "Answer 1" in day and time preferences columns in Professor data excel*/ 
		String no = "no";
		x += 8;// moves x coordinate because day and time preferences start at column 22
		/*use array to temporarily hold dayAndTimePref data that needs to be stored to TTF so that loop can be used*/
		String dayAndTimePrefOne = "MWF";
		String dayAndTimePrefTwo = "MW ";
		String dayAndTimePrefThree = "M  ";
		String dayAndTimePrefFour = "TR ";
		String dayAndTimePrefFive = "T  ";
		String dayAndTimePrefSix = "W  ";
		String dayAndTimePrefSeven = "R  ";
		String dayAndTimePrefEight = "F  ";
		String [] dayAndTimePrefsArr = new String[]{dayAndTimePrefOne, dayAndTimePrefTwo, dayAndTimePrefThree, dayAndTimePrefFour, dayAndTimePrefFive, dayAndTimePrefSix, dayAndTimePrefSeven, dayAndTimePrefEight}; 
		int arrIndexCounter = 0;// increments for each iteration of while loop below to move to next dayAndTimePref temp variable  
		/*while loop iterates through columns containing dayAndTimePref data*/ 
		while (sheet.getCell(x,y).getContents().toLowerCase().contains(yes) || sheet.getCell(x,y).getContents().toLowerCase().contains(no))
		{
			if (sheet.getCell(x,y).getContents().toLowerCase().contains(yes))
				dayAndTimePrefsArr[arrIndexCounter] = dayAndTimePrefsArr[arrIndexCounter].concat(", 8 - 10");
			if (sheet.getCell(x + 1,y).getContents().toLowerCase().contains(yes))
				dayAndTimePrefsArr[arrIndexCounter] = dayAndTimePrefsArr[arrIndexCounter].concat(", 10 - 12");
			if (sheet.getCell(x + 2,y).getContents().toLowerCase().contains(yes))
				dayAndTimePrefsArr[arrIndexCounter] = dayAndTimePrefsArr[arrIndexCounter].concat(", 12 - 2");
			if (sheet.getCell(x + 3,y).getContents().toLowerCase().contains(yes))
				dayAndTimePrefsArr[arrIndexCounter] = dayAndTimePrefsArr[arrIndexCounter].concat(", 2 - 4");
			if (sheet.getCell(x + 4,y).getContents().toLowerCase().contains(yes))
				dayAndTimePrefsArr[arrIndexCounter] = dayAndTimePrefsArr[arrIndexCounter].concat(", 4 - 6");
			if (sheet.getCell(x + 5,y).getContents().toLowerCase().contains(yes))
				dayAndTimePrefsArr[arrIndexCounter] = dayAndTimePrefsArr[arrIndexCounter].concat(", 6 - 8");
		
			x += 6; //moves x coordinate to read times for next set of days
			arrIndexCounter++;		
			
		}// end while
		
		newTTF.changeDayAndTimePrefOne(dayAndTimePrefsArr[0]);
		newTTF.changeDayAndTimePrefTwo(dayAndTimePrefsArr[1]);
		newTTF.changeDayAndTimePrefThree(dayAndTimePrefsArr[2]);
		newTTF.changeDayAndTimePrefFour(dayAndTimePrefsArr[3]);
		newTTF.changeDayAndTimePrefFive(dayAndTimePrefsArr[4]);
		newTTF.changeDayAndTimePrefSix(dayAndTimePrefsArr[5]);
		newTTF.changeDayAndTimePrefSeven(dayAndTimePrefsArr[6]);
		newTTF.changeDayAndTimePrefEight(dayAndTimePrefsArr[7]);
		
		/*
		 * Test Print
		System.out.println("Test: "+ newTTF.getDayAndTimePrefOne());
		System.out.println("Test: "+ newTTF.getDayAndTimePrefTwo());
		System.out.println("Test: "+ newTTF.getDayAndTimePrefThree());
		System.out.println("Test: "+ newTTF.getDayAndTimePrefFour());
		System.out.println("Test: "+ newTTF.getDayAndTimePrefFive());
		System.out.println("Test: "+ newTTF.getDayAndTimePrefSix());
		System.out.println("Test: "+ newTTF.getDayAndTimePrefSeven());
		System.out.println("Test: "+ newTTF.getDayAndTimePrefEight());
		*/
		
		/*assign values to timePref attributes using data in dayAndTime attributes*/
		/*use dayAndTimePrefsArr and new array to temporarily hold timePref data that needs to be stored to TTF so that loop can be used*/
		String timePrefOne = "0";
		String timePrefTwo = "0";
		String timePrefThree = "0";
		String timePrefFour = "0";
		String timePrefFive = "0";
		String timePrefSix = "0";
		String timePrefSeven = "0";
		String timePrefEight = "0";
		String [] timePrefsArr = new String[]{timePrefOne, timePrefTwo, timePrefThree, timePrefFour, timePrefFive, timePrefSix, timePrefSeven, timePrefEight};
		String [] tempStringArr = new String[7];
		String [] tempStringArr2 = new String[7];
		
		for (int i = 0; i < 8; i++)
		{
			if (dayAndTimePrefsArr[i].length() > 5 && dayAndTimePrefsArr[i].length() < 13)/*this tests to see how many time prefs are contained in the dayAndTime
				attribute and reassigns them to temp time pref attributes */
			{
				tempStringArr = dayAndTimePrefsArr[i].split(",");
				tempStringArr2 = tempStringArr[1].split(" ");/*split by "," and " " so that only integers are saved to temp time 
				prefs so that they can be parsed to int and stored in Profs time pref attributes*/
				timePrefsArr[i] = tempStringArr2[1];
				//System.out.println("Test for if one time pref: " + timePrefsArr[i]);
			}//end if	
			else if (dayAndTimePrefsArr[i].length() > 5 && dayAndTimePrefsArr[i].length() < 21)
			{
				tempStringArr = dayAndTimePrefsArr[i].split(",");
				tempStringArr2 = tempStringArr[1].split(" ");
				timePrefsArr[i] = tempStringArr2[1];
				tempStringArr2 = tempStringArr[2].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				//System.out.println("Test for if two time prefs: " + timePrefsArr[i]);
			}// end if 
			else if (dayAndTimePrefsArr[i].length() > 5 && dayAndTimePrefsArr[i].length() < 30)
			{
				tempStringArr = dayAndTimePrefsArr[i].split(",");
				tempStringArr2 = tempStringArr[1].split(" ");
				timePrefsArr[i] = tempStringArr2[1];
				tempStringArr2 = tempStringArr[2].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[3].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				//System.out.println("Test for if three time prefs: " + timePrefsArr[i]);
			}// end if
			else if (dayAndTimePrefsArr[i].length() > 5 && dayAndTimePrefsArr[i].length() < 37)
			{
				tempStringArr = dayAndTimePrefsArr[i].split(",");
				tempStringArr2 = tempStringArr[1].split(" ");
				timePrefsArr[i] = tempStringArr2[1];
				tempStringArr2 = tempStringArr[2].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[3].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[4].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				//System.out.println("Test for if four time prefs: " + timePrefsArr[i]);
			}// end if 
			else if (dayAndTimePrefsArr[i].length() > 5 && dayAndTimePrefsArr[i].length() < 46)
			{
				tempStringArr = dayAndTimePrefsArr[i].split(",");
				tempStringArr2 = tempStringArr[1].split(" ");
				timePrefsArr[i] = tempStringArr2[1];
				tempStringArr2 = tempStringArr[2].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[3].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[4].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[5].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				//System.out.println("Test for if five time prefs: " + timePrefsArr[i]);
			}	// end if
			else if (dayAndTimePrefsArr[i].length() > 5 && dayAndTimePrefsArr[i].length() < 52)
			{
				tempStringArr = dayAndTimePrefsArr[i].split(",");
				tempStringArr2 = tempStringArr[1].split(" ");
				timePrefsArr[i] = tempStringArr2[1];
				tempStringArr2 = tempStringArr[2].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[3].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[4].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[5].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[6].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				//System.out.println("Test for if six time prefs: " + timePrefsArr[i]);
			}// end if
		}// end for
		//assign temp time prefs to Prof timePref attributes 
		newTTF.changeTimePrefOne(Integer.parseInt(timePrefsArr[0]));
		newTTF.changeTimePrefTwo(Integer.parseInt(timePrefsArr[1]));
		newTTF.changeTimePrefThree(Integer.parseInt(timePrefsArr[2]));
		newTTF.changeTimePrefFour(Integer.parseInt(timePrefsArr[3]));
		newTTF.changeTimePrefFive(Integer.parseInt(timePrefsArr[4]));
		newTTF.changeTimePrefSix(Integer.parseInt(timePrefsArr[5]));
		newTTF.changeTimePrefSeven(Integer.parseInt(timePrefsArr[6]));
		newTTF.changeTimePrefEight(Integer.parseInt(timePrefsArr[7]));
		
		/*
		 * Test Print
		System.out.println(newTTF.getTimePrefOne());
		System.out.println(newTTF.getTimePrefTwo());
		System.out.println(newTTF.getTimePrefThree());
		System.out.println(newTTF.getTimePrefFour());
		System.out.println(newTTF.getTimePrefFive());
		System.out.println(newTTF.getTimePrefSix());
		System.out.println(newTTF.getTimePrefSeven());
		System.out.println(newTTF.getTimePrefEight());
		*/
		
		/*assign order of preferences using data in columns 71 -75*/ 
		/*use arrays to temporarily hold preference ranking data that needs to be stored to TTF so that loop can be used*/
		String prefOne = "blank";
		String prefTwo = "blank";
		String prefThree = "blank";
		String prefFour = "blank";
		String prefFive = "blank";
		String[] rankOfPreferences = new String[]{prefOne, prefTwo, prefThree, prefFour, prefFive};
		String coursePrefered = "course";
		String daysPrefered = "days";
		String timesPrefered = "times";
		String numPrepsPreferred = "number of preps";
		String onlineOrOnCampusPreferred = "online or on campus";
		String[] preferences = new String[]{coursePrefered, daysPrefered, timesPrefered, numPrepsPreferred, onlineOrOnCampusPreferred};
		try
		{
		for (int i = 0; i < 5; i++)// 5 preferences being ranked so check 5 columns (71-75)
			rankOfPreferences[Integer.parseInt(sheet.getCell(71 + i,y).getContents().trim()) - 1] = preferences[i];
		}// end try
		catch (NumberFormatException e)
		{
			//do nothing because number format exception means no data was entered for this column 
		}// end catch
		
		newTTF.changeFirstPref(rankOfPreferences[0]);
		newTTF.changeSecondPref(rankOfPreferences[1]);
		newTTF.changeThirdPref(rankOfPreferences[2]);
		newTTF.changeFourthPref(rankOfPreferences[3]);
		newTTF.changeFifthPref(rankOfPreferences[4]);
		
		/*
		 * Test Print
		System.out.println(newTTF.getFirstPref());
		System.out.println(newTTF.getSecondPref());
		System.out.println(newTTF.getThirdPref());
		System.out.println(newTTF.getFourthPref());
		System.out.println(newTTF.getFifthPref());
		*/
		return newTTF;
	}// end buildTTF()
	
	public NonTenureTrackFaculty buildNTTF(int passX, int passY)
	{
		int x = passX;
		int y = passY;
	
		/*build new TTF object and pass some Professor preference response data as parameters of TTF object*/
		newNTTF = new NonTenureTrackFaculty(sheet.getCell(16,y).getContents(), Integer.parseInt(sheet.getCell(15,y).getContents()), 
				Integer.parseInt(sheet.getCell(70,y).getContents()), sheet.getCell(17,y).getContents(), sheet.getCell(18,y).getContents(), 
				sheet.getCell(19,y).getContents(), sheet.getCell(20,y).getContents(), sheet.getCell(21,y).getContents(), "blank", "blank", "blank",
				"blank", "blank", "blank", "blank", "blank", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "71", "72", "73", "74", "75", "blank", "blank", "blank",
				"blank", "blank", 0.0);
		
		/*Test print*/
		/*
		System.out.println("NTTF built");
		System.out.println(newNTTF.getCourseLoad());
		System.out.println(newNTTF.getName());
		System.out.println(newNTTF.getNumPreps());
		System.out.println(newNTTF.getCoursePrefOne());
		System.out.println(newNTTF.getCoursePrefTwo());
		System.out.println(newNTTF.getCoursePrefThree());
		System.out.println(newNTTF.getCoursePrefFour());
		System.out.println(newNTTF.getCoursePrefFive());
		*/
		
		/*parse and store Professor data that could not be passed as param, starting with day and time preferences*/
		String yes = "answer"; /* yes is represented as "Answer 1" day and time preferences columns in Prof data*/ 
		String no = "no";
		x += 8;// day and time preferences start at column 22
		/*use array to temporarily hold dayAndTimePref data that needs to be stored to NTTF so that loop can be used*/
		String dayAndTimePrefOne = "MWF";// columns 22 - 27
		String dayAndTimePrefTwo = "MW ";// columns 28 - 33
		String dayAndTimePrefThree = "M  ";
		String dayAndTimePrefFour = "TR ";
		String dayAndTimePrefFive = "T  ";
		String dayAndTimePrefSix = "W  ";
		String dayAndTimePrefSeven = "R  ";
		String dayAndTimePrefEight = "F  ";
		String [] dayAndTimePrefsArr = new String[]{dayAndTimePrefOne, dayAndTimePrefTwo, dayAndTimePrefThree, dayAndTimePrefFour, dayAndTimePrefFive, 
				dayAndTimePrefSix, dayAndTimePrefSeven, dayAndTimePrefEight}; 
		int arrIndexCounter = 0;// increments for each iteration of while loop below to move to next dayAndTimePref temp variable  
		/*while loop iterates through columns containing dayAndTimePref data*/ 
		while (sheet.getCell(x,y).getContents().toLowerCase().contains(yes) || sheet.getCell(x,y).getContents().toLowerCase().contains(no))
		{
			if (sheet.getCell(x,y).getContents().toLowerCase().contains(yes))
				dayAndTimePrefsArr[arrIndexCounter] = dayAndTimePrefsArr[arrIndexCounter].concat(", 8 - 10");
			if (sheet.getCell(x + 1,y).getContents().toLowerCase().contains(yes))
				dayAndTimePrefsArr[arrIndexCounter] = dayAndTimePrefsArr[arrIndexCounter].concat(", 10 - 12");
			if (sheet.getCell(x + 2,y).getContents().toLowerCase().contains(yes))
				dayAndTimePrefsArr[arrIndexCounter] = dayAndTimePrefsArr[arrIndexCounter].concat(", 12 - 2");
			if (sheet.getCell(x + 3,y).getContents().toLowerCase().contains(yes))
				dayAndTimePrefsArr[arrIndexCounter] = dayAndTimePrefsArr[arrIndexCounter].concat(", 2 - 4");
			if (sheet.getCell(x + 4,y).getContents().toLowerCase().contains(yes))
				dayAndTimePrefsArr[arrIndexCounter] = dayAndTimePrefsArr[arrIndexCounter].concat(", 4 - 6");
			if (sheet.getCell(x + 5,y).getContents().toLowerCase().contains(yes))
				dayAndTimePrefsArr[arrIndexCounter] = dayAndTimePrefsArr[arrIndexCounter].concat(", 6 - 8");
		
			x += 6;
			arrIndexCounter++;		
			
		}// end while
		
		newNTTF.changeDayAndTimePrefOne(dayAndTimePrefsArr[0]);
		newNTTF.changeDayAndTimePrefTwo(dayAndTimePrefsArr[1]);
		newNTTF.changeDayAndTimePrefThree(dayAndTimePrefsArr[2]);
		newNTTF.changeDayAndTimePrefFour(dayAndTimePrefsArr[3]);
		newNTTF.changeDayAndTimePrefFive(dayAndTimePrefsArr[4]);
		newNTTF.changeDayAndTimePrefSix(dayAndTimePrefsArr[5]);
		newNTTF.changeDayAndTimePrefSeven(dayAndTimePrefsArr[6]);
		newNTTF.changeDayAndTimePrefEight(dayAndTimePrefsArr[7]);
		
		/*
		System.out.println("Test: "+ newNTTF.getDayAndTimePrefOne());
		System.out.println("Test: "+ newNTTF.getDayAndTimePrefTwo());
		System.out.println("Test: "+ newNTTF.getDayAndTimePrefThree());
		System.out.println("Test: "+ newNTTF.getDayAndTimePrefFour());
		System.out.println("Test: "+ newNTTF.getDayAndTimePrefFive());
		System.out.println("Test: "+ newNTTF.getDayAndTimePrefSix());
		System.out.println("Test: "+ newNTTF.getDayAndTimePrefSeven());
		System.out.println("Test: "+ newNTTF.getDayAndTimePrefEight());
		*/
		
		/*assign values to timePref attributes using data in dayAndTime attributes*/
		/*use dayAndTimePrefsArr and new array to temporarily hold timePref data that needs to be stored to NTTF so that loop can be used*/
		String timePrefOne = "0";
		String timePrefTwo = "0";
		String timePrefThree = "0";
		String timePrefFour = "0";
		String timePrefFive = "0";
		String timePrefSix = "0";
		String timePrefSeven = "0";
		String timePrefEight = "0";
		String [] timePrefsArr = new String[]{timePrefOne, timePrefTwo, timePrefThree, timePrefFour, timePrefFive, timePrefSix, timePrefSeven, timePrefEight};
		String [] tempStringArr = new String[7];
		String [] tempStringArr2 = new String[7];
		for (int i = 0; i < 8; i++)
		{
			if (dayAndTimePrefsArr[i].length() > 5 && dayAndTimePrefsArr[i].length() < 12)/*this tests to see how many time prefs are contained in the 
			dayAndTime attribute and reassigns them to temp time pref attributes */
			{
				tempStringArr = dayAndTimePrefsArr[i].split(",");
				tempStringArr2 = tempStringArr[1].split(" ");/*split by "," and " " so that only integers are saved to temp time prefs so that 
				they can be parsed to int and stored in Profs time pref attributes*/
				timePrefsArr[i] = tempStringArr2[1];
				//System.out.println("Test for if one time pref: " + timePrefsArr[i]);
			}//end if	
			else if (dayAndTimePrefsArr[i].length() > 5 && dayAndTimePrefsArr[i].length() < 21)
			{
				tempStringArr = dayAndTimePrefsArr[i].split(",");
				tempStringArr2 = tempStringArr[1].split(" ");
				timePrefsArr[i] = tempStringArr2[1];
				tempStringArr2 = tempStringArr[2].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				//System.out.println("Test for if two time prefs: " + timePrefsArr[i]);
			}// end if 
			else if (dayAndTimePrefsArr[i].length() > 5 && dayAndTimePrefsArr[i].length() < 30)
			{
				tempStringArr = dayAndTimePrefsArr[i].split(",");
				tempStringArr2 = tempStringArr[1].split(" ");
				timePrefsArr[i] = tempStringArr2[1];
				tempStringArr2 = tempStringArr[2].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[3].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				//System.out.println("Test for if three time prefs: " + timePrefsArr[i]);
			}// end if
			else if (dayAndTimePrefsArr[i].length() > 5 && dayAndTimePrefsArr[i].length() < 37)
			{
				tempStringArr = dayAndTimePrefsArr[i].split(",");
				tempStringArr2 = tempStringArr[1].split(" ");
				timePrefsArr[i] = tempStringArr2[1];
				tempStringArr2 = tempStringArr[2].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[3].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[4].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				//System.out.println("Test for if four time prefs: " + timePrefsArr[i]);
			}// end if 
			else if (dayAndTimePrefsArr[i].length() > 5 && dayAndTimePrefsArr[i].length() < 46)
			{
				tempStringArr = dayAndTimePrefsArr[i].split(",");
				tempStringArr2 = tempStringArr[1].split(" ");
				timePrefsArr[i] = tempStringArr2[1];
				tempStringArr2 = tempStringArr[2].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[3].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[4].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[5].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				//System.out.println("Test for if five time prefs: " + timePrefsArr[i]);
			}	// end if
			else if (dayAndTimePrefsArr[i].length() > 5 && dayAndTimePrefsArr[i].length() < 52)
			{
				tempStringArr = dayAndTimePrefsArr[i].split(",");
				tempStringArr2 = tempStringArr[1].split(" ");
				timePrefsArr[i] = tempStringArr2[1];
				tempStringArr2 = tempStringArr[2].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[3].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[4].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[5].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				tempStringArr2 = tempStringArr[6].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				//System.out.println("Test for if six time prefs: " + timePrefsArr[i]);
			}// end if
		}// end for
		//assign temp time prefs to Prof timePref attributes 
		newNTTF.changeTimePrefOne(Integer.parseInt(timePrefsArr[0]));
		newNTTF.changeTimePrefTwo(Integer.parseInt(timePrefsArr[1]));
		newNTTF.changeTimePrefThree(Integer.parseInt(timePrefsArr[2]));
		newNTTF.changeTimePrefFour(Integer.parseInt(timePrefsArr[3]));
		newNTTF.changeTimePrefFive(Integer.parseInt(timePrefsArr[4]));
		newNTTF.changeTimePrefSix(Integer.parseInt(timePrefsArr[5]));
		newNTTF.changeTimePrefSeven(Integer.parseInt(timePrefsArr[6]));
		newNTTF.changeTimePrefEight(Integer.parseInt(timePrefsArr[7]));
		
		/*
		 * Test Print
		System.out.println(newNTTF.getTimePrefOne());
		System.out.println(newNTTF.getTimePrefTwo());
		System.out.println(newNTTF.getTimePrefThree());
		System.out.println(newNTTF.getTimePrefFour());
		System.out.println(newNTTF.getTimePrefFive());
		System.out.println(newNTTF.getTimePrefSix());
		System.out.println(newNTTF.getTimePrefSeven());
		System.out.println(newNTTF.getTimePrefEight());
		*/
		
		/*assign order of preferences using data in columns 71 -75*/ 
		/*use arrays to temporarily hold preference ranking data that needs to be stored to NTTF so that loop can be used*/
		String prefOne = "blank";
		String prefTwo = "blank";
		String prefThree = "blank";
		String prefFour = "blank";
		String prefFive = "blank";
		String[] rankOfPreferences = new String[]{prefOne, prefTwo, prefThree, prefFour, prefFive};
		String coursePrefered = "course";
		String daysPrefered = "days";
		String timesPrefered = "times";
		String numPrepsPreferred = "number of preps";
		String onlineOrOnCampusPreferred = "online or on campus";
		String[] preferences = new String[]{coursePrefered, daysPrefered, timesPrefered, numPrepsPreferred, onlineOrOnCampusPreferred};
		try
		{
		for (int i = 0; i < 5; i++)// 5 preferences being ranked so check 5 columns (71-75)
			rankOfPreferences[Integer.parseInt(sheet.getCell(71 + i,y).getContents().trim()) - 1] = preferences[i];
		}// end try
		catch (NumberFormatException e)
		{
			//do nothing because number format exception means no data was entered for this column 
		}// end catch	
		
		newNTTF.changeFirstPref(rankOfPreferences[0]);
		newNTTF.changeSecondPref(rankOfPreferences[1]);
		newNTTF.changeThirdPref(rankOfPreferences[2]);
		newNTTF.changeFourthPref(rankOfPreferences[3]);
		newNTTF.changeFifthPref(rankOfPreferences[4]);
		
		/*
		 * Test Print
		System.out.println(newNTTF.getFirstPref());
		System.out.println(newNTTF.getSecondPref());
		System.out.println(newNTTF.getThirdPref());
		System.out.println(newNTTF.getFourthPref());
		System.out.println(newNTTF.getFifthPref());
		*/
		
		return newNTTF;
	}// end buildNTTF()
}// end Input
	
