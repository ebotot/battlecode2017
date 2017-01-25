package ebotplayer.util;

import battlecode.common.*;
/**
 * Created by ebot on 1/10/17.
 */
public class Movement {
    private RobotController rc;
    private Direction dir;
    public Movement(RobotController rc) {
        this.rc = rc;
        dir = new Direction(0);
    }
    private void setDir(Direction dir){
        this.dir = dir;
    }

    public void wander(float offset, int checks) throws GameActionException {
        if (rc.canMove(dir)) {
            rc.move(dir);
        } else {
            int currentCheck = 1;
            while (currentCheck <= checks) {
                if (rc.canMove(dir.rotateRightDegrees(offset * currentCheck))) {
                    setDir(dir.rotateRightDegrees(offset * currentCheck));
                    rc.move(dir);
                    break;
                }
                currentCheck++;
            }
        }
    }
    public void offense() throws GameActionException {
        //TODO move towards enemy
    }
    public void moveCorner() throws GameActionException {
        //TODO for the Archon to hide in corner and have trees build around
    }
}
