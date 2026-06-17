package io.github.some_example_name;

import static io.github.some_example_name.Blackjack.WinStates.*;


import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {


    private Blackjack b1;
    @Override
    public void create() {
        b1 = new Blackjack();
        //b1.newRound();
        b1.setEinsatz(100);
        setScreen(new FirstScreen(b1));
    }
    @Override
    public void render() {
        super.render();
        if(b1.getWinState() == Playing) {
            if (Gdx.input.isKeyJustPressed(Keys.H)) { b1.hit(b1.playerHand); }
            if (Gdx.input.isKeyJustPressed(Keys.D)) { b1.verdoppeln(b1.playerHand); }
            if (Gdx.input.isKeyJustPressed(Keys.S)) { b1.stand(); }
        }else {
            if (Gdx.input.isKeyJustPressed(Keys.N)) {
                b1.endRound();
                b1.newRound();
            }
        }
    }
}
