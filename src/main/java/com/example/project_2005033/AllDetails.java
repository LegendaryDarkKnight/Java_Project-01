package com.example.project_2005033;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AllDetails {
    @FXML
    private Label name_1;
    @FXML
    private Label year;
    @FXML
    private Label production;
    @FXML
    private Label time;
    @FXML
    private Label genre;
    @FXML
    private Label budget;
    @FXML
    private Label revenue;
    @FXML
    private Label profit;

    public void setDetails(Movies m)
    {
        name_1.setText("   "+m.getTitle());
        year.setText("   Released : "+m.getYear());
        time.setText("   Running Time: "+m.getRun_time());
        production.setText("   "+m.getProduction());
        budget.setText("   Budget: "+String.valueOf(m.getBudget()));
        revenue.setText("   Revenue: "+String.valueOf(m.getRevenue()));
        profit.setText("   Profit: "+String.valueOf(m.getProfit()));
        genre.setText("   "+m.getAllGenre());
    }
}
