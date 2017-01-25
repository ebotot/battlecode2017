package ebotplayer.util;

import battlecode.common.*;
/**
 * Created by ebot on 1/12/17.
 */
public class Attack {
    private RobotController rc;
    private Common c;
    public Attack(RobotController rc) {
        this.rc = rc;
        c = new Common(rc);
    }
    public void bullet() throws GameActionException { //help determining good fire-type at bullet levels, my #'s are pretty random
        RobotInfo[] robots = rc.senseNearbyRobots(rc.getType().sensorRadius, c.enemy());
        RobotInfo[] friends = rc.senseNearbyRobots(rc.getType().sensorRadius, rc.getTeam());
        if (robots.length > 0) {
            for (RobotInfo r : robots) {
                for (RobotInfo f : friends) {
                    if (rc.getLocation().directionTo(r.location) != rc.getLocation().directionTo(f.location)) {
                        if (rc.canFirePentadShot()) {
                            rc.firePentadShot(rc.getLocation().directionTo(r.location));
                        } else if (rc.canFireTriadShot()) {
                            rc.fireTriadShot(rc.getLocation().directionTo(r.location));
                        } else if (rc.canFireSingleShot()) {
                            rc.fireSingleShot(rc.getLocation().directionTo(r.location));
                        }
                    }
                }
            }
        }
    }
}