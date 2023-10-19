import javax.swing.*;
import java.util.ArrayList;

public class Ghost extends Thread {
    int GhostX, GhostY;
    static ArrayList<Upgrade> upgrades = new ArrayList<>();
    Ghost() {
        SpawnPosition();
    }

    @Override
    public void run() {
        if(Update.Alive) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            SpawnUpgrade();
            run();
        }
    }

    void SpawnPosition(){
        int X = (int) (Math.random() * ((MapTable.map.length-1) - 0 + 1));
        int Y = (int) (Math.random() * ((MapTable.map.length-1) - 0 + 1));
        if(MapTable.map[Y][X]!=1) {
            GhostX = X;
            GhostY = Y;
        }else SpawnPosition();
    }
    void SpawnUpgrade(){//(int) (Math.random() * (4 - 1 + 1)) + 1
        if(((int) (Math.random() * (4 - 1 + 1)) + 1)==4)
            switch ((int) (Math.random() * (5 - 1 + 1)) + 1)
            {
                case 1:
                    upgrades.add(new SpeedBoost(GhostY,GhostX));
                    break;
                case 2:
                    upgrades.add(new voracity(GhostY,GhostX));
                    break;
                case 3:
                    upgrades.add(new shield(GhostY,GhostX));
                    break;
                case 4:
                    Upgrade up = new teleport(GhostY,GhostX);
                    upgrades.add(up);
                    Update.TeleportsPositions();
                    Update.teleports=true;
                    coolDown cd = new coolDown(up);
                    cd.start();
                    break;
                case 5:
                    upgrades.add(new freezer(GhostY,GhostX));
                    break;
        }
    }
}
