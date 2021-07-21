package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class MyClient {

    public Point getPointFromServer(String host, int port) throws Exception {

        // Connect to the server
        Socket server = new Socket(host, port);
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));

        // Read lines
        String[] line = fromServer.readLine().split(",");

        // Return new point
        return new Point(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
    }
}
