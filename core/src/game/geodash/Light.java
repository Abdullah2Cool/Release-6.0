package game.geodash;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import box2dLight.ConeLight;
import box2dLight.RayHandler;

import static game.geodash.GamGeoDash.PPM;

/**
 * Created by hafiz on 1/4/2017.
 */

public class Light {
    ConeLight light;
    Vector2 vPos;

    public Light(RayHandler rayHandler, int nRays, int nDist, float coneDegrees) {
        light = new ConeLight(rayHandler, nRays, Color.GOLD, nDist, 0, 0, 0, coneDegrees);
    }
    void update (Body body) {
        vPos = body.getPosition().scl(PPM);
        light.setPosition(vPos.x + 5, vPos.y);
    }
}
