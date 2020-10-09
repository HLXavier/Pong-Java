package pongMeu;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
	
	public int x, y, width, height;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 10;
		this.height = 100;
	}
	
	public void tick() {
		y += (GameMeu.ball.y - y - 50) * 0.02;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
	
}
