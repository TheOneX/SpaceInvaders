package spaceinvaders;

/**
 *
 * @author krismaini
 */
public class Bullet extends Thing{

    public Bullet(int posX, int posY, int width, int height, float pixelsPrMiliSec) {
        super(posX, posY, width, height, pixelsPrMiliSec);
    }
    public void moveUp(){
        this.setPosY(this.getPosY() - 1);
    }
}
