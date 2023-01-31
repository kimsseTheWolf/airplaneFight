package com.kimsse.airplanefight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.awt.*;
import java.util.Iterator;

public class Bullet {
    public Texture bulletTexture;
    public Rectangle bulletRect;
    public Array<Rectangle> bulletRects;
    public Integer speed;
    private Rocket rocket;
    public Integer bulletNum;


    public Bullet(Texture bulletTexture){
        this.bulletTexture = bulletTexture;
        this.bulletRect = new Rectangle();
        this.bulletRects = new Array<>();
        this.speed = 500;
        this.rocket = Game.rocket;
        this.bulletNum = 100;
    }

    public void shoot(){
        Rectangle targetBullet = new Rectangle();
        targetBullet.x = rocket.rocketRect.x;
        targetBullet.y = rocket.rocketRect.y;
        targetBullet.width = 128;
        targetBullet.height = 128;
        bulletRects.add(targetBullet);
    }

    public void fly(){
        for (Iterator<Rectangle> iter = bulletRects.iterator(); iter.hasNext();){
            Rectangle targetBullet = iter.next();
            targetBullet.y += this.speed * Gdx.graphics.getDeltaTime();
            if (targetBullet.y > 1024){
                iter.remove();
            }
            for (Iterator<Rectangle> enemyIter = Game.enemies.iterator(); enemyIter.hasNext();){
                Rectangle targetEnemy = enemyIter.next();
                if (targetBullet.overlaps(targetEnemy)){
                    System.out.println("Bullet hits the enemy!");
                    enemyIter.remove();
                }
            }
//            if (targetBullet.overlaps(Game.enemy.enemyRect)){
//
//            }
        }
    }

    public void trigger(){
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            this.shoot();
        }
    }
}
