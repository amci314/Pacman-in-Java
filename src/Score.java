import javax.swing.*;
import java.io.Serializable;

public class Score implements Serializable {
    String nickname;
    int points;
    String time;

    public Score(String nickname, int points, String time) {
        this.nickname = nickname;
        this.points = points;
        this.time = time;
    }
    public String getName() {
        return nickname;
    }

    public int getPoints() {
        return points;
    }
    public String getTime(){
        return time;
    }

}