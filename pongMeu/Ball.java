package pongMeu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	
	public double x, y, dx, dy, speed;
	public int width, height;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 8;
		this.height = 8;
		this.speed = 4;
		
		int angle = 180;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick() {
		if(y+(dy*speed)+ width >= GameMeu.height) {
			dy *= -1;
		} else if(y+(dy*speed) < 0) {
			dy *= -1;
		}
		
		if(x < 0)
		{
			System.out.println("Ponto do inimigo!");
			try {
				GameMeu.thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			x = 320;
			y = 200;
			int angle = 180;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			
		}else if(x >= GameMeu.width) {
			System.out.println("Ponto nosso! YAYY!");
			try {
				GameMeu.thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			x = 320;
			y = 200;
			int angle = 180;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
		}
		
		Rectangle bounds = new Rectangle((int)x, (int)y, width, height);
		Rectangle boundsPlayer = new Rectangle(GameMeu.player.x, GameMeu.player.y, GameMeu.player.width, GameMeu.player.height);
		Rectangle boundsEnemy = new Rectangle(GameMeu.enemy.x, GameMeu.enemy.y, GameMeu.enemy.width, GameMeu.enemy.height);
		
		if(bounds.intersects(boundsPlayer)) {
			int angle = new Random().nextInt(60) + 150;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			dx *= -1;
		} else if(bounds.intersects(boundsEnemy)) {
			int angle = new Random().nextInt(60) + 330;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			dx *= -1;
		}
		
		x += dx*speed;
		y += dy*speed;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, width, height);
	}
}
