package edu.wit.comp1050;

import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;


public class SettingsMain {

    private static SettingsMain instance;
    private FileBasedConfiguration configuration;

    public static void main(String... args) {
        System.out.println(SettingsMain.getInstance().getProperty("timer.time"));
        System.out.println(SettingsMain.getInstance().getProperty("show.score"));

    }

    private SettingsMain() {
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName("settings.properties"));
        try {
            configuration = builder.getConfiguration();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static synchronized SettingsMain getInstance() {
        if (instance == null) {
            instance = new SettingsMain();
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) configuration.getProperty(key);
    }

}
