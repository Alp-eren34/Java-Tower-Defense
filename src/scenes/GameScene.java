package scenes;

import main.Game;

import java.awt.image.BufferedImage;

public class GameScene {
    protected Game game;
    protected int animationIndex;
    protected int ANIMATION_SPEED = 25;
    protected int tick;

    public GameScene(Game game){
        this.game = game;
    }

    public Game getGame(){
        return game;
    }

    protected boolean isAnimation(int SpriteID){
        return getGame().getTileManager().isSpriteAnimation(SpriteID);
    }

    protected void updateTick() {
        tick++;
        if(tick>=ANIMATION_SPEED){
            tick = 0;
            animationIndex++;
            if(animationIndex >= 4){
                animationIndex = 0;
            }
        }
    }
    protected BufferedImage getSprite(int spriteId){
        return getGame().getTileManager().getSprite(spriteId);
    }

    protected BufferedImage getSprite(int spriteId,int animationIndex){
        return getGame().getTileManager().getAniSprite(spriteId,animationIndex);
    }
}
