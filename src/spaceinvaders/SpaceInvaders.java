/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import applicationapi.*;
import applicationapi.graphics.*;
import applicationapi.input.keyboard.KeyEvent;
import applicationapi.input.keyboard.KeyboardListener;
import java.util.logging.Level;
import java.util.logging.Logger;

;

/**
 *
 * @author TheOneX
 */
public class SpaceInvaders implements Application, KeyboardListener {

    private Sprite invaderSprite;
    private Sprite bulletSprite;
    private Sprite shipSprite;
    private Invader invader;
    private Ship ship;
    private Screen s;
    private boolean direction = true; // true = right, false = left
    SpriteFactory sf;
    SpriteBuilder bld;
    int r;
    int t;

    public void buildInvaders() {

        invader = new Invader(5, 5, s.getWidth(), s.getHeight(), ((float) s.getWidth()) / 5000.0f);
        r = invader.getHeight() / 24;
        t = r + r + 1;
        bld = sf.newSprite(t, t);
        bld.setAnchor(r, r);
        invaderSprite = bld.build();
    }

    @Override
    public boolean initialize(Device device) {
        s = device.getScreen();
        if (s == null) {
            return false;
        }
        sf = s.getSpriteFactory();
        buildInvaders();

        // ship
        ship = new Ship(s.getWidth() / 2, s.getHeight() - 50, s.getWidth(), s.getHeight(), 0.0f);
        int d = r / 4;
        bld = sf.newSprite(r, d);
        bld.setAnchor(r, r);
        shipSprite = bld.build();
        // build bullet
        return true;
    }

    @Override
    public boolean update(long time) {
        System.out.println("invaderX =" + invader.getPosX() + "/" + s.getWidth());
        System.out.println("invaderY =" + invader.getPosY() + "/" + s.getHeight());
        System.out.println("ShipY =" + ship.getPosY());

        if (direction) {
            System.out.println("Direction = right");
        }
        if (!direction) {
            System.out.println("Direction = left");
        }
        if (direction == true) {
            if (invader.getPosX() >= s.getWidth()) {
                invader.moveDown();
                direction = false;
            } else {
                invader.moveRight(time);
            }
        }
        if (direction == false) {
            if (invader.getPosX() <= 0) {
                invader.moveDown();
                direction = true;
            } else {
                invader.moveLeft(time);
            }
        }

        return (ship.getPosY() > invader.getPosY());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawSprite(ship.getPosX(), ship.getPosY(), shipSprite);
        canvas.drawSprite(invader.getPosX(), invader.getPosY() * 2, invaderSprite);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onKeyPress(KeyEvent ke) {
        //ship.moveleft/right
        Object left = ke.getKey().VK_A;
        Object right = ke.getKey().VK_S;
        Object shoot = ke.getKey().VK_SPACE;
        if (ke.getKey().equals(left)) {
            ship.moveLeft();
        } else {
            if (ke.getKey().equals(right)) {
                ship.moveRight();
            } else{
                if(ke.getKey().equals(shoot)){
                    //ship.bullet.start
                }
            }
        }
    }

    @Override
    public void onKeyRelease(KeyEvent ke) {
        //ship.shootBullet
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
