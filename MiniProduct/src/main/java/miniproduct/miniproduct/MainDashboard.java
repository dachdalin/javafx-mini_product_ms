package miniproduct.miniproduct;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;

import static miniproduct.miniproduct.cdata.username;

public class MainDashboard implements Initializable {
    @FXML
    private Label Label_Users;
    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private TableView<ProductData> TableProduct;

    @FXML
    private TableColumn<ProductData, Integer> amount;

    @FXML
    private TableColumn<ProductData, Integer> id;

    @FXML
    private TableColumn<ProductData, String> name;

    @FXML
    private TableColumn<ProductData, Integer> price;

    @FXML
    private TableColumn<ProductData, Integer> qty;
    // fields input form
    @FXML
    private TextField TxtAmount;
    @FXML
    private TextField TxtName;
    @FXML
    private TextField TxtPrice;

    @FXML
    private TextField TxtQty;
    // button action
    @FXML
    private Button Btn_Delete;
    @FXML
    private Button Btn_Edit;
    private Alert Alert;
    private ObservableList<ProductData> data;
    private Connection connect;
    @FXML
    private AnchorPane dashboard_total;
    @FXML
    private AnchorPane dashboard_view;

    @FXML
    private Button Btn_Home;

    @FXML
    private Button Btn_View;
    @FXML
    private Hyperlink btn_logOut;
    @FXML
    private Label Label_name;

    @FXML
    private Label Label_Coca;

    @FXML
    private Label Label_DutchMilk;

    @FXML
    private Label Label_Fanta;

    @FXML
    private Label Label_FruitJuice;

    @FXML
    private Label Label_Sting;

    @FXML
    private Label Label_Pepsi;

