package com.example.restaurant_system.Controllers;

import com.example.restaurant_system.DataBaseConnection.UserDAO;
import com.example.restaurant_system.Users.Customer;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class RegistrationFormController {
    @FXML
    private TextField name;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Text successfullRegistr;
    @FXML
    private Text errorRegistr;
    @FXML
    private Button btSignUp;

    @FXML
    public void toChangeCursor(MouseEvent event) throws IOException {
        btSignUp.setCursor(Cursor.HAND);
    }

    public void saveClient() throws SQLException {
        if(username.getText().isEmpty() || password.getText().isEmpty() || name.getText().isEmpty() || UserDAO.getUsername(username.getText())){
            successfullRegistr.setText("");
            errorRegistr.setText("Please enter another user ID or try to fill all blanks");
        }
        else {
            Customer customer = new Customer(username.getText(), password.getText(), name.getText());
            UserDAO.saveUser(customer);
            errorRegistr.setText("");
            successfullRegistr.setText("Successful registration");
        }
    }
}
