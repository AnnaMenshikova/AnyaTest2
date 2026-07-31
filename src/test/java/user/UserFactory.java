package user;

import utils.PropertyReader;

public class UserFactory {
    public static User withAdminPermission() {
        return new User(
                PropertyReader.getProperty("saucedemo.user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withLockedAdminPermission() {
        return new User(
                PropertyReader.getProperty("saucedemo.locked_user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withProblemAdminPermission() {
        return new User(
                PropertyReader.getProperty("saucedemo.problem_user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withGlitchAdminPermission() {
        return new User(
                PropertyReader.getProperty("saucedemo.performance_glitch_user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withErrorAdminPermission() {
        return new User(
                PropertyReader.getProperty("saucedemo.error_user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withVisualAdminPermission() {
        return new User(
                PropertyReader.getProperty("saucedemo.visual_user"),
                PropertyReader.getProperty("saucedemo.password"));
    }
}
