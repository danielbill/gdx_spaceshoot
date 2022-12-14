package danbl.game.spaceshoot.controller;
/*
Time : 22/10/18 11:30    
Author : 毕磊              
Site : ---                 
File : BulletController.java          
Project: libgdx_test   
说明:
*/

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import danbl.game.core.controller.BaseController;
import danbl.game.spaceshoot.entity.Bullet;
import danbl.game.spaceshoot.entity.PlayerShip;
import danbl.game.spaceshoot.entity.Ship;
import danbl.game.spaceshoot.entity.Shooter;
import danbl.game.spaceshoot.view.GameScreen;

import java.util.LinkedList;
import java.util.ListIterator;

public class BulletController extends BaseController {
    private LinkedList<Bullet> playerBullets;
    private LinkedList<Bullet> enemyBullets;

    public BulletController(GameScreen gs){
        super(gs);
        playerBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
    }

    public void getEnemyBullets(Shooter enemy){
        if(enemy.canFireBullet()){
            enemyBullets.add(enemy.fireBullet());
        }
    }

    public void getPlayerBullets(Shooter player){
        if(player==null) return;
        if(player.canFireBullet()){
            playerBullets.add(player.fireBullet());
        }
    }

    public void render(Batch batch , float delta){
        ListIterator<Bullet> iter = playerBullets.listIterator();
        while (iter.hasNext()){
            Bullet bullet = iter.next();
            bullet.render(batch,delta);
            if(bullet.getY()>gs.WORLD_HEIGHT+bullet.getHeight()){
                iter.remove();
            }
        }
        iter = enemyBullets.listIterator();
        while (iter.hasNext()){
            Bullet bullet = iter.next();
            bullet.render(batch,delta);
            if(bullet.getY()<0- bullet.getHeight()){
                iter.remove();
            }
        }

    }

    private boolean detectBulletHitShip(LinkedList<Bullet> bullets, Ship ship){
        ListIterator<Bullet> iter = bullets.listIterator();
        boolean isHit = false;
        while (iter.hasNext()){
            Bullet bullet = iter.next();
            if(bullet.isCollideWith(ship)){
                iter.remove();
                ship.collided();
                isHit=true;
            }
        }
        return isHit;
    }

    public void detectBulletHitPlayer(PlayerShip player){
        if(player ==null) return;
        if(detectBulletHitShip(this.enemyBullets,player)){
            gs.shakeCamera();
            player.playHit();
            Gdx.app.log("bullet", "the player ship hit");
        }
    }

    public void detectBulletHitEnemy(Ship enemy){
        detectBulletHitShip(this.playerBullets,enemy);
    }




}
