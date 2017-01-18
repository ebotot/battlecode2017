package ebotplayer.units;

import battlecode.common.*;
import ebotplayer.util.*;
/**
 * Created by ebot on 1/10/17.
 */
public class Archon {
    private RobotController rc;
    private Common c;
    private Movement m;
    private int gBuilds;
    private int tcount;
    private double gardNum;
    private int gtimer;

    public Archon(RobotController rc) {
        this.rc = rc;
        c = new Common(rc);
        m = new Movement(rc);
        gBuilds = 0;
        gardNum = 0;
        tcount = 0;
        gtimer = 0;
        int resetBuilds = 0;
        while(true) {
            try {
                c.vp();
                // gBuilds counts how many times gardeners were built and stops after 6 are built
                // resetBuilds is 20 rounds after gbuilds was set
                // meaning that after 20 rounds gardeners may be built again
                //if (rc.getRoundNum() == resetBuilds) {
                //    gBuilds = 0;
                //}
                c.shake();
                tcount = rc.getTreeCount();
                gardNum = 20-tcount;
                // if we stick to this method, we could have something similar for lumberjacks
                if (gardNum>0) {                        //&& rc.senseNearbyTrees(5).length < 4){
                    hGardener();
                }
                else {
                    if (gtimer%25 == 0) {
                        hGardener();
                    }
                    gtimer++;
                }
                //if (gBuilds < 7) {
                //    hGardener();
                //    resetBuilds = rc.getRoundNum() + 20;
                //}
                m.wander(45,7);
                Clock.yield();
            } catch (Exception e) {
                System.out.println("Archon Exception");
                e.printStackTrace();
            }
        }
    }
    private void hGardener() throws GameActionException {
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
