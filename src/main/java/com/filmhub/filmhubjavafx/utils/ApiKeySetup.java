package com.filmhub.filmhubjavafx.utils;

import java.io.IOException;
import java.util.Scanner;

public class ApiKeySetup
{
    public static void setupApiKey()
    {
        try
        {
            String apiKey;

            System.out.println("No API-Key found. Please enter your API-KEY:");
            Scanner scanner = new Scanner(System.in);
            apiKey = scanner.nextLine();
            ApiKeyManager.saveApiKey(apiKey);
            System.out.println("API-Key saved successfully.");
        } catch (IOException e)
        {
            System.err.println("Error while saving the API-Key: " + e.getMessage());
        }
    }
}
