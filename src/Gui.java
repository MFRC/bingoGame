/*Authors: Kevin Fontela, Melissa Fernandez
 * Sergio Pisano
 * Java Class
 * Final Project Part 3*/


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


import JavaFxExample.ButtonClickHandlerDown;
import BINGOGAME.JavaFxExample.ButtonClickHandlerLeft;
import BINGOGAME.JavaFxExample.ButtonClickHandlerMoveAnimation;
import BINGOGAME.JavaFxExample.ButtonClickHandlerRight;
import BINGOGAME.JavaFxExample.ButtonClickHandlerRotate;
import BINGOGAME.JavaFxExample.ButtonClickHandlerRotateAnimation;
import BINGOGAME.JavaFxExample.ButtonClickHandlerScaleAnimation;
import BINGOGAME.JavaFxExample.ButtonClickHandlerSwap;
import BINGOGAME.JavaFxExample.ButtonClickHandlerUp;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Gui extends Application {

	@Override
	public void start(Stage stage) throws Exception{



		stage.setTitle("BINGO GAME USER REGISTRATION");

		// Create the registration form grid pane
		//GridPane gridPane = createRegistrationFormPane();
		// Add UI controls to the registration form grid pane
		//addUIControls(gridPane);
		// Create a scene with registration form grid pane as the root node
		GridPane gridPane = new GridPane();
		Scene scene = new Scene(gridPane);
		stage.setHeight(500); 
		stage.setWidth(500);
		stage.setResizable(false); 
		// Set the scene in primary stage	
		stage.setScene(scene);
		stage.show();


		// Instantiate a new Grid Pane


		// Position the pane at the center of the screen, both vertically and horizontally
		gridPane.setAlignment(Pos.CENTER);

		// Set a padding of 20px on each side
		gridPane.setPadding(new Insets(40, 40, 40, 40));

		// Set the horizontal gap between columns
		gridPane.setHgap(10);

		// Set the vertical gap between rows
		gridPane.setVgap(10);

		// Add Column Constraints

		// columnOneConstraints will be applied to all the nodes placed in column one.
		ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
		columnOneConstraints.setHalignment(HPos.RIGHT);

		// columnTwoConstraints will be applied to all the nodes placed in column two.
		ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
		columnTwoConstrains.setHgrow(Priority.ALWAYS);

		gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

		// Add Header
		Label headerLabel = new Label("BINGO GAME USER REGISTRATION ");
		headerLabel.setStyle("-fx-text-fill: #152579");  

		headerLabel.setFont(Font.font("Dokdo", FontWeight.BOLD, 20));
		gridPane.add(headerLabel, 0,0,2,1);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

		// Add Name Label
		Label nameLabel = new Label("PLAYER 1 NAME:");
		gridPane.add(nameLabel, 0,1); 

		// Add Name Text Field
		TextField nameField = new TextField();
		nameField.setPrefHeight(30);
		gridPane.add(nameField, 1,1);

		// Add Name Label
		Label nameLabel1 = new Label("PLAYER 2 NAME:");
		gridPane.add(nameLabel1, 0,2);

		// Add Name Text Field
		TextField nameField1 = new TextField(); 
		nameField1.setPrefHeight(30);
		gridPane.add(nameField1, 1,2);




		// Add Submit Button
		Button submitButton = new Button("Submit");
		submitButton.setPrefHeight(40);
		submitButton.setDefaultButton(true);
		submitButton.setPrefWidth(100);
		gridPane.add(submitButton, 0, 4, 2, 1);
		GridPane.setHalignment(submitButton, HPos.CENTER);
		GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

		submitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(nameField.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your player name 1");
					return;
				}
				if(nameField1.getText().isEmpty()) {
					showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your player name 2");
					return;
				}


				// parse from text field to string so that player class can set player names
				Player player= new Player();
				Player player1= new Player();

				String name = nameField.getText();
				player.setName(name);

				String name1 = nameField1.getText();
				player1.setName(name1);


				// *************************************************************
				// ******************* BINGO GAME WINDOW ***********************
				// *************************************************************

				GridPane gridPane2 = new GridPane();
				Scene bingoScene=new Scene(gridPane2);
				submitButton.setOnAction(e-> stage.setScene(bingoScene));
				Stage stage2 = new Stage();

				stage2.setHeight(650);
				stage2.setWidth(800);
				stage2.show();
				stage.close(); 
				showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!", "Welcome " + nameField.getText() +" and " +nameField1.getText());
				stage2.setTitle("BINGO GAME"); 
				String photo="file:C:\\Users\\KEVIN\\Desktop\\eclipse\\workPath\\javaexercises\\src\\BINGOGAME\\";
				//HBox hbox = new HBox(); 
				gridPane2.setPadding(new Insets(5)); 
				gridPane2.setHgap(5);
				gridPane2.setVgap(5);
				int counter=1;



				for(int i=1;i<16;i++) {
					for(int j=1;j<6;j++)
					{ 

						Image image = new Image(photo+Integer.toString(counter)+".JPG");

						ImageView imageView =new ImageView();


						imageView.setFitHeight(25);
						imageView.setFitWidth(25);
						imageView.setImage(image);
						gridPane2.add(imageView, j, i );


						counter++;
					}

				}

				try {
					int [][] array= new int [5][5];
					int [][] array1= new int [5][5];
					array=player.getBingoCard().getBingoCardArray();
					array1=player1.getBingoCard().getBingoCardArray();

					//GridPane gridPane1 = new GridPane();
					int row=0;
					int colum=0;
					for(int i=1;i<6;i++)
					{
						for(int j=8; j<13;j++)
						{
							Label bingoCardNumber=new Label(); 
							Label bingoCardNumber2=new Label();
							bingoCardNumber.setStyle("-fx-border-color:black; -fx-font-size:30;");
							bingoCardNumber2.setStyle("-fx-border-color:black; -fx-font-size:30;");
							if(array[row][colum]<10)
							{      
								bingoCardNumber.setText("0"+Integer.toString(array[row][colum]));
							} 
							else if(i==3&&j==10)
								bingoCardNumber.setText("X ");
							else 
								bingoCardNumber.setText(Integer.toString(array[row][colum]));

							if(array1[row][colum]<10)
							{      
								bingoCardNumber2.setText("0"+Integer.toString(array1[row][colum]));
							} 
							else if(i==3&&j==10)
								bingoCardNumber.setText("X ");
							else 
								bingoCardNumber2.setText(Integer.toString(array1[row][colum]));

							gridPane2.add(bingoCardNumber,j+5,i+2,1,1);
							gridPane2.add(bingoCardNumber2,j+20,i+2,1,1);
							colum++;
						}
						row++;
						colum=0;

					}
					BingoWheel bingoWheel=new BingoWheel();
					ArrayList<String>s=new ArrayList<String>();
					s=bingoWheel.getCalledBingoBalls();

					String []a=new String [2];
					a=BingoWheel.bingoRandomBall();
					int calledBall=Integer.parseInt(a[0]);


					Image image = new Image(photo+a[0]+".JPG");

					ImageView imageView =new ImageView(); 


					imageView.setFitHeight(120);
					imageView.setFitWidth(120);
					imageView.setImage(image);
					gridPane2.add(imageView, 25, 13);
					Button myButtonRound = new Button("NEXT ROUND");
					gridPane2.add(myButtonRound, 27, 17);
					myButtonRound.setOnAction(new EventHandler<ActionEvent>() { 

						@Override
						public void handle(ActionEvent event) {
							String []b=new String [2];
							b=BingoWheel.bingoRandomBall();
							int calledBall1=Integer.parseInt(b[0]);
							while(bingoWheel.getCalledBingoBalls().contains(Integer.toString(calledBall1))) {
								b=BingoWheel.bingoRandomBall();
								calledBall1=Integer.parseInt(b[0]);
							}

							while(!(bingoWheel.getCalledBingoBalls().contains(Integer.toString(calledBall1)))&&(player.getScore()<6&&player1.getScore()<6)){
								player.getBingoCard().checkWinPattern(player,calledBall1);
								player1.getBingoCard().checkWinPattern(player1,calledBall1);

								bingoWheel.setCalledBingoBalls(calledBall1);

								Image image1 = new Image(photo+calledBall1+"m.JPG");

								ImageView imageView1 =new ImageView(); 
								imageView1.setFitHeight(120);
								imageView1.setFitWidth(120);
								imageView1.setImage(image1);

								gridPane2.add(imageView1, 25, 13);
								int [][]array2=new int [5][5];
								int [][]array3=new int [5][5];
								int value;
								for(int i=0;i<5;i++) {
									for(int j=0;j<5;j++) {
										array2=player.getBingoCard().getBingoCardArray();
										array3=player1.getBingoCard().getBingoCardArray();
										if(array2[i][j]>=100) {
											value=array2[i][j]/100; 

											Image image2 = new Image(photo+value+"m.JPG");
											ImageView imageView2 =new ImageView(); 
											imageView2.setFitHeight(25);
											imageView2.setFitWidth(25);
											imageView2.setImage(image2);

											ImageView imageView9 =new ImageView(); 
											imageView9.setFitHeight(30);
											imageView9.setFitWidth(30);
											imageView9.setImage(image2);
											if(value%5==0)
												gridPane2.add(imageView2, 5, value/5);
											else
												gridPane2.add(imageView2, value%5, (value/5)+1);
											gridPane2.add(imageView9, 13+j, 3+i);
										}
										if(array3[i][j]>=100) {
											value=array3[i][j]/100;

											Image image3 = new Image(photo+value+"m.JPG");
											ImageView imageView3 =new ImageView(); 
											imageView3.setFitHeight(25);
											imageView3.setFitWidth(25); 
											imageView3.setImage(image3);

											ImageView imageView8 =new ImageView(); 
											imageView8.setFitHeight(30);
											imageView8.setFitWidth(30);
											imageView8.setImage(image3);
											if(value%5==0)
												gridPane2.add(imageView3, 5, value/5);
											else
												gridPane2.add(imageView3, value%5, (value/5)+1);
											gridPane2.add(imageView8, 28+j, 3+i);
										} 
									}
								}

							}
							if(player.getScore()==6||player1.getScore()==6)
							{
								GridPane gridPane3 = new GridPane();
								Scene bingoScene1=new Scene(gridPane3);
								//stage2.setScene(bingoScene1);
								Stage stage3 = new Stage();

								stage3.setHeight(450);
								stage3.setWidth(400);


								stage2.close(); 

								Image image11 = new Image(photo+".JPG");
								ImageView imageView12 =new ImageView(); 
								imageView12.maxHeight(400);
								imageView12.maxWidth(400);

								imageView12.setImage(image11); 
								gridPane3.add(imageView12, 0, 4, 2, 1);
								GridPane.setHalignment(imageView12, HPos.CENTER);
								GridPane.setMargin(imageView12, new Insets(20, 0,20,0));
								stage3.setScene(bingoScene1);
								Label l=new Label("BINGO!!!!");
								gridPane3.add(l,0,4,2,1);
								stage3.show();


							}


						}

					});

					stage2.setScene(bingoScene);
					stage2.show();
				}

				catch(Exception e) {
					e.printStackTrace();
				}
				stage2.setScene(bingoScene);
				stage2.show();
			}
		});
	}


	private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}

	public static void main(String[] args) {
		launch(args);
	}















