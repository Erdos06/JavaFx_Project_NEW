package com.example.restaurant_system.Controllers;

import com.example.restaurant_system.DataBaseConnection.UserDAO;
import com.example.restaurant_system.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginPageController {
    public static ArrayList<String> username = new ArrayList<>();

    //UI controllers
    @FXML
    private TextField idField;
    @FXML
    private TextField passwordField;
    @FXML
    private Text ErrorTxt;
    @FXML
    private Button signInButton;
    @FXML
    private Button signUpButton;

    @FXML
    void btSignInPressed(ActionEvent event) throws IOException, SQLException {
        String inputUsername = idField.getText();  // Получаем имя из поля ввода
        String inputPassword = passwordField.getText();  // Получаем пароль из поля ввода

        String dbPassword = UserDAO.getPassword(inputUsername);  // Получаем пароль из базы

        if (dbPassword != null && dbPassword.equals(inputPassword) && UserDAO.isManager(inputUsername)) {
              // Переход в другую сцену
            if (username.isEmpty()){
                username.add(inputUsername);
            }
            else {
                username.remove(0);
                username.add(inputUsername);
            }
            switchToScene2(event);
        }
        else {
            ErrorTxt.setText("ID or Password is wrong");  // Ошибка
        }
    }

    @FXML
    void btSignUpPressed(ActionEvent event) throws IOException {
        switchToRegistrForm(event);
    }
    @FXML
    public void switchToScene2(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainPageForManager.fxml"));
        Parent newRoot = fxmlLoader.load();

        MainPageForManagerController controller = fxmlLoader.getController();

        if(!username.isEmpty()) {
            controller.setWelcomeText("Welcome, " + UserDAO.getName(username.get(0)));
        }
        else {
            System.out.println("EMPTY");
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newRoot));
        stage.show();
    }
    @FXML
    public void switchToRegistrForm(ActionEvent event) {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/restaurant_system/RegistrationForm.fxml"));
                Parent newRoot = loader.load();
                Stage registrationStage = new Stage();
                registrationStage.setScene(new Scene(newRoot));
                registrationStage.setTitle("The Red Cafe");
                registrationStage.getIcons().add(new Image("https://bcassetcdn.com/public/blog/wp-content/uploads/2019/07/18094833/the-red-cafe.png"));

                // Блокируем главное окно входа
                registrationStage.initModality(Modality.APPLICATION_MODAL);

                registrationStage.showAndWait(); // Ждём закрытия перед возвратом управления
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



    @FXML
    public void toChangeCursor(MouseEvent event) throws IOException{
        signInButton.setCursor(Cursor.HAND);
        signUpButton.setCursor(Cursor.HAND);
    }
}


