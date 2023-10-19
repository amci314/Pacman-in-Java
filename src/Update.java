import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Update extends Thread {
    static JTable Screen;
    static KeyEvent k;
    public static int PacPositionX=5, PacPositionY=0;
    static int delay=126;
    static boolean ableToEat = false, unkillable = false, teleports =false, freezing=false;
    static int tp2X;
    static int tp2Y;
    static int HP=2;
    static boolean Alive = true;
    Update(JTable jTable, KeyEvent key){
        Screen=jTable;
        k=key;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if(!Alive) {
                    interrupt();
                }
                Thread.sleep(delay);
                Move();
                upgradePicked();
                HPcheck();
            } catch (InterruptedException e) {

                throw new RuntimeException(e);
            }
            Screen.repaint();
            //run();
        }
    }
     void Move() {
        switch (k.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                MapTable.pacman = new ImageIcon("pacMan2.png");
                Screen.repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                MapTable.pacman = new ImageIcon("pacMan1.png");

                if (PacPositionX == 0)
                    PacPositionX = MapTable.map.length-1;
                else if (MapTable.map[PacPositionY][PacPositionX - 1] != 1)
                    PacPositionX--;
            break;
            case KeyEvent.VK_RIGHT:
                MapTable.pacman = new ImageIcon("pacManRight2.png");
                Screen.repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                MapTable.pacman = new ImageIcon("pacManRight1.png");

                if (PacPositionX == MapTable.map.length-1)
                    PacPositionX = 0;
                else if (MapTable.map[PacPositionY][PacPositionX + 1] != 1)
                    PacPositionX++;
            break;
            case KeyEvent.VK_DOWN:
                MapTable.pacman = new ImageIcon("pacManDown2.png");
                Screen.repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                MapTable.pacman = new ImageIcon("pacManDown1.png");

                if (PacPositionY == MapTable.map.length-1)
                    PacPositionY = 0;
                else if (MapTable.map[PacPositionY+1][PacPositionX] != 1)
                    PacPositionY++;
            break;
            case KeyEvent.VK_UP:
                MapTable.pacman = new ImageIcon("pacManUp2.png");
                Screen.repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                MapTable.pacman = new ImageIcon("pacManUp1.png");

                if (PacPositionY == 0)
                    PacPositionY = MapTable.map.length-1;
                else if (MapTable.map[PacPositionY-1][PacPositionX] != 1)
                    PacPositionY--;
            break;
        }
    }
    void upgradePicked(){
        for (Upgrade u:Ghost.upgrades) {
            if(u.x==PacPositionX && u.y==PacPositionY){
                if (u.getClass().equals(SpeedBoost.class)) {
                    delay /= 2;
                    coolDown cd = new coolDown(u);
                    cd.start();
                }
                else if (u.getClass().equals(voracity.class)) {
                    ableToEat = true;
                    coolDown cd = new coolDown(u);
                    cd.start();
                }
                else if (u.getClass().equals(shield.class)) {
                    unkillable = true;
                    coolDown cd = new coolDown(u);
                    cd.start();
                }
                else if (u.getClass().equals(teleport.class)) {
                    PacPositionX = tp2X;
                    PacPositionY = tp2Y;
                    teleports = false;
                }
                else if (u.getClass().equals(freezer.class)) {
                    freezing = true;
                    coolDown cd = new coolDown(u);
                    cd.start();
                }
                Ghost.upgrades.remove(u);
                break;
            }
            if(teleports && PacPositionX==tp2X && PacPositionY==tp2Y){
                PacPositionY = u.y;
                PacPositionX = u.x;
                teleports = false;
                Ghost.upgrades.remove(u);
                break;
            }
        }
        Screen.repaint();
    }

    static void TeleportsPositions(){
        int X2 = (int) (Math.random() * ((MapTable.map.length-1) - 0 + 1));
        int Y2 = (int) (Math.random() * ((MapTable.map.length-1) - 0 + 1));
        if(MapTable.map[Y2][X2]!=1) {
            tp2X = X2;
            tp2Y = Y2;
        }else TeleportsPositions();
    }

    static void HPcheck(){

        for (Ghost g:GameplayWindow.Ghosts) {
            if(g.GhostX == PacPositionX && g.GhostY == PacPositionY){
                if(!freezing && !ableToEat)HP--;
                if(HP==1)
                    GameplayWindow.healthLabel.setIcon(new ImageIcon("heart1.png"));
                else if(HP==0) {
                    GameplayWindow.healthLabel.setIcon(new ImageIcon("heart0.png"));
                    Death();
                }
                GameplayWindow.healthLabel.repaint();
            }
        }


    }
    static void HPminus(){
        if(!freezing&& !ableToEat)HP--;
        if(HP==1)
            GameplayWindow.healthLabel.setIcon(new ImageIcon("heart1.png"));
        else if(HP==0) {
            GameplayWindow.healthLabel.setIcon(new ImageIcon("heart0.png"));
            Death();
        }
        GameplayWindow.healthLabel.repaint();
    }

    static void Death(){
        GameplayWindow.Ghosts.clear();
        Ghost.upgrades.clear();
        Alive = false;
    }

}


