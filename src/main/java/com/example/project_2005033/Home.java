package com.example.project_2005033;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private Label prodName;

    @FXML
    private Button button2;
    @FXML
    private AnchorPane anchorHome;
    @FXML
    private Label picture;

    @FXML
    private ImageView imageView;
    private FXMLLoader fxmlLoader;

    static int exitToken;

//    public Home()
//    {
//        prodName.setText(LogInPage.sk);
//        button2.setText(LogInPage.sk);
//    }


    public void openSearchMovies(ActionEvent a)
    {
        try {
            ProductionSearch.choice=4;
            fxmlLoader = new FXMLLoader(Main.class.getResource("tableview.fxml"));
            stage = (Stage) ((Node) a.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Hello2!");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e)
        {
            System.out.println("Not Found");
        }
    }
    public void openSearchProduction(ActionEvent a)
    {
        try {
            fxmlLoader = new FXMLLoader(Main.class.getResource("Production_Search.fxml"));
            stage = (Stage) ((Node) a.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Hello2!");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e)
        {
            System.out.println("Not Found");
        }
    }
    public void openAdder(ActionEvent a)
    {
        try {
            fxmlLoader = new FXMLLoader(Main.class.getResource("AddMovie.fxml"));
            stage = (Stage) ((Node) a.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Hello2!");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e)
        {
            System.out.println("Not Found");
        }
    }
    public void Exiter(ActionEvent a)  {

        try {
            Client.ois.close();
            Client.oos.close();
            Client.socket.close();
            //LogInPage.sk="";
            Client.end2();//
            fxmlLoader = new FXMLLoader(Main.class.getResource("LogInPage.fxml"));
            stage = (Stage) ((Node) a.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Hello2!");
            stage.setScene(scene);
            stage.show();
            System.out.println("logout");
            //Client.oos.writeObject(new DataWrap(LogInPage.sk,Client.socket));//
            //Client.end();
            exitToken=1;
        }
        catch (Exception e)
        {
            System.out.println("Cannot logout");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println(LogInPage.sk);
        try {
            //System.out.println(LogInPage.sk);
            prodName.setText("Welcome "+LogInPage.sk);
            button2.setText("In "+LogInPage.sk);
            String s1="/Image/"+LogInPage.sk.toUpperCase()+".jpg";
            anchorHome.setStyle("-fx-background-image: url(Image/background.jpg)");
            //if(!LogInPage.sk.equalsIgnoreCase(""))
            imageView.setImage(new Image(getClass().getResourceAsStream(s1)));
        }
        catch (Exception e)
        {
            System.out.println(e);
            imageView.setImage(new Image(getClass().getResourceAsStream("/Image/background3.jpg")));
        }
    }

    public void TransferMovie(ActionEvent a) {
        try {
            fxmlLoader = new FXMLLoader(Main.class.getResource("Transfer.fxml"));
            stage = (Stage) ((Node) a.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Hello2!");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e)
        {
            System.out.println("Not Found");
        }
    }
}
