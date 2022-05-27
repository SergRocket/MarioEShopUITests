package Utils;

import Base.BaseTest;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Reporter {
    private static final Logger LOGGER = LogManager.getLogManager().getLogger(Reporter.class.getName());

    public static void log(String string){
        printMessage(string);
    }

    private static void printMessage(String message){
        BaseTest.getTest().get().info(message);
        LOGGER.info(message);
    }

    public static void logOnFail(String string){
        LOGGER.error(string);
    }

}
