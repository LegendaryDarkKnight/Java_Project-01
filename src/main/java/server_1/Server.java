package server_1;

import com.example.project_2005033.Movies;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static server_1.Server.*;
class ThreadingMain implements Runnable
{
    Thread t;
    Socket socket;
    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;
    private final String OUTPUT_FILE_NAME="src\\main\\java\\server_1\\movies.txt";
    ThreadingMain(Socket socket) throws IOException {
        this.socket=socket;
        t=new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        while(true){

        try {
            //System.out.println(123);
            Object msg = SocketInput.get(socket).readObject();//Reads an Object From client

            if (msg instanceof String) {

                //while Logging in
                System.out.println("From Client: " + (String) msg);
                String s2 = (String) msg;
                if(s2.equalsIgnoreCase("logout"))
                {
                    Object msg2=SocketInput.get(this.socket).readObject();
                    String s1=((String)msg2);
                    SocketMap.get(s1.toUpperCase()).remove(this.socket);
                    break;
                }
                else {
                    if (!movie_map.containsKey(s2.toUpperCase())) {
                        SocketOutput.get(socket).writeObject(null);//If the production Company is not Available
                        ClientCount--;
                        Checker = false;
                    } else {
                        if (!SocketMap.containsKey(s2.toUpperCase())) {
                            SocketMap.put(s2.toUpperCase(), new ArrayList<>());
                            //System.out.println(1112);
                        }
                        SocketMap.get(s2.toUpperCase()).add(socket);
                        List<Movies> x = movie_map.get(s2.toUpperCase());
                        SocketOutput.get(socket).writeObject(x);

                        //System.out.println(11334);
                    }
                }
            } else if (msg instanceof Movies) {
                //while adding a movie
                Movies temp = (Movies) msg;
                movie_map.get(temp.getProduction().toUpperCase()).add(temp);
                System.out.println("Done1");

                if (SocketMap.containsKey(temp.getProduction().toUpperCase())) {
                    for (Socket s : SocketMap.get(temp.getProduction().toUpperCase())) {

                        try {
                            System.out.println(1313);
                            List<Movies> moviesList= new ArrayList<>(movie_map.get(temp.getProduction().toUpperCase()));
                            SocketOutput.get(s).writeObject(moviesList);
                            for(Movies x:movie_map.get(temp.getProduction().toUpperCase())) {
                                System.out.println(x.getTitle());
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }

                    }
                }
                //writes as soon as the movie is added
                BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
                for (Map.Entry<String, List<Movies>> entry : movie_map.entrySet()) {
                    for (Movies list : entry.getValue()) {
                        bw.write(list.write_in_File());
                        bw.write(System.lineSeparator());
                    }
                }
                bw.close();
            } else if (msg instanceof String[]) {
                //while transferring a movie
                String[] s2 = (String[]) msg;//s2[0]=from s2[1]=to s2[2]=movie
                if (movie_map.containsKey(s2[1].toUpperCase())) {
                    //Finding The movie within
                    Movies temp=new Movies();
                    //System.out.println(s2[2]);
                    for (Movies m : movie_map.get(s2[0].toUpperCase())) {
                        System.out.println(m.getTitle());
                        if (m.getTitle().equalsIgnoreCase(s2[2])) {
                            temp = new Movies(m);
                            System.out.println("changing");
                            movie_map.get(s2[0].toUpperCase()).remove(m);
                            break;
                        }
                    }
                        System.out.println("added");
                        temp.setProduction(s2[1]);
                        movie_map.get(s2[1].toUpperCase()).add(new Movies(temp));

                        System.out.println("done");
                        List<Movies> reduced=new ArrayList<>(movie_map.get(s2[0].toUpperCase()));
                        List<Movies> added=new ArrayList<>(movie_map.get(s2[1].toUpperCase()));
                        //for(Movies x:reduced)
                            //System.out.println(x.getTitle());

                        for (Socket s : SocketMap.get(s2[0].toUpperCase())) {
                            try {
                                SocketOutput.get(s).writeObject(reduced);
                            } catch (Exception e) {
                                System.out.println(e);
                            }

                        }
                        for (Socket s : SocketMap.get(s2[1].toUpperCase())) {
                            try {
                                SocketOutput.get(s).writeObject(added);
                            } catch (Exception e) {
                                System.out.println(e);
                            }

                        }
                        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
                        for (Map.Entry<String, List<Movies>> entry : movie_map.entrySet()) {
                            for (Movies list : entry.getValue()) {
                                bw.write(list.write_in_File());
                                bw.write(System.lineSeparator());
                            }
                        }
                        bw.close();
                }

            } else if (msg instanceof Integer) {
                ClientCount--;
                System.out.println("ending");
                break;
            }

            else
            {
                break;
            }
        } catch (Exception e) {
            System.out.println(e);
            break;
        }
    }
        if(Checker)
        {
            System.out.println(ClientCount);
            if(ClientCount==0){
                System.exit(0);
            }
        }

    }
}

public class Server{

    private static final String INPUT_FILE_NAME = "src\\main\\java\\server_1\\movies.txt";
    private static final String OUTPUT_FILE_NAME = "out.txt";

    static Map<String,List<Socket>> SocketMap = new HashMap<>();
    static Map<Socket,ObjectOutputStream> SocketOutput=new HashMap<>();
    static Map<Socket,ObjectInputStream> SocketInput=new HashMap<>();
    static List<Movies> movies_list = new ArrayList<Movies>();
    static Map<String, List<Movies>> movie_map=new HashMap<String, List<Movies>>();

    static Map<String,String> password=new HashMap<>();
    static long ClientCount=0;

    static boolean Checker=false;

    static void FileReader()//Reads all from Movies.txt
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                Movies m = new Movies(line);
                if(!movie_map.containsKey(m.getProduction().toUpperCase()))
                {
                    //System.out.println(m.getProduction());
                    movie_map.put(m.getProduction().toUpperCase(),new ArrayList());
                }
                movie_map.get(m.getProduction().toUpperCase()).add(m);
            }
            br.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    static void FileReader2()
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader("password.txt"));
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                String []s=line.split(",");
                password.put(s[0].toUpperCase(),s[1]);

            }
            br.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public static void main(String[] args) throws IOException{

        FileReader();
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("Server Started");
        Socket socket;
        while (true) {
            Checker=true;
            socket = serverSocket.accept();
            ClientCount++;
            SocketOutput.put(socket,new ObjectOutputStream(socket.getOutputStream()));
            SocketInput.put(socket,new ObjectInputStream(socket.getInputStream()));
            new ThreadingMain(socket);
        }
    }
}
