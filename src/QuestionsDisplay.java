import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class QuestionsDisplay {
    JPanel panel = new JPanel();
    JFrame frame;
    public int times, c=0;
    public String[][] allQuestions;
    protected myTimer clock;
    public boolean[] answered;

    JButton trueButton = new JButton("True"),falseButton = new JButton("False");

    JLabel life = new JLabel(), points = new JLabel(), time = new JLabel(), question = new JLabel("", SwingConstants.CENTER);

    private MainClass display;

    public QuestionsDisplay(final MainClass main, int time, String[][] allQuestions){
        this.display = main;
        this.times = time;
        this.allQuestions = allQuestions;
        answered = new boolean[allQuestions.length];
        QuestionsDisplay();
        clock = new myTimer(QuestionsDisplay.this, times);
        nextQuestion(true);

        trueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checker(c - 1,true)){
                    answered[c - 1] = true;
                    display.points++;
                    displayMessage("Good job. You were Right! Your score: " + display.points, false);
                }
                else {
                    answered[c - 1] = false;
                    display.life--;
                    displayMessage("Don't worry. You were wrong! Your life: " + display.life, false);
                }
                updateScreen();
                nextQuestion(false);
            }
        });
        falseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checker(c - 1,false)){
                    display.points++;
                    answered[c - 1] = true;
                    displayMessage("Good job. You were Right! Your score: " + display.points, false);
                }
                else {
                    answered[c - 1] = false;
                    display.life--;
                    displayMessage("Don't worry. You were wrong! Your life: " + display.life, false);
                }
                updateScreen();
                nextQuestion(false);
            }
        });
    }
    protected void closeGame(){
        System.exit(0);
    }

    public void updateScreen(){
        life.setText("Your life: "+ display.life);
        points.setText("Your points: "+display.points);
    }

    private void displayMessage(String message, boolean endOfGame){
        Object[] options1 = new Object[2];
        if (endOfGame) {
            options1[0] = "Close The Game";
            options1[1] = "Restart";
        }
        else {
            options1[0] = "Close The Game";
            options1[1] = "Continue";
        }
        int result = JOptionPane.showOptionDialog(null, message, "Info",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options1, null);
        if (result == JOptionPane.YES_OPTION){
            closeGame();
        }
        else {
            if (endOfGame) {
                display.life = 5;
                display.points = 0;
                allQuestions = display.takeQuestion();
                c = 0;
                updateScreen();
            }
        }
    }

    public void nextQuestion(boolean isStart){
        if (display.life <= 0){
            int d = 0;
            for(boolean f: answered){
                if (f) d++;
            }
            JOptionPane.showMessageDialog(null, "Game Over! " + "You lose the game. You have answered: " + d + " of " + "25" + " Your life left: " + display.life + ".      Good Bye!");
            displayMessage("Game Over! " + "You lose the game. You have answered: " + d + " of " + "25"+ " Your life left: " + display.life + ".      Good Bye!", true);
        }
        if(c < 25){
            if (isStart){
                clock.pause();
                clock = new myTimer(QuestionsDisplay.this, times);
            }
            else {
                clock.pause();
                clock = new myTimer(QuestionsDisplay.this, times + 1);
            }
            question.setText(allQuestions[c][0]);
            c++;
        }
        else {
            int d = 0;
            for(boolean f: answered){
                if (f) d++;
            }
            JOptionPane.showMessageDialog(null, "Game Over! " + "You won the game. You have answered: " + d + " of " + answered.length + " Your life left: " + display.life + ".      Good Bye!");
            displayMessage("Game Over! " + "You lose the game. You have answered: " + d + " of " + answered.length + " Your life left: " + display.life + ".      Good Bye! if you don't want to continue", true);

        }
    }
    protected boolean checker(int nQuestion, boolean isTrue){
        //System.out.println(allQuestions[c][0] + ", " + allQuestions[c][1]);
        if (isTrue){
            if (allQuestions[nQuestion][1].equals("True")){
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if (allQuestions[nQuestion][1].equals("False")) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    public void autoSave(){
        if (display.life > 0) {
            display.life -= 1;
            nextQuestion(false);
            updateScreen();
            clock.pause();
            displayMessage("Don't worry. Your life: " + display.life, false);
            clock = new myTimer(QuestionsDisplay.this, 20);
        }
        else{
            int d = 0;
            for(boolean f: answered){
                if (f) d++;
            }
            JOptionPane.showMessageDialog(null, "Game Over! " + " You have answered: " + d + " of " + answered.length + " Your life left: " + display.life + ".      Good Bye!");
            System.exit(0);
        }
    }
    public void QuestionsDisplay(){
        frame = new JFrame("True or False?");

        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

        life.setText("Your life: "+ display.life);
        life.setForeground(Color.blue);
        life.setBounds(30,0,100,50);

        points.setText("Your points: "+display.points);
        points.setForeground(Color.blue);
        points.setBounds(470,0,100,50);

        question.setBounds(50,50,500,250);
        question.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        question.setForeground(Color.blue);
        question.setBackground(Color.lightGray);
        question.setOpaque(true);

        time.setText(Integer.toString(times));
        time.setBounds(270,10,50,30);

        trueButton.setBounds(180,350,100, 30);
        trueButton.setForeground(Color.yellow);

        falseButton.setBounds(350,350,100, 30);
        falseButton.setForeground(Color.red);

        panel.add(question);
        panel.add(life);
        panel.add(points);
        panel.add(trueButton);
        panel.add(falseButton);
        panel.add(time);

        frame.setSize(600,450);
        frame.setLocationRelativeTo(null);

        panel.setLayout(null);
    }


}
