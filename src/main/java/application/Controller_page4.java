package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Controller_page4 {
    boolean flag = false;
    Stage stage;
    @FXML
    AnchorPane anchorPane2;
    Scene scene;
    @FXML
    private Button back;
    @FXML
    private Button closeb;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setstage(Stage stage) {
        this.stage = stage;
    }

    private static int count = 0;

    public void create_list_movies() {
        int x;
        String connectionUrl;
        connectionUrl = "jdbc:mysql://SQL:1234@DESKTOP-HRP200M:3306/test";
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            System.out.println("Connection successful");
            String sql2 = "SELECT count(*) FROM (\n" +
                    "    SELECT M_name, imdb_ratings, ROW_NUMBER() OVER (ORDER BY M_name) AS mid FROM Animemovie\n" +
                    "    UNION\n" +
                    "    SELECT TVS_name, imdb_ratings, ((select count(*) from Animemovie) + (ROW_NUMBER() OVER (ORDER BY TVS_name))) AS mid FROM Animetvseries\n" +
                    ") AS subquery;";
            try (PreparedStatement statement = connection.prepareStatement(sql2)) {
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                x = resultSet.getInt(1);
                System.out.println(x);
                this.setCount(x);
            }/* catch (Exception e) {
                e.printStackTrace();
            }*/
        } catch (SQLException e) {
        }
    }

    public void create_listx() {
        if (!flag) {
            int count = this.getCount();
            double Y = 0;
            System.out.println("metoed x triggered!");
            System.out.println(count);
            try {
                for (int i = 0; i < count; i++) {
                    System.out.println("Inside for");
                    Line line = new Line();
                    line.setStroke(Color.WHITE);
                    line.setStartX(200);
                    line.setEndX(1000);
                    line.setStartY(Y);
                    line.setEndY(Y);
                    line.setStrokeWidth(2.0);
                    anchorPane2.getChildren().add(line);
                    Label label = new Label();
                    label.setText(getname(i + 1));
                    label.setLayoutY(Y + 10);
                    label.setFont(Font.font("Poppins", 20));
                    label.setTextFill(Color.WHITE);
                    label.setLayoutX(200);
                    anchorPane2.getChildren().add(label);
                    label.setOnMouseClicked(event -> {
                        try {
                            switchtoinfo3(label.getText());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    Label label2 = new Label();
                    label2.setText(getrate(i + 1));
                    label2.setLayoutY(Y + 10);
                    label2.setFont(Font.font("Poppins", 20));
                    label2.setTextFill(Color.WHITE);
                    label2.setLayoutX(950);
                    anchorPane2.getChildren().add(label2);
                    Y += 50;
                    Line line2 = new Line();
                    line2.setStroke(Color.WHITE);
                    line2.setStartX(200);
                    line2.setEndX(1000);
                    line2.setStartY(Y);
                    line2.setEndY(Y);
                    line2.setStrokeWidth(2.0);
                    Y += 10;
                    anchorPane2.getChildren().add(line2);
                    flag = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getCount() {
        System.out.println(count);
        return count;
    }

    public void setCount(int count) {
        System.out.println("Setcount triggerd");
        System.out.println(count);
        System.out.println(this.getCount());
        Controller_page4.count = count;
        System.out.println(count);
        System.out.println(this.getCount());
    }

    public String getname(int x) {
        String name;
        String connectionUrl;
        connectionUrl = "jdbc:mysql://SQL:1234@DESKTOP-HRP200M:3306/test";
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            System.out.println("Connection successful");
            String sql3 = "SELECT * FROM (\n" +
                    "    SELECT M_name, imdb_ratings, ROW_NUMBER() OVER (ORDER BY M_name) AS mid FROM Animemovie\n" +
                    "    UNION\n" +
                    "    SELECT TVS_name, imdb_ratings, ((select count(*) from animemovie) + (ROW_NUMBER() OVER (ORDER BY TVS_name))) AS mid FROM Animetvseries\n" +
                    ") AS subquery where mid = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql3)) {
                statement.setString(1, String.valueOf(x));
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                name = resultSet.getString(1);
                System.out.println(name);
                return name;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getrate(int x) {
        String rate;
        String connectionUrl;
        connectionUrl = "jdbc:mysql://SQL:1234@DESKTOP-HRP200M:3306/test";
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            System.out.println("Connection successful");
            String sql4 = "SELECT * FROM (\n" +
                    "    SELECT M_name, imdb_ratings, ROW_NUMBER() OVER (ORDER BY M_name) AS mid FROM Animemovie\n" +
                    "    UNION\n" +
                    "    SELECT TVS_name, imdb_ratings, ((select count(*) from animemovie) + (ROW_NUMBER() OVER (ORDER BY TVS_name))) AS mid FROM Animetvseries\n" +
                    ") AS subquery where mid = ?";
            ;
            try (PreparedStatement statement = connection.prepareStatement(sql4)) {
                statement.setString(1, String.valueOf(x));
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                rate = resultSet.getString(2);
                System.out.println(rate);
                return rate;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchtoinfo3(String name) throws IOException {
        showinfo3(name);
        System.out.println(name);
    }

    public void showinfo3(String name) throws IOException {
        String connectionUrl;
        connectionUrl = "jdbc:mysql://SQL:1234@DESKTOP-HRP200M:3306/test";
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            String sql = "SELECT COUNT(*) FROM Animemovie WHERE M_name = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                int count = resultSet.getInt(1);
                if (count == 1) {
                    Con2 con2 = new Con2();
                    con2.setname(name);
                    con2.Createstg();
                } else {
                    Con3 con3 = new Con3();
                    con3.setname(name);
                    con3.Createstg();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void back(ActionEvent event){
        Controller con = new Controller();
        try {
            con.switch2page1(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
