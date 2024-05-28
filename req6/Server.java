package req;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static final int MAKE_PORT = 6666;
    public static final int CANCEL_PORT = 6667;
    public static Hospital h;

    static {
        try {
            h = new Hospital();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    ;
    public static int clientNumber = 0; // to keep track of the number of clients connecting to the server.

    public static void main(String[] args) throws IOException {
        System.out.println("The server started .. ");
        //3mlna 2 thread 34an lo gali client 3la port 1 ha4t8l we lo gali client 3la port 2 ha4t8l
        new Thread() {
            public void run() {
                try {
                    ServerSocket ss = new ServerSocket(MAKE_PORT);
                    while (true) {
                        new Server.CaseChanger(ss.accept(), clientNumber++).start();
                        //when a client accepts the request create a new object
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            public void run() {
                try {
                    ServerSocket ss = new ServerSocket(CANCEL_PORT);
                    while (true) {
                        new Server.CaseChanger(ss.accept(), clientNumber++).start();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private static class CaseChanger extends Thread {
        Socket socket;
        int clientNo;

        public CaseChanger(Socket s, int clientNo) {
            this.socket = s;
            this.clientNo = clientNo;
            System.out.println("Connection with Client #" + clientNo + "at socket " + socket);
        }

        public void run() {
            try {
                //buffer to read from the socket
                BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                //writer to send to the socket
                PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
                //receive the patient name
                String pName = br.readLine();
                int drID,timeSlot;
                String message = null;
                while (true) {
                    if ((message = br.readLine()) == null || message.equals("."))
                        break;
                    drID = Integer.parseInt(br.readLine());//read the doc ID from the client
                    timeSlot = Integer.parseInt(br.readLine());//read the time slot from the client

                    String response = null;

                    if (this.socket.getLocalPort() == MAKE_PORT) {
                        response = h.MakeAppointment(drID,timeSlot,pName);
                    }
                    else if (this.socket.getLocalPort() == CANCEL_PORT) {
                           response = h.CancelAppointment(drID,timeSlot,pName);
                    }
                    out.println(response);//send the response to the client
                    h.Print();
                }
                out.close();
                br.close();
                //socket.close();
                //System.out.println("Connection with Client #" + this.clientNo + " finished..");
            } catch (IOException e) {
                System.out.println("Error handling client# " + this.clientNo + ": " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Couldn't close a socket, what's going on?");
                }
                System.out.println("Connection with client# " + this.clientNo + " closed");
            }
        }
    }
}
