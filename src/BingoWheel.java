/*Authors: Melissa Fernandez-Rubio, Kevin Fontela
 Course: COP 2800 
 Date: 03/28/2019
 Final Project Part1
 Instructor: Sergio Pisano*/

import java.util.ArrayList;
import java.util.Random;

public class BingoWheel { 
	private ArrayList<String> calledBingoBalls=new ArrayList<String>();             //where we will put all called number to avoid repetitions 

	public static String [] bingoRandomBall()                                             //method that will generate a random number between 1 and 75 for the ball that comes out of the Bingo Wheel
	{   String []wheel=new String [2]; 
	//it returns only a number because we don't need the letter as a returned value since with the number we already know
	Random num=new Random();  
	//what letter is and also we already know in with column to look for the number
	int rand= num.nextInt(75)+1;
 
	if(rand>0&&rand<16)  
		wheel[1]="B";
	else if(rand>15&&rand<31)
		wheel[1]="I";
	else if(rand>30&&rand<46)
		wheel[1]="N";
	else if(rand>45&&rand<61)
		wheel[1]="G";
	else
		wheel[1]="O";
	wheel[0]=(Integer.toString(rand));

	return wheel;
	}

	public ArrayList<String> getCalledBingoBalls()                                  //method that will return the called Balls, which we will use in Part 2, but we don't use in this part
	{
		return calledBingoBalls; 
	}

	public void setCalledBingoBalls(int calledNumber)                              //method that will add to the Arraylist calledBingoBalls those numbers that have been called already
	{
		calledBingoBalls.add(Integer.toString(calledNumber));
	}
}
