package com.angrybirds.chatnchill;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

import java.io.*;

public class gamestatemanager{
    private static final String Save_file = "game_save.json";

    public static void savegame(gamestate gameState) {
        Json json = new Json();
        try {
            Gdx.files.local(Save_file).writeString(json.toJson(gameState),false);
            Gdx.app.log("Save Game", "Game state saved successfully to: " + Save_file);
        } catch (Exception e) {
            Gdx.app.error("Save Game", "Failed to save game: " + e.getMessage(), e);
        }
    }

    public static gamestate loadgame() {
        File saveFile = new File(Save_file);
        if (!saveFile.exists()) {
            Gdx.app.log("Load Game", "No save file found at: " + saveFile.getAbsolutePath());
            return null;
        }

        Json json = new Json();
        try {
            String jsonData = Gdx.files.local(Save_file).readString();
            gamestate gameState = json.fromJson(gamestate.class, jsonData);
            Gdx.app.log("Load Game", "Game state loaded successfully from: " + saveFile.getAbsolutePath());
            return gameState;
        } catch (Exception e) {
            Gdx.app.error("Load Game", "Failed to load game: " + e.getMessage(), e);
            return null;
        }
    }
}
