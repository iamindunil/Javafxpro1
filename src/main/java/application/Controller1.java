package application;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class Controller1 {
	@FXML
	private TextField textField;

	@FXML
	private PasswordField textField1;

	@FXML
	private PasswordField textField2;

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
	ImageView icon3;

	@FXML
	AnchorPane mypanex;

	@FXML
	AnchorPane myAnchorPane;

	@FXML
	Label warn;

	private boolean flagx;

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

	public void login(ActionEvent event) throws SQLException {
		if (!Objects.equals(textField.getText(), "") && !Objects.equals(textField2.getText(), "") && !Objects.equals(textField1.getText(), "")) {
			if (textField1.getText().equals(textField2.getText())) {
				String connectionUrl;
				connectionUrl = "jdbc:mysql://SQL:1234@DESKTOP-HRP200M:3306/test";
				try (Connection connection = DriverManager.getConnection(connectionUrl)) {
					System.out.println("Connection successful");
					String enteredUsername = textField.getText();
					String enteredPassword = textField1.getText();
					String sql = "insert into users values (?, ?);";

					try (PreparedStatement statement = connection.prepareStatement(sql)) {
						statement.setString(1, enteredUsername);
						statement.setString(2, enteredPassword);
						int rowsInserted = statement.executeUpdate();
						if (rowsInserted > 0) {
							System.out.println("Insertion successful");
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Successful");
							alert.setHeaderText("The Sign up is Successful!");
							alert.setContentText("The sign up is Successful. You will be forwarded to the login page!");

							if(alert.showAndWait().get() == ButtonType.OK) {
								loginx(new ActionEvent());
							}
						} else {
							Alert alert = new Alert(AlertType.WARNING);
							alert.setTitle("Sign up failed!");
							alert.setHeaderText("The Creation of the user Login Failed");
							alert.setContentText("Something went wrong!. Please try again later");
							alert.showAndWait();
							System.out.println("Insertion failed");
						}
					} catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
			}else{
				warn.setText("The password does not match!");
			}
		}else{
			warn.setText("All the fields are required!");
		}
	}

	 	@FXML
	    public void mentered(MouseEvent event1) {
	        textField.setStyle("-fx-border-color: #FFFFFF;");
	        System.out.println("mentered methoed triggered");
	        //fadeIn();
	    }

	    @FXML
	    public void mexit(MouseEvent event2) {
	        textField.setStyle("-fx-border-color: #999999;");
	        System.out.println("mexit methoed triggered");
	        //fadeIn();
	    }

	    @FXML
	    public void mentered1(MouseEvent event1) {
			discon();
	        textField1.setStyle("-fx-border-color: #FFFFFF;");
	        System.out.println("mentered methoed triggered");
	        //fadeIn();
	    }

	    @FXML
	    public void mexit1(MouseEvent event2) {
	        textField1.setStyle("-fx-border-color: #999999;");
	        System.out.println("mexit methoed triggered");
	        //fadeIn();
	    }

		public void discon(){
			if(!flagx){
				FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), textField2);
				fadeTransition.setFromValue(0);
				fadeTransition.setToValue(1.0);
				fadeTransition.playFromStart();
				FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(2000), icon2);
				fadeTransition1.setFromValue(0);
				fadeTransition1.setToValue(1.0);
				fadeTransition1.playFromStart();
				flagx = true;
			}
		}
	    private void fadeIn() {
	        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), textField);
	        fadeTransition.setFromValue(0.5);
	        fadeTransition.setToValue(1.0);
	        fadeTransition.playFromStart();
	    }

	public void loginx(ActionEvent event) throws InterruptedException {
		TranslateTransition animation = new TranslateTransition(Duration.seconds(1), image);
		animation.setToY(55);
		animation.play();
		TranslateTransition animation1 = new TranslateTransition(Duration.seconds(1),textField);
		animation1.setToY(65);
		animation1.play();
		TranslateTransition animation1x = new TranslateTransition(Duration.seconds(1), icon);
		animation1x.setToY(65);
		animation1x.play();
		TranslateTransition animation2 = new TranslateTransition(Duration.seconds(1),textField1);
		animation2.setToY(65);
		animation2.play();
		TranslateTransition animation2x = new TranslateTransition(Duration.seconds(1), icon3);
		animation2x.setToY(65);
		animation2x.play();
		imgshange();
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(700), textField2);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.playFromStart();
		FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(700), icon2);
		fadeTransition1.setFromValue(1);
		fadeTransition1.setToValue(0);
		fadeTransition1.playFromStart();
		animation2x.setOnFinished(event1 -> {
			FXMLLoader fxmlLoader1 = new FXMLLoader(Main.class.getResource("hello-view1.fxml"));
			Scene scene = null;
			try {
				scene = new Scene(fxmlLoader1.load());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			stage = (Stage) pane.getScene().getWindow();
			stage.setScene(scene);
			stage.centerOnScreen();
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
