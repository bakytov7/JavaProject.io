
public class checker{
    public static boolean getAnswer(int id, String answer){

        if (MainClass.questions[id][1].equals(answer)) {

                System.out.println("Good job");
                System.out.println("  ======================");
                MainClass.points += 1;
                return true;

            } else {

            System.out.println("Don't worry!");
            System.out.println("  ======================");
                MainClass.life -= 1;
            }

            return false;
    }

}

