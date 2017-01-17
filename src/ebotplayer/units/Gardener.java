package ebotplayer.units;

import battlecode.common.*;
import ebotplayer.util.*;
/**
 * Created by ebot on 1/10/17.
 */
public class Gardener {
    private RobotController rc;
    private Common c;
    public Gardener(RobotController rc) {
        this.rc = rc;
        Movement m = new Movement(rc);
        Common c = new Common(rc);
        boolean type;
        double rand = Math.random();
        //im so fucking confused guys whats the halfway point for Math.random() LOL
        //i keep adjusting and it seems to change from map to map?
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
    void water() throws GameActionException {
        TreeInfo[] fTrees = rc.senseNearbyTrees(rc.getType().sensorRadius, rc.getTeam());
        if (fTrees.length > 0) {
            float lowestH = 50;
            int tID = 0;
            for(TreeInfo t : fTrees) {
                if (t.getHealth() < lowestH && rc.canWater(t.getID())) {
                    lowestH = t.getHealth();
                    tID = t.getID();
                }
            }
            if (rc.canWater(tID)) {
                rc.water(tID);
                System.out.println("watered");
            }
        }
    }
    void bUnit(RobotType type) throws GameActionException {
        Direction bDirection = Tools.randomDirection();
        if (rc.canBuildRobot(type, bDirection)) {
            rc.buildRobot(type, bDirection);
        }
    }
}

