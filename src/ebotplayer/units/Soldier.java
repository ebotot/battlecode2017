package ebotplayer.units;

import battlecode.common.*;
import ebotplayer.util.*;
/**
 * Created by ebot on 1/10/17.
 */
public class Soldier {
    public Soldier(RobotController rc) {
        Movement m = new Movement(rc);
        Attack a = new Attack(rc);
        Common c = new Common(rc);
        while(true) {
            try {
                //TODO fix running into bullets
                a.bullet();
                c.shake();
                m.wander(45, 7);
                Clock.yield();
            } catch (Exception e) {
                System.out.println("Soldier Exception");
                e.printStackTrace();
            }
        }
    }
}
