package sample.entities;

import io.github.n3roo.event.mouse.Mouse;
import io.github.n3roo.util.TimeHelper;
import io.github.n3roo.world.World;
import javafx.geometry.Point2D;
import io.github.n3roo.event.keyboard.KeyEvent;
import io.github.n3roo.math.Position;
import io.github.n3roo.math.physics.Force;
import io.github.n3roo.world.entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends Entity {

    private TimeHelper fireTimer;

    /**
     * It creates an entity. All the attributes are initialized as well.
     *
     * @param position the entity position.
     */
    public Player(Position position) {
        super(position);
        fireTimer = new TimeHelper();
        fireTimer.start();
    }

    @Override
    public void update(double delta){
        double inputSensibility = 5d;

        if(KeyEvent.isKeyDown(KeyCode.LEFT))
            addForce(new Force(new Point2D(-inputSensibility * delta, 0d), Force.Mode.Velocity));
        if(KeyEvent.isKeyDown(KeyCode.RIGHT))
            addForce(new Force(new Point2D(+ inputSensibility * delta, 0d), Force.Mode.Velocity));
        if(KeyEvent.isKeyDown(KeyCode.UP))
            addForce(new Force(new Point2D(0d, - inputSensibility * delta), Force.Mode.Velocity));
        if(KeyEvent.isKeyDown(KeyCode.DOWN))
            addForce(new Force(new Point2D(0d, + inputSensibility * delta), Force.Mode.Velocity));

        if(KeyEvent.isKeyDown(KeyCode.SPACE))
            attack();
    }

    /**
     * It throws a bullet according to the mouse position. Maximum one bullet per 100 milliseconds.
     */
    private void attack(){
        if(fireTimer.isDelayComplete(100)) {
            double dx = Mouse.getX() - position.x;
            double dy = Mouse.getY() - position.y;

            Bullet bullet = new Bullet(position.copy(), 5);
            bullet.fire(new Point2D(dx/10, dy/10));
            World.addEntity(bullet);

            fireTimer.reset();
            fireTimer.start();
        }
    }

    @Override
    public void render(GraphicsContext g){
        super.render(g);

        g.setFill(Color.GOLD);
        g.fillRect(position.x - 5, position.y - 5, 10, 10);
    }
}
