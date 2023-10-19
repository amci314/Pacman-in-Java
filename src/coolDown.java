public class coolDown extends Thread{
    Upgrade upgrade;
    coolDown(Upgrade u){
        upgrade = u;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(upgrade.getClass().equals(SpeedBoost.class))
            Update.delay*=2;

        else if(upgrade.getClass().equals(voracity.class)){
            Update.ableToEat=false;
            for (int i = 0; i < MapTable.map.length; i++) {
                for (int j = 0; j < MapTable.map.length; j++) {
                    if(MapTable.map[i][j]==4)
                        MapTable.map[i][j]=3;
                }
            }
        }

        else if(upgrade.getClass().equals(shield.class))
            Update.unkillable=false;

        else if(upgrade.getClass().equals(teleport.class)) {
            Update.teleports = false;
            Update.tp2X = -1;
            Update.tp2Y = -1;
            upgrade.y = -1;
            upgrade.x = -1;
            Ghost.upgrades.remove(upgrade);
        }

        else if(upgrade.getClass().equals(freezer.class))
            Update.freezing=false;
    }
}
