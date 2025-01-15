package com.filmhub.filmhubjavafx.utils;

import java.io.*;

public class ApiKeyManager
{
    private static final String API_KEY_FILE = "api_key.ser";

    public static void saveApiKey(String apiKey) throws IOException
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(API_KEY_FILE)))
        {
            oos.writeObject(apiKey);
        }
    }

    public static String loadApiKey() throws IOException, ClassNotFoundException
    {
        File file = new File(API_KEY_FILE);
        if (!file.exists())
        {
            throw new FileNotFoundException("The API-key file was not found: " + API_KEY_FILE);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
        {
            return (String) ois.readObject();
        }
    }
}
