package hooks;

import base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BaseClass {

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("\n--- Starting Scenario: " + scenario.getName() + " ---");
        setupRequest();
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("--- Finished Scenario: " + scenario.getName() + " ---\n");
    }
}