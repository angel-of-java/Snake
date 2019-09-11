import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Snake {
	
	private Node head = null;
	private Node tail = null;
	private Direction dir;	
	private int length = 0;
	private Node n = new Node(4,5,Direction.RIGHT);
	private Yard y;
	public boolean live = false;
	Font fontBig = new Font("����", Font.BOLD, 50);
	Font fontSmall = new Font("����", Font.TYPE1_FONT, 20);
	
	public Snake(Yard y) {
		this.head = n;
		this.tail = n;
		this.y = y;
		live = true;
		length++;
	}
	
	/**
	 * ���ߵ�β����ӽڵ� 
	 */
	public void addNodetoTail() {
		Node n = null;
		switch(tail.dir) {
			case LEFT : 
				n = new Node(tail.row,tail.col+1,tail.dir);			
				break;
			case UP : 
				n = new Node(tail.row+1,tail.col,tail.dir);
				break;
			case RIGHT : 
				n = new Node(tail.row,tail.col-1,tail.dir);
				break;
			case DOWN : 
				n = new Node(tail.row-1,tail.col,tail.dir);
				break;
		}
		//�ƶ�β��ָ��
		tail.nextNode = n;
		n.beforeNode = tail;
		tail = n;
		length++;
	}
	
	/**
	 * ���ߵ�ͷ����ӽڵ�
	 */
	public void addNodetoHead() {
		Node n = null;
		switch(head.dir) {
			case LEFT : 
				n = new Node(head.row,head.col-1,head.dir);			
				break;
			case UP : 
				n = new Node(head.row-1,head.col,head.dir);
				break;
			case RIGHT : 
				n = new Node(head.row,head.col+1,head.dir);
				break;
			case DOWN : 
				n = new Node(head.row+1,head.col,head.dir);
				break;
		}
		//�ƶ�ͷָ��
		n.nextNode = head;
		head.beforeNode = n;
		head = n;
	}
	
	/**
	 * ���ߵ�β��ɾ���ڵ�
	 */
	private void deleteFromTail() {
		if(length == 0) return;
		//�ƶ�β��ָ��
		tail.beforeNode.nextNode = null;
		tail = tail.beforeNode;
	}
	
	/**
	 * ������
	 * @param g
	 */
	public void draw(Graphics g) {
		if(length <= 0) return;
		
		move(g);
		//һ���ڵ�һ���ڵ�Ļ���ֱ�����߻���
		for(Node n = head; n != null; n = n.nextNode) {
			n.draw(g);
		}
	}
	
	/**
	 * ʵ���ߵ��ƶ�
	 */
	public void move(Graphics g) {
		addNodetoHead();
		deleteFromTail();
		checkDead(g);
	}
	
	/**
	 * ������Ƿ�����
	 */
	public void checkDead(Graphics g) {
		//�ж����Ƿ�ײ��ǽ
		Color c = g.getColor();
		if(head.row<2 || head.col<0 || head.row>Yard.ROWS || head.col>Yard.COLS) {
			g.setFont(fontBig);
			g.drawString("��Ϸ����", 100, 200);
			g.setColor(Color.BLACK);
			g.setFont(fontSmall);
			g.drawString("���ո�����¿�ʼ", 120, 230);
			g.setColor(c);
			live = false;
			return;
		}
		
		//�ж����Ƿ�ײ���Լ�������
		for(Node n = head.nextNode; n != null; n = n.nextNode) {
			if(head.row == n.row && head.col == n.col) {
				g.setFont(fontBig);
				g.drawString("��Ϸ����", 100, 200);
				g.setColor(Color.BLACK);
				g.setFont(fontSmall);
				g.drawString("���ո�����¿�ʼ", 120, 230);
				g.setColor(c);
				live = false;
				return;
			}
		}
	}
	
	public Rectangle getRect() {
		return new Rectangle(head.col * head.size, head.row * head.size, head.size, head.size);
	}
	
	/**
	 * �߳Ե�
	 * @param e ������ĵ�
	 */
	public void eat(Egg e) {
		//������Ƿ�Ե���
		if(getRect().intersects(e.getRect())) {
			y.score++;			
			e.reAppear();
			addNodetoTail();
		}
	}
	
	//�������̣������ߵ��ƶ�����
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
			case KeyEvent.VK_LEFT:
				if(head.dir != Direction.RIGHT) 
					head.dir = Direction.LEFT;			
				break;
			case KeyEvent.VK_UP:
				if(head.dir != Direction.DOWN) 
					head.dir = Direction.UP;
				break;
			case KeyEvent.VK_RIGHT:
				if(head.dir != Direction.LEFT) 
					head.dir = Direction.RIGHT;
				break;
			case KeyEvent.VK_DOWN:
				if(head.dir != Direction.UP) 
					head.dir = Direction.DOWN;
				break;	
		}

	}
	
	private class Node{
		int row,col;
		int size = Yard.BLOCK_SIZE;
		Direction dir;
		Node nextNode = null;
		Node beforeNode = null;
						
		Node(int row,int col,Direction dir){
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
		
		public void draw(Graphics g) {
			g.setColor(Color.BLUE);
			g.fillRect(col*size, row*size, size, size);
		}
	}

}
