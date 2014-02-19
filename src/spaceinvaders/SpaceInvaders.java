/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import applicationapi.*;
import applicationapi.graphics.*;
import applicationapi.input.keyboard.Key;
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
    private Bullet bullet;
    private Invader invader;
    private Ship ship;
    private Screen s;
    private boolean direction = true; // true = right, false = left
    SpriteFactory sf;
    SpriteBuilder bld;
    int r;
    int t;
    boolean KeyADown = false;
    boolean KeySDown = false;
    boolean keySpaceDown = false;
    int maxRight;
    int maxLeft;

    public void buildInvaders() {

        invader = new Invader(5, 5, s.getWidth(), s.getHeight(), ((float) s.getWidth()) / 5000.0f);
        r = invader.getHeight() / 24;
        t = r + r + 1;
        bld = sf.newSprite(t, t);
        bld.setAnchor(r, r);
        invaderSprite = bld.build();
    }

    public void buildShip() {
        ship = new Ship(s.getWidth() / 2, s.getHeight() - 50, s.getWidth(), s.getHeight(), 0.0f);
        int d = r / 4;
        bld = sf.newSprite(r, d);
        bld.setAnchor(r, r);
        shipSprite = bld.build();
    }

    public void buildBullet() {
        bullet = new Bullet(ship.getPosX(), ship.getPosY(), s.getWidth(), s.getHeight(), 1000.0f);
        bld = sf.newSprite(5, 10);
        bld.setAnchor(5, 5);
        bulletSprite = bld.build();
    }

    @Override
    public boolean initialize(Device device) {
        device.getKeyboard().addKeyboardListener(this);
        s = device.getScreen();
        if (s == null) {
            return false;
        }
        maxRight = s.getWidth();
        maxLeft = 0;
        sf = s.getSpriteFactory();
        buildInvaders();
        buildShip();
        return true;
    }

    @Override
    public boolean update(long time) {
        if (KeyADown) {
            if (ship.getPosX() > maxLeft) {
                ship.moveLeft();
            }
            //else{some kind of animation to show u can't move}
        }
        if (KeySDown) {
            if ((ship.getPosX() + ship.getWidth()) / 2 < maxRight) {
                ship.moveRight();
            }
        }
        if (keySpaceDown) {
            System.out.println("bullit build");
            buildBullet();
        }
//        System.out.println("invaderX =" + invader.getPosX() + "/" + s.getWidth());
//        System.out.println("invaderY =" + invader.getPosY() + "/" + s.getHeight());
//        System.out.println("ShipY =" + ship.getPosY());
        if (bullet != null) {
            bullet.moveUp();
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
        if (ke.getKey().equals(Key.VK_A)) {
            System.out.println("Key a pressed");
            KeyADown = true;
        } else {
            if (ke.getKey().equals(Key.VK_S)) {
                System.out.println("Key s pressed");
                KeySDown = true;
            } else {
                if (ke.getKey().equals(Key.VK_SPACE)) {
                    System.out.println("Key space pressed");
                    keySpaceDown = true;
                }
            }
        }
    }

    @Override
    public void onKeyRelease(KeyEvent ke) {
        if (ke.getKey().equals(Key.VK_A)) {
            KeyADown = false;
        } else {
            if (ke.getKey().equals(Key.VK_S)) {
                KeySDown = false;
            } else {
                if (ke.getKey().equals(Key.VK_SPACE)) {
                    keySpaceDown = false;
                }
            }
        }
    }

}
