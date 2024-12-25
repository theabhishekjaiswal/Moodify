package com.abhi.apitesting;

import okhttp3.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChatGPTService {
    private static final String API_KEY = "dusre ka api key dekhna galat baat hai...sudhar jao";

    private static final String BASE_URL = "https://api.openai.com/v1/chat/completions";

    public static void getChatGPTResponse(String prompt, Callback callback) throws JSONException {
        OkHttpClient client = new OkHttpClient();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", "gpt-3.5-turbo"); // use the correct model name as per OpenAI's documentation
        jsonObject.put("messages", new JSONArray().put(new JSONObject()
                .put("role", "user")
                .put("content", prompt)));

        RequestBody body = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        client.newCall(request).enqueue(callback); // Asynchronous request
    }
}
