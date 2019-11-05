
/*Authors: Melissa Fernandez-Rubio, Kevin Fontela
 Course: COP 2800 
 Date: 03/28/2019
 Final Project Part1
 Instructor: Sergio Pisano*/
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws IOException {


		String []arr= new String[2];
		BingoWheel bingoWheel=new BingoWheel();

		Player player1= new Player();
		Player player2= new Player();

		player1.showCard();
		player2.showCard(); 
		System.in.read();

		Scanner input=new Scanner(System.in);
		String userInput;
		System.out.println("Would you like to begging the BINGO GAME?");
		userInput=input.next();
		while(!(userInput.equalsIgnoreCase("y")||userInput.equalsIgnoreCase("n"))) {
			System.out.println("Enter y or n ");
			userInput=input.next();
		}  

		if(userInput.equalsIgnoreCase("y")) {

			System.out.print("Enter the player 1 name: ");
			player1.setName(input.next());
 
			System.out.print("Enter the player 2 name: ");
			player2.setName(input.next());

			ArrayList<String>s=new ArrayList<String>();
			s=bingoWheel.getCalledBingoBalls();
			int i=0;
			while(player1.getScore()<6 && player2.getScore()<6) { 
				arr=BingoWheel.bingoRandomBall();
				int calledBall=(Integer.parseInt(arr[0])); 
 
				while(!(bingoWheel.getCalledBingoBalls().contains(Integer.toString(calledBall)))){
					player2.getBingoCard().checkWinPattern(player2,calledBall);
					player1.getBingoCard().checkWinPattern(player1,calledBall);

					bingoWheel.setCalledBingoBalls(calledBall);

					System.out.print("ROUND "+(i+1)+"\nCalled Balls so far are: ");
					for(i=0;i<s.size();i++) {
						System.out.print(" "+s.get(i));

					}

					System.out.println("\n"+player1.getName());
					player1.showCard();
					System.out.println("\n"+player1.getName()+" 's score: "+ player1.getScore());
					System.out.println("\n"+player2.getName());
					player2.showCard();
					System.out.println("\n"+player2.getName()+" 's score: "+player2.getScore());
					System.out.println("Press enter key to go to the other round");
					System.in.read();
				}
			}


			//for(int i=0;i<s.size();i+ +) { 



			//}
 
			if(player2.getScore()==6) {
				player2.showCard();
				System.out.println(player2.getName()+" BINGO!!!");
			}
			if(player1.getScore()==6)
			{player1.showCard(); 
			System.out.println(player1.getName()+" BINGO!!!");
			}



		}
		else 
			System.out.println("Please come back and play! :))");

		input.close();
	}



}

