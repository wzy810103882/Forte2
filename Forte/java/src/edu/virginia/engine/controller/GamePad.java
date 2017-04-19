package edu.virginia.engine.controller;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;

import java.util.HashMap;

/**
 * Wrapper class for Controllers (variable names chosen to reflect ps3 controllers)
 */
public class GamePad {

    /* Constants defining the various buttons on a typical controller */
    /* Might be necessary to change the string bindings depending on your particular gamepad configuration */
    public static final String BUTTON_CROSS = "0"; //X on ps3
    public static final String BUTTON_CIRCLE = "1"; //Cross on ps3
    public static final String BUTTON_SQUARE = "2"; //Square on ps3
    public static final String BUTTON_TRIANGLE = "3"; //Triangle on ps3
    public static final String BUTTON_L1 = "4"; //L1 hammer on ps3
    public static final String BUTTON_R1 = "5"; //R1 hammer on ps3
    public static final String BUTTON_L3 = "8";
    public static final String BUTTON_R3 = "9";
    public static final String BUTTON_SELECT = "6";
    public static final String BUTTON_START = "7";
    public static final String DPAD_UP = "DPAD_UP"; //X on ps3
    public static final String DPAD_RIGHT = "DPAD_RIGHT"; //X on ps3
    public static final String DPAD_LEFT = "DPAD_LEFT"; //X on ps3
    public static final String DPAD_DOWN = "DPAD_DOWN"; //X on ps3
    private final double threshold = 0.0001;
    /* The controller that was detected */
    private Controller controller;
    /* Stores id to GamePadComponent pairs */
    private HashMap<String, GamePadComponent> components;

    public GamePad(Controller controller) {
        if (controller == null)
            System.out.println("WARNING in GamePad.java [Constructor]: Trying to initialize a GamePad with a NULL controller");

        this.controller = controller;
        this.components = new HashMap<String, GamePadComponent>();
    }

    /**
     * Use the constants at the top of this class as the parameter to this method
     */
    public boolean isButtonPressed(String buttonId) {
        if (this.components.containsKey(buttonId))
            return this.components.get(buttonId).getData() != 0.0;
        return false;
    }

    /**
     * Use the constants at the top of this class as the parameter to this method
     */
    public boolean isDPadPressed(String id) {
        if (this.components.containsKey("pov")) {
            double data = this.components.get("pov").getData();
            if (id.equals(GamePad.DPAD_UP)) return (data > 0.0 && data < 0.5);
            if (id.equals(GamePad.DPAD_RIGHT)) return (data > 0.25 && data < 0.75);
            if (id.equals(GamePad.DPAD_DOWN)) return (data > 0.5 && data < 1.0);
            if (id.equals(GamePad.DPAD_LEFT)) return (data > 0.75 || (data < 0.25 && data > 0.0));
        }
        return false;
    }

    public double getLeftStickXAxis() {
        if (this.components.containsKey("x")) {
            double toReturn = this.components.get("x").getData();
            if (Math.abs(toReturn) > threshold) return toReturn;
        }
        return 0.0;
    }

    public double getLeftStickYAxis() {
        if (this.components.containsKey("y")) {
            double toReturn = this.components.get("y").getData();
            if (Math.abs(toReturn) > threshold) return toReturn;
        }
        return 0.0;
    }

    public double getRightStickXAxis() {
        if (this.components.containsKey("rx")) {
            double toReturn = this.components.get("rx").getData();
            if (Math.abs(toReturn) > threshold) return toReturn;
        }
        return 0.0;
    }

    public double getRightStickYAxis() {
        if (this.components.containsKey("ry")) {
            double toReturn = this.components.get("ry").getData();
            if (Math.abs(toReturn) > threshold) return toReturn;
        }
        return 0.0;
    }

    public void update() {
        controller.poll();
		/* Go trough all components of the controller. */
        Component[] components = controller.getComponents();
        for (int i = 0; i < components.length; i++) {
            Component component = components[i];
            Identifier id = component.getIdentifier();
            this.components.put(id.getName(), new GamePadComponent(component));
        }
    }

    /**
     * DIAGNOSTIC METHOD. Invoke this if you want to see all of the buttons on your controller
     * That are being detected and checked every frame. This will print out their name, their id, and
     * the current value for that button.
     * <p>
     * You can use this to figure out what to set the string bindings at the top of this class to
     */
    public void printButtonSummary() {
        controller.poll();
        Component[] components = controller.getComponents();
        for (int i = 0; i < components.length; i++) {
            Component component = components[i];
            System.out.println(component.getName() + ": " + component.getIdentifier().getName() + "; Data: " + component.getPollData());
        }
    }

}
