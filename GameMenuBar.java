package com.example.ultimateticktacktoefx;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameMenuBar extends MenuBar {
    private Stage primaryStage;

    public GameMenuBar(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Menu menu = new Menu("Historia Gry");
        MenuItem showHistoryItem = new MenuItem("Pokaż historię gry");
        showHistoryItem.setOnAction(e -> showHistoryWindow());
        menu.getItems().add(showHistoryItem);

        getMenus().add(menu);
    }

    private void showHistoryWindow() {
        Stage historyStage = new Stage();
        historyStage.setTitle("Historia Gry");

        VBox historyLayout = new VBox(10);

        // Create the ListView and populate it with GameMove objects
        ListView<String> listView = new ListView<>(FXCollections.observableArrayList(UlitmateTTT.gameHistory));

        // Customize the display of each GameMove object in the ListView


        // Create a ScrollPane and set the ListView as its content
        ScrollPane scrollPane = new ScrollPane(listView);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        historyLayout.getChildren().add(scrollPane);

        historyStage.setScene(new Scene(historyLayout, 400, 300));
        historyStage.show();
    }
}
