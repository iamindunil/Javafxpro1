package application;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
public class Controller {
	public String Username;
	
	@FXML
	private TextField textField;

	@FXML
	private PasswordField textField1;
	
	@FXML
	private Button closeb;

	@FXML
	private AnchorPane pane;

	@FXML
	private Label user;

	@FXML
	ImageView image;

	@FXML
	ImageView icon;

	@FXML
	ImageView icon2;

	@FXML
	AnchorPane mypanex;

	@FXML
	AnchorPane myAnchorPane;
	
	protected Stage stage;
	
	public void logout(ActionEvent event) {
		closeb = new Button();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("EXIT");
		alert.setHeaderText("You are about to Close the window!");
		alert.setContentText("Do you want to Exit?");
		
		if(alert.showAndWait().get() == ButtonType.OK) {
			stage = (Stage) pane.getScene().getWindow();
			System.out.println("You logged out of the system!");
			stage.close();
		}
	}

	public void login(ActionEvent event) {
		String connectionUrl;
		connectionUrl = "jdbc:mysql://SQL:1234@DESKTOP-HRP200M:3306/test";
		try (Connection connection = DriverManager.getConnection(connectionUrl)) {
			System.out.println("Connection successful");
			System.out.println("<Checking the username>");
			String enteredUsername = textField.getText();
			System.out.println("<Checking the password>");
			String enteredPassword = textField1.getText();
			String sql = "SELECT COUNT(*) FROM Users WHERE user_name = ? AND passord = ?";

			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, enteredUsername);
				statement.setString(2, enteredPassword);
				ResultSet resultSet = statement.executeQuery();
				resultSet.next();
				int count = resultSet.getInt(1);
				if (count == 1) {
					// Authentication successful
					System.out.println("Auth successful");
					Username = enteredUsername;
					switch2Scene2();
				} else {
					// Authentication failed
					System.out.println("Auth failed");
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Invalid Login!");
					alert.setHeaderText("Login credentials don't match !");
					alert.setContentText("Re enter the Username and Password");
					alert.showAndWait();
				}
			} catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
		catch (SQLException e) {
			System.out.println("Connection failed: " + e.getMessage());
		}
	}

	/*public String getsqlattribute(){
		//todo
		return "todo";
	}*/

	public void switch2Scene2() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view2.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
		stage = (Stage) pane.getScene().getWindow();
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
		stage.hide();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
		stage.show();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
		switch2page1(stage);
    }

	public void switch2page1(Stage stage) throws IOException {
		FXMLLoader fxmlLoader1 = new FXMLLoader(Main.class.getResource("page1.fxml"));
		Scene scene = new Scene(fxmlLoader1.load());
		stage.setScene(scene);
		stage.centerOnScreen();
		Controller_page page1 = fxmlLoader1.getController();
		page1.setstage(stage);
		page1.exe(Username);
		stage.show();

	}

	 	@FXML
	    public void mentered(MouseEvent event1) {
	        textField.setStyle("-fx-border-color: #FFFFFF;");
	        System.out.println("mentered methoed triggered");
	        fadeIn();
	    }

	    @FXML
	    public void mexit(MouseEvent event2) {
	        textField.setStyle("-fx-border-color: #999999;");
	        System.out.println("mexit methoed triggered");
	        fadeIn();
	    }
	    
	    /*the animation for password field failed 
	     * TODO
	     */
	    @FXML
	    public void mentered1(MouseEvent event1) {
	        textField1.setStyle("-fx-border-color: #FFFFFF;");
	        System.out.println("mentered methoed triggered");
	        fadeIn();
	    }

	    @FXML
	    public void mexit1(MouseEvent event2) {
	        textField1.setStyle("-fx-border-color: #999999;");
	        System.out.println("mexit methoed triggered");
	        fadeIn();
	    }

	    private void fadeIn() {
	        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), textField);
	        fadeTransition.setFromValue(0.5);
	        fadeTransition.setToValue(1.0);
	        fadeTransition.playFromStart();
	    }

		public void signup(ActionEvent event) throws InterruptedException {
			TranslateTransition animation = new TranslateTransition(Duration.seconds(1), image);
			animation.setToY(-55);
			animation.play();
			TranslateTransition animation1 = new TranslateTransition(Duration.seconds(1),textField);
			animation1.setToY(-65);
			animation1.play();
			TranslateTransition animation1x = new TranslateTransition(Duration.seconds(1), icon);
			animation1x.setToY(-65);
			animation1x.play();
			TranslateTransition animation2 = new TranslateTransition(Duration.seconds(1),textField1);
			animation2.setToY(-65);
			animation2.play();
			TranslateTransition animation2x = new TranslateTransition(Duration.seconds(1), icon2);
			animation2x.setToY(-65);
			animation2x.play();
			imgshange();
			animation2x.setOnFinished(event1 -> {
				FXMLLoader fxmlLoader1 = new FXMLLoader(Main.class.getResource("hello-view1.1.fxml"));
				Scene scene = null;
				try {
					scene = new Scene(fxmlLoader1.load());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				stage = (Stage) pane.getScene().getWindow();
				stage.setScene(scene);
				stage.centerOnScreen();
				Controller1 con = new Controller1();

				stage.show();
			});
		}

		public void imgshange(){
			FadeTransition fadeOutAnchorPane = new FadeTransition(Duration.seconds(0.5), myAnchorPane);
			fadeOutAnchorPane.setFromValue(0.6); // starting opacity
			fadeOutAnchorPane.setToValue(0);    // target opacity

			FadeTransition fadeInPaneX = new FadeTransition(Duration.seconds(0.5), mypanex);
			fadeInPaneX.setFromValue(0);    // starting opacity
			fadeInPaneX.setToValue(0.6);    // target opacity

			fadeOutAnchorPane.setOnFinished(event -> {
				// Once fade out animation of myAnchorPane finishes, fade in animation of mypanex starts
				fadeInPaneX.play();
			});
			fadeOutAnchorPane.play();
		}
}
