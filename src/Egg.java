import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {
	private int row,col;
	private int size = Yard.BLOCK_SIZE;
	private static Random r = new Random();
	
	public Egg() {
		//��ֹ�����ڱ߿���,������ɼ�����λ��
		this.row = r.nextInt(Yard.ROWS-2)+2;
		this.col = r.nextInt(Yard.COLS-1)+1;
	}
	
	public void reAppear() {
		this.row = r.nextInt(Yard.ROWS-2)+2;
		this.col = r.nextInt(Yard.COLS-1)+1;
	}
	
	public Rectangle getRect() {
		return new Rectangle(col * size, row * size, size, size);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(col * size, row * size, size, size);
	}
}
