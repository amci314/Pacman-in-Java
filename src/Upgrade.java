import javax.swing.*;

public class Upgrade {
    int x=0,y=0;
    ImageIcon upgradeIcon;
    Upgrade(int y, int x){
        this.x =x;
        this.y=y;
    }
}

class SpeedBoost extends Upgrade{
    SpeedBoost(int y, int x) {
        super(y, x);
        upgradeIcon= new ImageIcon("speedBoost.png");
    }
}

class voracity extends Upgrade{
    voracity(int y, int x) {
        super(y, x);
        upgradeIcon = new ImageIcon("eatEm.png");
    }
}
class shield extends Upgrade {
    shield(int y, int x) {
        super(y, x);
        upgradeIcon = new ImageIcon("shield.png");
    }
}
class teleport extends Upgrade {
    teleport(int y, int x) {
        super(y, x);
        upgradeIcon = new ImageIcon("portal1.png");
    }
}
class freezer extends Upgrade {
    freezer(int y, int x) {
        super(y, x);
        upgradeIcon = new ImageIcon("freeze.png");
    }
}
