/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import applicationapi.*;
import applicationapi.graphics.*;

;

/**
 *
 * @author TheOneX
 */
public class SpaceInvaders implements Application {

    private Sprite invaderSprite;
    private Sprite bulletSprite;
    private Sprite shipSprite;
    private Invader invader;
    private Ship ship;
    private Screen s;
    private boolean direction = true; // true = right, false = left

    @Override
    public boolean initialize(Device device) {
        s = device.getScreen();
        if (s == null) {
            return false;
        }
        SpriteFactory sf = s.getSpriteFactory();
        //invader 
        invader = new Invader(5, 5, s.getWidth(), s.getHeight(), ((float) s.getWidth()) / 5000.0f);
        int r = invader.getHeight() / 24;
        int t = r + r + 1;
        SpriteBuilder bld = sf.newSprite(t, t);
        bld.setAnchor(r, r);
        invaderSprite = bld.build();
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
            }
            else{invader.moveRight(time);}
        }
        if (direction == false) {
            if (invader.getPosX() <= 0) {
                invader.moveDown();
                direction = true;
            }
            else{invader.moveLeft(time);}
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

}
