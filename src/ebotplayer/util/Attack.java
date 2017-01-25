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
    public void bullet() throws GameActionException { //TODO help determining good fire-type at bullet levels, my #'s are pretty random
       RobotInfo[] robots = rc.senseNearbyRobots(rc.getType().sensorRadius, c.enemy());
       if (robots.length > 0) {
           if (rc.canFirePentadShot() && rc.getTeamBullets() > 200) {
               rc.firePentadShot(rc.getLocation().directionTo(robots[0].location));
           } else if (rc.canFireTriadShot()  && rc.getTeamBullets() > 100) {
               rc.fireTriadShot(rc.getLocation().directionTo(robots[0].location));
           } else if (rc.canFireSingleShot()) {
               rc.fireSingleShot(rc.getLocation().directionTo(robots[0].location));
           }
       } //TODO friendly fire also sometimes soldier endlessly spams bullets eg ChilisArmy
    }
}
