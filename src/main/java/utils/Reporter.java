package utils;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

public class Reporter {
    private static final Logger LOGGER = LogManager.getLogger(Reporter.class.getName());

    public static void log(String string) {
        printMessage(string);
    }

    private static void printMessage(String message) {
        BaseTest.getTest().get().info(message);
        LOGGER.info(message);
    }

    public static void logOnFail(String string) {
        LOGGER.error(string);
    }

}
