package com.example.demo;

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
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winningposition={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activeplayer=0;
    boolean gameActive=true;
public void dropin(View view){
    ImageView counter=(ImageView)view;
    counter.getTag();
    int tappedCounter=Integer.parseInt(counter.getTag().toString());
    if(gamestate[tappedCounter]==2 && gameActive) {
        gamestate[tappedCounter] = activeplayer;
        counter.setTranslationY(-1500);
        if (activeplayer == 0) {

            counter.setImageResource(R.drawable.yellow);
            activeplayer = 1;
        } else {
            counter.setImageResource(R.drawable.red);
            activeplayer = 0;
        }
        counter.animate().translationYBy(1500).rotation(60).setDuration(300);
        for (int[] winningposition : winningposition) {
            if (gamestate[winningposition[0]] == gamestate[winningposition[1]] && gamestate[winningposition[1]] == gamestate[winningposition[2]]
                    && gamestate[winningposition[0]] != 2) {
                gameActive=false;
                String winner="";
                if (activeplayer == 1)
                    winner="yellow";
                else
                    winner="red";
                //Toast.makeText(this, winner+"won!", Toast.LENGTH_SHORT).show();
                Button playAgainButton=findViewById(R.id.PlayAgainButton);
                TextView winnerTextView=findViewById(R.id.textView);
                winnerTextView.setText(winner+"won!");
                playAgainButton.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);
            }

        }

    }
}
public void playAgain(View view){
    Button playAgainButton=findViewById(R.id.PlayAgainButton);
    TextView winnerTextView=findViewById(R.id.textView);
    playAgainButton.setVisibility(View.INVISIBLE);
    winnerTextView.setVisibility(View.INVISIBLE);
    GridLayout gridLayout=findViewById(R.id.gridlayout);
    for(int i=0;i<gridLayout.getChildCount();i++)
    {
        ImageView counter=(ImageView) gridLayout.getChildAt(i);
        counter.setImageDrawable(null);
    }
    for(int i=0;i<gamestate.length;i++)
     gamestate[i]=2;
     activeplayer=0;
     gameActive=true;
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
