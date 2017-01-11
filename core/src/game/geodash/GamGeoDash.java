package game.geodash;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;

public class GamGeoDash extends Game implements InputProcessor {
    ScrPlay scrPlay;
    ScrSplashScreen scrSplashScreen;
    ScrMenu scrMenu;
    int nWidth, nHeight;
    float Magnification;
    OrthographicCamera camera;
    World world;
    Box2DDebugRenderer b2dr;
    SpriteBatch batch;
    public static int PPM = 32;
    public static boolean bPlayerDead;
    RayHandler rayHandler;
    ShapeRenderer shape;

    @Override
    public void create() {
        nWidth = Gdx.graphics.getWidth();
        nHeight = Gdx.graphics.getHeight();
        Magnification = 1f;
        batch = new SpriteBatch();
//		camera = new OrthographicCamera();
//		camera.setToOrtho(false, nWidth / 2 / Magnification, nHeight / 2 / Magnification);
//		camera.position.set(nWidth / 2, nHeight / 2, 0);
        camera = new OrthographicCamera(Gdx.graphics.getWidth() / Magnification, Gdx.graphics.getHeight() / Magnification);
//		camera.position.set(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 2, 0);
        camera.position.set(camera.viewportWidth - camera.viewportWidth / 2, camera.viewportHeight - camera.viewportHeight / 2, 0);
        world = new World(new Vector2(0, -100f), false);
        b2dr = new Box2DDebugRenderer();
        rayHandler = new RayHandler(world);
        shape = new ShapeRenderer();
        scrPlay = new ScrPlay(this);
        scrSplashScreen = new ScrSplashScreen(this);
        scrMenu = new ScrMenu(this);
        bPlayerDead = false;
        setScreen(scrSplashScreen);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void setScreen(Screen screen) {
        super.setScreen(screen);
    }

    @Override
    public void render() {
        super.render();
        updateView();
    }

    public void updateView() {
        world.step(1 / 60f, 6, 2);
//        world.step(1/ 60f, 10, 10);
        camera.position.x = scrPlay.getPlayer().getPosition().x + 250;
        camera.position.y = scrPlay.getPlayer().getPosition().y + 60;
//        camera.position.y = scrPlay.player.getPosition().y - 120;
//		System.out.println(camera.position.y);
        camera.position.x = MathUtils.clamp(camera.position.x, 0, 14500);
        camera.position.y = MathUtils.clamp(camera.position.y, 20, 500);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        b2dr.render(world, camera.combined);
        rayHandler.setCombinedMatrix(camera.combined, camera.position.x,
                camera.position.y, camera.viewportWidth, camera.viewportHeight);
        rayHandler.updateAndRender();
        shape.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
//		camera.setToOrtho(false, nWidth / 2 / Magnification, nHeight / 2 / Magnification);
        camera.setToOrtho(false, nWidth / Magnification, nHeight / Magnification);
    }

    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
        b2dr.dispose();
        batch.dispose();
        scrPlay.dispose();
        rayHandler.dispose();
        shape.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println(screenX + ", " + screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
