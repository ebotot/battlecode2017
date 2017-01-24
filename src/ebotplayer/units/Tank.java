package ebotplayer.units;

import battlecode.common.*;
import ebotplayer.units.base.BulletUnit;
/**
 * Created by ebot on 1/10/17.
 */
public class Tank extends BulletUnit{
    public Tank(RobotController rc) {
        super(rc);
        while(true) {
            try {
                c.vp();
                u.sendAliveSignal();
                a.bullet();
                c.shake();
                if (!rc.hasAttacked()) {
                    m.wander(30, 12);
                }
                c.vpEnd();
                Clock.yield();
            } catch (Exception e) {
                System.out.println("Tank Exception");
                e.printStackTrace();
            }
        }
    }
}
