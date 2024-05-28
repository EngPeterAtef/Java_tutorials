package req;

public class Doctor {
    int id;
    String name;
    boolean []timeslots ;
    //initially false means free appointment and true means busy appointment
    String[] patients;
    int numPatients;
    public Doctor(int n){
        numPatients = n;
        timeslots = new boolean[n];
        patients =new String[n];
    }
    public String reserve(int timeSlot,String pName){
        String res= null;
        if (timeSlot>numPatients){
            res = "the timeslot index is out of boundary (Failure)";
        }
        else if(timeslots[timeSlot]){
            res = "the doctor is already busy at this timeslot (Failure)";
        }
        else if(!timeslots[timeSlot]){//false means it's not busy
            timeslots[timeSlot] = true;
            patients[timeSlot] = pName;
            res = "Making the appointment is done successfully (Success)";
        }
        return res;
    }
    public String cancel(int timeSlot,String pName){
        String res= null;
        if (timeSlot>numPatients){
            res = "the timeslot index is out of boundary (Failure)";
        }
        else if(!timeslots[timeSlot]){
            res = "the doctor doesn’t have an appointment at this timeslot (Failure)";
        }
        else if (timeslots[timeSlot]){
            if (!patients[timeSlot].equals(pName)){
                res = "the doctor has an appointment to a different patient name at this timeslot (Failure)";
            }
            else {
                timeslots[timeSlot] = false;
                patients[timeSlot] = "";
                res = "Cancelling the appointment is done successfully (Success)";
            }
        }
        return res;
    }
    public void printDoc(){
        System.out.println("Dr_ID:  "+id + " Dr Name: "+name);
        for (int i = 0; i < numPatients; i++) {
            if (timeslots[i]){
                System.out.println("In timeslot:"+i+" Patient: "+patients[i]);
            }
        }
        System.out.println("****************************************************");
    }
}
