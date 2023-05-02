package com.example.project_2005033;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static com.example.project_2005033.LogInPage.sk;

public class AddMovie {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    @FXML
    private TextField name;
    @FXML
    private TextField year;
    @FXML
    private TextField time;
    @FXML
    private TextField revenue;
    @FXML
    private TextField budget;
    @FXML
    private TextField genre1;
    @FXML
    private TextField genre2;
    @FXML
    private TextField genre3;

    @FXML
    private Label shower;

    public boolean checker()
    {
        System.out.println(2);
        if(name.getText().isEmpty())
        {
            System.out.println("IN");
            shower.setText("Name not entered");
            return false;
        }
        System.out.println(3);
        if(year.getText().isEmpty())
        {
            shower.setText("Year not entered");
            return false;
        }
        System.out.println(4);
        if(time.getText().isEmpty())
        {
            shower.setText("Running Time not entered");
            return false;
        }
        System.out.println(5);
        if(revenue.getText().isEmpty())
        {
            shower.setText("Revenue not entered");
            return false;
        }
        System.out.println(5);
        if(budget.getText().isEmpty())
        {
            shower.setText("Budget not entered");
            return false;
        }
        System.out.println(6);
        if(genre1.getText().isEmpty()&&genre2.getText().isEmpty()&&genre3.getText().isEmpty())
        {
            shower.setText("Name not entered");
            return false;
        }
        return true;
    }
    public void alertOn()
    {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid type");
        alert.setContentText("Check Format in year, budget, revenue");
        alert.setHeaderText("Some error");
        alert.showAndWait();
    }
    public void alertOn2()
    {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid type");
        alert.setContentText("Name is Already in list");
        alert.setHeaderText("Check Input");
        alert.showAndWait();
    }
    public void OnSubmission()
    {
        Movies temp=new Movies();
        System.out.println(1);
        try {
            if (checker()) {
                System.out.println("IN");
                for(Movies x:Client.list)
                {
                    if(x.getTitle().equalsIgnoreCase(name.getText()))
                    {
                        //shower.setText("Already That Name exists");
                        Throwable x2=new Throwable("error");
                        throw x2;
                    }
                }
                temp.setTitle(name.getText());
                temp.setYear(Integer.parseInt(year.getText()));
                temp.setRun_time(Integer.parseInt(time.getText()));
                if(!genre1.getText().isEmpty()) {
                    temp.setGenre1(genre1.getText(), 0);
                }
                else{
                    temp.setGenre1(" ",0);
                }
                if(!genre2.getText().isEmpty()) {
                    temp.setGenre1(genre2.getText(), 1);
                }
                else{
                    temp.setGenre1(" ",1);
                }
                if(!genre3.getText().isEmpty()) {
                    temp.setGenre1(genre3.getText(), 2);
                }
                else{
                    temp.setGenre1(" ",2);
                }
                temp.setProduction(sk);
                System.out.println(LogInPage.sk+" "+temp.getProduction());
                temp.setRevenue(Long.parseLong(revenue.getText()));
                temp.setBudget(Long.parseLong(budget.getText()));
                temp.setProfit(temp.getRevenue()- temp.getBudget());
                System.out.println(temp.getProfit());
                System.out.println(temp.getRevenue()- temp.getBudget());
                Client.list.add(temp);
                shower.setText("Added Successfully");
                Client.addMovie(temp);
                System.out.println(temp.write_in_File());
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            alertOn();
        } catch (Throwable e) {
            //throw new RuntimeException(e);
            alertOn2();
            System.out.println(e);
        }

    }
    public void exiter(ActionEvent a)
    {
        try {
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
