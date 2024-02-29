package com.example.ultimateticktacktoefx;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;
import java.util.ArrayList;
import java.util.Random;

public class TickTackToe extends VBox {
    private String[][] playingField = new String[3][3];

    int BigFieldI;
    int BigFieldJ;
    private String winner = null;
    private GridPane gridPane;
    private StackPane stackPane;
    Button endbutton = new Button();

    ArrayList<Button> buttons = new ArrayList<>();


    public TickTackToe() {
        stackPane = new StackPane();
        boolean listenForInput = true;
        gridPane = new GridPane();
        GridPane glassWall = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPrefSize(800, 600);
        generateButtons();
        endbutton.setPrefSize(260, 172);
        endbutton.setText("E");
        endbutton.setOnAction(e -> {
            gridPane.setVisible(true);
            glassWall.toFront();
            endbutton.setVisible(false);
        });
        endbutton.setVisible(false);
        endbutton.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        setSpacing(10);
        setAlignment(Pos.CENTER);
        getChildren().addAll(stackPane);
        stackPane.getChildren().add(endbutton);
        stackPane.getChildren().add(glassWall);
        stackPane.getChildren().add(gridPane);

        stackPane.setOnMouseEntered(e -> {
            if (winner != null) {


                gridPane.setVisible(true);
                glassWall.toFront();
                endbutton.setVisible(false);
            }
        });

        stackPane.setOnMouseExited(e -> {
            if (winner != null) {
                endbutton.setVisible(true);
                gridPane.setVisible(false);

                gridPane.setVisible(false);
                glassWall.toBack();
                endbutton.setVisible(true);
            }
        });
        setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode.isDigitKey()) {
                int number = Integer.parseInt(keyCode.getName());
                fireButton(number);
            }
        });
    }


    private void generateButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                int finalI = i;
                int finalJ = j;

                button.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                button.setPrefSize(75, 50);
                button.setOnAction(e -> {
                    actionListenerHelper(finalI, finalJ);
                    button.setText(UlitmateTTT.getPlayer());
                    try {
                        checkForWin();
                        UlitmateTTT.checkForBigWin();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    new UlitmateTTT.GameMove(BigFieldI, BigFieldJ, finalI, finalJ, UlitmateTTT.getPlayer());
                    // System.out.println("GRACZ "+UlitmateTTT.getPlayer()+"WYKONAŁ RUCH");
                    UlitmateTTT.round += 1;
                    button.setDisable(true);
                    boolean alreadyPlayed = true;
                    UlitmateTTT.lastMoveX = finalI;
                    UlitmateTTT.lastMoveY = finalJ;
                    UlitmateTTT.choseEnabledField();
                    if (MainMenu.Computer) {
                        if (UlitmateTTT.getPlayer().equals(MainMenu.ComputerPlayer)) {
                            makeMove();
                        }
                    }


                });
                gridPane.add(button, j, i);
                buttons.add(button);
            }
        }
    }

    private void actionListenerHelper(int i, int j) {
        playingField[i][j] = UlitmateTTT.getPlayer();
    }

    private void printPlayingField() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("[" + playingField[i][j] + "]");
            }
            System.out.println();
        }
    }


    public void checkForWin() {
        // Check rows for a win
        for (int row = 0; row < 3; row++) {
            if (playingField[row][0] != null && playingField[row][0].equals(playingField[row][1]) && playingField[row][1].equals(playingField[row][2])) {
                // System.out.println(getPlayer() + " WON!");
                winner = UlitmateTTT.getPlayer();
                setEndGameState();
            }
        }

        // Check columns for a win
        for (int col = 0; col < 3; col++) {
            if (playingField[0][col] != null && playingField[0][col].equals(playingField[1][col]) && playingField[1][col].equals(playingField[2][col])) {
                //System.out.println(getPlayer() + " WON!");
                winner = UlitmateTTT.getPlayer();
                setEndGameState();
            }
        }

        // Check diagonals for a win
        if (playingField[0][0] != null && playingField[0][0].equals(playingField[1][1]) && playingField[1][1].equals(playingField[2][2])) {
            //System.out.println(getPlayer() + " WON!");
            winner = UlitmateTTT.getPlayer();
            setEndGameState();
        }
        if (playingField[0][2] != null && playingField[0][2].equals(playingField[1][1]) && playingField[1][1].equals(playingField[2][0])) {
            //System.out.println(getPlayer() + " WON!");
            winner = UlitmateTTT.getPlayer();
            setEndGameState();
        }
    }

    private void setEndGameState() {
        if (winner != null) {
            endbutton.setText(winner);
            endbutton.setVisible(true);
            gridPane.setVisible(false);
            endbutton.toFront();


        }
    }

    public String getWinner() {
        return winner;
    }


    public void DisableEmpty() {
        for (Button button : buttons) {
            if (!(button.getText().equals("X") || button.getText().equals("O"))) {
                button.setDisable(true);
            }

        }

    }

    public void EnableEmpty() {
        for (Button button : buttons) {
            if (!(button.getText().equals("X") || button.getText().equals("O"))) {
                button.setDisable(false);
            }

        }
    }


    public void makeAIMove() {
        System.out.println("Making easy ai move");
        if (UlitmateTTT.getPlayer().equals(MainMenu.ComputerPlayer)) {
            ArrayList<Button> emptyButtons = new ArrayList<>();

            for (Button button : buttons) {
                if (!(button.getText().equals("X") || button.getText().equals("O"))) {
                    emptyButtons.add(button);
                }
            }

            if (!emptyButtons.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(emptyButtons.size());
                Button randomButton = emptyButtons.get(randomIndex);
                randomButton.fire();
            }
        }
    }

    public void setIandJ(int I, int J) {
        this.BigFieldI = I;
        this.BigFieldJ = J;
    }


    public void fireButton(int number) {
        if (number >= 1 && number <= buttons.size()) {
            Button button = buttons.get(number - 1);
            if (!button.isDisabled()) {
                button.fire();
            }
        }
    }


    public void hardAiMove() {
        System.out.println("Hard ai move");
        if (UlitmateTTT.getPlayer().equals(MainMenu.ComputerPlayer)) {
            ArrayList<Button> emptyButtons = new ArrayList<>();

            for (Button button : buttons) {
                if (!(button.getText().equals("X") || button.getText().equals("O"))) {
                    emptyButtons.add(button);
                }
            }

            if (!emptyButtons.isEmpty()) {
                // Perform an optimal move
                Button optimalButton = getOptimalMove(emptyButtons);
                optimalButton.fire();
            }
        }
    }

    private Button getOptimalMove(ArrayList<Button> emptyButtons) {
        // Check for a winning move
        for (Button button : emptyButtons) {
            int index = buttons.indexOf(button);
            int row = index / 3;
            int col = index % 3;
            playingField[row][col] = UlitmateTTT.getPlayer();
            if (checkForWinningMove()) {
                playingField[row][col] = null;
                return button;
            }
            playingField[row][col] = null;
        }

        // Check for blocks
        String opponentPlayer = UlitmateTTT.getPlayer().equals("X") ? "O" : "X";
        for (Button button : emptyButtons) {
            int index = buttons.indexOf(button);
            int row = index / 3;
            int col = index % 3;
            playingField[row][col] = opponentPlayer;
            if (checkForWinningMove()) {
                playingField[row][col] = null;
                return button;
            }
            playingField[row][col] = null;
        }

        // random if not found
        Random random = new Random();
        int randomIndex = random.nextInt(emptyButtons.size());
        return emptyButtons.get(randomIndex);
    }

    private boolean checkForWinningMove() {
        // Check rows for a win
        for (int row = 0; row < 3; row++) {
            if (playingField[row][0] != null && playingField[row][0].equals(playingField[row][1]) &&
                    playingField[row][1].equals(playingField[row][2])) {
                return true;
            }
        }

        // Check columns for a win
        for (int col = 0; col < 3; col++) {
            if (playingField[0][col] != null && playingField[0][col].equals(playingField[1][col]) &&
                    playingField[1][col].equals(playingField[2][col])) {
                return true;
            }
        }

        // Check diagonals for a win
        if (playingField[0][0] != null && playingField[0][0].equals(playingField[1][1]) &&
                playingField[1][1].equals(playingField[2][2])) {
            return true;
        }
        if (playingField[0][2] != null && playingField[0][2].equals(playingField[1][1]) &&
                playingField[1][1].equals(playingField[2][0])) {
            return true;
        }

        return false;
    }

    void makeMove() {
        if (MainMenu.Hard) {
            hardAiMove();
        } else makeAIMove();
    }
}


