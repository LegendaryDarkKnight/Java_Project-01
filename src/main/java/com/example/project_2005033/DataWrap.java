package com.example.project_2005033;

import java.io.Serializable;
import java.net.Socket;

public class DataWrap implements Serializable {
    String s;
    Socket socket;
    public DataWrap(String s, Socket socket)
    {
        this.s=s;
        this.socket=socket;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
