package seedu.intern;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.intern.commons.core.Config;
import seedu.intern.commons.core.LogsCenter;
import seedu.intern.commons.core.Version;
import seedu.intern.commons.exceptions.DataLoadingException;
import seedu.intern.commons.util.ConfigUtil;
import seedu.intern.commons.util.StringUtil;
import seedu.intern.logic.Logic;
import seedu.intern.logic.LogicManager;
import seedu.intern.model.InternTracker;
import seedu.intern.model.Model;
import seedu.intern.model.ModelManager;
import seedu.intern.model.ReadOnlyInternTracker;
import seedu.intern.model.ReadOnlyUserPrefs;
import seedu.intern.model.UserPrefs;
import seedu.intern.model.util.SampleDataUtil;
import seedu.intern.storage.InternTrackerStorage;
import seedu.intern.storage.JsonInternTrackerStorage;
import seedu.intern.storage.JsonUserPrefsStorage;
import seedu.intern.storage.Storage;
import seedu.intern.storage.StorageManager;
import seedu.intern.storage.UserPrefsStorage;
import seedu.intern.ui.Ui;
import seedu.intern.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 2, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing LetsGetHired ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        initLogging(config);

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        InternTrackerStorage dataStorage = new JsonInternTrackerStorage(userPrefs.getInternTrackerFilePath());
        storage = new StorageManager(dataStorage, userPrefsStorage);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s intern tracker and {@code userPrefs}. <br>
     * The data from the sample intern tracker will be used instead if {@code storage}'s intern tracker is not found,
     * or an empty intern tracker will be used instead if errors occur when reading {@code storage}'s intern tracker.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        logger.info("Using data file : " + storage.getInternTrackerFilePath());

        Optional<ReadOnlyInternTracker> dataOptional;
        ReadOnlyInternTracker initialData;
        try {
            dataOptional = storage.readInternTracker();
            if (!dataOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getInternTrackerFilePath()
                        + " populated with a sample InternTracker.");
            }
            initialData = dataOptional.orElseGet(SampleDataUtil::getSampleInternTracker);
        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getInternTrackerFilePath() + " could not be loaded."
                    + " Will be starting with an empty InternTracker.");
            initialData = new InternTracker();
        }

        return new ModelManager(initialData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            if (!configOptional.isPresent()) {
                logger.info("Creating new config file " + configFilePathUsed);
            }
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataLoadingException e) {
            logger.warning("Config file at " + configFilePathUsed + " could not be loaded."
                    + " Using default config properties.");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using preference file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            if (!prefsOptional.isPresent()) {
                logger.info("Creating new preference file " + prefsFilePath);
            }
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataLoadingException e) {
            logger.warning("Preference file at " + prefsFilePath + " could not be loaded."
                    + " Using default preferences.");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting LetsGetHired " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Intern Tracker ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
