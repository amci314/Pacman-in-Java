import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScoreList implements Serializable {
    private String description;
    private List<Score> scores;
    private int numberOfEntries;
    private String saveName;
    private boolean autoSave;

    // should be used, if the user wants to store his scores on disk
    public ScoreList(String description, int numberOfEntries, String saveName, boolean autoSave) {
        this.description = description;
        scores = new ArrayList<>();
        this.numberOfEntries = numberOfEntries;
        this.saveName = saveName;
        this.autoSave = autoSave;
    }

    // should be used, if the scores dont have to be saved to a file
    public ScoreList(String description, int numberOfEntries) {
        this.description = description;
        this.numberOfEntries = numberOfEntries;
        saveName = null;
        autoSave = false;
    }

    private void sort() {
        scores.sort(new Comparator<Score>() {
            @Override
            public int compare(Score score1, Score score2) {
                Integer int1 = score1.getPoints();
                Integer int2 = score2.getPoints();
                return int1.compareTo(int2);
            }
        });
    }

    public boolean addScore(Score score) {
        if (scores.size() < numberOfEntries) {
            scores.add(score);
            sort();
            if (autoSave) {
                save();
            }
            return true;
        }

        Score lowestScore = scores.get(0);
        if (score.getPoints() > lowestScore.getPoints()) {
            scores.remove(lowestScore);
            scores.add(score);
            sort();
            if (autoSave) {
                save();
            }
            return true;
        }

        return false;
    }

    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveName))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder returnValue = new StringBuilder();
        returnValue.append(description);
        returnValue.append("\n\n");
        for (Score score : scores) {
            returnValue.append(score.getName());
            returnValue.append(" - ");
            returnValue.append(score.getPoints());
            returnValue.append("\n");
        }
        return returnValue.toString();
    }
}

