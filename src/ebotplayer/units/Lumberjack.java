package ebotplayer.units;

import battlecode.common.*;
import ebotplayer.util.*;
/**
 * Created by ebot on 1/10/17.
 */
public class Lumberjack {
    private RobotController rc;
    public Lumberjack(RobotController rc) {
        this.rc = rc;
        Attack a = new Attack(rc);
        Movement m = new Movement(rc);
        while(true) {
            try {
                a.strike();
                chop();
                m.wander(45, 7);
                Clock.yield();
            } catch (Exception e) {
                System.out.println("Lumberjack Exception");
                e.printStackTrace();
            }
        }
    }
    boolean canChop() throws GameActionException {
        TreeInfo[] trees = rc.senseNearbyTrees(rc.getType().sensorRadius, Team.NEUTRAL);
        if (trees.length > 0) {
            if (rc.canChop(trees[0].location) && (trees[0].containedBullets > 0 || trees[0].containedRobot != null)) {
                return true;
            }
        }
        return false;
    }
    void chop() throws GameActionException {
        if (canChop()) {
            //rc.chop(trees[0].location);
            System.out.println("chopped");
        }
    }
}
