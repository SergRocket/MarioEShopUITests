package test.data;

import utils.APIClient;

public class TestRailAPISetup {

    private static APIClient testRailApiClient;
    private static APIClient createClientInstance() {
        testRailApiClient = new APIClient("https://sergqaajun.testrail.io/");
        testRailApiClient.setUser("serg.lishko1988@gmail.com");
        testRailApiClient.setPassword("fg78N7RS");
        return testRailApiClient;
    }

    public static APIClient getClientInstance() {
        if(testRailApiClient == null)
            createClientInstance();

        return testRailApiClient;
    }
}
