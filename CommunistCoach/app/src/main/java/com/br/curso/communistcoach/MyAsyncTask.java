package com.br.curso.communistcoach;

import android.os.AsyncTask;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAsyncTask extends AsyncTask<Void, Void, Quote> {
    public static final String URL = "https://api.api-ninjas.com/v1/quotes";
    private TextView newPhrase;
    private TextView author;

    public MyAsyncTask(TextView newPhrase, TextView author) {
        this.newPhrase = newPhrase;
        this.author = author;
    }

    @Override
    protected Quote doInBackground(Void... voids) {
        try {
            String content = getQuote();
            return mapContent(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Quote quote) {
        super.onPostExecute(quote);
        if (quote != null) {
            newPhrase.setText(quote.getQuote());
            author.setText(quote.getAuthor());
        }
    }

    private String getQuote() throws IOException {
        URL url = new URL("https://api.api-ninjas.com/v1/quotes?category=history");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        String apiKey = "";
        setHeaders(connection, apiKey);
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        StringBuilder content = readResponse(connection);
        System.out.println("Response: " + content);

        connection.disconnect();
        return content.toString();
    }

    private void setHeaders(HttpURLConnection connection, String apiKey) throws ProtocolException {
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Api-Key", apiKey);
        connection.setRequestProperty("accept", "application/json");
    }

    @NonNull
    private StringBuilder readResponse(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content;
    }

    private Quote mapContent(String content) throws IOException {
        return new ObjectMapper().readValue(content, Quote[].class)[0];
    }

}
