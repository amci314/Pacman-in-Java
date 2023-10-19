import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.ArrayList;

public class GameplayWindow extends JFrame {
    static JTable Screen;
    int Size;
    Update myThread;
    GhostUpdate ghostUpdate;
    static int Score=0;
    static JLabel ScoreTXT;
    static JLabel TimerTXT;
    static JLabel healthLabel;
    static ImageIcon health = new ImageIcon("heart.png");
    static ArrayList<Ghost> Ghosts = new ArrayList<>();
    public GameplayWindow(int mapSize) {
        Score =0;
//        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
//            public Component getTableCellRendererComponent(JTable table, Object value,
//                                                           boolean isSelected, boolean hasFocus,
//                                                           int row, int column) {
//                Component c = super.getTableCellRendererComponent(table, value,
//                        isSelected, hasFocus, row, column);
//                if(RenderFigure())
//                    c.setBackground(Color.blue);
//                else
//                    c.setBackground(Color.BLACK);
//                return c;
//            }
//        };
//        jTable.setDefaultRenderer(Object.class, renderer);
        MapTable mapTable = new MapTable(mapSize);
        JTable jTable = new JTable(mapTable);

        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable.getTableHeader().setResizingAllowed(false);
        jTable.getTableHeader().setReorderingAllowed(false);

        int sizeOfCell = 17;
        while (sizeOfCell * mapSize > 990)
            sizeOfCell--;
        for (int i = 0; i < mapSize; i++) {
            jTable.getColumnModel().getColumn(i).setMinWidth(sizeOfCell);
            jTable.getColumnModel().getColumn(i).setMaxWidth(sizeOfCell);
            jTable.getColumnModel().getColumn(i).setWidth(sizeOfCell);
            jTable.setRowHeight(sizeOfCell);
        }

        for (int i = 0; i < mapSize/10; i++) {
            Ghosts.add(new Ghost());
        }
        for (Ghost g:Ghosts) {
            g.start();
        }

        this.getContentPane().setBackground(Color.BLACK);

        JPanel underGame = new JPanel(new BorderLayout());
        underGame.setBackground(Color.black);

        ScoreTXT = new JLabel("SCORE:"+Score);
        ScoreTXT.setForeground(Color.white);

        TimerTXT = new JLabel("  0:00");
        TimerTXT.setForeground(Color.white);
        Stopwatch stopwatch = new Stopwatch(TimerTXT);
        stopwatch.start();

        healthLabel = new JLabel(health);

        underGame.add(ScoreTXT,BorderLayout.WEST);
        underGame.add(TimerTXT,BorderLayout.CENTER);
        underGame.add(healthLabel, BorderLayout.EAST);

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Update.Death();
                JTextField textField = new JTextField();
                Object[] message = {
                        "Enter your nickname:", textField , "Save it?"
                };
                int UserName = JOptionPane.showConfirmDialog(null, message, "", JOptionPane.YES_NO_OPTION);
                if (UserName == JOptionPane.YES_OPTION) {
                    String nickname = textField.getText();
                    ScoreWindow.addScore(new Score(nickname, Score, TimerTXT.getText()));
                }
                dispose();
            }
        };

        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK);
        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "ctrl-shift-q");
        this.getRootPane().getActionMap().put("ctrl-shift-q", action);


        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
        add(jTable);
        add(underGame, BorderLayout.SOUTH);
        pack();

        Size = mapSize;
        Screen = jTable;


        this.setLocationRelativeTo(null);
        jTable.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                //or interrupt thread

               if(ghostUpdate == null){
                   ghostUpdate = new GhostUpdate(Screen,Ghosts);
                   ghostUpdate.start();
               }
                if (myThread == null) {
                    myThread = new Update(Screen, e);
                    myThread.start();
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_UP)
                    myThread.k = e;

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
}

