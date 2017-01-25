package ebotplayer.units;

import battlecode.common.*;
import ebotplayer.units.base.Unit;
import ebotplayer.util.*;
/**
 * Created by ebot on 1/10/17.
 */
public class Archon extends Unit {
    private int gards;
    public Archon(RobotController rc) {
        super(rc);
        int gards = 4;
        while(true) {
            try {
                c.vp();
                gardCount();
                hGardener();
                c.shake();
                m.wander(30, 12);
                c.vpEnd();
                Clock.yield();
            } catch (Exception e) {
                System.out.println("Archon Exception");
                e.printStackTrace();
            }
        }
    }
    private void hGardener() throws GameActionException {
        if (rc.hasRobotBuildRequirements(RobotType.GARDENER) && u.unitCount(RobotType.GARDENER) < gards) {
            Direction bDirection = Tools.randomDirection();
            int i = 0;
            while (!rc.canHireGardener(bDirection) && i < 3) {
                bDirection = Tools.randomDirection();
                i++;
            } if (rc.canHireGardener(bDirection)) {
                rc.hireGardener(bDirection);
            }
        }
    }
    private void gardCount() throws GameActionException { //TODO help determining good gardeners counts at bullet levels, my #'s are pretty random
        if (rc.getTeamBullets() < 100) {
            gards = 4;
        } else if (rc.getTeamBullets() < 200) {
            gards = 6;
        } else if (rc.getTeamBullets() < 400) {
            gards = 8;
        } else {
            gards = 20;
        }
    }
}
