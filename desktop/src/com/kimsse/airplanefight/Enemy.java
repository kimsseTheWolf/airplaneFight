package com.kimsse.airplanefight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.kimsse.airplanefight.Game;

import java.util.Iterator;

public class Enemy {
    public Texture enemyTexture;
    public Rectangle enemyRect;
    public Array<Rectangle> enemyRects;
    public Integer healthPercent;
    public Integer speed;
    private Game game;
    public Rocket rocket;

    // Constructor
    public Enemy(Texture enemyTexture){
        this.enemyTexture = enemyTexture;
        this.enemyRects = new Array<Rectangle>();
        this.enemyRect = new Rectangle();
        this.healthPercent = 10;
        this.speed = 100;
    }

    public Enemy(Texture enemyTexture, Integer healthPercent, Integer speed){
        this.enemyTexture = enemyTexture;
        this.enemyRects = new Array<Rectangle>();
        this.healthPercent = healthPercent;
        this.speed = speed;
    }

    // Initialize the locations
    public void initialize(){
        this.enemyRects.clear();
        this.rocket = game.rocket;
    }

    // Generate the enemies into the list
    public void spawnEnemy(){
        Rectangle targetRect = new Rectangle();
        targetRect.x = MathUtils.random(0, 1920);
        targetRect.y = 1080;
        targetRect.width = 128;
        targetRect.height = 128;
        this.enemyRects.add(targetRect);
    }

    public void moveEnemy(){
        for (Iterator<Rectangle> iter = this.enemyRects.iterator(); iter.hasNext();){
            // Switch to the next item
            Rectangle targetEnemy = iter.next();
            // Fall the target enemy down
            targetEnemy.y -= this.speed * Gdx.graphics.getDeltaTime();
            // Detect whether the enemy hits the edge of the screen or the player
            if (targetEnemy.y >= 1080){
                iter.remove();
            }
            if (targetEnemy.overlaps(this.rocket.rocketRect)){
                System.out.println("Rocket is overlaping the rocket!");
                this.rocket.cutHPValue(10);
                iter.remove();
            }
        }
    }
}
