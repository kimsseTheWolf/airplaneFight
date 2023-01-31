package com.kimsse.airplanefight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.kimsse.airplanefight.Game;

public class Rocket {
    public Texture rocketTexture;
    public Rectangle rocketRect;
    public Integer healthPercent;
    public Integer speed;


    //Constructor
    public Rocket(Texture rocketTexture){
        this.rocketTexture = rocketTexture;
        this.rocketRect = new Rectangle();
        this.healthPercent = 100;
        this.speed = 500;
    }

    //Spaceship on-screen position initialization
    public void initialize(){
        this.rocketRect.x = 1920 / 2;
        this.rocketRect.y = 300;
        this.rocketRect.width = 128;
        this.rocketRect.height = 128;
    }

    //Spaceship Movement Control
    public void moveRocket(){
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
            rocketRect.x -= 500 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
            rocketRect.x += 500 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)){
            rocketRect.y += 500 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)){
            rocketRect.y -= 500 * Gdx.graphics.getDeltaTime();
        }
    }

    //Cut the HP value
    public void cutHPValue(Integer blood){
        this.healthPercent -= blood;
    }

}
