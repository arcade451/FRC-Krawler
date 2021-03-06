package com.team2052.frckrawler.bluetooth.client;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.team2052.frckrawler.Constants;
import com.team2052.frckrawler.database.RxDBManager;
import com.team2052.frckrawler.db.Event;
import com.team2052.frckrawler.db.Game;
import com.team2052.frckrawler.db.Metric;
import com.team2052.frckrawler.db.Robot;
import com.team2052.frckrawler.db.RobotEvent;
import com.team2052.frckrawler.db.Team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A object that contains all the data that is sent to the scout
 * Easier to manage
 *
 * @author Adam
 * @since 12/24/2014.
 */
public class ScoutPackage implements Serializable {
    private final ArrayList<Team> teams = new ArrayList<>();
    private final Event event;
    private final List<Metric> metrics;
    private final List<RobotEvent> robot_events;
    private final List<Robot> robots = new ArrayList<>();
    private final Game game;
    private final String TAG = ScoutPackage.class.getSimpleName();

    public ScoutPackage(RxDBManager rxDbManager, Event event) {
        this.game = rxDbManager.getGamesTable().load(event.getGame_id());
        this.event = event;
        game.resetMetricList();
        metrics = game.getMetricList();

        robot_events = event.getRobotEventList();

        for (RobotEvent robotEvent : robot_events) {
            robots.add(rxDbManager.getRobotEventsTable().getRobot(robotEvent));
        }

        for (RobotEvent robotEvent : robot_events) {
            teams.add(rxDbManager.getRobotEventsTable().getTeam(robotEvent));
        }
    }

    public void save(final RxDBManager rxDbManager, Context context) {
        Log.d(TAG, "Saving");
        rxDbManager.runInTx(() -> {
                    for (Metric metric : metrics) {
                        rxDbManager.getMetricsTable().insert(metric);
                    }

                    for (RobotEvent robotEvent : robot_events) {
                        rxDbManager.getRobotEventsTable().insert(robotEvent);
                    }

                    for (Robot robot : robots) {
                        rxDbManager.getRobotsTable().insert(robot);
                    }

                    for (Team team : teams) {
                        rxDbManager.getTeamsTable().insert(team);
                    }

                    rxDbManager.getEventsTable().insert(event);
                    rxDbManager.getGamesTable().insert(game);
                }
        );

        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREFS_FILE_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(Constants.CURRENT_SCOUT_EVENT_ID, event.getId());
        editor.apply();
    }

    public Event getEvent() {
        return event;
    }
}
