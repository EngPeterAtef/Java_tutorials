package req;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static final int MAKE_PORT = 6666;
    public static final int CANCEL_PORT = 6667;

    public static void main(String[] args) throws IOException {
        //b. Opens two sockets with the server, one on the make appointment port 6666, and
        //the other on the cancel appointment port 6667
        Socket makeSocket = new Socket("localhost", MAKE_PORT);
        Socket cancelSocket = new Socket("localhost", CANCEL_PORT);
        //writer for each socket
        PrintWriter makeOut = new PrintWriter(makeSocket.getOutputStream(), true);
        PrintWriter cancelOut = new PrintWriter(cancelSocket.getOutputStream(), true);
        //buffer reader to read from the console
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        //buffer reader to read from the socket
        BufferedReader makeReader = new BufferedReader(new InputStreamReader(makeSocket.getInputStream()));
        BufferedReader cancelReader = new BufferedReader(new InputStreamReader(cancelSocket.getInputStream()));
        //a. Prompts the user to enter his name (the patient’s name)
        System.out.println("Enter The Patient Name");
        String pName = consoleReader.readLine();//take the patient name
        //c. Sends the patient’s name to both sockets
        makeOut.println(pName);
        cancelOut.println(pName);

        String message = null;//message received from the server or the console
        String drID,timeSlot;
        String response;
        //d. In a loop
        while (true){
            //i. Prompts and asks the user if he wants to make or cancel an appointment
            System.out.println("Do you want to make(m) or cancel(c) an appointment?\na period '.' to quit");
            if((message = consoleReader.readLine()) == null)
                break;
            if(message.equals(".")){
                makeOut.println(message);//send to the server
                cancelOut.println(message);
                if (message.equals("."))
                    break;
            }
            //we take the input from the console in the if condition so that I don't take an input unless there is
            //appointment making or cancellation
            if(message.equals("m")){
                //ii. Reads the doctor id and the timeslot index from the user
                System.out.println("Enter The Doc ID");
                drID = consoleReader.readLine();
                System.out.println("Enter The required Time slot");
                timeSlot = consoleReader.readLine();
                makeOut.println(message);//send to the server
                //iii. Sends the doctor id and the timeslot index to the appropriate socket
                makeOut.println(drID); //send message to server
                makeOut.println(timeSlot);
                //iv. Reads the response message from the server
                response = makeReader.readLine();//read from the server
                //v. Prints the response message to the user on console
                System.out.println("The received message = " + response);
            }
            else if(message.equals("c")){//cancel or period
                cancelOut.println(message);//send c
                System.out.println("Enter The Doc ID");
                drID = consoleReader.readLine();
                System.out.println("Enter The required Time slot");
                timeSlot = consoleReader.readLine();
                cancelOut.println(drID);
                cancelOut.println(timeSlot);
                response=cancelReader.readLine();
                System.out.println("The received message = " + response);
            }
        }
        makeOut.close();
        cancelOut.close();
        consoleReader.close();
        makeReader.close();
        cancelReader.close();
        makeSocket.close();
        cancelSocket.close();
    }
}
