import javax.swing.*;

public class Stopwatch extends Thread {
    private JLabel label;

    public Stopwatch(JLabel label) {
        this.label = label;
    }

    public void run() {
        int seconds = 0;
        int minutes = 0;
        while (true) {
            if(!Update.Alive)
                break;
            try {
                Thread.sleep(1000);
                seconds++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (seconds == 60) {
                minutes++;
                seconds = 0;
            }
             label.setText("  "+minutes + ((seconds > 9) ?  ":" + seconds :":0" + seconds));
        }
    }
}