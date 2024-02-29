package com.example.ultimateticktacktoefx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class UlitmateTTT extends Application {

    static TickTackToe[][] bigField = new TickTackToe[3][3];
    public static ArrayList<String> gameHistory = new ArrayList<>();
    static int round = 1;
    static int lastMoveX = 10;
    static int lastMoveY = 10;
    static VBox root = new VBox(10);
    GameMenuBar bar;
    MainMenu menu = new MainMenu(this);

    @Override
    public void start(Stage primaryStage) {
        bar = new GameMenuBar(primaryStage);
        root.getChildren().addAll(bar, menu);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Ultimate Tick Tack Toe");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

    static public void checkForBigWin() {
        // Check rows for a win
        for (int row = 0; row < 3; row++) {
            if (bigField[row][0].getWinner() != null && bigField[row][0].getWinner().equals(bigField[row][1].getWinner()) && bigField[row][1].getWinner().equals(bigField[row][2].getWinner())) {
                ShowEndGameMessage();
                root.getChildren().removeAll();

            }
        }

        // Check columns for a win
        for (int col = 0; col < 3; col++) {
            if (bigField[0][col].getWinner() != null && bigField[0][col].getWinner().equals(bigField[1][col].getWinner()) && bigField[1][col].getWinner().equals(bigField[2][col].getWinner())) {
                ShowEndGameMessage();
                root.getChildren().removeAll();
            }
        }
        // Check diagonals for a win
        if (bigField[0][0].getWinner() != null && bigField[0][0].getWinner().equals(bigField[1][1].getWinner()) && bigField[1][1].getWinner().equals(bigField[2][2].getWinner())) {
            ShowEndGameMessage();
            root.getChildren().removeAll();
        }
        if (bigField[0][2].getWinner() != null && bigField[0][2].getWinner().equals(bigField[1][1].getWinner()) && bigField[1][1].getWinner().equals(bigField[2][0].getWinner())) {
            ShowEndGameMessage();
            root.getChildren().removeAll();
        }


    }

    public void generatePlayingField() {

        GridPane gridPane = new GridPane();
        gridPane.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (bigField[lastMoveX][lastMoveY].getWinner() != null){
            if (keyCode.isDigitKey()) {
                int number = Integer.parseInt(keyCode.getName());
                System.out.println(number);
                }}});

        gridPane.setHgap(1);
        gridPane.setVgap(1);
        gridPane.setPadding(new Insets(10));
        gridPane.setAlignment(Pos.BASELINE_CENTER);


        root.setAlignment(Pos.CENTER);

        for (int rows = 0; rows < 3; rows++) {
            for (int cols = 0; cols < 3; cols++) {
                TickTackToe ttt = new TickTackToe();
                bigField[rows][cols] = ttt;
                ttt.setIandJ(rows,cols);
                gridPane.add(ttt, cols, rows);
            }
        }
        root.getChildren().remove(menu);
        root.getChildren().add(gridPane);


        if (MainMenu.ComputerPlayer.equals("O")) {
            Random random = new Random();
            int randomI = random.nextInt(3);
            int randomJ = random.nextInt(3);
            bigField[randomI][randomJ].makeMove();
        }


    }

    static void choseEnabledField() {
            if (bigField[lastMoveX][lastMoveY].getWinner() != null) {
                for (int rows = 0; rows < 3; rows++) {
                    for (int cols = 0; cols < 3; cols++) {
                        if (rows == lastMoveX && cols == lastMoveY) {
                            bigField[rows][cols].DisableEmpty();
                        } else if (bigField[rows][cols].getWinner() == null) {
                            bigField[rows][cols].EnableEmpty();
                        }
                    }
                }} else {
            for (int rows = 0; rows < 3; rows++) {
                for (int cols = 0; cols < 3; cols++) {
                    if (rows == lastMoveX && cols == lastMoveY) {
                        bigField[rows][cols].EnableEmpty();
                        bigField[rows][cols].makeMove();
                        //System.out.println(rows+ " "+cols+"is beeing played");
                    } else {
                        bigField[rows][cols].DisableEmpty();
                        //System.out.println(rows+" "+cols+"is getting disabled");
                    }
                }
            }
        }}

    static String getPlayer() {
        if (round % 2 == 0) {
            return "X";
        } else if (round % 2 == 1) {
            return "O";
        } else {
            return "ERROR";
        }
    }


   public static class GameMove {
        String who;
        String where;
        String whatMove;
        int id;
        static int idgenerator = 1;

        GameMove(int bigFieldX, int bigFieldY, int smallFieldX, int smallFieldY, String player) {
            id = idgenerator;
            idgenerator+=1;
            who = player;
            where = bigFieldX+" "+bigFieldY;
            whatMove = smallFieldX+" "+smallFieldY;

                gameHistory.add(this.toString());
        }


       @Override
       public String toString() {
           return "LP. "+id+" "+" Plansza: "+where+" Ruch: "+whatMove+" Gracz: "+who;
       }
   }

    static void ShowEndGameMessage(){
        Stage endStage = new Stage();
        VBox box = new VBox(10);
        String message = "WYGRAŁ GRACZ: "+getPlayer();
        Text text = new Text(message);
        box.getChildren().add(text);
        endStage.setScene(new Scene(box));
        endStage.show();
        for (int i =0;i<3;i++){
            for(int j =0;j<3;j++){
                bigField[i][j].DisableEmpty();
            }
        }
   }


   }

