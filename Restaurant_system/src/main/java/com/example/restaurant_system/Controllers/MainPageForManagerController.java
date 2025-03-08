package com.example.restaurant_system.Controllers;

import com.example.restaurant_system.DataBaseConnection.UserDAO;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MainPageForManagerController {
    @FXML
    private Button btOrders;
    @FXML
    private Button btMenu;
    @FXML
    private Button btAccount;
    @FXML
    private Button btChat;
    @FXML
    private Text welcomeText;

    @FXML
    public void toChangeCursor(MouseEvent event) throws IOException {
        btOrders.setCursor(Cursor.HAND);
        btMenu.setCursor(Cursor.HAND);
        btAccount.setCursor(Cursor.HAND);
        btChat.setCursor(Cursor.HAND);
    }

    @FXML
    void btOrdersPressed(ActionEvent event) throws IOException, SQLException {
        switchToOredersMan(event);  // Переход в другую сцену
    }

    @FXML
    void btMenuPressed(ActionEvent event) throws IOException {
        switchToMenuMan(event);
    }

    @FXML
    void btAccountInfoPressed(ActionEvent event) throws IOException {
        switchToAccountInfo(event);
    }

    @FXML
    public void switchToOredersMan(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("OrdersPageForManager.fxml"));
        Parent newRoot = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newRoot));
        stage.show();
    }

    @FXML
    public void switchToMenuMan(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MenuPageForManager.fxml"));
        Parent newRoot = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newRoot));
        stage.show();
    }

    @FXML
    public void switchToAccountInfo(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ManagerAccountInfoPage.fxml"));
        Parent newRoot = fxmlLoader.load();

        ManagerAccountInfoPageController controller = fxmlLoader.getController();
        controller.setProperties(UserDAO.getName(LoginPageController.username.get(0)), LoginPageController.username.get(0), UserDAO.getPassword(LoginPageController.username.get(0)));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newRoot));
        stage.show();
    }

    @FXML
    public void setWelcomeText(String text) throws IOException {
        welcomeText.setText(text);
    }
}