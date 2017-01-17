package ebotplayer.units;

import battlecode.common.*;
import ebotplayer.util.*;
/**
 * Created by ebot on 1/10/17.
 */
public class Lumberjack {
    private RobotController rc;
    private Common c;
    private Movement m;
    private Attack a;
    private int choppingID;
    public Lumberjack(RobotController rc) {
        this.rc = rc;
        c = new Common(rc);
        m = new Movement(rc);
        a = new Attack(rc);
        int choppingID;
        while(true) {
            try {
                c.vp();
                a.strike();
                if (canChop()) {
                    chop();
                } else {
                    m.wander(45, 7);
                }
                Clock.yield();
            } catch (Exception e) {
                System.out.println("Lumberjack Exception");
                e.printStackTrace();
            }
        }
    }
    private boolean canChop() {
        TreeInfo[] trees = rc.senseNearbyTrees(rc.getType().sensorRadius, Team.NEUTRAL);
        if (trees.length > 0) {
            for (TreeInfo ctree : trees) {
                if (rc.canChop(trees[0].location) && (trees[0].containedBullets > 0 || trees[0].containedRobot != null)) {
                    choppingID = trees[0].ID;
                    return true;
                }
            } for (TreeInfo ctree : trees) { //repeated the for-loop so that if no bullet or robot trees are nearby, they'll jsut cut down a regular one
                //should probably find a more elegant solution
                //or maybe we should just have them cut down all trees regardless or only special trees?
                if (rc.canChop(trees[0].location)) {
                    choppingID = trees[0].ID;
                    return true;
                }
            }
        }
        return false;
    }
    private void chop() throws GameActionException {
        if (rc.canChop(choppingID)) {
            rc.chop(choppingID);
            System.out.println("chopped");
        }
    }
}
