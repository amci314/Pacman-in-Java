import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MapTable extends AbstractTableModel {
    int size;
    public static int[][] map;
    public MapTable(int Size){
        this.size=Size;
        map = new int[size][size];
        GenerateMap();
    }

    @Override
    public int getRowCount() {
        return size;
    }

    @Override
    public int getColumnCount() {
        return size;
    }

    public static ImageIcon pacman = new ImageIcon("pacMan1.png");
    void GenerateMap(){
        for (int i = 0; i <= size; i+=6) {
            for (int j = 0; j <= size; j+=6) {
                drawFigure(i,j);
            }

        }
//        for (int i = 1; i < size/2; i++) {
//            if((int) (Math.random() * 2 + 1)==2 && columns.contains(i-1))
//                columns.add(i);
//        }
//        for (int i = 1; i < size/2; i++) {
//            if((int) (Math.random() * 2 + 1)==2 && rows.contains(i-1))
//                rows.add(i);
//        }
//
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                //map[i][j]=1;
//                if(i==0||i==size)
//                    map[i][j]=1; // wall
//
//                if(j==0||j==size) {
//                    if (j == size / 2)
//                        map[i][j] = 0;
//                    else map[i][j] = 1;
//                }
//                if(rows.contains(i))
//                    for (int k = 0; k < (int)(Math.random()*((size-1)+1)); k++) {
//                        map[i][k]=0;
//                    }
//                if(columns.contains(j))
//                    for (int k = 0; k < (int)(Math.random()*((size-1)+1)); k++) {
//                        map[k][j] = 0;
//                    }
//            }
//        }
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }
    }
    void drawFigure(int row, int col){ //(int)(Math.random()*((5-1)+1))
        switch ((int)(Math.random()*((5-1)+1))){
            case 1:
                for (int i = row; i < row + 7; i++) {
                    for (int j = col; j < col + 7; j++) {
                        if(i<size&&j<size) {
                            if (i == row  || j == col)
                                map[i][j] = 0;
                            else map[i][j] = 1;
                        }
                    }
                }
            break;
            case 2:
                for (int i = row; i < row + 7; i++) {
                    for (int j = col; j < col + 7; j++) {
                        if(i<size&&j<size) {
                            if (i == row  || j == col
                                    ||i==row+3||j==col+2)
                                map[i][j] = 0;
                            else
                                map[i][j] = 1;
                        }
                    }
                }
            break;
            case 3:
                for (int i = row; i < row + 7; i++) {
                    for (int j = col; j < col + 7; j++) {
                        if(i<size&&j<size) {
                            if (i == row || j == col
                                    ||i==row+4||j==col+4)
                                map[i][j] = 0;
                            else
                                map[i][j] = 1;
                        }
                    }
                }
            break;
            case 4:
                for (int i = row; i < row + 7; i++) {
                    for (int j = col; j < col + 7; j++) {
                        if(i<size&&j<size) {
                            if (i == row  || j == col
                                    ||i==row+4||j==col+4||i==row+2||j==col+2)
                                map[i][j] = 0;
                            else
                                map[i][j] = 1;
                        }
                    }
                }
            break;
            default:
                for (int i = row; i < row + 7; i++) {
                    for (int j = col; j < col + 7; j++) {
                        if(i<size&&j<size) {
                            if (i == row || j == col
                                    ||i==row+2||j==col+3)
                                map[i][j] = 0;
                            else
                                map[i][j] = 1;
                        }
                    }
                }
            break;
        }


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(i==0||i==size-1||j==size-1||j==0)
                    map[i][j]=0;
            }
        }
    }

    ImageIcon blackSquare = new ImageIcon("BlackSquare.png");
    ImageIcon blueSquare = new ImageIcon("BlueSquare.png");
    ImageIcon point = new ImageIcon("point.png");
    ImageIcon ghost = new ImageIcon("Ghost.png");
    ImageIcon ghostAte = new ImageIcon("GhostAte.png");
    ImageIcon eatenGhost = new ImageIcon("eatenGhost.png");

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {

//        if(Math.tan(rowIndex)>0.5||Math.sin(colIndex)>0.9)
//            return blackSquare;
//        if(rowIndex == colIndex-2|| (rowIndex!=size && rowIndex+1 == colIndex-2))
//            return blackSquare;
//        if(rowIndex == size-colIndex|| (rowIndex!=size && rowIndex+1 == size-colIndex))
//            return blackSquare;
//        if(rowIndex == colIndex-2+(size/2)|| (rowIndex!=size && rowIndex+1 == colIndex-2+(size/2)))
//            return blackSquare;
//        if(rowIndex == (size/2)-colIndex|| (rowIndex!=size && rowIndex+1 == (size/2)-colIndex))
//            return blackSquare;
        for (Ghost g:GameplayWindow.Ghosts) {
            if(colIndex==g.GhostX && rowIndex==g.GhostY && colIndex==Update.PacPositionX&&rowIndex==Update.PacPositionY) {
                if (Update.ableToEat) {
                    g.SpawnPosition();
                    GameplayWindow.Score += 10;
                    GameplayWindow.ScoreTXT.setText("Score:" + GameplayWindow.Score);
                    map[rowIndex][colIndex] = 4;
                    return eatenGhost;
                }
                else if (Update.unkillable)
                    return new ImageIcon("shieldPacMan.png");
                else if(!Update.freezing)
                {
                    return ghostAte; //-hp
                }
            }


            if(colIndex==g.GhostX && rowIndex==g.GhostY)
                if(!Update.freezing)
                    return ghost;
                else return new ImageIcon("freezedGhost.png");
        }

        if(colIndex==Update.PacPositionX&&rowIndex==Update.PacPositionY){
            if(map[rowIndex][colIndex]==0){
                GameplayWindow.Score++;
                GameplayWindow.ScoreTXT.setText("Score:"+GameplayWindow.Score);
                map[rowIndex][colIndex]=3;
            }
            if(Update.Alive)
                return pacman;
            else return new ImageIcon("pacManDead.png");
        }

        for (Upgrade u:Ghost.upgrades)
            if(rowIndex == u.y && colIndex == u.x)
                return u.upgradeIcon;

        if(Update.teleports && Update.tp2Y==rowIndex && Update.tp2X==colIndex)
            return new ImageIcon("portal2.png");

        switch (map[rowIndex][colIndex]){
            case 0: return point;
            case 1: return blueSquare;
            case 3: return blackSquare;
            case 4: return eatenGhost;
            default: return blackSquare;
        }
    }
    public Class<?> getColumnClass(int column){
        return ImageIcon.class;
    }
}
