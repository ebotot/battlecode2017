package ebotplayer.units;

import battlecode.common.*;
import ebotplayer.util.*;
/**
 * Created by ebot on 1/10/17.
 */
public class Soldier {
    private RobotController rc;
    private Common c;
    private Movement m;
    private Attack a;
    public Soldier(RobotController rc) {
        this.rc = rc;
        c = new Common(rc);
        m = new Movement(rc);
        a = new Attack(rc);
        while(true) {
            try {
                c.vp();
                a.bullet();
                System.out.println("harry is orange");
                c.shake();
                if (!rc.hasAttacked()) {
                    m.wander(45, 7);
                }
                Clock.yield();
            } catch (Exception e) {
                System.out.println("Soldier Exception");
                e.printStackTrace();
            }
        }
    }
}
