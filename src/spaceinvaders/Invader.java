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
        this.setPosX((int)(time / this.getPixelsPrMiliSec()));
    }
    
    public void moveRight(long time){
        this.setPosX((int)(time * this.getPixelsPrMiliSec()));
    }
    public void moveDown(){
        this.setPosY(this.getPosY() - 10);
    }
}
