package com.mindgames;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataManager {
    private static final String USER_DATA_FILE = "src/main/resources/UserData.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type USER_LIST_TYPE = new TypeToken<List<User>>() {}.getType();

    public static void saveUser(User user) {
        List<User> users = loadUsers();
        users.removeIf(existingUser -> existingUser.getUsername().equals(user.getUsername()));
        users.add(user);

        try (Writer writer = Files.newBufferedWriter(Paths.get(USER_DATA_FILE))) {
            GSON.toJson(users, writer);
            System.out.println("User data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving user data: " + e.getMessage());
        }
    }

    public static List<User> loadUsers() {
        if (!Files.exists(Paths.get(USER_DATA_FILE))) {
            return new ArrayList<>();
        }

        try (Reader reader = Files.newBufferedReader(Paths.get(USER_DATA_FILE))) {
            return GSON.fromJson(reader, USER_LIST_TYPE);
        } catch (JsonSyntaxException e) {
            System.err.println("Error: JSON file is not in the expected format. Resetting to an empty list.");
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading user data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static User loadUser(String username) {
        return loadUsers().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}