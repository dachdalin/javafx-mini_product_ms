module miniproduct.miniproduct {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;


    opens miniproduct.miniproduct to javafx.fxml;
    exports miniproduct.miniproduct;
}