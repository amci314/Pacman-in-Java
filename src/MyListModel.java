import javax.swing.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class MyListModel extends AbstractListModel<String> {
    ArrayList<Score> scores;
    MyListModel(ArrayList<Score> Scores){
        scores = Scores;
    }
    @Override
    public int getSize() {
        return scores.size();
    }

    @Override
    public String getElementAt(int index) {
        return scores.get(index).nickname + " - "+scores.get(index).points+ scores.get(index).time;
    }
}
