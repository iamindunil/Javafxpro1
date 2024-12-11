package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.io.IOException;
import java.sql.*;


public class Con3 {
    @FXML
    private Label TVS_tag;
    @FXML
    private Label TVS_name;
    @FXML
    private Label directors;
    @FXML
    private Label writers;
    @FXML
    private Label characters;
    @FXML
    private Label genres;
    @FXML
    private Label imdb_ratings;
    @FXML
    private Label rel_date;
    @FXML
    private Label country_or;
    @FXML
    private Label languages;
    @FXML
    private Label prod_company;
    @FXML
    private Label runtime;
    @FXML
    private Label mal_ratings;
    @FXML
    private Label Char1;
    private String name;


    protected void setname(String name) {
        this.name = name;
    }

    public void Createstg() {
        Stage stagex = new Stage();
        FXMLLoader fxmlLoaderx = new FXMLLoader(Main.class.getResource("con3.fxml"));
        fxmlLoaderx.setController(this);
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoaderx.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stagex.setTitle("Hello!");
        stagex.initStyle(StageStyle.DECORATED);
        stagex.setResizable(false);
        stagex.setScene(scene);
        stagex.show();
        scene.setOnMouseEntered(event -> {
            loadclass();
        });
    }

    protected void loadclass() {
        String connectionUrl;
        connectionUrl = "jdbc:mysql://SQL:1234@DESKTOP-HRP200M:3306/test";
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            System.out.println("Connection successful");
            String sql = "SELECT COUNT(*) FROM Animetvseries WHERE TVS_name = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                int count = resultSet.getInt(1);
                if (count == 1) {
                    TVS_tag.setText("Anime");
                    System.out.println("Entry integration successful - Animetvseries");
                    Animemovieload();
                } else {
                    Connection connection2 = DriverManager.getConnection(connectionUrl);
                    System.out.println("Connection 2 established!");
                    String sql2 = "SELECT COUNT(*) FROM Lactiontvseries WHERE TVS_name = ?";
                    try (PreparedStatement statement2 = connection2.prepareStatement(sql2)) {
                        statement2.setString(1, name);
                        ResultSet resultSet2 = statement2.executeQuery();
                        resultSet2.next();
                        int count2 = resultSet2.getInt(1);
                        if (count2 == 1) {
                            Char1.setText("Actors                                -");
                            System.out.println("Entry integration successful - Animemovie");
                            Lactionmovieload();
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Animemovieload () {
        Animetvseries movie1;
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Animetvseries.class);
        try {
            Session session;
            SessionFactory sessionfactory1 = configuration.buildSessionFactory();
            session = sessionfactory1.openSession();
            Transaction transaction = session.beginTransaction();
            movie1 = session.get(Animetvseries.class, name);
            transaction.commit();
            System.out.println(movie1.toString());
            loadallani(movie1);
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
    protected void loadallani (Animetvseries movie){
        TVS_name.setText(movie.getTVS_name());
        directors.setText(movie.getDirectors());
        writers.setText(movie.getDirectors());
        characters.setText(movie.getCharacters());
        genres.setText(movie.getGenres());
        imdb_ratings.setText(String.valueOf(movie.getImdb_ratings()));
        rel_date.setText(movie.getRel_date());
        country_or.setText(movie.getCountry_or());
        languages.setText(movie.getLanguages());
        prod_company.setText(movie.getProd_company());
        runtime.setText(movie.getRuntime());
        mal_ratings.setText(String.valueOf(movie.getMal_ratings()));
    }

    public void Lactionmovieload () {
        Lactiontvseries movie1;
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Lactiontvseries.class);
        try {
            Session session;
            SessionFactory sessionfactory1 = configuration.buildSessionFactory();
            session = sessionfactory1.openSession();
            Transaction transaction = session.beginTransaction();
            movie1 = session.get(Lactiontvseries.class, name);
            transaction.commit();
            System.out.println(movie1.toString());
            loadallLaction(movie1);
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
    protected void loadallLaction (Lactiontvseries movie){
        TVS_name.setText(movie.getTVS_name());
        directors.setText(movie.getDirectors());
        writers.setText(movie.getDirectors());
        characters.setText(movie.getActors());
        genres.setText(movie.getGenres());
        imdb_ratings.setText(String.valueOf(movie.getImdb_ratings()));
        rel_date.setText(movie.getRel_date());
        country_or.setText(movie.getCountry_or());
        languages.setText(movie.getLanguages());
        prod_company.setText(movie.getProd_company());
        runtime.setText(movie.getRuntime());
        mal_ratings.setText("N/A");
    }
}