    public  void countCoca(){
       String sql = "SELECT COUNT(name) FROM products WHERE name = 'coca'";
       connect = Database.conn();
       try {
           PreparedStatement ps = connect.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           rs.next();
           Label_Coca.setText(rs.getString(1));
       }catch (Exception e){
           e.printStackTrace();
       }
    }
    //function count Sting
    public void countSting(){
        String sql = "SELECT COUNT(name) FROM products WHERE name = 'sting'";
        connect = Database.conn();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Label_Sting.setText(rs.getString(1));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //function count DutchMilk
    public void countDutchMilk(){
        String sql = "SELECT COUNT(name) FROM products WHERE name = 'dutch milk'";
        connect = Database.conn();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Label_DutchMilk.setText(rs.getString(1));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //function count Fanta
    public void countFanta(){
        String sql = "SELECT COUNT(name) FROM products WHERE name = 'fanta'";
        connect = Database.conn();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Label_Fanta.setText(rs.getString(1));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //function count FruitJuice
    public void countFruitJuice(){
        String sql = "SELECT COUNT(name) FROM products WHERE name = 'fruit juice'";
        connect = Database.conn();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Label_FruitJuice.setText(rs.getString(1));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //function count Pepsi
    public void countPepsi(){
        String sql = "SELECT COUNT(name) FROM products WHERE name = 'pepsi'";
        connect = Database.conn();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Label_Pepsi.setText(rs.getString(1));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //function count users
    public void countUsers(){
        String sql = "SELECT COUNT(username) FROM users";
        connect = Database.conn();

        try {
            int nc = 0;
           PreparedStatement prepare = connect.prepareStatement(sql);
           ResultSet result = prepare.executeQuery();

           while (result.next()){
               nc = result.getInt(1);
               Label_Users.setText(String.valueOf(nc));


           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showName(){
        String user = username;
        user = user.substring(0,1).toUpperCase() + user.substring(1);
        Label_name.setText(user);
    }

    public void switchForm(ActionEvent event)  {

        if (event.getSource() == Btn_Home){
           dashboard_total.setVisible(true);
           dashboard_view.setVisible(false);
           showName();
           countCoca();
           countSting();
           countDutchMilk();
           countFanta();
           countFruitJuice();
           countPepsi();
           countUsers();

        }else if (event.getSource() == Btn_View){
            dashboard_total.setVisible(false);
            dashboard_view.setVisible(true);
            showData();

        }

    }
    //function logout button
    public void btn_logOut(){
        Alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        Alert.setContentText("Do you want to logout?");
        Alert.setTitle("Logout");
        Alert.showAndWait();
        try {
            if (Alert.getResult() == ButtonType.OK){
                btn_logOut.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Login Form");
                stage.setScene(scene);
                stage.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<ProductData> getData() {
        ObservableList<ProductData> listdata = FXCollections.observableArrayList();
        String sql = "SELECT * FROM products";
        connect = Database.conn();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ProductData productShow = new ProductData();
            while (rs.next()){
//            productShow = new ProductData(
//                    rs.getInt("id"),
//                    rs.getString("name"),
//                    rs.getInt("qty"),
//                    rs.getInt("price"),
//                    rs.getInt("amount"));

                productShow.setId(rs.getInt("id"));
                productShow.setName(rs.getString("name"));
                productShow.setQty(rs.getInt("qty"));
                productShow.setPrice(rs.getInt("price"));
                productShow.setAmount(rs.getInt("amount"));

            }
            listdata.add(productShow);
        }catch (Exception e){
            e.printStackTrace();
        }
        return listdata;
    }
    public void showData(){
        data = getData();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        TableProduct.setItems(data);
    }

    // function select data from table
    public void selectData(){
        ProductData product = TableProduct.getSelectionModel().getSelectedItem();
        int selectID = TableProduct.getSelectionModel().getFocusedIndex();
        if ((selectID-1) <-1){
           return;
        }
        TxtName.setText(product.getName());
        TxtPrice.setText(String.valueOf(product.getPrice()));
        TxtQty.setText(String.valueOf(product.getQty()));
        TxtAmount.setText(String.valueOf(product.getAmount()));

        cdata.id = product.getId();

    }
    // function insert data to table
    public void Btn_Insert(){
        String sql = "INSERT INTO products (name,qty,price,amount) values (?,?,?,?)";
        connect = Database.conn();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, TxtName.getText());
            ps.setInt(2, Integer.parseInt(TxtQty.getText()));
            ps.setInt(3, Integer.parseInt(TxtPrice.getText()));
            ps.setInt(4, Integer.parseInt(TxtAmount.getText()));
            ps.executeUpdate();
            if (ps.executeUpdate()>0){
                Alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                Alert.setContentText("Insert Success");
                Alert.showAndWait();
                showData();
                clearText();
            }else{
                Alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
                Alert.setContentText("Insert Failed");
                Alert.show();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // function delete data from table
    public void Btn_Delete(){
        String sql = "DELETE FROM products WHERE id =" + cdata.id;
        connect = Database.conn();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            if (ps.executeUpdate()==0){
                Alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
                Alert.setContentText("Delete Success");
                Alert.showAndWait();
                showData();
                clearText();
            }else {
                Alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
                Alert.setContentText("Delete Failed");
                Alert.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // function edit data from table
    public void Btn_Edit(){
        String sql = "UPDATE products SET name='"+this.TxtName.getText()+"',qty='"+this.TxtQty.getText()+"',price='"+this.TxtPrice.getText()+"',amount='"+this.TxtAmount.getText()+"' WHERE id=" + cdata.id;
        connect = Database.conn();
        try {
            PreparedStatement updateProduct = connect.prepareStatement(sql);
            updateProduct.executeUpdate();
            if (updateProduct.executeUpdate()>0){
                Alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                Alert.setContentText("Edit Success");
                Alert.show();
                showData();
                clearText();
            }else {
                Alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
                Alert.setContentText("Edit Failed");
                Alert.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // function clear data on input form
    public void clearText(){
        TxtName.clear();
        TxtPrice.clear();
        TxtQty.clear();
        TxtAmount.clear();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showData();
        showName();
        countCoca();
        countDutchMilk();
        countFanta();
        countPepsi();
        countUsers();
        countSting();
    }
}
