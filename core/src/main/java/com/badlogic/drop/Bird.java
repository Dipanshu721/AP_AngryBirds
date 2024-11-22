package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;


public abstract class Bird {
    protected Texture Face;

    public void dispose(){
        Face.dispose();
    }

    public Texture getFace(){
        return Face;
    }

}
