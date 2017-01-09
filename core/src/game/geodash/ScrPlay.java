package game.geodash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import box2dLight.RayHandler;

import static game.geodash.GamGeoDash.bPlayerDead;

/**
 * Created by hafiz on 12/13/2016.
 */

public class ScrPlay implements Screen {
    private GamGeoDash game;
    private Map map;
    private Player player;
    private SpriteBatch batch;
    private ContactListener1 contactListener;
    private RayHandler rayHandler;
    private float Ambience = 0, ChangeRate = 0.001f;
    boolean bChangeLighting = true;

    public ScrPlay(GamGeoDash game) {
        this.game = game;
        map = new Map("map.tmx", game.world);
        rayHandler = new RayHandler(game.world);
        player = new Player(new Vector2(150, 200), 32, this.game.world, "geoDash.png", rayHandler);
        batch = game.batch;
        contactListener = new ContactListener1();
        this.game.world.setContactListener(contactListener);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(46 / 255f, 210 / 255f, 255 / 255f, 1);
//        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
//        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        map.draw(game.camera);
        batch.begin();
        player.draw(batch);
        batch.end();
        if (bPlayerDead == true) {
            player.reset();
            bPlayerDead = false;
        }
        if (bChangeLighting) {
            rayHandler.setAmbientLight(Ambience);
            Ambience += ChangeRate;
            if (Ambience >= 0.9 || Ambience <= 0) {
                ChangeRate *= -1;
            }
        } else {
            rayHandler.setAmbientLight(0.1f);
        }
        rayHandler.setCombinedMatrix(game.camera.combined, game.camera.position.x,
                game.camera.position.y, game.camera.viewportWidth, game.camera.viewportHeight);
        rayHandler.updateAndRender();
    }

    public Player getPlayer () {
        return player;
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
        map.getMap().dispose();
        map.getTiledMapRenderer().dispose();
    }
}
