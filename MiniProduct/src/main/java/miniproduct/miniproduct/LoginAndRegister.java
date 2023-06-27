package miniproduct.miniproduct;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginAndRegister implements Initializable {
    @FXML
    private Pane login_pane;
    @FXML
    private Pane register_pane;

    private Connection connect;
    private Alert Alert;
    @FXML
    private AnchorPane dashboard_form;

    //function Register data on input form
    @FXML
    private Hyperlink btn_goLogIn;

    @FXML
    private Button btn_signUp;

    @FXML
    private TextField txt_email;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_username;

    public void btn_signUp(){
        if (txt_username.getText().isEmpty() || txt_password.getText().isEmpty() || txt_email.getText().isEmpty()){
            Alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
            Alert.setContentText("Please fill all fields");
            Alert.showAndWait();
        }else {
            String sql = "INSERT INTO users(username,password,email) values(?,?,?)";
            connect = Database.conn();
            try {
                String checkUsername ="SELECT username FROM users WHERE username = '" + txt_username.getText() + "'";
                PreparedStatement check = connect.prepareStatement(checkUsername);
                ResultSet rs = check.executeQuery();
                if (rs.next()){
                    Alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
                    Alert.setContentText("Username already exist");
                    Alert.showAndWait();
                } else if (txt_password.getText().length() < 5) {
                    Alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
                    Alert.setContentText("Password must be at least 5 characters");
                    Alert.showAndWait();

                }else {
                PreparedStatement ps = connect.prepareStatement(sql);
                ps.setString(1, txt_username.getText());
                ps.setString(2, txt_password.getText());
                ps.setString(3, txt_email.getText());
                ps.executeUpdate();
                register_pane.getScene().getWindow().hide();
                Alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                Alert.setContentText("Register Success");
                Alert.showAndWait();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Form Login");
                stage.setScene(scene);
                stage.show();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void btn_goLogIn() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Form Login");
        stage.setScene(scene);
        stage.show();
        register_pane.getScene().getWindow().hide();

    }
    //function login data on input form
    @FXML
    private Hyperlink btn_go_signUp;

    @FXML
    private Button btn_signIn;

    @FXML
    private PasswordField txt_passwordF;

    @FXML
    private TextField txt_usernameF;

    public void btn_signIn(){
        if (txt_usernameF.getText().isEmpty() || txt_passwordF.getText().isEmpty()){
            Alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
            Alert.setContentText("Please fill all fields");
            Alert.showAndWait();
        }else {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            connect = Database.conn();
            try {
                PreparedStatement ps = connect.prepareStatement(sql);
                ps.setString(1, txt_usernameF.getText());
                ps.setString(2, txt_passwordF.getText());
                ResultSet rs = ps.executeQuery();

                if (rs.next()){
                    cdata.username = txt_usernameF.getText();

                    login_pane.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard-view.fxml")));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Mini Product Management");
                    stage.setScene(scene);
                    stage.show();
                    dashboard_form.setVisible(true);
                }else {
                    Alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
                    Alert.setContentText("Wrong username or password");
                    Alert.show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void btn_go_signUp() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register-view.fxml")));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Form Register");
        stage.setScene(scene);
        stage.show();
        login_pane.getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      //
    }
}
