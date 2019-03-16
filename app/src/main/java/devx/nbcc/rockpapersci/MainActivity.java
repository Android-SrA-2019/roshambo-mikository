package devx.nbcc.rockpapersci;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Rochambo game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Rochambo();

    }

    protected void chose(View sender){
        Log.d("CHOSE", "chose");
        ImageButton iv = (ImageButton)sender;
        ImageView player = findViewById(R.id.imgPlayerChoice);
        ImageView opponent = findViewById(R.id.imgComputer);
        if(iv.getId() == R.id.ibtPaper){
            game.playerMakesMove(Rochambo.PAPER);
            player.setImageResource(R.drawable.paper);
        }else if(iv.getId() == R.id.ibtRock){
            game.playerMakesMove(Rochambo.ROCK);
            player.setImageResource(R.drawable.rock);
        }else if(iv.getId() == R.id.ibtScisors){
            game.playerMakesMove(Rochambo.SCISSOR);
            player.setImageResource(R.drawable.scissors);
        }

        switch(game.getGameMove()){
            case Rochambo.ROCK:
                opponent.setImageResource(R.drawable.rock);
                break;
            case Rochambo.PAPER:
                opponent.setImageResource(R.drawable.paper);
                break;
            case Rochambo.SCISSOR:
                opponent.setImageResource(R.drawable.scissors);
                break;
                default:
                    opponent.setImageResource(R.drawable.none);
                    break;
        }
        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(player,
                "rotationX", 0f, 360f)
                .setDuration(500);

        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(opponent,
                "rotationY", 0f, 360f)
                .setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorGame, animatorPlayer);
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.start();
        ((TextView)findViewById(R.id.lblResult)).setText(game.winLoseOrDraw());
    }
}
