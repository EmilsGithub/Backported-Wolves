package net.emilsg.backported_wolves.config;

import net.emilsg.backported_wolves.BackportedWolves;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BackportedWolvesConfig {
    public static final String FILE_VERSION = "1.1.0";
    private static final Logger LOGGER = BackportedWolves.LOGGER;
    private final Map<String, ConfigEntry<?>> configs;
    private final File configFile;
    private static BackportedWolvesConfig instance;
    private static final String fileName = "backported_wolves.properties";

    public BackportedWolvesConfig(String configFilePath) {
        this.configFile = new File(configFilePath);
        this.configs = new HashMap<>();
        initializeConfigs();
        loadOrCreateConfig();
    }

    public static void init() {
        LOGGER.info("Initializing Backported-Wolves Config.");
    }

    public static BackportedWolvesConfig getInstance() {
        if (instance == null) {
            instance = new BackportedWolvesConfig(FabricLoader.getInstance().getConfigDir().resolve(fileName).toString());
        }
        return instance;
    }

    public static final String SPAWN_WEIGHT = "spawn_weight";

    public void initializeConfigs() {
        configs.put(SPAWN_WEIGHT, new ConfigEntry<>( 8, "Spawn Weight of the Wolves"));
    }

    public boolean isConfigVersionCurrent() {
        if(getCurrentFileVersion() != null) {
            return getCurrentFileVersion().equals("# File Version: " + FILE_VERSION);
        }
        return false;
    }

    public String getCurrentFileVersion() {
        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("# File Version:")) {
                    return line;
                }
            }
        } catch (IOException e) {
            LOGGER.error("Error reading config file for version check.", e);
        }
        return null;
    }

    public String getFileVersionNumber(String versionLine) {
        return versionLine.replace("# File Version: ", "");
    }

    private void loadOrCreateConfig() {
        if (configFile.exists()) {
            loadConfig();
        } else {
            LOGGER.info("Backported-Wolves config file is missing, generating default one." );
            createDefaultConfig();
        }
    }

    private void loadConfig() {
        Properties props = new Properties();

        try (FileReader reader = new FileReader(configFile)) {
            props.load(reader);

            for (Map.Entry<String, ConfigEntry<?>> entry : configs.entrySet()) {
                String key = entry.getKey();
                ConfigEntry<?> configEntry = entry.getValue();

                if (props.containsKey(key)) {

                    updateConfigEntry(key, configEntry, props.getProperty(key));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addMissingConfigsAndUpdateVersion() {
        loadConfig();

        StringBuilder newFileContent = new StringBuilder();
        boolean versionMismatch = true;
        boolean changesMade = false;

        try (FileReader reader = new FileReader(configFile)) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.startsWith("# File Version:")) {
                        if (line.equals("# File Version: " + FILE_VERSION)) {
                            versionMismatch = false;
                        } else {
                            line = "# File Version: " + FILE_VERSION;
                            changesMade = true;
                        }
                    }
                    newFileContent.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            LOGGER.error("Failed to read existing configuration for version check.", e);
        }

        for (Map.Entry<String, ConfigEntry<?>> entry : configs.entrySet()) {
            if (!newFileContent.toString().contains(entry.getKey() + "=")) {
                ConfigEntry<?> configEntry = entry.getValue();
                String key = entry.getKey();
                String value = configEntry.getValue().toString();
                String comment = configEntry.getComment();

                if (comment != null && !comment.isEmpty()) {
                    newFileContent.append("\n# ").append(comment).append(" Default value: ").append(value);
                }
                newFileContent.append("\n").append(key).append("=").append(value).append("\n");
                changesMade = true;
            }
        }

        if (changesMade) {
            try (FileWriter writer = new FileWriter(configFile, false)) {
                writer.write(newFileContent.toString());
                LOGGER.info("Configuration file updated with missing entries and/or version update.");
            } catch (IOException e) {
                LOGGER.error("Failed to update configuration file.", e);
            }
        } else {
            LOGGER.info("Configuration file up-to-date. No changes made.");
        }
    }



    @SuppressWarnings("unchecked")
    private <T> void updateConfigEntry(String key, ConfigEntry<T> configEntry, String value) {
        T convertedValue = (T) convertStringToType(key, value, configEntry.getValue().getClass(), configEntry);
        configEntry.setValue(convertedValue);
    }

    private <T> Object convertStringToType(String key, String value, Class<?> type, ConfigEntry<T> configEntry) {
        try {
            if (Boolean.class.isAssignableFrom(type)) {
                return type.cast(Boolean.parseBoolean(value));
            }
            if (Integer.class.isAssignableFrom(type)) {
                return type.cast(Integer.parseInt(value));
            }
            if (Float.class.isAssignableFrom(type)) {
                return type.cast(Float.parseFloat(value));
            }
            if (Double.class.isAssignableFrom(type)) {
                return type.cast(Double.parseDouble(value));
            }
        } catch (Exception e) {
            LOGGER.warn("Error converting config value for key '" + key + "' with value '" + value + "'. Please check your backported_wolves.properties file.");
            return configEntry.getDefaultValue();
        }
        throw new IllegalArgumentException("Unsupported type for configuration key '" + key + "': " + type);
    }

    public void createDefaultConfig() {
        try (FileWriter writer = new FileWriter(configFile)) {
            writer.write("# File Version: " + BackportedWolvesConfig.FILE_VERSION + "\n\n");

            for (Map.Entry<String, ConfigEntry<?>> entry : configs.entrySet()) {
                String key = entry.getKey();
                ConfigEntry<?> configEntry = entry.getValue();
                String value = configEntry.getValue().toString();
                String comment = configEntry.getComment();

                if (comment != null && !comment.isEmpty()) {
                    writer.write("# " + comment + " Default value: " + value + "\n");
                }
                writer.write(key + "=" + value + "\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <T> void resetConfigEntryValue(ConfigEntry<T> configEntry) {
        configEntry.setValue(configEntry.getDefaultValue());
    }

    public void resetConfig() {
        for (Map.Entry<String, ConfigEntry<?>> entry : configs.entrySet()) {
            resetConfigEntryValue(entry.getValue());
        }
        createDefaultConfig();
        LOGGER.info("Configuration reset to default values.");
    }

    public boolean getBoolean(String key) {
        return (boolean) configs.get(key).getValue();
    }

    public int getInteger(String key) {
        return (int) configs.get(key).getValue();
    }

    public float getFloat(String key) {
        return (float) configs.get(key).getValue();
    }

    public double getDouble(String key) {
        return (double) configs.get(key).getValue();
    }

    private static class ConfigEntry<T> {
        private T value;
        private final T defaultValue;
        private final String comment;

        public ConfigEntry(T defaultValue, String comment) {
            this.defaultValue = defaultValue;
            this.comment = comment;
            this.value = defaultValue;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public T getDefaultValue() {
            return defaultValue;
        }

        public String getComment() {
            return comment;
        }
    }
}
