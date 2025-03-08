package com.example.restaurant_system.Controllers;

import com.example.restaurant_system.DataBaseConnection.UserDAO;
import com.example.restaurant_system.Main;
import com.example.restaurant_system.Users.MainUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ManagerAccountInfoPageController {
    @FXML
    private Button btLogOut;
    @FXML
    private Button btChange;
    @FXML
    private Button btGoBack;
    @FXML
    private TextField name;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Text errorRegistr;
    @FXML
    private Text successfullRegistr;


    @FXML
    void btLogOutPressed(ActionEvent event) throws IOException {
        switchToLogOut(event);
    }
    @FXML
    void btGoBackPressed(ActionEvent event) throws IOException {
        LoginPageController controller = new LoginPageController();
        controller.switchToScene2(event);
    }
    @FXML
    public void toChangeCursor(MouseEvent event) throws IOException{
        btGoBack.setCursor(Cursor.HAND);
        btChange.setCursor(Cursor.HAND);
        btLogOut.setCursor(Cursor.HAND);
    }

    @FXML
    public void switchToLogOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginPage.fxml"));
        Parent newRoot = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newRoot));
        stage.show();
    }

    @FXML
    public void changeBtnPressed(ActionEvent event) throws IOException, SQLException {
        MainUser user = new MainUser(username.getText(), password.getText(), name.getText());
        UserDAO.changeUser(user);
        successfullRegistr.setText("Successfully changed!");
        LoginPageController.username.set(0, username.getText());
        LoginPageController controller = new LoginPageController();
        controller.switchToScene2(event);
    }

    public void setProperties(String name, String username, String password){
        this.name.setText(name);
        this.username.setText(username);
        this.password.setText(password);
    }
}
