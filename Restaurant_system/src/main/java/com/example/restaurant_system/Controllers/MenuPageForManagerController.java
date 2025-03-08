package com.example.restaurant_system.Controllers;

import com.example.restaurant_system.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPageForManagerController {
    @FXML
    private Button btGoBack;
    @FXML
    void btGoBackPressed(ActionEvent event) throws IOException {
        LoginPageController controller = new LoginPageController();
        controller.switchToScene2(event);
    }
    @FXML
    public void toChangeCursor(MouseEvent event) throws IOException {
        btGoBack.setCursor(Cursor.HAND);
    }

    public void switchBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainPageForManager.fxml"));
        Parent newRoot = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newRoot));
        stage.show();
    }
}
