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
import java.util.ArrayList;
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
    ArrayList<Sprite> bullets = new ArrayList();
    ArrayList<Bullet> bulls = new ArrayList();

    public void buildInvaders() {

        r = s.getHeight() / 24;
        t = r + r + 1;
        invader = new Invader(5, 5, t, t, ((float) s.getWidth()) / 5000.0f);
        System.out.println("invader width = "+invader.getWidth()+"\ninvader height = "+invader.getHeight());
        System.out.println("invader x = "+invader.getPosX());System.out.println("invader y = "+invader.getPosY());
        bld = sf.newSprite(t, t);
        bld.setAnchor(invader.getPosX(), invader.getPosY());
        invaderSprite = bld.build();
    }

    public void buildShip() {
        int d = r / 4;
        ship = new Ship(s.getWidth() / 2, s.getHeight() - 50, r, d, 0.0f);
        System.out.println("ship width = "+ship.getWidth()+"\nship height = "+ship.getHeight());
        System.out.println("ship x = "+ship.getPosX());System.out.println("ship y = "+ship.getPosY());
        bld = sf.newSprite(r, d);
        bld.setAnchor(r, r);
        shipSprite = bld.build();
        System.out.println("Anchor x, y = "+shipSprite.getAnchorX()+", "+shipSprite.getAnchorY());
    }

    public void buildBullet() {
        bullet = new Bullet(ship.getPosX(), ship.getPosY() + 15, 5, 10, 1000.0f);
        bld = sf.newSprite(5, 10);
        bld.setAnchor(ship.getPosX(), ship.getPosY()-5);
        bulletSprite = bld.build();
        bullets.add(bulletSprite);
        bulls.add(bullet);
    }

    @Override
    public boolean initialize(Device device) {
        device.getKeyboard().addKeyboardListener(this);
        s = device.getScreen();
        System.out.println("Screen w = "+s.getWidth());System.out.println("Screen h = "+s.getHeight());
        if (s == null) {
            return false;
        }
        maxRight = s.getWidth();
        maxLeft = 0;
        sf = s.getSpriteFactory();
        buildInvaders();
        buildShip();
//        buildBullet();
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
        if (bullet != null) {
            if (bullet.getHeight() / 2 + bullet.getPosY() < invader.getHeight() / 2 + invader.getPosY()
                    && bullet.getWidth() / 2 - bullet.getPosX() > invader.getWidth() / 2 - invader.getPosX()) {
                return false;
            }
        }
        return (ship.getPosY() > invader.getPosY());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawSprite(ship.getPosX(), ship.getPosY(), shipSprite);
        canvas.drawSprite(invader.getPosX(), invader.getPosY(), invaderSprite);
        if (!bullets.isEmpty()) {
            for (Sprite sprite : bullets) {
//                int index = bullets.indexOf(sprite);
//                Bullet temp = bulls.get(index);
                canvas.drawSprite(bullet.getPosX(), bullet.getPosY(), bulletSprite);
            }
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onKeyPress(KeyEvent ke) {
        if (ke.getKey().equals(Key.VK_A)) {
            System.out.println("Key A pressed");
            KeyADown = true;
        } else {
            if (ke.getKey().equals(Key.VK_S)) {
                System.out.println("Key S pressed");
                KeySDown = true;
            } else {
                if (ke.getKey().equals(Key.VK_F)) {
                    System.out.println("Key F pressed");
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
                if (ke.getKey().equals(Key.VK_F)) {
                    keySpaceDown = false;
                }
            }
        }
    }

}
