import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Yard extends Frame{
	/**
	 * ���ڵ�����
	 */
	public static final int ROWS = 30;
	/**
	 * ���ڵ�����
	 */
	public static final int COLS = 30;
	/**
	 * ���ڵĸ��Ӵ�С
	 */
	public static final int BLOCK_SIZE = 15;	
	
	int score = 0;
	Snake s = new Snake(this);
	Egg e = new Egg();
	
	/**
	 * ��ʾ�����д���
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
		//��ֹ�û��������ڴ�С���������ִ���
		this.setResizable(false);
		this.addKeyListener(new KeyMonitor());
		new Thread(new PaintThread()).start();
	}
	
	//�Զ�����
	@Override
	public void paint(Graphics g) {
		/*//������񣬷������
		Color c = g.getColor();
		g.setColor(Color.gray);		
		//��������
		for(int i = 1; i< ROWS; i++) {
			g.drawLine(0, i*BLOCK_SIZE, COLS * BLOCK_SIZE, i*BLOCK_SIZE);
		}
		// ��������
		for (int i = 1; i < COLS; i++) {
			g.drawLine(i*BLOCK_SIZE, 0, i*BLOCK_SIZE, ROWS*BLOCK_SIZE);
		}
		g.setColor(c);*/
		
		// ��ʾ����
		Color c = g.getColor();
		g.setColor(Color.gray);
		g.drawString("������ " + score, 30, 50);
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
			//��������ˣ����ո�������¿�ʼ
			else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				//�ر�ԭ���Ĵ��ڣ��½�����
				setVisible(false);
				new Yard().launch();
			}
			
		}		
	}
	
}