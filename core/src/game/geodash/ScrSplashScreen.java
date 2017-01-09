package game.geodash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import box2dLight.RayHandler;

/**
 * Created by hafiz on 1/9/2017.
 */

public class ScrSplashScreen implements Screen {
    boolean bComplete = false;
    private SpriteBatch batch;
    private RayHandler rayHandler;
    private float fAmbience;
    private GamGeoDash game;

    public ScrSplashScreen(GamGeoDash game) {
        this.game = game;
        this.batch = game.batch;
        this.rayHandler = game.rayHandler;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (fAmbience <= 1) {
            rayHandler.setAmbientLight(fAmbience);
            fAmbience += 0.01;
        } else {
            batch.begin();

            batch.end();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
