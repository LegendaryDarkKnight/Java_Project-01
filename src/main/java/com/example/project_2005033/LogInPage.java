package com.example.project_2005033;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class LogInPage implements Serializable, Initializable {

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    public static String sk;
    public static String pk;
    @FXML
    private Button logIn;
    @FXML
    private TextField userName;
    @FXML
    private TextField passWord;

    @FXML
    private Label texter;

    @FXML
    private AnchorPane anchor;

    @FXML
    private Label imager;

    public String proName;

    @FXML
    public ImageView image1;
    @FXML
    public ImageView image2;
    @FXML
    public ImageView image3;
    @FXML
    public Button info;

    public void mouseOnBox()
    {
        userName.setStyle("-fx-background-color:white; -fx-text-fill:black;");
        //texter.setStyle("-fx-text-fill:white;");
        //anchor.setStyle("-fx-background-color: linear-gradient(to left, #6526bd, #9c1fbf);");
    }
    public void mouseOutBox()
    {
        userName.setStyle("-fx-background-color: #3a055e; -fx-text-fill:white;");
        //texter.setStyle(" ");
        //anchor.setStyle("-fx-background-color: linear-gradient(to right, #6526bd, #9c1fbf);");
    }

    public void welcome(ActionEvent a) throws IOException {

        texter.setText("Welcome "+ userName.getText());
        sk=userName.getText();
        boolean x=Client.clientCaller(sk);
        if(x) {
            try {
                fxmlLoader = new FXMLLoader(Main.class.getResource("Home.fxml"));
                //System.out.println(1);
                stage = (Stage) ((Node) a.getSource()).getScene().getWindow();
                //System.out.println(2);
                Scene scene = new Scene(fxmlLoader.load(),800,500);
                //System.out.println(3);
                stage.setTitle("Hello2!");
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                System.out.println("Not Found " + e);
            }

        }
        else
        {
            texter.setText("Not found "+ userName.getText());
        }
    }
    public void info(ActionEvent actionEvent)
    {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information System");
        alert.setContentText("Supervised by: Dr.Mohammad Mahfuzul Islam.\nDeveloped by: Somik Dasgupta,");
        alert.setHeaderText("This is Java Fx Project of CSE 108 Course.");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchor.setStyle("-fx-background-image: url(Image/background3.jpg) ");
        //System.out.println("Client Started. ");

    }
}
