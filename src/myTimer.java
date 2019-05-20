import java.util.Timer;
import java.util.TimerTask;

public class myTimer {
    private QuestionsDisplay display;
    private int time;
    protected Timer timer;
    public myTimer(final QuestionsDisplay main, int times){
        this.display = main;
        this.time = times;

        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                if (time == 0){
                    display.autoSave();
                    timer.cancel();
                }
                else{
                    time--;
                    display.time.setText(Integer.toString(time));
                }
            }
        }, 1000, 1000);
    }
    public void pause(){
        timer.cancel();
    }

}
