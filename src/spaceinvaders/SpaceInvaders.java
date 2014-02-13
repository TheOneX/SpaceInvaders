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
        invader = new Invader(0, s.getHeight()/2, s.getWidth(), s.getHeight(), ((float) s.getWidth()) / 5000.0f);
        int r = invader.getHeight() / 24;
        SpriteBuilder bld = sf.newSprite(r / 2, r / 2);
        bld.setAnchor(r, r);
        invaderSprite = bld.build();
        // ship
        ship = new Ship(0, s.getHeight() - 50, s.getWidth(), s.getHeight(), 0.0f);
        int d = r / 4;
        bld = sf.newSprite(r, d);
        bld.setAnchor(r, r);
        shipSprite = bld.build();
        // build bullet
        return true;
    }

    @Override
    public boolean update(long time) {
        System.out.println("X ="+invader.getPosX());
        System.out.println("invaderY ="+ invader.getPosY());
        System.out.println("ShipY ="+ship.getPosY());
        
        if(direction == true){
            invader.moveRight(time);
            }
        if(direction == false){
            invader.moveLeft(time);
        }
        
      if(invader.getPosX()== s.getWidth()){
                    invader.moveDown();
                    direction = false;
            }
      if(invader.getPosX() == 0){
          invader.moveDown();
          direction = true;
      }
            
        
        return (ship.getPosY() > invader.getPosY());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawSprite(ship.getPosX(), ship.getPosY(), shipSprite);
        canvas.drawSprite(invader.getPosX(), invader.getPosY()*2, invaderSprite);
    }

    @Override
    public void destroy() {
       
    }

}
