package com.abhi.apitesting;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;
import java.util.Random;
public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodViewHolder> {

    private List<String> moodList;
    private Context context;

    public MoodAdapter(Context context, List<String> moodList, Map<String, String[]> moodMap) {
        this.context = context;
        this.moodList = moodList;
    }

    @NonNull
    @Override
    public MoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.mood_item, parent, false);
        return new MoodViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoodViewHolder holder, int position) {
        String mood = moodList.get(position);

        // Set custom text for the mood
        String moodText = getCustomTextForMood(mood);
        holder.moodText.setText(moodText);

        // Set the drawable image for the mood
        int moodImageResId = getDrawableForMood(mood);
        holder.moodImage.setImageResource(moodImageResId);

        // Set click listener for each mood item
        holder.itemView.setOnClickListener(v -> {
            String[] selectedMoodArray = getMoodArray(mood.toLowerCase());
            String videoId = selectRandomVideo(selectedMoodArray);

            Intent intent = new Intent(context, VideoPlayerActivity.class);
            intent.putExtra("videoId", videoId);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return moodList.size();
    }

    // ViewHolder to hold each mood item
    public class MoodViewHolder extends RecyclerView.ViewHolder {
        TextView moodText;
        ImageView moodImage;

        public MoodViewHolder(View itemView) {
            super(itemView);
            moodText = itemView.findViewById(R.id.moodText);
            moodImage = itemView.findViewById(R.id.moodImage);
        }
    }

    // Helper method to get the custom text for each mood
    private String getCustomTextForMood(String mood) {
        switch (mood.toLowerCase()) {
            case "happy": return "Feeling Joyful";
            case "sad": return "Feeling Blue";
            case "angry": return "Feeling Furious";
            case "fearful": return "Feeling Scared";
            case "loving": return "Feeling Romantic";
            case "peaceful": return "Feeling Calm";
            case "energetic": return "Feeling Active";
            case "bored": return "Feeling Dull";
            case "confused": return "Feeling Lost";
            case "hopeful": return "Feeling Optimistic";
            case "breakup": return "Feeling Heartbroken";
            case "motivation": return "Feeling Inspired";
            default: return "Feeling Undefined";
        }
    }

    // Helper method to get the drawable resource for each mood
    private int getDrawableForMood(String mood) {
        switch (mood.toLowerCase()) {
            case "happy": return R.drawable.happybg;
            case "sad": return R.drawable.sadbg;
            case "angry": return R.drawable.angrybg;
            case "fearful": return R.drawable.fearfulbg;
            case "loving": return R.drawable.lovingbg;
            case "peaceful": return R.drawable.peaceful;
            case "energetic": return R.drawable.energeticbg;
            case "bored": return R.drawable.boredbg;
            case "confused": return R.drawable.confusedbg;
            case "hopeful": return R.drawable.hopefulbg;
            case "breakup": return R.drawable.breakupbg;
            case "motivation": return R.drawable.motivationbg;
            default: return R.drawable.musicbg;
        }
    }

    // Helper method to get corresponding mood array
    private String[] getMoodArray(String mood) {
        // Replace with actual mood arrays from your MainActivity
        return MainActivity.getMoodArray(mood);
    }

    // Helper method to select a random video
    private String selectRandomVideo(String[] moodArray) {
        if (moodArray == null || moodArray.length == 0) {
            return "default_video_id"; // Default video ID
        }
        Random random = new Random();
        int randomIndex = random.nextInt(moodArray.length);
        return moodArray[randomIndex];
    }
}
