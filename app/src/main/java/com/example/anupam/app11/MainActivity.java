package com.example.anupam.app11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {
    private static final SecureRandom secureRandomNumbers = new SecureRandom();

    private enum Status {NOTSTARTEDYET, PROCEED, WON, LOST};
    private static final int TIGER_CLAWS = 2;
    private static final int tree=3;
    private static final int CEVEN=7;
    private static final int WE_LEVEN=11;
    private static final int PANTHER=12;

    TextView txtCalculations;
    ImageView imgDice;
    Button btnRestartThegame;
    String oldCalculationsValue="";
    boolean firstTime;
    Status gameStatus = Status.NOTSTARTEDYET;
    int points;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        txtCalculations=(TextView) findViewById(R.id.txtCalculations);
            imgDice=(ImageView) findViewById(R.id.imgDice);
            btnRestartThegame=(Button) findViewById(R.id.btnRestartTheGame);
            final TextView txtGameStatus=(TextView) findViewById(R.id.txtGameStatus);

            makeBtnRestartInvisible();
            txtGameStatus.setText("");
            txtCalculations.setText("");

            imgDice.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    if(gameStatus == Status.NOTSTARTEDYET)
                    {
                        int diceSum=letsRollTheDice();
                        oldCalculationsValue=txtCalculations.getText().toString();
                        points=0;
                        switch (diceSum)
                        {
                            case CEVEN:
                            case WE_LEVEN:
                                gameStatus=Status.WON;
                                txtGameStatus.setText("You win");
                                makeImgDiceInvisible();
                                makeBtnRestartVisible();
                                break;

                            case TIGER_CLAWS:
                            case tree:
                            case PANTHER:
                                gameStatus=Status.LOST;
                                txtGameStatus.setText("You lost ! ");
                                makeImgDiceInvisible();
                                makeBtnRestartVisible();
                                break;
                            case 4:
                            case 5:
                            case 6:
                            case 8:
                            case 9:
                            case 10:
                                gameStatus=Status.PROCEED;
                                points=diceSum;
                                txtCalculations.setText(oldCalculationsValue + "Your point is : "+points+ " \n");
                                oldCalculationsValue="Your point is : "+points+"\n";
                                txtGameStatus.setText("Continue the game");
                                break;
                        }
                        return;
                    }
                    if(gameStatus==Status.PROCEED)
                    {
                        int diceSum=letsRollTheDice();
                        if(diceSum==points)
                        {
                            gameStatus=Status.WON;
                            txtGameStatus.setText("You win");
                            makeImgDiceInvisible();
                            makeBtnRestartVisible();
                        }
                        else if(diceSum==CEVEN)
                        {
                            gameStatus=Status.LOST;
                            txtGameStatus.setText("You lost ! ");
                            makeImgDiceInvisible();
                            makeBtnRestartVisible();
                        }
                    }
                }
            });

             btnRestartThegame.setOnClickListener(new View.OnClickListener()
             {
                 @Override
                 public void onClick(View view) {
                     gameStatus = Status.NOTSTARTEDYET;
                     txtGameStatus.setText("");
                     txtCalculations.setText("");
                     oldCalculationsValue="";
                     makeImgDiceVisible();
                     makeBtnRestartInvisible();
                 }
             });
    }
    private void makeImgDiceInvisible()
    {
        imgDice.setVisibility(View.INVISIBLE);
    }
    private void makeBtnRestartInvisible()
    {
        btnRestartThegame.setVisibility(View.INVISIBLE);
    }
    private void makeImgDiceVisible()
    {
        imgDice.setVisibility(View.VISIBLE);
    }
    private void makeBtnRestartVisible()
    {
        btnRestartThegame.setVisibility(View.VISIBLE);
    }
    private int letsRollTheDice()
    {
        int randDice1 = 1+secureRandomNumbers.nextInt(6);
        int randDice2 = 1+secureRandomNumbers.nextInt(6);
        int sum=randDice1+randDice2;
        txtCalculations.setText(String.format(oldCalculationsValue+"You rolled %d+%d=%d %n",randDice1,randDice2,sum));
        return sum;
    }
}
