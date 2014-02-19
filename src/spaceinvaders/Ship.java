package spaceinvaders;

/**
 *
 * @author TheOneX
 */
public class Ship extends Thing{

    
    
    public Ship(int posX, int posY, int width, int height, float pixelsPrMiliSec) {
        super(posX, posY, width, height, pixelsPrMiliSec);
    }
    
    public void moveLeft(){
//        System.out.println("Moving ship left");
        this.setPosX(this.getPosX()-1);
    }
    public void moveRight(){
        System.out.println("Moving ship right");
        this.setPosX(this.getPosX()+1);
    }
    
}
