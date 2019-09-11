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

	//构造函数，显示游戏开始界面
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
		
		Button button = new Button("开始游戏");
		button.setFont(new Font("宋体", Font.BOLD, 30));
		this.add(button);
		button.addKeyListener(new KeyMonitor());
		button.addMouseListener(new MouseMonitor());
	}
	
	public static void main(String[] args) {
		new Start();
	}
	
	//监听键盘，按空格键开始游戏
	class KeyMonitor extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				setVisible(false);
				new Yard().launch();
			}
		}
	}

	//监听鼠标，点击按钮开始游戏
	class MouseMonitor extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {			
			setVisible(false);
			new Yard().launch();
		}
	}
}










