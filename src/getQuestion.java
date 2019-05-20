import java.io.File;
import java.util.Scanner;

public class getQuestion {
    public static String arr[];
    private static Scanner question;

    public static void openFile(){
        try{
            question = new Scanner(new File("TrueOrFalse.txt"));
        }
        catch (Exception e){
            System.out.println("Could not find!");
        }
    }
    public static void fromFiles(){
        openFile();
        int c = 0;
        while(question.hasNextLine()){
            arr = question.nextLine().split("%");
            MainClass.questions[c][0] = arr[0];
            MainClass.questions[c][1] = arr[1];
            c++;
        }
        question.close();
    }
}
