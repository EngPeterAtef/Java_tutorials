package req;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hospital{
    private Doctor[] doctors;
    int noDr;//number of doctors in the hospital
    public Hospital() throws FileNotFoundException {
        File myObj = new File("data.txt");
        Scanner myReader = new Scanner(myObj);
        noDr = Integer.parseInt(myReader.nextLine());//first line is number of doctors in the hospital
        doctors = new Doctor[noDr];

        int numPatients;
        String newLine;
        String[] inputs = new String[3];
        for (int i = 0; i < noDr; i++) {
            newLine = myReader.nextLine();//read doctor data
            inputs = newLine.split(" ");//split on space
//           System.out.println(Integer.parseInt(inputs[0]));
//           System.out.println(inputs[1]);
            //the line in the file like this :id name numberofpatients
            numPatients = Integer.parseInt(inputs[2]);//read the number of patients to send it to the constructor
//            System.out.println(numPatients);
            doctors[i] = new Doctor(numPatients);
            doctors[i].id= Integer.parseInt(inputs[0]);//doctor id
            doctors[i].name = inputs[1];//doctor name
        }
        myReader.close();
    }
    public String MakeAppointment(int drID,int timeSlot,String pName){
        String res;
        if(-1<drID&&drID<noDr) {//check on the passed doctor ID
            res= doctors[drID].reserve(timeSlot, pName);
        }
        else{
            res = "the doctor id is not found in hospital (Failure)";
        }
        return res;
    }
    public String CancelAppointment(int drID,int timeSlot,String pName){
        String res;
        if(-1<drID&&drID<noDr) {
            res =  doctors[drID].cancel(timeSlot, pName);
        }
        else {
            res = "the doctor id is not found in hospital (Failure)";
        }
        return res;
    }
    public void Print(){
        for (int i = 0; i < noDr; i++) {
            doctors[i].printDoc();
        }
    }
}
