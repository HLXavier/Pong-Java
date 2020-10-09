package pongMeu;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class GameMeu implements Runnable, KeyListener{
	
	static int width = 640;
	static int height = 400;
	
	private JFrame frame;
	public static Thread thread;
	private Canvas canvas;
	private BufferStrategy bs;
	private Graphics g;
	
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
		
	public GameMeu() {
		initFrame();
		canvas.addKeyListener(this);
		player = new Player(0, 150);
		enemy = new Enemy(630, 150);
		ball = new Ball(width/2, height/2);
		thread = new Thread(this);
		thread.start();
		
	}
	
	public static void main(String[] args) {
		new GameMeu();
	}
	
	public void initFrame() {
		frame = new JFrame();
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}

	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
		
	}
	
	public void render() {
		bs = canvas.getBufferStrategy();
		if(bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		//Draw
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		player.render(g);
		enemy.render(g);
		ball.render(g);
		//End Draw
		bs.show();
		g.dispose();
	}

	public void run() {
		while(true) {
			tick();
			render();
			try {
				thread.sleep(1000/144);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
	}
	
}
