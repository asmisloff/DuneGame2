package com.dune.game.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
    private final Vector2 position;
    private final Vector2 velocity;

    private int velocityScale;

    private final TextureRegion texture;
    private boolean isActive;
    private final int w, h, xBound, yBound;
    public Projectile(TextureAtlas atlas, int xBound, int yBound) {
        this.position = new Vector2(-100 ,-100);
        this.velocity = new Vector2(0 ,0);
        this.isActive = false;
        texture = new TextureRegion(atlas.findRegion("bullet"));
        w = texture.getRegionWidth();
        h = texture.getRegionHeight();
        this.xBound = xBound;
        this.yBound = yBound;
        velocityScale = 200;
    }

    public void setup(Vector2 startPosition, float angle) {
        position.set(startPosition)
                .add(40 * MathUtils.cosDeg(angle), 40 * MathUtils.sinDeg(angle));
        velocity.set(velocityScale * MathUtils.cosDeg(angle), velocityScale * MathUtils.sinDeg(angle));
    }

    public void update(float dt) {
        // position.x += velocity.x * dt;
        // position.y += velocity.y * dt;
        position.mulAdd(velocity, dt);
    }

    public void render(SpriteBatch batch) {
        if (isActive) {
            batch.draw(texture, position.x - w/2, position.y - h/2);
            checkBounds();
        }
    }

    public void checkBounds() {
        if (position.x < 0 || position.x > xBound) { setActive(false); position.set(-100, -100); }
        if (position.y < 0 || position.y > yBound) { setActive(false); position.set(-100, -100); }
    }

    public int getVelocityScale() {
        return velocityScale;
    }

    public void setVelocityScale(int velocityScale) {
        this.velocityScale = velocityScale;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
