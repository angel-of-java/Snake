import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Start extends Frame {

	//���캯������ʾ��Ϸ��ʼ����
	Start(){
		this.setSize(Yard.COLS * Yard.BLOCK_SIZE, Yard.ROWS * Yard.BLOCK_SIZE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}			
		});
		this.setResizable(false);
		
		Button button = new Button("��ʼ��Ϸ");
		button.setFont(new Font("����", Font.BOLD, 30));
		this.add(button);
		button.addKeyListener(new KeyMonitor());
		button.addMouseListener(new MouseMonitor());
	}
	
	public static void main(String[] args) {
		new Start();
	}
	
	//�������̣����ո����ʼ��Ϸ
	class KeyMonitor extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				setVisible(false);
				new Yard().launch();
			}
		}
	}

	//������꣬�����ť��ʼ��Ϸ
	class MouseMonitor extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {			
			setVisible(false);
			new Yard().launch();
		}
	}
}










