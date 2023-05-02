package com.example.project_2005033;

import java.util.*;

//import static com.example.project_2005033.TableViewController.Server.movies_list;

public class ProductionSearchMethods {
    private int choice=0;

    public int getChoice() {
        return choice;
    }

    private String s;

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public List<Movies> mostRecentMovies(String p)
    {

            List<Movies> lp = new ArrayList<Movies>();
            List<Movies> lp1 = new ArrayList<Movies>();
            boolean ind = true;
            int recent = 0;
            try{
            for (Movies list : Client.list) {
                if (list.getProduction().equalsIgnoreCase(p)) {
                    lp.add(list);
                    ind = false;
                    if (recent < list.getYear()) {
                        recent = list.getYear();
                    }
                }
            }
            if (ind) {
                return null;
            } else {
                for (Movies list2 : lp) {
                    if (list2.getYear() == recent) {
                        lp1.add(list2);
                    }
                }
                return lp1;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
            return null;
    }
    public List<Movies> maxRev(String s)
    {
        long max=0;
        boolean ind=false;
        List<Movies> lp=new ArrayList<Movies>();
        for(Movies list:Client.list)
        {
            if(s.equalsIgnoreCase(list.getProduction()))
            {
                ind=true;
                max=(list.getRevenue()>max)? list.getRevenue() : max;
            }
        }
        if(ind)
        {
            for(Movies list: Client.list)
            {
                if(max== list.getRevenue())
                    lp.add(list);
            }
            return lp;
        }
        else
        {
            return null;
        }
    }
    public long totalProfit(String s)
    {
        long profit=(long) -1e18;
        for(Movies list:Client.list)
        {
            if(list.getProduction().equalsIgnoreCase(s))
            {
                if(profit==(long) -1e18)
                    profit=0;
                profit+= (list.getRevenue()- list.getBudget());
            }
        }
        return profit;
    }
//    public Map<String,Integer> PrintAllProd()
//    {
//        Map<String, Integer> proName = new HashMap<String, Integer>();
//        for(Movies list:movies_list)
//        {
//            if(proName.containsKey(list.getProduction()))
//            {
//                proName.put(list.getProduction(), proName.get(list.getProduction())+1);
//            }
//            else
//            {
//                proName.put(list.getProduction(), 1);
//            }
//        }
//        return proName;
//    }

    ProductionSearchMethods()
    {
    }
}
