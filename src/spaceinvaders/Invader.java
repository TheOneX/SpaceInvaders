/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spaceinvaders;

/**
 *
 * @author TheOneX
 */
public class Invader  extends Thing{

    public Invader(int posX, int posY, int width, int height, float pixelsPrMiliSec) {
        super(posX, posY, width, height, pixelsPrMiliSec);
    }
    
    public void moveLeft(long time){
        System.out.println("Moving invader left");
        this.setPosX(this.getPosX()-1);//(int)(time / this.getPixelsPrMiliSec()));
    }
    
    public void moveRight(long time){
        System.out.println("Moving invader right");
        this.setPosX(this.getPosX()+1);//(int)(time * this.getPixelsPrMiliSec()));
    }
    public void moveDown(){
        System.out.println("Moving invader down");
        this.setPosY(this.getPosY() + 10);
    }
}
