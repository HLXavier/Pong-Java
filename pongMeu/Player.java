package pongMeu;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	public boolean up, down;
	public int x;
	public int y;
	public int width;
	public int height;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 10;
		this.height = 100;
	}
	
	public void tick() {
		if(up) {
			y-=3;
		} else if(down) {
			y+=3;
		}
		
		if(y<0) {
			y = 0;
		} else if(y + height > GameMeu.height) {
			y = GameMeu.height - height;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
	
}
