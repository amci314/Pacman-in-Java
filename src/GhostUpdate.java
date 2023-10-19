import javax.swing.*;
import java.util.ArrayList;

public class GhostUpdate extends Thread{
    static JTable Screen;
    ArrayList<Ghost> ghosts;
    GhostUpdate(JTable jTable, ArrayList<Ghost> arr){
        Screen=jTable;
        ghosts = arr;
    }
    @Override
    public void run() {
        while (true) {
            if (Update.Alive) {
                try {
                    Thread.sleep(226);
                    GhostMove();
                } catch (InterruptedException e) {

                    throw new RuntimeException(e);
                }
                Screen.repaint();
                //run();
            }else {
                ghosts.clear();
                break;
            }
        }
    }
    private void GhostMove() {
        if(!Update.freezing) {
            for (Ghost g : ghosts) {
                switch ((int) (Math.random() * (4 - 1 + 1)) + 1) {
                    case 1:
                        if (g.GhostX == MapTable.map.length - 1) {
                            g.GhostX = 0;
                            break;
                        } else if (MapTable.map[g.GhostY][g.GhostX + 1] != 1) {
                            g.GhostX++;
                            break;
                        }
                    case 2:
                        if (g.GhostX == 0) {
                            g.GhostX = MapTable.map.length - 1;
                            break;
                        } else if (MapTable.map[g.GhostY][g.GhostX - 1] != 1) {
                            g.GhostX--;
                            break;
                        }
                    case 3:
                        if (g.GhostY == 0) {
                            g.GhostY = MapTable.map.length - 1;
                            break;
                        } else if (MapTable.map[g.GhostY - 1][g.GhostX] != 1) {
                            g.GhostY--;
                            break;
                        }
                    case 4:
                        if (g.GhostY == MapTable.map.length - 1) {
                            g.GhostY = 0;
                            break;
                        } else if (MapTable.map[g.GhostY + 1][g.GhostX] != 1) {
                            g.GhostY++;
                            break;
                        }
                    default:
                        GhostMove(g);
                        break;
                }
                CheckPos(g);
            }
        }
        //Screen.repaint();
    }
    void GhostMove(Ghost g){
        switch ((int) (Math.random() * (4 - 1 + 1)) + 1) {
            case 1:
                if(g.GhostX==MapTable.map.length - 1) {
                    g.GhostX = 0;
                    break;
                }
                else if (MapTable.map[g.GhostY][g.GhostX+1]!=1) {
                    g.GhostX++;
                    break;
                }
            case 2:
                if(g.GhostX==0) {
                    g.GhostX = MapTable.map.length - 1;
                    break;
                }
                else if (MapTable.map[g.GhostY][g.GhostX-1]!=1) {
                    g.GhostX--;
                    break;
                }
            case 3:
                if(g.GhostY==0) {
                    g.GhostY = MapTable.map.length - 1;
                    break;
                }
                else if (MapTable.map[g.GhostY-1][g.GhostX]!=1) {
                    g.GhostY--;
                    break;
                }
            case 4:
                if(g.GhostY==MapTable.map.length - 1) {
                    g.GhostY = 0;
                    break;
                }
                else if (MapTable.map[g.GhostY+1][g.GhostX]!=1) {
                    g.GhostY++;
                    break;
                }
            default:
                break;
        }
        //Screen.repaint();
    }

    void CheckPos(Ghost g){
        if(g.GhostY == Update.PacPositionY && g.GhostX == Update.PacPositionX)
            Update.HPminus();
    }
}
