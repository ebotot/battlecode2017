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
        //dodge();
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
    public void moveTo(Direction move) throws GameActionException {
        if (rc.canMove(move)) {
            rc.move(move);
        } else {
            for (int i = 0 ; i < 5 ; i++) {
                if(rc.canMove(move.rotateRightDegrees(10 * i))) {
                    rc.move(move.rotateRightDegrees(10 * i));
                } else if (rc.canMove(move.rotateLeftDegrees(10 * i))) {
                    rc.move(move.rotateLeftDegrees(10 * i));
                }
            }
        }
    }
    public void dodge() throws GameActionException { //no idea if this does shit, yeah it probably doesnt
        BulletInfo[] bullets = rc.senseNearbyBullets();
        for (BulletInfo b : bullets) {
            if (b.dir == b.location.directionTo(rc.getLocation())) {
                dir = b.dir.rotateLeftDegrees(90);
                System.out.println("dodge");
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
