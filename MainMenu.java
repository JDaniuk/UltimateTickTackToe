package com.example.ultimateticktacktoefx;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainMenu extends VBox {
    static boolean Computer;
    static boolean Hard;
    static boolean XFirst;
    static UlitmateTTT instance;
    static String ComputerPlayer;
    private ToggleGroup toggleGroup;
    private RadioButton playerFirstRadioButton;
    private RadioButton computerFirstRadioButton;
    private Button easyButton;
    private Button normalButton;


    MainMenu(UlitmateTTT i) {
        instance = i;
        Button PVP = new Button("PvP");
        PVP.setOnAction(e->{
            Computer = false;
            easyButton.setDisable(true);
            normalButton.setDisable(true);
            playerFirstRadioButton.setDisable(true);
            computerFirstRadioButton.setDisable(true);

        });
        Button PVE = new Button("PvC");

        Button startButton = new Button("Start");
        startButton.setOnAction(e->{
            instance.generatePlayingField();
        });

        toggleGroup = new ToggleGroup();
        playerFirstRadioButton = new RadioButton("Player First");
        computerFirstRadioButton = new RadioButton("Computer First");
        playerFirstRadioButton.setDisable(true);
        computerFirstRadioButton.setDisable(true);
        playerFirstRadioButton.setToggleGroup(toggleGroup);
        computerFirstRadioButton.setToggleGroup(toggleGroup);

        easyButton = new Button("Easy");
        normalButton = new Button("Normal");
        easyButton.setDisable(true);
        normalButton.setDisable(true);

        GridPane gp = new GridPane();
        gp.add(PVP, 1, 1);
        gp.add(PVE, 1, 2);
        gp.add(playerFirstRadioButton, 2, 1);
        gp.add(computerFirstRadioButton, 2, 2);
        gp.add(easyButton, 3, 1);
        gp.add(normalButton, 3, 2);
        gp.add(startButton, 1, 3);

        PVE.setOnAction(e -> {
            playerFirstRadioButton.setDisable(false);
            computerFirstRadioButton.setDisable(false);
            easyButton.setDisable(false);
            normalButton.setDisable(false);
            Computer = true;
            System.out.println("Player vs Computer Enabled");
            startButton.setDisable(true);
        });

        PVP.setOnAction(e->{
            Computer = false;
            easyButton.setDisable(true);
            normalButton.setDisable(true);
            startButton.setDisable(false);
            playerFirstRadioButton.setDisable(true);
            computerFirstRadioButton.setDisable(true);
        });

        playerFirstRadioButton.setOnAction(e->{
            if (playerFirstRadioButton.isSelected()){
                ComputerPlayer = "X";
                startButton.setDisable(false);
            }
        });
        computerFirstRadioButton.setOnAction(e->{
            if(computerFirstRadioButton.isSelected()){
                ComputerPlayer = "O";
                startButton.setDisable(false);
            }
        });
        easyButton.setOnAction(e->{
            Hard = false;
        });

        normalButton.setOnAction(e->{
            Hard = true;
        });
        this.getChildren().add(gp);
        this.setVisible(true);
    }



}