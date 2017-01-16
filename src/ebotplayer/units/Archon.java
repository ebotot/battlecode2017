package ebotplayer.units;

import battlecode.common.*;
import ebotplayer.util.*;
/**
 * Created by ebot on 1/10/17.
 */
public class Archon {
    private RobotController rc;
    private int gBuilds;
    public Archon(RobotController rc) {
        this.rc = rc;
        Movement m = new Movement(rc);
        Common c = new Common(rc);
        gBuilds = 0;
        int resetBuilds = 0;
        while(true) {
            try {
                // gBuilds counts how many times gardeners were built and stops after 6 are built
                // resetBuilds is 20 rounds after gbuilds was set
                // meaning that after 20 rounds gardeners may be built again
                if (rc.getRoundNum() == resetBuilds) {
                    gBuilds = 0;
                }
                c.shake();
                if (gBuilds < 7) {
                    hGardener();
                    resetBuilds = rc.getRoundNum() + 20;
                }
                m.wander(45,7);
                Clock.yield();
            } catch (Exception e) {
                System.out.println("Archon Exception");
                e.printStackTrace();
            }
        }
    }
    void hGardener() throws GameActionException {
        if (rc.hasRobotBuildRequirements(RobotType.GARDENER)) {
            Direction bDirection = Tools.randomDirection();
            int i = 0;
            while (!rc.canHireGardener(bDirection) && i < 3) {
                bDirection = Tools.randomDirection();
                i++;
            } if (rc.canHireGardener(bDirection)) {
                rc.hireGardener(bDirection);
                gBuilds++;
            }
        }
    }
}
