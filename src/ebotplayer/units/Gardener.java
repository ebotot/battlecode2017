package ebotplayer.units;

import battlecode.common.*;
import ebotplayer.util.*;
/**
 * Created by ebot on 1/10/17.
 */
public class Gardener {
    private RobotController rc;
    private Common c;
    private boolean type;
    public Gardener(RobotController rc) {
        this.rc = rc;
        Movement m = new Movement(rc);
        Common c = new Common(rc);
        double rand = Math.random();
        // the random stuff is used to determine what i want my gardner to do
        //if type = true, i have the gardners build robots
        //if type = false, i have the gardeners plant trees
        // for some reason Math.random() produced mostly small decimals even tho
        // the javadocs said that it should be a decimal from 0-1
        // from my testing 0.25 seems to be the halfway point? idk something to look into
        if (rand < 0.25) {
            type = true;
            System.out.println("builder: " + rand);
        }
        else {
            type = false;
            System.out.println("planter: " + rand);
        }
        while(true) {
            try {
                if (type) {
                    c.shake();
                    bUnit(RobotType.SOLDIER);
                    bUnit(RobotType.LUMBERJACK);
                    //TODO unit count
                    m.wander(45, 7);
                    Clock.yield();
                }
                else if (!type) {
                    water();
                    plant();
                    Clock.yield();
                }
            } catch (Exception e) {
                System.out.println("Gardener Exception");
                e.printStackTrace();
            }
        }
    }
    void plant() throws GameActionException {
        if (rc.hasTreeBuildRequirements()) {
            Direction seed = Tools.randomDirection();
            int i = 0;
            // used i < 5 to make sure gardener would not get stuck in infinite loop finding a nonexistent valid direction
            while (!rc.canPlantTree(seed) && i < 2) {
                seed = Tools.randomDirection();
                i++;
            } if (rc.canPlantTree(seed)) {
                rc.plantTree(seed);
            }
        }
    }
    void water() throws GameActionException { //TODO test watering
        TreeInfo[] fTrees = rc.senseNearbyTrees(rc.getType().sensorRadius, c.friendly());
        if (fTrees.length>0) {
            float lowestH = 50;
            int tID = 0;
            for(TreeInfo t : fTrees) {
                if (t.getHealth() < 0)
                lowestH = t.getHealth();
                tID = t.getID();
            }
            rc.water(tID);
        }
    }
    void bUnit(RobotType type) throws GameActionException {
        Direction bDirection = Tools.randomDirection();
        if (rc.canBuildRobot(type, bDirection)) {
            rc.buildRobot(type, bDirection);
        }
    }
}

