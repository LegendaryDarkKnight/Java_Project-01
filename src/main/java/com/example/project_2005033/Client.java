package com.example.project_2005033;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server_1.MyObjectOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client implements Serializable {
    public static List<Movies> list;
    public static String IP_add="127.0.0.1";

    public static int port=6666;
    public static Socket socket;
    public static ObjectOutputStream oos;

    public static ObjectOutputStream oos2;

    public static ObjectInputStream ois;
    public static ObjectInputStream ois2;
    static {

    }

    public static boolean clientCaller(String s1) throws IOException {//
        System.out.println("Client Started. ");
        try {
            socket = new Socket(IP_add, port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            //System.out.println(121);
            oos.writeObject(s1);
            //System.out.println(122);
            Object fromServer=ois.readObject();
            //System.out.println(123);
            if(fromServer==null)
            {
                System.out.println("Not Found");
                return false;
            }
            else {
                List<Movies> temp=new ArrayList<>((ArrayList) fromServer);
                Client.list=new ArrayList<>();//
                for(Movies x:temp)//
                {
                    Client.list.add(new Movies(x,1));
                }

                for (Movies m : list)
                    m.ShowInfo();
                new ClientThread(Client.socket);
            }
            //oos.
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    public static void addMovie(Movies m) throws IOException
    {
        //Client.list.add(new Movies(m,1));//
        System.out.println("Client Started. ");
        try{
            oos.writeObject(m);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public  static void transferMovie(String []s) throws IOException
    {
        System.out.println("Client Started. ");
        try{
            oos.writeObject(s);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public static void end()
    {
        try{
            oos.writeObject(1);
        }
        catch (Exception e)
        {

        }
    }
    public static void end2()
    {

        try{
            oos.writeObject("logout");
            oos.writeObject(LogInPage.sk);
        }
        catch (Exception e)
        {

        }
        try{
            ClientThread.t.stop();
        }
        catch (Exception e)
        {

        }
    }

}
class ClientThread implements Runnable{

    static Thread t;
    ClientThread(Socket socket)
    {
        //Client.socket=socket;
        t=new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        while (true) {
            if(Home.exitToken==1)//
                break;//

            Object fromServer = null;
//            System.out.println("Not Yet Found");
            try {
                fromServer=Client.ois.readObject();
//                System.out.println("Found");
            } catch (IOException e) {
                System.out.println(e);
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
            try {
//                System.out.println("Inserted");
                //Client.list = (ArrayList) fromServer;
                List<Movies> temp=new ArrayList<>((ArrayList) fromServer);
                Client.list.clear();//
                for(Movies x:temp)//
                {
                    Client.list.add(new Movies(x,1));
                }

                if(TableViewController.teller==1) {
                    Platform.runLater(new Runnable() {//update if anywhere table view is on
                        @Override
                        public void run() {
                            // Update UI here.
                            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("tableview.fxml"));
                            Scene scene = null;
                            try {
                                scene = new Scene(fxmlLoader.load());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Main.stage1.setTitle("Hello!");
                            Main.stage1.setScene(scene);
                            Main.stage1.show();
                        }
                    });
                }

            } catch (Exception e) {
                System.out.println(e+"ss");
            }
        }
    }

}