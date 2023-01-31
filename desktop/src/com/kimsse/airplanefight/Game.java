package com.kimsse.airplanefight;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.kimsse.airplanefight.Rocket;
import com.kimsse.airplanefight.Enemy;
import com.badlogic.gdx.math.Rectangle;


import java.awt.*;

public class Game extends ApplicationAdapter {
    // Class Global Variables
    public Texture rocketTexture;
    public Texture enemyTexture;
    public Texture bulletTexture;
    public OrthographicCamera camera;
    public SpriteBatch batch;
    public static Rocket rocket;
    public static Enemy enemy;
    public static Array<Rectangle> enemies;
    public long lastGenerateTime;
    public Bullet bullet;
    public BitmapFont font;

    @Override
    public void create(){
        // Load all the textures for the game
        rocketTexture = new Texture(Gdx.files.internal("rocket.png"));
        enemyTexture = new Texture(Gdx.files.internal("enemy.png"));
        bulletTexture = new Texture(Gdx.files.internal("bullet.png"));

        // initialize the different characters
        rocket = new Rocket(rocketTexture);
        rocket.initialize();
        enemy = new Enemy(enemyTexture);
        enemy.initialize();
        enemies = enemy.enemyRects;
        bullet = new Bullet(bulletTexture);
        font = new BitmapFont();

        // Setup camera
        camera = new OrthographicCamera(1920, 1080);
        camera.setToOrtho(false);

        // Setup sprite batch
        batch = new SpriteBatch();
    }

    @Override
    public void render(){
        // Screen refresh
        ScreenUtils.clear(0,0,0,1);

        //Update camera
        camera.update();

        // Combine the batch coordinate system with the camera's
        batch.setProjectionMatrix(camera.combined);

        // Move the Rocket
        rocket.moveRocket();
        enemy.moveEnemy();
        bullet.fly();
        bullet.trigger();

        // Generate the enemies
        if (TimeUtils.nanoTime() - lastGenerateTime >= 100000000){
            enemy.spawnEnemy();
            lastGenerateTime = TimeUtils.nanoTime();
        }

        // Start recording
        batch.begin();

        // Rend the fonts in the game
        font.draw(batch, "Your HP:" + rocket.healthPercent, 100, 1000);

        // Action Codes here
        batch.draw(rocket.rocketTexture, rocket.rocketRect.x, rocket.rocketRect.y);
        for (Rectangle targetEnemy : enemies){
            batch.draw(enemyTexture, targetEnemy.x, targetEnemy.y);
        }
        for (Rectangle targetBullet: bullet.bulletRects){
            batch.draw(bullet.bulletTexture, targetBullet.x, targetBullet.y);
        }

        // Stop recording
        batch.end();


    }
}
