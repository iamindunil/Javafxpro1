package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Controller_page {
    protected AnchorPane anchorPane;
    private Stage stage;
    public void setstage(Stage stage){
        this.stage = stage;
    }
    @FXML
    private Label user;

    @FXML
    private Label label;

    @FXML
    private Button closeb;

    private final String text = "Enter a prompt or try clicking a category! ";
    private IntegerProperty charIndex = new SimpleIntegerProperty(0);

    public void startAnimation() {
        Timeline typingAnimation = new Timeline();
        typingAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(50), event -> {
            if (charIndex.get() >= text.length()) {
                typingAnimation.stop();
            } else {
                label.setText(text.substring(0, charIndex.get()));
                charIndex.set(charIndex.get() + 1);
            }
        }));
        typingAnimation.setCycleCount(Animation.INDEFINITE);
        typingAnimation.play();

        // Optional cursor animation (replace with your Rectangle setup)
        Timeline cursorAnimation = new Timeline();
        cursorAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(300), event -> {
            // Toggle visibility of the rectangle representing the cursor
        }));
        cursorAnimation.setCycleCount(Animation.INDEFINITE);
        cursorAnimation.play();
    }
    public void exe(String Username){
        if(Username != null){
            user.setText(Username + "!");
        }
        else{
            user.setText("!");
        }
        startAnimation();
    }

    public void page2action(MouseEvent event){
        try {
            switch2page2();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void switch2page2() throws IOException {
        FXMLLoader fxmlLoader3 = new FXMLLoader(Main.class.getResource("page2.fxml"));
        Scene scene = new Scene(fxmlLoader3.load());
        getAnchorPane(fxmlLoader3,scene);
        stage.setScene(scene);
        stage.centerOnScreen();
        Controller_page2 page3 = new Controller_page2();
        page3.create_list_movies();
        stage.show();
    }

    public void getAnchorPane(FXMLLoader fxmlLoader3,Scene scene) {
        Controller_page2 page3 = fxmlLoader3.getController();
        this.anchorPane = page3.anchorPane;
        page3.setstage(stage);
        page3.setScene(scene);
    }

    public void page3action(MouseEvent event){
        try {
            switch2page3();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void switch2page3() throws IOException {
        FXMLLoader fxmlLoader4 = new FXMLLoader(Main.class.getResource("page3.fxml"));
        Scene scene = new Scene(fxmlLoader4.load());
        getAnchorPane1(fxmlLoader4,scene);
        stage.setScene(scene);
        stage.centerOnScreen();
        Controller_page3 page3 = new Controller_page3();
        page3.create_list_movies();
        stage.show();
    }
    public void getAnchorPane1(FXMLLoader fxmlLoader3,Scene scene) {
        Controller_page3 page3 = fxmlLoader3.getController();
        this.anchorPane = page3.anchorPane1;
        page3.setstage(stage);
        page3.setScene(scene);
    }
    public void page4action(MouseEvent event){
        try {
            switch2page4();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void switch2page4() throws IOException {
        FXMLLoader fxmlLoader4 = new FXMLLoader(Main.class.getResource("page4.fxml"));
        Scene scene = new Scene(fxmlLoader4.load());
        getAnchorPane2(fxmlLoader4,scene);
        stage.setScene(scene);
        stage.centerOnScreen();
        Controller_page4 page4 = new Controller_page4();
        page4.create_list_movies();
        stage.show();
    }
    public void getAnchorPane2(FXMLLoader fxmlLoader3,Scene scene) {
        Controller_page4 page4 = fxmlLoader3.getController();
        this.anchorPane = page4.anchorPane2;
        page4.setstage(stage);
        page4.setScene(scene);
    }
    public void logout(ActionEvent event) {
        closeb = new Button();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("LOGOUT");
        alert.setHeaderText("You are about to Log out!");
        alert.setContentText("Do you want to log out and close the window?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You logged out of the system!");
            stage.close();
        }
    }
}
