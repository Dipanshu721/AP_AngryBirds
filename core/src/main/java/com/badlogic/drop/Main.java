package com.badlogic.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
    private SpriteBatch batch;

    public SpriteBatch getbatch(){
        return this.batch;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new HomeScreen(this));
    }

    @Override
    public void render(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose(){
        getScreen().dispose();
        batch.dispose();
    }
}
