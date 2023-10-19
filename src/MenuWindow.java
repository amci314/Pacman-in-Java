import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuWindow extends JFrame{
    public MenuWindow() {
        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(3,1,0,10));


        JButton NGButton = new JButton("New Game");
        NGButton.setBackground(Color.black);
        NGButton.setForeground(Color.yellow);
        NGButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mapSize = Integer.parseInt(JOptionPane.showInputDialog(null, "Type size (10-100)", "",JOptionPane.PLAIN_MESSAGE));
                if(mapSize>9 && mapSize<101) {
                    Update.Alive=true;
                    Update.HP=2;
                    Update.PacPositionX=5;
                    Update.PacPositionY=0;
                    SwingUtilities.invokeLater(() -> new GameplayWindow(mapSize));
                }
            }
        });
        NGButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                NGButton.setIcon(new ImageIcon("fire.gif"));
                NGButton.setHorizontalTextPosition(JLabel.CENTER);
                NGButton.setForeground(Color.white);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                NGButton.setIcon(null);
                NGButton.setForeground(Color.yellow);
            }
        });
        NGButton.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 30));



        JButton Sbutton = new JButton("Scores");
        Sbutton.setBackground(Color.black);
        Sbutton.setForeground(Color.yellow);
        Sbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new ScoreWindow());
            }
        });
        Sbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Sbutton.setIcon(new ImageIcon("scoresE.jpg"));
                Sbutton.setHorizontalTextPosition(JLabel.CENTER);
                Sbutton.setForeground(Color.white);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                Sbutton.setIcon(null);
                Sbutton.setForeground(Color.yellow);
            }
        });
        Sbutton.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 30));



        JButton Ebutton = new JButton("Exit");
        Ebutton.setBackground(Color.black);
        Ebutton.setForeground(Color.yellow);
        Ebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        Ebutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Ebutton.setForeground(Color.white);
                Ebutton.setBackground(Color.red);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                Ebutton.setBackground(Color.black);
                Ebutton.setForeground(Color.yellow);
            }
        });
        Ebutton.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 30));

        menu.add(NGButton);
        menu.add(Sbutton);
        menu.add(Ebutton);

        menu.setBackground(Color.blue);

        setSize(350,350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        add(menu);
    }
}
