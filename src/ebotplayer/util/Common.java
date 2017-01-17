package ebotplayer.util;

import battlecode.common.*;
/**
 * Created by ebot on 1/12/17.
 */
public class Common {
    private RobotController rc;
    public Common(RobotController rc) {
        this.rc = rc;
    }
    public Team enemy() {
        return rc.getTeam().opponent();
    }
    public void shake() throws GameActionException {
        TreeInfo[] trees = rc.senseNearbyTrees(rc.getType().sensorRadius, Team.NEUTRAL);
        if (trees.length > 0) {
            //TODO add for-loop
            if (rc.canShake(trees[0].location) && trees[0].containedBullets > 0) {
                rc.shake(trees[0].location);
                System.out.println("shook");
            }

        }
    }
}
