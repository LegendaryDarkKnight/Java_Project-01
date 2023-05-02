//package com.example.project_2005033;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Main extends Application {
//    static List<Movies> list=new ArrayList<Movies>();
//
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        /* // for simple controls uncomment this block
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml")); // for simple controls
//        primaryStage.setTitle("JavaFX Controls");
//        primaryStage.setScene(new Scene(root, 500, 300));
//        primaryStage.show(); */
//
//        /* // for list view uncomment this block
//        Parent root = FXMLLoader.load(getClass().getResource("listview.fxml")); // for simple controls
//        primaryStage.setTitle("List View");
//        primaryStage.setScene(new Scene(root, 500, 300));
//        primaryStage.show(); */
//
//        // for table view
//        try {
//            BufferedReader br = new BufferedReader(new FileReader("movies.txt"));
//
//            while (true) {
//                String line = br.readLine();
//                if (line == null) break;
//                Movies m = new Movies(line);
//                list.add(m);
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println(e);
//        }
////        for(Movies m:list)
////            m.ShowInfo();
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            //System.out.println(1);
//            loader.setLocation(getClass().getResource("tableview.fxml"));
//            //System.out.println(2);
//            Parent root = loader.load();
//            //System.out.println(3);
// //           TableViewController controller = loader.getController();
////            System.out.println(4);
// //           controller.load();
////            System.out.println(5);
//            primaryStage.setTitle("Table View");
//            //System.out.println(6);
//            primaryStage.setScene(new Scene(root));
//            //System.out.println(7);
//            primaryStage.show();
//            //System.out.println(8);
//        }
//        catch(Exception e)
//        {
//            System.out.println(e);
//        }
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//}
package com.example.project_2005033;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    public static Stage stage1;
    @Override
    public void start(Stage stage) throws IOException {
        stage1=stage;
        //Home.exitToken=0;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LogInPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void stop() throws Exception
    {
        super.stop();
        Client.end();
        System.exit(0);
    }
    public static void main(String[] args) {
        launch();
    }
}