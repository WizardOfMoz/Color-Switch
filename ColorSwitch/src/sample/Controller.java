package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {


    public void NewGame(ActionEvent event) {

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(Main.gameScene);
        window.show();


    }

    public void ResumeGame(ActionEvent event) throws IOException {

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("resume.fxml"));
        window.setScene(new Scene(root,600,800,Color.BLACK));
        window.show();

    }

    public void MainMenu(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        window.setScene(new Scene(root,600,800,Color.BLACK));
        window.show();
    }
}