package com.example.project_2005033;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Movies implements Serializable
{
    private String title, production;
    private String []genre=new String[3];
    private int year, run_time;
    private long budget, revenue, profit;

    private String allGenre;
    @FXML
    private Button button;
    static Map<String,Long> movie_pre=new HashMap<String,Long>();

    public long getProfit() {
        return profit;
    }

    public void setProfit(long profit) {
        this.profit = profit;
    }

    public String getAllGenre() {
        return allGenre;
    }

    public void setAllGenre(String allGenre) {
        this.allGenre = allGenre;
    }

    //    static Movies[] movies_c;
//    static int m_count=0;
    public Movies(String s2)
    {
        //System.out.println(s2+"in f");
        //String s1=s2;
        String []s=s2.split(",");
        this.title=s[0];
        this.year=Integer.parseInt(s[1]);
        for(int i=0; i<3; i++)
            this.genre[i]=s[2+i];
        this.run_time=Integer.parseInt(s[5]);
        this.production=s[6];
        this.budget=Long.parseLong(s[7]);
        this.revenue=Long.parseLong(s[8]);
        this.profit=this.revenue-this.budget;
        if(!this.title.equalsIgnoreCase(" "))
        movie_pre.put(this.title.toUpperCase(),this.revenue-this.budget);
        allGenre=genre[0]+" "+genre[1]+" "+genre[2];
    }
    public Movies()
    {

    }
    public Movies(Movies m)
    {
        this.title=m.title;
        this.year=m.year;
        for(int i=0; i<3; i++)
            this.genre[i]=m.genre[i];
        this.run_time=m.run_time;
        this.production=m.production;
        this.budget=m.budget;
        this.revenue=m.revenue;
        this.profit=this.revenue-this.budget;
        if(!this.title.equalsIgnoreCase(" "))
            movie_pre.put(this.title.toUpperCase(),this.revenue-this.budget);
        allGenre=genre[0]+" "+genre[1]+" "+genre[2];
    }
    public Movies(Movies m,int some)//
    {
        this.title=m.title;
        this.year=m.year;
        for(int i=0; i<3; i++)
            this.genre[i]=m.genre[i];
        this.run_time=m.run_time;
        this.production=m.production;
        this.budget=m.budget;
        this.revenue=m.revenue;
        this.profit=this.revenue-this.budget;
        if(!this.title.equalsIgnoreCase(" "))
            movie_pre.put(this.title.toUpperCase(),this.revenue-this.budget);
        allGenre=genre[0]+" "+genre[1]+" "+genre[2];
        this.button=new Button("More");
        button.setOnAction(event -> {
            Stage stage;
            try{
                if(event.getSource()==button)
                {
                    stage=new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AllDetails.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 800, 500);
                    stage.setTitle("Hello!");
                    AllDetails allDetails=fxmlLoader.getController();
                    allDetails.setDetails(this);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initOwner(button.getScene().getWindow());
                    stage.setScene(scene);
                    stage.showAndWait();
                }
                else
                {
                    stage=(Stage) button.getScene().getWindow();
                    stage.close();
                }
            }
            catch (Exception e)
            {

                System.out.println(e);
            }

        });
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public String getGenre1(int k)
    {
        return genre[k];
    }

    public void setGenre1(String s, int k)
    {
        genre[k]=s;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRun_time() {
        return run_time;
    }

    public void setRun_time(int run_time) {
        this.run_time = run_time;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public String write_in_File()
    {
        return title+","+year+","+genre[0]+","+genre[1]+","+genre[2]+","+run_time+","+production+","+budget+","+revenue;
    }

    public void ShowInfo()
    {
        System.out.print(System.lineSeparator());
        System.out.print("Movie Name: "+getTitle()+System.lineSeparator());
        System.out.print("Year of Release: "+getYear()+System.lineSeparator()+"Genre: ");
        for(int i=0; i<3; i++)
        {
            if(genre[i]!=" ")
            {
                System.out.print(getGenre1(i)+", ");
            }
        }
        System.out.print(System.lineSeparator());
        System.out.print("Running Time: "+getRun_time()+System.lineSeparator());
        System.out.print("Production Company: "+getProduction()+System.lineSeparator());
        System.out.print("Budget: "+getBudget()+",  Revenue: "+getRevenue()+System.lineSeparator());
        System.out.print(System.lineSeparator());
    }
    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public static Map<String, Long> getMovie_pre() {
        return movie_pre;
    }

    public static void setMovie_pre(Map<String, Long> movie_pre) {
        Movies.movie_pre = movie_pre;
    }



}
