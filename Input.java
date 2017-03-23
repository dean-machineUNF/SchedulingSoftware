import java.io.File; 
import java.io.IOException;
import java.util.Date; 

import jxl.*; 
import jxl.read.biff.BiffException;

public class Input 
{
		
	private TenureTrackFaculty newTTF;
	private NonTenureTrackFaculty newNTTF;
	private Professor[] tempProfArray;
	private int tempArrayIndex = 0;
	private int numOfProfsCount = 0;
	Workbook workbook;
	Sheet sheet; 
	
	public Professor[] readProfessorData() throws IOException, BiffException 
	{
		
		workbook = Workbook.getWorkbook(new File("English+Faculty+Survey.xls"));
		sheet = workbook.getSheet(0);
		
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
		
		System.out.println("NUmber of iterations of while " + numOfProfsCount);
		
		
		tempProfArray = new Professor[numOfProfsCount];
	
		
		for (y = 3; (y - 3) < numOfProfsCount; y++)//reset y to 3 to start at beginning of Prof data and only do loop statements for number of Profs
		{
			x = 14;// reset x to beginning of Prof data for each iteration of loop
			
			
			//test to see if data in this row is for a TTF
			if (sheet.getCell(x,y).getContents().contains("TTF") || sheet.getCell(x,y).getContents().startsWith("T"))
			{
				
				tempProfArray[tempArrayIndex] = buildTTF(x,y);// call method that builds TTF and store returned TTF into array 
				tempArrayIndex++;
				//newTTF.toString();
				
			
				
				
			}// end if
			
			//test to see if row contains data for a NTTF
			else if (sheet.getCell(x,y).getContents().contains("NTTF") || sheet.getCell(x,y).getContents().startsWith("N"))
			{
				tempProfArray[tempArrayIndex] = buildNTTF(x,y);// call method that builds TTF and store returned TTF into array 
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
	
	
	public TenureTrackFaculty buildTTF(int passX, int passY)
	{
		int x = passX;
		int y = passY;
	
		/*build new TTF object and pass some Professor preference response data as parameters of TTF object*/
		newTTF = new TenureTrackFaculty(sheet.getCell(16,y).getContents(), Integer.parseInt(sheet.getCell(15,y).getContents()), Integer.parseInt(sheet.getCell(70,y).getContents()),
				sheet.getCell(17,y).getContents(), sheet.getCell(18,y).getContents(), sheet.getCell(19,y).getContents(), sheet.getCell(20,y).getContents(), sheet.getCell(21,y).getContents(), 
				"blank", "blank", "blank", "blank", "blank", "blank", "blank", "blank", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "71", "72", "73", "74", "75", "blank", "blank", "blank", "blank", "blank", 0.0);
		
		/*Test print*/
		System.out.println("TTF built");
		System.out.println(newTTF.getCourseLoad());
		System.out.println(newTTF.getName());
		System.out.println(newTTF.getNumPreps());
		System.out.println(newTTF.getCoursePrefOne());
		System.out.println(newTTF.getCoursePrefTwo());
		System.out.println(newTTF.getCoursePrefThree());
		System.out.println(newTTF.getCoursePrefFour());
		System.out.println(newTTF.getCoursePrefFive());
		
		
		/*parse and store Professor data that could not be passed as param, starting with day and time preferences*/
		String yes = "answer"; // yes is represented as "Answer 1" day and time preferences columns in Prof data*/ 
		String no = "no";
		x += 8;// day and time preferences start at column 22
		/*use array to temporarily hold dayAndTimePref data that needs to be stored to TTF so that loop can be used*/
		String dayAndTimePrefOne = "MWF";// columns 22 - 27
		String dayAndTimePrefTwo = "MW ";// columns 28 - 33
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
			
			
			x += 6;
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
		
		System.out.println("Test: "+ newTTF.getDayAndTimePrefOne());
		System.out.println("Test: "+ newTTF.getDayAndTimePrefTwo());
		System.out.println("Test: "+ newTTF.getDayAndTimePrefThree());
		System.out.println("Test: "+ newTTF.getDayAndTimePrefFour());
		System.out.println("Test: "+ newTTF.getDayAndTimePrefFive());
		System.out.println("Test: "+ newTTF.getDayAndTimePrefSix());
		System.out.println("Test: "+ newTTF.getDayAndTimePrefSeven());
		System.out.println("Test: "+ newTTF.getDayAndTimePrefEight());
		
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
			if (dayAndTimePrefsArr[i].length() > 5 && dayAndTimePrefsArr[i].length() < 13)//this tests to see how many time prefs are contained in the dayAndTime attribute and reassigns them to temp time pref attributes 
			{
				tempStringArr = dayAndTimePrefsArr[i].split(",");
				tempStringArr2 = tempStringArr[1].split(" ");//split by "," and " " so that only integers are saved to temp time prefs so that they can be parsed to int and stored in Profs time pref attributes
				timePrefsArr[i] = tempStringArr2[1];
				System.out.println("Test for if one time pref: " + timePrefsArr[i]);
			}//end if	
			else if (dayAndTimePrefsArr[i].length() > 5 && dayAndTimePrefsArr[i].length() < 21)
			{
				tempStringArr = dayAndTimePrefsArr[i].split(",");
				tempStringArr2 = tempStringArr[1].split(" ");
				timePrefsArr[i] = tempStringArr2[1];
				tempStringArr2 = tempStringArr[2].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				System.out.println("Test for if two time prefs: " + timePrefsArr[i]);
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
				System.out.println("Test for if three time prefs: " + timePrefsArr[i]);
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
				System.out.println("Test for if four time prefs: " + timePrefsArr[i]);
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
				System.out.println("Test for if five time prefs: " + timePrefsArr[i]);
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
				System.out.println("Test for if six time prefs: " + timePrefsArr[i]);
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
		System.out.println(newTTF.getTimePrefOne());
		System.out.println(newTTF.getTimePrefTwo());
		System.out.println(newTTF.getTimePrefThree());
		System.out.println(newTTF.getTimePrefFour());
		System.out.println(newTTF.getTimePrefFive());
		System.out.println(newTTF.getTimePrefSix());
		System.out.println(newTTF.getTimePrefSeven());
		System.out.println(newTTF.getTimePrefEight());
		
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
		}
		catch (NumberFormatException e)
		{
			//do nothing because number format exception means no data was entered for this column 
		}	
		
		newTTF.changeFirstPref(rankOfPreferences[0]);
		newTTF.changeSecondPref(rankOfPreferences[1]);
		newTTF.changeThirdPref(rankOfPreferences[2]);
		newTTF.changeFourthPref(rankOfPreferences[3]);
		newTTF.changeFifthPref(rankOfPreferences[4]);
		
		System.out.println(newTTF.getFirstPref());
		System.out.println(newTTF.getSecondPref());
		System.out.println(newTTF.getThirdPref());
		System.out.println(newTTF.getFourthPref());
		System.out.println(newTTF.getFifthPref());
		
		return newTTF;
	}
	
	public NonTenureTrackFaculty buildNTTF(int passX, int passY)
	{
		int x = passX;
		int y = passY;
	
		/*build new TTF object and pass some Professor preference response data as parameters of TTF object*/
		newNTTF = new NonTenureTrackFaculty(sheet.getCell(16,y).getContents(), Integer.parseInt(sheet.getCell(15,y).getContents()), Integer.parseInt(sheet.getCell(70,y).getContents()),
				sheet.getCell(17,y).getContents(), sheet.getCell(18,y).getContents(), sheet.getCell(19,y).getContents(), sheet.getCell(20,y).getContents(), sheet.getCell(21,y).getContents(), 
				"blank", "blank", "blank", "blank", "blank", "blank", "blank", "blank", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "71", "72", "73", "74", "75", "blank", "blank", "blank", "blank", "blank", 0.0);
		
		/*Test print*/
		System.out.println("NTTF built");
		System.out.println(newNTTF.getCourseLoad());
		System.out.println(newNTTF.getName());
		System.out.println(newNTTF.getNumPreps());
		System.out.println(newNTTF.getCoursePrefOne());
		System.out.println(newNTTF.getCoursePrefTwo());
		System.out.println(newNTTF.getCoursePrefThree());
		System.out.println(newNTTF.getCoursePrefFour());
		System.out.println(newNTTF.getCoursePrefFive());
		
		
		/*parse and store Professor data that could not be passed as param, starting with day and time preferences*/
		String yes = "answer"; // yes is represented as "Answer 1" day and time preferences columns in Prof data*/ 
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
		
		System.out.println("Test: "+ newNTTF.getDayAndTimePrefOne());
		System.out.println("Test: "+ newNTTF.getDayAndTimePrefTwo());
		System.out.println("Test: "+ newNTTF.getDayAndTimePrefThree());
		System.out.println("Test: "+ newNTTF.getDayAndTimePrefFour());
		System.out.println("Test: "+ newNTTF.getDayAndTimePrefFive());
		System.out.println("Test: "+ newNTTF.getDayAndTimePrefSix());
		System.out.println("Test: "+ newNTTF.getDayAndTimePrefSeven());
		System.out.println("Test: "+ newNTTF.getDayAndTimePrefEight());
		
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
			if (dayAndTimePrefsArr[i].length() > 5 && dayAndTimePrefsArr[i].length() < 12)//this tests to see how many time prefs are contained in the dayAndTime attribute and reassigns them to temp time pref attributes 
			{
				tempStringArr = dayAndTimePrefsArr[i].split(",");
				tempStringArr2 = tempStringArr[1].split(" ");//split by "," and " " so that only integers are saved to temp time prefs so that they can be parsed to int and stored in Profs time pref attributes
				timePrefsArr[i] = tempStringArr2[1];
				System.out.println("Test for if one time pref: " + timePrefsArr[i]);
			}//end if	
			else if (dayAndTimePrefsArr[i].length() > 5 && dayAndTimePrefsArr[i].length() < 21)
			{
				tempStringArr = dayAndTimePrefsArr[i].split(",");
				tempStringArr2 = tempStringArr[1].split(" ");
				timePrefsArr[i] = tempStringArr2[1];
				tempStringArr2 = tempStringArr[2].split(" ");
				timePrefsArr[i] = timePrefsArr[i].concat(tempStringArr2[1]);
				System.out.println("Test for if two time prefs: " + timePrefsArr[i]);
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
				System.out.println("Test for if three time prefs: " + timePrefsArr[i]);
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
				System.out.println("Test for if four time prefs: " + timePrefsArr[i]);
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
				System.out.println("Test for if five time prefs: " + timePrefsArr[i]);
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
				System.out.println("Test for if six time prefs: " + timePrefsArr[i]);
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
		System.out.println(newNTTF.getTimePrefOne());
		System.out.println(newNTTF.getTimePrefTwo());
		System.out.println(newNTTF.getTimePrefThree());
		System.out.println(newNTTF.getTimePrefFour());
		System.out.println(newNTTF.getTimePrefFive());
		System.out.println(newNTTF.getTimePrefSix());
		System.out.println(newNTTF.getTimePrefSeven());
		System.out.println(newNTTF.getTimePrefEight());
		
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
		}
		catch (NumberFormatException e)
		{
			//do nothing because number format exception means no data was entered for this column 
		}	
		
		newNTTF.changeFirstPref(rankOfPreferences[0]);
		newNTTF.changeSecondPref(rankOfPreferences[1]);
		newNTTF.changeThirdPref(rankOfPreferences[2]);
		newNTTF.changeFourthPref(rankOfPreferences[3]);
		newNTTF.changeFifthPref(rankOfPreferences[4]);
		
		System.out.println(newNTTF.getFirstPref());
		System.out.println(newNTTF.getSecondPref());
		System.out.println(newNTTF.getThirdPref());
		System.out.println(newNTTF.getFourthPref());
		System.out.println(newNTTF.getFifthPref());
		
		return newNTTF;
	}
}// end Input
	
