module com.example.restaurant_system {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.restaurant_system to javafx.fxml;
    exports com.example.restaurant_system;
    exports com.example.restaurant_system.Controllers;
    opens com.example.restaurant_system.Controllers to javafx.fxml;
}