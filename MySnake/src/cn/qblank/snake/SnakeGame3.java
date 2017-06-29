package cn.qblank.snake;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

import cn.qblank.FrameTool.FrameUtil;

/**
 * 贪吃蛇
 * 
 * @author 岳某
 * 
 */
public class SnakeGame3 {
	// 地图的宽(列数)
	public static final int WIDTH = 35;

	// 地图的高
	public static final int HEIGHT = 8;

	// 地图
	private char[][] background = new char[HEIGHT][WIDTH];

	// 使用集合保存所有信息
	LinkedList<Point> snake = new LinkedList<>();

	// 食物
	Point food;
	
	

	// 定义四个常量表示四个方向
	public static final int UP_DIRECTION = 1;

	public static final int DOWN_DIRECTION = -1;

	public static final int LEFT_DIRECTION = 2;

	public static final int RIGHT_DIRECTION = -2;

	// 记录蛇当前的方向
	int currentDirection = -2;
	
	//判断游戏是否结束	默认为false，游戏没有结束
	static boolean isGameOver = false;

	/**
	 * 蛇移动的方法
	 */
	public void move() {
		// 获取蛇头
		Point head = snake.getFirst();
		switch (currentDirection) {
		// 向上
		case 1:
			// 添加蛇头
			snake.addFirst(new Point(head.x, head.y - 1));
			System.out.println("向上");
			break;
		// 向下
		case -1:
			// 添加蛇头
			snake.addFirst(new Point(head.x, head.y + 1));
			System.out.println("向下");
			break;
		// 向左
		case 2:
			if (head.x == 0) {
				snake.addFirst(new Point(WIDTH - 1, head.y ));
				
			}else{
				snake.addFirst(new Point(head.x - 1, head.y));
			}
			
			System.out.println("向左");
			break;
		// 向右
		case -2:
			if (head.x == WIDTH - 1) {
				snake.addFirst(new Point(0 , head.y));
			}else{
				snake.addFirst(new Point(head.x + 1, head.y));
				
			}
			System.out.println("向右");
			break;

		default:
			break;
		}
		//如果吃到了食物
		if (eatFood()) {
			//创建新的食物
			createFood();
			
		}else{
			// 删除蛇尾
			snake.removeLast();
		}
		

	}

	// 改变当前方向的方法
	public void changeDirection(int newDirection) {
		if (currentDirection + newDirection != 0) {
			this.currentDirection = newDirection;

		}
	}
	
	/**
	 * 判断是否结束游戏
	 */
	public void isGameOver(){
		
		//撞墙死亡
		Point head = snake.getFirst();
		if (background[head.y][head.x] == '*') {
			isGameOver = true;
		}
			
		//咬到自己
		for (int i = 1; i < snake.size(); i++) {
			Point body = snake.get(i);
			if (head.equals(body)) {
				isGameOver = true;
			}
		}
		
		
	}

	/**
	 * 生成食物
	 */
	public void createFood() {
		// 创建随机数
		Random random = new Random();
		while (true) {
			int x = random.nextInt(WIDTH);
			int y = random.nextInt(HEIGHT);
			if (background[y][x] != '*') {
				food = new Point(x, y);
				break;
			}

		}

	}

	/**
	 * 显示食物
	 */
	public void showFood() {
		background[food.y][food.x] = '@';
	}
	
	
	/**
	 * 吃食物
	 */
	public boolean eatFood(){
		//获取蛇头
		Point head = snake.getFirst();
		if (head.equals(food)) {
			return true;
		}else{
			return false;
		}
		
	}

	/**
	 * 初始化蛇
	 */
	public void initSnake() {
		int x = WIDTH / 2;
		int y = HEIGHT / 2;
		snake.addFirst(new Point(x - 1, y));
		snake.addFirst(new Point(x, y));
		snake.addFirst(new Point(x + 1, y));
	}

	/**
	 * 显示蛇
	 */
	public void showSnake() {
		// 取出蛇头
		Point head = snake.getFirst();
		background[head.y][head.x] = '$';
		// 给蛇身赋值
		for (int i = 1; i < snake.size(); i++) {
			Point body = snake.get(i);
			background[body.y][body.x] = '#';
		}

	}

	/**
	 * 初始化地图
	 */
	public void initBackground() {
		for (int row = 0; row < background.length; row++) {
			for (int cols = 0; cols < background[row].length; cols++) {
				if (row == 0 || row == (HEIGHT - 1)) {
					background[row][cols] = '*';
				} else if(cols == 0 || cols == (WIDTH - 1)){
					background[row][cols] = '|';
				}else{
					background[row][cols] = ' ';
				}
			}
		}
	}

