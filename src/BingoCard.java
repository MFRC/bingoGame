/*Authors: Melissa Fernandez-Rubio, Kevin Fontela
 Course: COP 2800 
 Date: 03/28/2019
 Final Project Part3
 Instructor: Sergio Pisano*/



import java.util.ArrayList;

public  class BingoCard implements Winnable {
 
	private int [][]bingoCardArray=new int[5][5]; 

	public void checkWinPattern(Player p, int calledBall) {

		ArrayList<Integer>pattern;
		pattern = new ArrayList<Integer>();  

		bingoCardArray = p.getBingoCard().getBingoCardArray();
		pattern.add(bingoCardArray[0][0]);
		pattern.add(bingoCardArray[0][4]);
		pattern.add(bingoCardArray[1][1]);
		pattern.add(bingoCardArray[1][3]);
		pattern.add(bingoCardArray[3][2]);
		pattern.add(bingoCardArray[4][2]);

		if(pattern.contains(calledBall)) {
			p.setScore();

			for(int i=0;i<5;i++)
				for(int j=0;j<5;j++) {
					if(bingoCardArray[i][j]==calledBall)
						bingoCardArray[i][j]=bingoCardArray[i][j]*100;
				}

		}

		//  p.getBingoCard().setArray(bingoCardArray);

	}

	//Array where we storage the random numbers that simulates the Bingo Cards  



	/*public BingoCard() {
		BingoCard bingoCard= new BingoCard();
		bingoCard.setBingoCardArray();
	}*/
	public void setBingoCardArray() 
	{   
		String [] array=new String[2]; 


		int numberBall=0;                                           //variable where we storage the random numbers 
		BingoWheel bingoWheel=new BingoWheel();                     //Creating an object type BingoWheel

		for(int i=0;i<5;i++)                                        //this loop will fill up the Bingo Card 
			for(int j=0;j<5;j++)
			{   
				array=BingoWheel.bingoRandomBall();
				numberBall= Integer.parseInt(array[0]);
				while(!(numberBall>(j*15)&&numberBall<(15*j+16))) {

					array=BingoWheel.bingoRandomBall();
					numberBall= Integer.parseInt(array[0]);

				}


				while(bingoWheel.getCalledBingoBalls().contains(Integer.toString(numberBall)))
				{
					array=BingoWheel.bingoRandomBall();
					numberBall= Integer.parseInt(array[0]);
					while(!(numberBall>(j*15)&&numberBall<(15*j+16))) {
						array=BingoWheel.bingoRandomBall();
						numberBall= Integer.parseInt(array[0]);
					} 
				}

				bingoWheel.setCalledBingoBalls(numberBall);

				bingoCardArray[i][j]=numberBall;


			}
	}

	public int [][] getBingoCardArray() {
		return bingoCardArray; 
	}
	public void showCardBingo()                                    //method that will show the bingoCardArray as a Bingo Card
	{  
		System.out.println("\n");
		String title []={"B","I", "N","G","O"};                    //the header of the card

		for(int i=0;i<title.length;i++){                           //prints the header that says "B I N G O"
			System.out.print(title[i]+ "\t");
		}

		System.out.println("\n"); 

		for(int i=0; i < 5; i++){               //prints the Card Bingo array

			for(int j=0; j <5;j++){

				if(bingoCardArray[i][j]>100 )
					System.out.print("["+bingoCardArray[i][j]/100 +"]" + "\t");                  //the square in the center will display an "X"
				else if(i==2&&j==2) {
					System.out.print("X"+ "\t");
				}
				else

					System.out.print(bingoCardArray[i][j]+ "\t");
			}
			System.out.println(); 
		} 
	}

	public void setArray(int [][]r){
		bingoCardArray=r;
	}
}
