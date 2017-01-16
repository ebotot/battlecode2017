package ebotplayer.util;

import battlecode.common.*;
/**
 * Created by ebot on 1/12/17.
 */
public class Attack {
    private RobotController rc;
    // i have no idea if i actually need to initialize c since i jsut call a method
    // if theres an error this is probably why
    // add this to constructor:       c = new Common(rc);
    private Common c;
    public Attack(RobotController rc) {
        this.rc = rc;
    }

    public void bullet() throws GameActionException {
       RobotInfo[] robots = rc.senseNearbyRobots(rc.getType().sensorRadius, c.enemy());
       if (robots.length > 0) {
           if (rc.canFirePentadShot()) {
               rc.firePentadShot(rc.getLocation().directionTo(robots[0].location));
           } else if (rc.canFireTriadShot()) {
               rc.fireTriadShot(rc.getLocation().directionTo(robots[0].location));
           } else if (rc.canFireSingleShot()) {
               rc.fireSingleShot(rc.getLocation().directionTo(robots[0].location));
           }
       }
    }
    public void strike() throws GameActionException {
        RobotInfo[] robots = rc.senseNearbyRobots(rc.getType().sensorRadius, c.enemy());
        if (robots.length > 0) {
            if (rc.canStrike()) {
                rc.strike();
                // if you see random print statements they are probably print statements im using to test in the client
                // go to the console tab to see these messages
                System.out.println("struck");
            }
        }
    }
}
