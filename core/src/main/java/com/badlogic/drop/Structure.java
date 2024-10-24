package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Structure {
    protected String material;
    protected Texture Face;

    public Texture getFace(){
        return Face;
    }

    public void dispose() {
        Face.dispose();
    }
}
