package com.jarhax.simplescreens;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    @Expose
    @SerializedName("game_load_screen_textures")
    public List<String> gameLoadScreenTextures = of("simplescreens:textures/gui/load_screens/game_load_background.png");

    @Expose
    @SerializedName("world_load_screen_textures")
    public List<String> worldLoadScreenTextures = of("simplescreens:textures/gui/load_screens/world_load_background.png");

    public static Config load() {

        final File configFile = FMLPaths.CONFIGDIR.get().resolve(Constants.MOD_ID + ".json").toFile();
        Config config = new Config();

        // Attempt to load existing config file
        if (configFile.exists()) {

            try (FileReader reader = new FileReader(configFile)) {

                config = GSON.fromJson(reader, Config.class);
                Constants.LOG.info("Loaded config file.");
            }
            catch (Exception e) {

                Constants.LOG.error("Could not read config file {}. Defaults will be used.", configFile.getAbsolutePath());
                Constants.LOG.catching(e);
            }
        }
        else {

            Constants.LOG.info("Creating a new config file at {}.", configFile.getAbsolutePath());
            configFile.getParentFile().mkdirs();
        }

        try (FileWriter writer = new FileWriter(configFile)) {

            GSON.toJson(config, writer);
            Constants.LOG.info("Saved config file.");
        }
        catch (Exception e) {

            Constants.LOG.error("Could not write config file '{}'!", configFile.getAbsolutePath());
            Constants.LOG.catching(e);
        }

        return config;
    }

    private static List<String> of(String... elements) {

        final ArrayList list = new ArrayList();
        Arrays.stream(elements).forEach(list::add);
        return list;
    }
}