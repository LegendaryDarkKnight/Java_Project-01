package com.example.project_2005033;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.*;

import static com.example.project_2005033.Client.list;
import static com.example.project_2005033.LogInPage.sk;

public class TableViewController implements Initializable{

    public static int teller=0;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    public static List<Movies> listMain;
    @FXML
    private Label prompt;
    @FXML
    private TableView<Movies> tableView;
    @FXML
    private TableColumn<Movies,String> movieTitle;
    @FXML
    private TableColumn<Movies,Integer> releaseYear;

    @FXML
    private TableColumn<Movies,String> productionCompany;

    @FXML
    private TableColumn<Movies, Button> more;
    ObservableList<Movies> data;
    public TableViewController(List<Movies> list1)
    {
        data=FXCollections.observableArrayList(list1);
    }
    public TableViewController()
    {
        data=FXCollections.observableArrayList(list);
    }
    private boolean init = true;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        teller=1;
        if(ProductionSearch.choice==3)
        {
            //ProductionSearchMethods p=new ProductionSearchMethods();
            ProductionSearchMethods p=new ProductionSearchMethods();
            ProductionSearch.totalProfit=p.totalProfit(LogInPage.sk);
            listMain=Client.list;
        }
        else if(ProductionSearch.choice ==2)
        {
            ProductionSearchMethods p=new ProductionSearchMethods();
            listMain=p.maxRev(LogInPage.sk);
        }
        else if(ProductionSearch.choice==1){
            ProductionSearchMethods p=new ProductionSearchMethods();
            listMain=p.mostRecentMovies(LogInPage.sk);
        }
        else if(ProductionSearch.choice==4)
        {
            listMain=Client.list;
        }
        data=FXCollections.observableArrayList(listMain);
        movieTitle.setCellValueFactory(new PropertyValueFactory<Movies,String>("title"));
        releaseYear.setCellValueFactory(new PropertyValueFactory<Movies,Integer>("year"));
        more.setCellValueFactory(new PropertyValueFactory<Movies,Button>("button"));//
        if(ProductionSearch.choice==3) {
            productionCompany.setText("Profit");
            prompt.setText("Total profit: " + ProductionSearch.totalProfit);
            productionCompany.setCellValueFactory(new PropertyValueFactory<Movies,String>("profit"));
        }
        else if(ProductionSearch.choice==2) {
            productionCompany.setText("Revenue");
            prompt.setText("Maximum Revenue " + sk);
            productionCompany.setCellValueFactory(new PropertyValueFactory<Movies,String>("revenue"));
        }
        else if(ProductionSearch.choice==1) {
            productionCompany.setText("Running Time");
            prompt.setText("Most recent "+LogInPage.sk);
            productionCompany.setCellValueFactory(new PropertyValueFactory<Movies,String>("run_time"));
        }
        else if (ProductionSearch.choice==4) {
            productionCompany.setText("Genre");
            prompt.setText("All movies "+LogInPage.sk.toUpperCase());
            productionCompany.setCellValueFactory(new PropertyValueFactory<Movies,String>("allGenre"));
        }
        //
        tableView.setItems(data);
    }

    public void exiter(ActionEvent a)
    {
        teller=0;
        try {
            if(ProductionSearch.choice==4)
                fxmlLoader = new FXMLLoader(Main.class.getResource("Home.fxml"));
            else
                fxmlLoader = new FXMLLoader(Main.class.getResource("Production_Search.fxml"));

            System.out.println(1);
            stage = (Stage) ((Node) a.getSource()).getScene().getWindow();
            System.out.println(2);
            Scene scene = new Scene(fxmlLoader.load());
            System.out.println(3);
            stage.setTitle("Hello2!");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Not Found " + e);
        }
    }
}
