package ebotplayer.util;

import battlecode.common.*;
/**
 * Created by ebot on 1/20/17.
 */
public class UnitCount {
    private RobotController rc;
    public UnitCount(RobotController rc) {
        this.rc = rc;
    }
    public void sendAliveSignal() throws GameActionException {
        rc.broadcast(getPreChannel(rc.getType()), rc.readBroadcast(getPreChannel(rc.getType())) + 1);
    }
    //cheat-sheet
    //Gardener = 0, 1
    //Soldier = 2, 3
    //Lumberjack = 4, 5
    //Scout = 6, 7
    //Tank = 8, 9
    public static int getPreChannel(RobotType type) {
        switch(type) {
            case GARDENER:
                return 0;
            case SOLDIER:
                return 2;
            case LUMBERJACK:
                return 4;
            case SCOUT:
                return 6;
            case TANK:
                return 8;
        }
        return 0;
    }
    public static int getChannel(RobotType type) {
        switch(type) {
            case GARDENER:
                return 1;
            case SOLDIER:
                return 3;
            case LUMBERJACK:
                return 5;
            case SCOUT:
                return 7;
            case TANK:
                return 9;
        }
        return 0;
    }
}
