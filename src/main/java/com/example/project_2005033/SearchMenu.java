package com.example.project_2005033;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchMenu {

    public Movies searchByName(String s)
    {
        List<Movies> list=new ArrayList<Movies>();
        boolean ind=true;
        for(Movies name: Client.list)
        {
            if(s.equalsIgnoreCase(name.getTitle()))
            {
                return name;
            }
        }
            System.out.println(s+" This Movie not Found");
        return null;
    }
    public List<Movies> searchByYear(int y)
    {
        List<Movies> m=new ArrayList<Movies>();
        int c=0;
        for(Movies list: Client.list)
        {
            if(y==list.getYear())
            {
                m.add(list);
                c++;
            }
        }
        if(c==0)
        {
            return null;
        }
        else
        {
            return m;
        }
    }
    public List<Movies> searchByGenre3(String s1, String s2, String s3)
    {
        boolean ind=true;
        List<Movies> m=new ArrayList<Movies>();
        for(Movies list: Client.list)
        {
            if(s1.equalsIgnoreCase(list.getGenre1(0))||s1.equalsIgnoreCase(list.getGenre1(1))||s1.equalsIgnoreCase(list.getGenre1(2)))
            {
                if(s2.equalsIgnoreCase(list.getGenre1(0))||s2.equalsIgnoreCase(list.getGenre1(1))||s2.equalsIgnoreCase(list.getGenre1(2)))
                {
                    if (s3.equalsIgnoreCase(list.getGenre1(0)) || s3.equalsIgnoreCase(list.getGenre1(1)) || s3.equalsIgnoreCase(list.getGenre1(2)))
                    {
                        m.add(list);
                        ind=false;
                    }
                }
            }
        }
        if(ind)
        {
            return null;
        }
        else
        {
            return m;
        }
    }
    public List<Movies> searchByGenre1(String s1) {
        boolean ind = true;
        List<Movies> m = new ArrayList<Movies>();
        for (Movies list : Client.list) {
            if (s1.equalsIgnoreCase(list.getGenre1(0)) || s1.equalsIgnoreCase(list.getGenre1(1)) || s1.equalsIgnoreCase(list.getGenre1(2))) {
                m.add(list);
                ind = false;
            }
        }
            if(ind)
            {
                return null;
            }
            else
            {
                return m;
            }

    }

    public List<Movies> searchByProduction(String s)
    {
        boolean ind=true;
        List<Movies> m=new ArrayList<Movies>();
        for(Movies list: Client.list)
        {
            if(s.equalsIgnoreCase(list.getProduction()))
            {
                m.add(list);
                ind=false;
            }
        }
        if(ind)
        {
            return null;
        }
        else
        {
            return m;
        }
    }
    public List<Movies> searchByRange(int a, int b)
    {
        boolean ind=true;
        List<Movies> m=new ArrayList<Movies>();
        for(Movies list: Client.list)
        {
            if(list.getRun_time()>=a&&list.getRun_time()<=b)
            {
                m.add(list);
                ind=false;
            }
        }
        if(ind)
        {
            return null;
        }
        else
        {
            return m;
        }
    }
    public Movies[] topTen()
    {
        Movies []m=new Movies[Client.list.size()];
        long []diff=new long[Client.list.size()];
        int i=0;
        for(Movies list:Client.list)
        {
            m[i]=list;
            diff[i]= list.getRevenue()- list.getBudget();
            i++;
        }
        System.out.println(i+" "+Client.list.size());
        Movies temp;
        long t1;
        for(i=0; i<Client.list.size()-1; i++)
        {
            for(int j=i+1; j<Client.list.size(); j++)
            {
                if(diff[i]<diff[j])
                {
                    temp=m[j];
                    m[j]=m[i];
                    m[i]=temp;
                    t1=diff[j];
                    diff[j]=diff[i];
                    diff[i]=t1;
                }
            }
        }
        return m;
    }
    SearchMenu()
    {


    }
}

