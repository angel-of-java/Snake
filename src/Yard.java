import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Yard extends Frame{
	/**
	 * 窗口的行数
	 */
	public static final int ROWS = 30;
	/**
	 * 窗口的列数
	 */
	public static final int COLS = 30;
	/**
	 * 窗口的格子大小
	 */
	public static final int BLOCK_SIZE = 15;	
	
	int score = 0;
	Snake s = new Snake(this);
	Egg e = new Egg();
	
	/**
	 * 显示并运行窗口
	 */
	public void launch() {
		this.setSize(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}			
		});
		//禁止用户调整窗口大小，否则会出现错误
		this.setResizable(false);
		this.addKeyListener(new KeyMonitor());
		new Thread(new PaintThread()).start();
	}
	
	//自动调用
	@Override
	public void paint(Graphics g) {
		/*//画出表格，方便调试
		Color c = g.getColor();
		g.setColor(Color.gray);		
		//画出横线
		for(int i = 1; i< ROWS; i++) {
			g.drawLine(0, i*BLOCK_SIZE, COLS * BLOCK_SIZE, i*BLOCK_SIZE);
		}
		// 画出列线
		for (int i = 1; i < COLS; i++) {
			g.drawLine(i*BLOCK_SIZE, 0, i*BLOCK_SIZE, ROWS*BLOCK_SIZE);
		}
		g.setColor(c);*/
		
		// 显示分数
		Color c = g.getColor();
		g.setColor(Color.gray);
		g.drawString("分数： " + score, 30, 50);
		g.setColor(c);
		
		s.eat(e);		
		e.draw(g);
		s.draw(g);
	}
	
	private class PaintThread implements Runnable{
		@Override
		public void run() {
			while(s.live) {
				repaint();
				try {
					Thread.sleep(180);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	class KeyMonitor extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if(s.live) {
				s.keyPressed(e);
			}
			//如果蛇死了，按空格键，重新开始
			else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				//关闭原来的窗口，新建窗口
				setVisible(false);
				new Yard().launch();
			}
			
		}		
	}
	
}