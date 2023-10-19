import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScoreWindow extends JFrame implements Serializable {
    static ArrayList<Score> scores = new ArrayList<>();
    public ScoreWindow(){
        load();

        MyListModel myListModel = new MyListModel(scores);
        JList jList = new JList<>(myListModel);
        JScrollPane jScrollPane = new JScrollPane(jList);

        jList.setFont(new Font("Monospaced", Font.BOLD, 15));
        jList.setBackground(Color.black);
        jList.setForeground(Color.white);

        setSize(200,350);
        setLocationRelativeTo(null);
        setVisible(true);
        add(jScrollPane);
    }
    public static void addScore(Score score){
        load();
        if(scores.size()==0){
            scores.add(score);
            save();
            return;
        }
        for (Score s:scores) {
            if(score.points>s.points) {
                scores.add(scores.indexOf(s), score);
                save();
                return;
            }
        }
        scores.add(score);
        save();
    }
    public static void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("saveFile"))) {
            oos.writeObject(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void load(){
        try {
            FileInputStream fis = new FileInputStream("saveFile");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Score> loadedScores = (ArrayList<Score>) ois.readObject();
            ois.close();
            fis.close();
            scores = loadedScores;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
