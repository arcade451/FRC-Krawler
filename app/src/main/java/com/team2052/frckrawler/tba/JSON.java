package com.team2052.frckrawler.tba;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.team2052.frckrawler.database.RxDBManager;
import com.team2052.frckrawler.db.Event;
import com.team2052.frckrawler.db.Team;
import com.team2052.frckrawler.tba.types.EventDeserializer;
import com.team2052.frckrawler.tba.types.TeamDeserializer;

/**
 * @author Adam
 */
public class JSON {
    private static RxDBManager _daoSession = null;
    private static Gson gson;
    private static JsonParser parser;

    public static Gson getGson() {
        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Event.class, new EventDeserializer());
            gsonBuilder.registerTypeAdapter(Team.class, new TeamDeserializer());
            gson = gsonBuilder.create();
        }
        return gson;
    }

    public static JsonObject getAsJsonObject(String in) {
        if (in == null || in.equals("")) {
            return new JsonObject();
        }
        JsonElement e = getParser().parse(in);
        if (e == null || e.isJsonNull()) {
            return new JsonObject();
        }
        return e.getAsJsonObject();
    }

    public static JsonParser getParser() {
        if (parser == null) {
            parser = new JsonParser();
        }
        return parser;
    }

    public static JsonArray getAsJsonArray(String in) {
        if (in == null || in.equals("")) {
            return new JsonArray();
        }
        return getParser().parse(in).getAsJsonArray();
    }

    public static RxDBManager get_daoSession() {
        return _daoSession;
    }

    public static void set_daoSession(RxDBManager rxDbManager) {
        JSON._daoSession = rxDbManager;
    }
}
