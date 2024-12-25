package com.abhi.apitesting;

import static com.abhi.apitesting.MainActivity.moodMap;
import static com.abhi.apitesting.MainActivity.selectRandomVideo;

import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoPlayerActivity extends AppCompatActivity {

    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer youTubePlayer;
    private SeekBar videoSeekBar;
    private boolean isUserSeeking = false; // To handle user interaction with the seek bar
    private boolean isPlaying = false; // To track whether the video is playing or paused

    ImageView music_player_image;
    AppCompatButton watchVideo;
    AppCompatButton refreshVideo;
    ProgressBar progreebaricon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        watchVideo=findViewById(R.id.watchVideo);
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        progreebaricon=findViewById(R.id.progressbar);
        refreshVideo=findViewById(R.id.refreshVideo);
        refreshVideo.setVisibility(View.GONE);

        refreshVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String moodId = getIntent().getStringExtra("mood");
            }
        });


        music_player_image = findViewById(R.id.music_player_image);
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 360,  // Start and end angle
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot X (center)
                Animation.RELATIVE_TO_SELF, 0.5f  // Pivot Y (center)
        );
        rotateAnimation.setDuration(2000); // Duration in milliseconds
        rotateAnimation.setRepeatCount(Animation.INFINITE); // Infinite rotation


        watchVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(youTubePlayerView.getVisibility()==View.VISIBLE){
                    youTubePlayerView.setVisibility(View.GONE);
                    watchVideo.setText("Watch Video ");
                }else{
                    youTubePlayerView.setVisibility(View.VISIBLE);
                    watchVideo.setText("Hide Video ");
                }

            }
        });

        String videoId = getIntent().getStringExtra("videoId");
        //Toast.makeText(this, videoId, Toast.LENGTH_LONG).show();


        videoSeekBar = findViewById(R.id.video_seekbar);
        ImageButton playPauseButton = findViewById(R.id.play_button); // Use single play-pause button
        ImageButton rewindButton = findViewById(R.id.rewind_button); // Rewind by 10 seconds
        ImageButton forwardButton = findViewById(R.id.forward_button); // Forward by 10 seconds

        getLifecycle().addObserver(youTubePlayerView);
        final float[] currentTimeInSeconds = {0f};
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer player) {
                youTubePlayer = player;
                youTubePlayer.loadVideo(videoId, 0);

                // Play/Pause button
                playPauseButton.setOnClickListener(v -> {
                    if (isPlaying) {
                        youTubePlayer.pause();
                    } else {
                        youTubePlayer.play();
                    }
                });
                //rewind button
                rewindButton.setOnClickListener(v -> {
                    float newTime = Math.max(currentTimeInSeconds[0] - 10, 0); // Rewind by 10 seconds
                    youTubePlayer.seekTo(newTime);
                });

                // Forward button
                forwardButton.setOnClickListener(v -> {
                    float newTime = currentTimeInSeconds[0] + 10; // Forward by 10 seconds
                    youTubePlayer.seekTo(newTime);
                });
            }

            @Override
            public void onStateChange(@NonNull YouTubePlayer player, @NonNull PlayerConstants.PlayerState state) {
                ImageButton playPauseButton = findViewById(R.id.play_button);

                if (state == PlayerConstants.PlayerState.PLAYING) {
                    runOnUiThread(()-> progreebaricon.setVisibility(View.GONE));
                    isPlaying = true;
                    playPauseButton.setImageResource(R.drawable.baseline_pause_24); // Pause icon
                    music_player_image.startAnimation(rotateAnimation);
                } else if (state == PlayerConstants.PlayerState.PAUSED || state == PlayerConstants.PlayerState.ENDED) {
                    isPlaying = false;
                    playPauseButton.setImageResource(R.drawable.baseline_play_circle_24); // Play icon
                    music_player_image.clearAnimation();
                }
            }

            @Override
            public void onVideoDuration(@NonNull YouTubePlayer player, float duration) {
                // Set max value for the SeekBar
                videoSeekBar.setMax((int) duration);
            }

            @Override
            public void onCurrentSecond(@NonNull YouTubePlayer player, float second) {
                currentTimeInSeconds[0] = second; // Update current playback time
                if (!isUserSeeking) {
                    // Update SeekBar progress
                    videoSeekBar.setProgress((int) second);
                }
            }
        });

        // SeekBar change listener
        videoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Do nothing here
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isUserSeeking = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isUserSeeking = false;
                if (youTubePlayer != null) {
                    // Seek to the selected position
                    youTubePlayer.seekTo(seekBar.getProgress());
                }
            }
        });
    }
}
