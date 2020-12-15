package com.example.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 = Kuning 1 = red 2 = kosong
    int activePlayer =0; //yellow

    int[] state = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] win = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameActive = true;

    public void counterClick(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (state[tappedCounter] == 2 && gameActive) {

            state[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotationY(3600).rotationX(3600).setDuration(700);

            for (int[] win : win) {
                if (state[win[0]] == state[win[1]] && state[win[1]] == state[win[2]] && state[win[0]] != 2) {

                    gameActive = false;

                    String winner = "";

                    if (activePlayer == 1) {
                        winner = "Kuning";
                    } else {
                        winner = "Merah";
                    }

                    Toast.makeText(this, winner + " Menang!", Toast.LENGTH_SHORT).show();

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerStatus = (TextView) findViewById(R.id.winnerStatus);
                    winnerStatus.setText(winner + " Menang!");

                    playAgainButton.setVisibility((view.VISIBLE));
                    winnerStatus.setVisibility((view.VISIBLE));
                }
            }
        }
    }

    public void playAgain(View view) {

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerStatus = (TextView) findViewById(R.id.winnerStatus);

        playAgainButton.setVisibility((view.INVISIBLE));
        winnerStatus.setVisibility((view.INVISIBLE));

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);

        }

        for (int i=0; i<state.length; i++){
            state[i] = 2;
        }
        activePlayer =0; //yellow

        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}