/*Authors: Kevin Fontela, Melissa Fernandez
 * Sergio Pisano
 * Java Class
 * Final Project Part 3*/


public class Player {
private String name; 
private int score=0;
private BingoCard bingoCard= new BingoCard(); 

 public String getName() { 
	 return name;
 }

public void setName(String nam) {
	this.name=nam;
}

public int getScore() {
	return score;
}
public void setScore() {
	this.score=this.score+1;
}
public Player() {
	bingoCard.setBingoCardArray();
	//bingoCard.showCardBingo();
}

public BingoCard getBingoCard() {
	return bingoCard;
}

public void showCard() {
	bingoCard.showCardBingo();
}
}