	/**
	 * 显示地图
	 */
	public void showBackground() {
		// 打印二维数组
		for (int i = 0; i < background.length; i++) {
			for (int j = 0; j < background[i].length; j++) {
				System.out.print(background[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * 刷新游戏
	 */
	public void refrash() {
		// 清空游戏之前的状态
		initBackground();
		// 把蛇最新的状态反馈到地图上
		showSnake();
		// 把食物的显示到地图上
		showFood();
		// 显示当前地图的信息
		showBackground();

	}

	/**
	 * 向上移动
	 */
	/*
	 * public void moveUp() { // 获取原来的蛇头 Point head = snake.getFirst(); //
	 * 添加新的蛇头 snake.addFirst(new Point(head.x , head.y - 1)); // 删除蛇尾
	 * snake.removeLast();
	 * 
	 * }
	 */

	/**
	 * 向下移动
	 */
	/*
	 * public void moveDown() { // 获取原来的蛇头 Point head = snake.getFirst();
	 * //添加新的蛇头 snake.addFirst(new Point(head.x , head.y + 1)); //删除蛇尾
	 * snake.removeLast(); }
	 */

	/**
	 * 向右移动
	 */
	/*
	 * public void moveRight() { //获取蛇头 Point head = snake.getFirst(); //添加新的蛇头
	 * snake.addFirst(new Point(head.x + 1, head.y )); //删除蛇尾
	 * snake.removeLast(); }
	 */

	/**
	 * 向左移动
	 */
	/*
	 * public void moveLeft() { //获取蛇头 Point head = snake.getFirst(); //添加新的蛇头
	 * snake.addFirst(new Point(head.x - 1, head.y )); //删除蛇尾
	 * snake.removeLast(); }
	 */

	public static void main(String[] args) throws InterruptedException {
		final SnakeGame3 snake = new SnakeGame3();
		// 初始化地图
		snake.initBackground();

		// 初始化蛇
		snake.initSnake();
		// 显示蛇
		snake.showSnake();

		// 初始化食物
		snake.createFood();

		// 把食物显示到地图上
		snake.showFood();

		snake.showBackground();

		JFrame frame = new JFrame("方向盘");
		frame.add(new JButton("↑"), BorderLayout.NORTH);
		frame.add(new JButton("↓"), BorderLayout.SOUTH);
		frame.add(new JButton("←"), BorderLayout.WEST);
		frame.add(new JButton("右"), BorderLayout.EAST);
		JButton button = new JButton("点击控制方向");
		frame.add(button);
		FrameUtil.initFrame(frame, 300, 300);
		
		//为按钮添加事件
		button.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				switch (code) {
					//上
					case KeyEvent.VK_UP:
						snake.changeDirection(UP_DIRECTION);
						break;
					//下
					case KeyEvent.VK_DOWN:
						snake.changeDirection(DOWN_DIRECTION);
						break;
					//左
					case KeyEvent.VK_LEFT:
						snake.changeDirection(LEFT_DIRECTION);
						break;
					//右
					case KeyEvent.VK_RIGHT:
						snake.changeDirection(RIGHT_DIRECTION);
						break;
	
					default:
						break;
					}
					
					snake.move();
					snake.isGameOver();
					snake.refrash();
					//判断游戏是否结束
					if (isGameOver) {
						System.out.println("游戏结束");
						System.exit(0);
						
					}
				
			}
			
		});

		// 跳出循环的条件
		/*
		 * boolean flag = true; while(flag){
		 */

		// 移动蛇
		// 向上移动三步
		/*
		 * for (int i = 0; i < 3; i++) { snake.moveUp(); snake.refrash();
		 * Thread.sleep(500); }
		 */

		// 向右移动五步
		/*
		 * for (int i = 0; i < 10; i++) { snake.moveRight(); snake.refrash();
		 * Thread.sleep(500);
		 * 
		 * }
		 */

		// 向下移动三步
		/*
		 * for (int i = 0; i < 3; i++) { snake.moveDown(); snake.refrash();
		 * Thread.sleep(500); }
		 */

		// 向左移动五步
		/*
		 * for (int i = 0; i < 10; i++) { snake.moveLeft(); snake.refrash();
		 * Thread.sleep(500);
		 * 
		 * }
		 */

		// }

	}
}
