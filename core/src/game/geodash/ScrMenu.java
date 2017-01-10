package game.geodash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import box2dLight.RayHandler;

/**
 * Created by hafiz on 1/10/2017.
 */

public class ScrMenu implements Screen {
    private GamGeoDash game;
    private SpriteBatch batch;
    private RayHandler rayHandler;
    private boolean bLightUp = true;
    private float fAmbience;

    public ScrMenu (GamGeoDash game) {
        this.game = game;
        rayHandler = game.rayHandler;
        batch = game.batch;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (bLightUp) {
            fAmbience += 0.001;
            if (fAmbience >= 1) {
                bLightUp = false;
            }
        }

        rayHandler.setAmbientLight(fAmbience);
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
