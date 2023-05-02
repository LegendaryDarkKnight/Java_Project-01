package com.example.project_2005033;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class Transfer {


    @FXML
    private TextField Movie_Name;
    @FXML
    private Label texter;
    @FXML
    private TextField To_Production;

    public void exiter(ActionEvent a)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Home.fxml"));
            Stage stage = (Stage) ((Node) a.getSource()).getScene().getWindow();
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


    public void transferMovie() {

        boolean flag=false;
        for(Movies m:Client.list)
        {
            if(m.getTitle().equalsIgnoreCase(Movie_Name.getText()))
            {
                flag=true;
                break;
            }
        }
        if(flag)
        {
            SearchMenu searchMenu=new SearchMenu();
            Movies toTransfer=searchMenu.searchByName(Movie_Name.getText());
            String []s=new String[3];
            s[0]=LogInPage.sk;
            s[1]=To_Production.getText();
            s[2]=Movie_Name.getText();
            try {
               Client.transferMovie(s);
               texter.setText("Sent Successfully");

            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        else{
            texter.setText("Movie not in class");
        }
    }
}
