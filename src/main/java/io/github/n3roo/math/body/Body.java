package io.github.n3roo.math.body;

import io.github.n3roo.math.body.shapes.Shape;

public class Body {

    /**
     * Shape of this rigid body.
     */
    private Shape hitbox;

    /**
     * Mass of this rigid body.
     */
    private double mass;

    /**
     * It creates a collision box. It is a square, with the (x, y) position being the top left corner.
     * @param hitbox the collision polygon,
     * @param mass the mass of the rigid body. (value < 0 means an infinite mass)
     */
    public Body(Shape hitbox, double mass){
        this.hitbox = hitbox;
        this.mass = mass;
    }

    /**
     * @return the shape of this rigid body.
     */
    public Shape getHitbox(){
        return this.hitbox;
    }

    /**
     * @return the mass of this rigid body.
     */
    public double getMass(){
        return this.mass;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Body)) return false;
        Body rb = (Body) o;
        return rb.getHitbox().equals(hitbox) && rb.getMass() == mass;
    }
}
