package com.example.project_2005033;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.example.project_2005033.LogInPage.sk;

public class ProductionSearch {
    //public static int tablePos=0;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public static int choice=0;
    public static long totalProfit=0;



    public void recentMovies(ActionEvent a)
    {
        choice=1;
        //ProductionSearchMethods p=new ProductionSearchMethods();
        //TableViewController.listMain=p.mostRecentMovies(sk);
        try {
            fxmlLoader = new FXMLLoader(Main.class.getResource("tableview.fxml"));
            //System.out.println(1);
            stage = (Stage) ((Node) a.getSource()).getScene().getWindow();
            //System.out.println(2);
            Scene scene = new Scene(fxmlLoader.load());
            //System.out.println(3);
            stage.setTitle("Hello2!");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Not Found " + e);
        }

    }
    public void MaxRevenue(ActionEvent a)
    {
        choice=2;
        //ProductionSearchMethods p=new ProductionSearchMethods();
        //TableViewController.listMain=p.maxRev(sk);
        try {
            fxmlLoader = new FXMLLoader(Main.class.getResource("tableview.fxml"));
            //System.out.println(1);
            stage = (Stage) ((Node) a.getSource()).getScene().getWindow();
            //System.out.println(2);
            Scene scene = new Scene(fxmlLoader.load());
            //System.out.println(3);
            stage.setTitle("Hello2!");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Not Found " + e);
        }

    }
    public void totalProf(ActionEvent a)
    {
        choice=3;
        //ProductionSearchMethods p=new ProductionSearchMethods();
        //TableViewController.listMain=Client.list;
        //totalProfit=p.totalProfit(sk);
        try {
            fxmlLoader = new FXMLLoader(Main.class.getResource("tableview.fxml"));
            //System.out.println(1);
            stage = (Stage) ((Node) a.getSource()).getScene().getWindow();
            //System.out.println(2);
            Scene scene = new Scene(fxmlLoader.load());
            //System.out.println(3);
            stage.setTitle("Hello2!");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Not Found " + e);
        }
    }
    public void backToMain(ActionEvent a)
    {
        try {
            choice=0;
            fxmlLoader = new FXMLLoader(Main.class.getResource("Home.fxml"));
            //System.out.println(1);
            stage = (Stage) ((Node) a.getSource()).getScene().getWindow();
            //System.out.println(2);
            Scene scene = new Scene(fxmlLoader.load());
            //System.out.println(3);
            stage.setTitle("Hello2!");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Not Found " + e);
        }
    }

}
