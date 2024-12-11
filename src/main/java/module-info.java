module application {
        requires javafx.controls;
        requires javafx.fxml;
        requires org.controlsfx.controls;
        requires javafx.graphics;
        requires java.sql;
        requires jakarta.persistence;
        requires org.hibernate.orm.core;
        requires java.naming;
    opens application to org.hibernate.orm.core, javafx.fxml;
    exports application;
}