
import java.util.Random;
import java.util.Scanner;

public class MainClass {
    public static String[][] questions= new String[100][2];
    public static Random rand = new Random();
    public static int points = 0;
    public static int life = 5;
    public static int id=0 , ids[];
    public static Scanner input = new Scanner(System.in);
    public QuestionsDisplay qsu;

    public MainClass(){
        takeQuestion();
        QuestionsDisplay qsu = new QuestionsDisplay(MainClass.this, 20, questions);
    }

    public static void main(String[] args) {
        new MainClass();
    }


    public static String[][] takeQuestion(){
        int c = 0;
        getQuestion.fromFiles();

        String[][] questsret = new String[25][2];
        int[] randoms = new int[25];

        Random rn = new Random();
        int nmqdb = 99;
        int randomed = rn.nextInt(nmqdb);
        questsret[0] = questions[randomed];
        randoms[0] = randomed;
        boolean flg = false;

        for (int i = 1; i < 25; i++){
            flg = false;
            for(int rd: randoms){
                if (rd == randomed){
                    randomed = rn.nextInt(nmqdb);
                    flg = true;
                    i--;
                    break;
                }
            }
            if (!flg) {
                randoms[i] = randomed;
                questsret[i] = questions[randomed];
            }

        }
        return questsret;
    }
}
