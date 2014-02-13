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
public class Thing {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private float pixelsPrMiliSec;

    public Thing(int posX, int posY, int width, int height, float pixelsPrMiliSec) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.pixelsPrMiliSec = pixelsPrMiliSec;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setPixelsPrMiliSec(float pixelsPrMiliSec) {
        this.pixelsPrMiliSec = pixelsPrMiliSec;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getPixelsPrMiliSec() {
        return pixelsPrMiliSec;
    }
}
