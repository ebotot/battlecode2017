package ebotplayer.units;

import battlecode.common.*;
import ebotplayer.units.base.BulletUnit;
/**
 * Created by ebot on 1/10/17.
 */
public class Soldier extends BulletUnit {
    public Soldier(RobotController rc) {
        super(rc);
        while(true) {
            try {
                c.vp();
                u.sendAliveSignal();
                a.bullet();
                c.shake();
                if (!rc.hasAttacked()) {
                    findEnemies();
                }
                c.vpEnd();
                Clock.yield();
            } catch (Exception e) {
                System.out.println("Soldier Exception");
                e.printStackTrace();
            }
        }
    }
    private void findEnemies() throws GameActionException { //TODO broken as well
        RobotInfo[] enemies = rc.senseNearbyRobots(rc.getType().sensorRadius, c.enemy());
        if (enemies.length > 0) {
            m.moveTo(rc.getLocation().directionTo(enemies[0].location));
            System.out.println("tracking down");
        } else {
            m.wander(30, 12);
            System.out.println("wandering");
        }
    }
}
