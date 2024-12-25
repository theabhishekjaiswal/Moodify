package com.abhi.apitesting;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TextView responseTextView;
    EditText userinput;
    AppCompatButton sendButton, redir;
    private RecyclerView moodRecyclerView;
    private MoodAdapter moodAdapter;
    private List<String> moodList; // Mood names list
    public static Map<String, String[]> moodMap; // Map for mood arrays
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI Elements
        userinput = findViewById(R.id.userInput);
        responseTextView = findViewById(R.id.responseTextView);
        redir = findViewById(R.id.redir);
        sendButton = findViewById(R.id.sendButton);
        progressBar = findViewById(R.id.pg);

        // Initialize moodList
        moodList = Arrays.asList(
                "Happy", "Sad", "Angry", "Fearful", "Loving",
                "Peaceful", "Energetic", "Bored", "Confused",
                "Hopeful", "Breakup", "Motivation"
        );

        // Initialize moodMap
        moodMap = new HashMap<>();
        moodMap.put("happy", new String[]{"Cc_cNEjAh_Y","6vKucgAeF_Q","smn3mDBOUy4","1GWyCJHuNms","3PqxT1VqyNc","t8F2WpKN3CU","4dsFQFCvVGU","caoGNx1LF2Q","kZqH9Kfv6BI","II2EO3Nw4m0","1Na8nKEUjYI","VzLG6OqOcn8","DmsOinqrPvQ","mxva6l4bCSI","gwa2Os2LbRA", "m_wLOr_qOH8", "SS3lIQdKP-A"});
        moodMap.put("sad", new String[]{"YVH2jonmu8g","U09XjCVlMp0","J7ck984Qhso","UlacMvx_VYk","fVeJ6sJERR4","lN1m7zLBbSU", "Q0tBgPdd_II", "-lDmXk8pBNI", "iCZfjggJg3M", "QKfGl39ZJWI", "f3FFOBrMmdg","0NFxcNheoLc", "Gh5wHtqW9Ek", "mVu6kdY3KIk", "TaQbAwPHUzE", "tLqtnGLfm4Q",
                "xo8JU-Vc1C0", "2IGDsD-dLF8", "oTnF5sNJnbE", "NV_XDwH606c", "gTfC1AZ6Mqo", "ZeJFzBmkxZg", "sg5iiZoD3I0", "kYHjzHnOsr0", "CSp6TC0gUdc", "OiR4pUOXQC0", "Hp4vbW1ZF0U", "o7In7se2Kl4", "ay-FD96c9-U","FXrjXa6eBc8"});
        moodMap.put("angry", new String[]{"zqGW6x_5N0k","VAJK04HOLd0","VOLKJJvfAbg","rnCQ-mKKHkU", "Gl5wBrNuxx4", "jFGKJBPFdUA","xutBFUf3LoU",  "zxlR20V4NFQ", "NbyHNASFi6U", "PqFMFVcCZgI", "uaCYeQ9FtSI", "x8F5dz8kv1w" });
        moodMap.put("fearful", new String[]{"CKpbdCciELk", "C805Nt0JPIY", "ldWFpeu36K0", "W-f8SY990ck", "jiG61g20JTY", "rnCQ-mKKHkU", "SY37qwaWajM", "arpFDGrq0dI","DejQSwtoUuo","hLelm6WGAYU", "cR9FSU29qqc"});
        moodMap.put("loving", new String[]{"qpIdoaaPa6U", "rTuxUAuJRyY","2vKMY75kvjI","cBGDDBHN22U", "ixCnsZswdpU", "gvyUuxdRdR4", "aiRDQlN_afw", "HexFqifusOk", "FGTv9-oQhIg"});
        moodMap.put("peaceful", new String[]{"rTuxUAuJRyY","1sRaLqtHXQU", "oWKgpB2zpgw", "yRrU0zCUVJg", "GxldQ9eX2wo", "kPa7bsKwL-c"});
        moodMap.put("energetic", new String[]{"VzLG6OqOcn8","x5fYTPvrz4g", "Q3JMD4oaXlI", "drUB2rn_Et8", "Wj8C_bpnkTY", "RCgbE6eS-DU", "gwa2Os2LbRA", "OljkSVLIt6c", "x8F5dz8kv1w", "8LZgzAZ2lpQ", "asYxxtiWUyw","xvYBg6MWPbM","AYmy75tnmTU", "PIyf0hMc498", "EEX_XM6SxmY", "FQS7i2z1CoA", "y3jVRNi-V5M"});
        moodMap.put("bored", new String[]{"cnGW8NR-toE", "Q2WcdaF8uL8","YR12Z8f1Dh8", "TWfzLpR3Q_E", "HW4EsVuAhRE", "SbRPW1UO244", "_Q5LH2DLd_8", "x8F5dz8kv1w", "OljkSVLIt6c"});
        moodMap.put("confused", new String[]{"gJLVTKhTnog", "Vx7YkKpt-J4",
                "_Q5LH2DLd_8", "FGTv9-oQhIg", "lTvrdaYBrXk", "DK_UsATwoxI", "D7w-v0zbV10", "vFiXnSXr3kU", "rFU28HBP7B0", "cYOB941gyXI", "BNfAf4To73c", "8v-TWxPWIWc", "p3HR9QDMj18", "9vkcYxbGdTE"});
        moodMap.put("hopeful", new String[]{"wT3RhIJZu4k","_-uhyTJC8SA","UBBHpoW3AKA",
                "psWV9GdEgzo", "gwa2Os2LbRA", "nCD2hj6zJEc", "4KFVySixs_E", "R_ha0AMNUn0", "AYmy75tnmTU", "FQS7i2z1CoA"});
        moodMap.put("breakup", new String[]{"npwn6KVMtFI","VOLKJJvfAbg","92QARS83DeY","vrqFJ-yjkRw", "VOLKJJvfAbg", "VMEXKJbsUmE","YVH2jonmu8g","U09XjCVlMp0","J7ck984Qhso","UlacMvx_VYk","fVeJ6sJERR4","lN1m7zLBbSU", "Q0tBgPdd_II", "-lDmXk8pBNI", "iCZfjggJg3M", "QKfGl39ZJWI", "f3FFOBrMmdg","0NFxcNheoLc", "Gh5wHtqW9Ek", "mVu6kdY3KIk", "TaQbAwPHUzE", "tLqtnGLfm4Q",
                "xo8JU-Vc1C0", "2IGDsD-dLF8", "oTnF5sNJnbE", "NV_XDwH606c", "gTfC1AZ6Mqo", "ZeJFzBmkxZg", "sg5iiZoD3I0", "kYHjzHnOsr0", "CSp6TC0gUdc", "OiR4pUOXQC0", "Hp4vbW1ZF0U", "o7In7se2Kl4", "ay-FD96c9-U","FXrjXa6eBc8"});
        moodMap.put("motivation", new String[]{"n4QK52sO720","9iIX4PBplAY","IjBAgWKW12Y","abiL84EAWSY","kd-6aw99DpA","s_-tthrE0Hg",
                "mOEL8Q-2bSo", "RCgbE6eS-DU", "vrqFJ-yjkRw", "bnqLzCsffwY", "UxLSZoFK8EM", "Yd5UIjbAEBk", "GLK7flBXqsk", "1JPlmLKsrB4", "DmsOinqrPvQ", "mxva6l4bCSI", "jFGKJBPFdUA", "zLVZxHWL0ro"});

        // RecyclerView Setup
        moodRecyclerView = findViewById(R.id.moodRecyclerView);
        moodAdapter = new MoodAdapter(this, moodList, moodMap); // Pass the moodMap to adapter
        moodRecyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // Grid with 2 columns
        moodRecyclerView.setAdapter(moodAdapter);

        // Redirect Button Click
        redir.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VideoPlayerActivity.class);
            intent.putExtra("videoId", "FRdcZKWjJPc");
            startActivity(intent);
        });

        // Send Button Click
        sendButton.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            String mood = userinput.getText().toString();
            String prompt = "Analyze the given mood: " + mood + ". Based on the analysis, return only one word that best represents the mood from the following options: happy, sad, angry, fearful, loving, peaceful, energetic, bored, confused, hopeful, breakup, motivation.";
            try {
                sendPromptToChatGPT(prompt);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Send prompt to ChatGPT
    private void sendPromptToChatGPT(String prompt) throws JSONException {
        ChatGPTService.getChatGPTResponse(prompt, new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    responseTextView.setText("Error: " + e.getMessage());
                });
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String jsonData = response.body().string();
                        String reply = new org.json.JSONObject(jsonData)
                                .getJSONArray("choices")
                                .getJSONObject(0)
                                .getJSONObject("message")
                                .getString("content")
                                .trim();

                        runOnUiThread(() -> {
                            responseTextView.setText(reply);
                            String videoId = selectRandomVideo(moodMap.get(reply.toLowerCase()));
                            if (videoId != null) {
                                Intent intent = new Intent(MainActivity.this, VideoPlayerActivity.class);
                                intent.putExtra("videoId", videoId);
                                intent.putExtra("mood", reply);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "No valid song found. Please retry", Toast.LENGTH_LONG).show();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error: Unable to fetch response.", Toast.LENGTH_LONG).show();
                }
                runOnUiThread(() ->{
                    progressBar.setVisibility(View.GONE);
                    userinput.setText("");
                });
            }
        });
    }

    // Select a random video from the mood array
    public static  String selectRandomVideo(String[] videos) {
        if (videos == null || videos.length == 0) {
            return null;
        }
        Random random = new Random();
        return videos[random.nextInt(videos.length)];
    }

    static String[] getMoodArray(String mood) {
        if (moodMap.containsKey(mood)) {
            return moodMap.get(mood); // Fetch the array from the map
        }
        return new String[]{}; // Return an empty array if the mood is not found
    }
}
