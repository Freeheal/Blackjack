package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
	private SpriteBatch batch;
	private Texture image;
	private Texture box;
	private BitmapFont font;
    private Blackjack b1;

    public FirstScreen(Blackjack b1){
        this.b1 = b1;
    }

    @Override
    public void show() {
    	batch = new SpriteBatch();
    	image = new Texture("hintergrund_chile.jpg");
        Pixmap pixmap  = new Pixmap(100,100,Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();

        box = new Texture(pixmap);
        pixmap.dispose();

        font = new BitmapFont();
    }


    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //batch.begin();
        //batch.draw(image, 0 ,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //batch.end();
        batch.begin();

        var width = Gdx.graphics.getWidth();
        var height = Gdx.graphics.getHeight();

        float boxX = 0;
        float boxY = 0;
        float boxWidth = width;
        float boxHeight = height;
        float padding = 20;

        batch.draw(box, boxX, boxY,width , height);
        font.draw(
                batch,
                createOutput(),
                boxX + padding,                       // X position + padding
                boxY + boxHeight - padding,           // Y position (starts from top of the box)
                boxWidth - (padding * 2),             // Target width for wrapping
                Align.left,                           // Alignment
                true                                  // Target wrap enabled
                );

        batch.end();
    }

    String createOutput(){
        int einsatz = b1.getEinsatz();
        int streak  = b1.getPlayer().getStreak();
        int geld = b1.getPlayer().getGeld();
        String name = b1.getPlayer().getName();
        String state = b1.getWinState().toString();
         var dealerhand = b1.dealerHand;
         String dealerHand = "DEALER:|";
         for(var item:dealerhand) {
                dealerHand+=item + "|";
         }
         var playerhand = b1.playerHand;
         String playerHand = "PLAYER:|";
         for(var item:playerhand) {
                playerHand+=item + "|";
         }

         dealerHand += b1.getWert(b1.dealerHand);
         playerHand += b1.getWert(b1.playerHand);

        return "NAME: " + name+ "  STATE: " +  state  + " GELD: " + geld +" STREAK: " +streak +" EINSATZ: " + einsatz +  "\n" + dealerHand + "\n" + playerHand;
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
}
