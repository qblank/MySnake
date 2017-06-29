package cn.qblank.snake;

public class SnakeGame1 {                     
	public static final int HEIGHT = 10; // 高
	public static final int WIDTH = 30; // 宽

	public static void main(String[] args) {
		drawMap();

	}

	/**
	 * 打印地图
	 */
	public static void drawMap() {

		char[][] background = new char[HEIGHT][WIDTH];

		for (int row = 0; row < background.length; row++) {
			for (int cols = 0; cols < background[row].length; cols++) {
				if (row == 0 || row == HEIGHT - 1 || cols == 0
						|| cols == WIDTH - 1) {
					background[row][cols] = '*';
				} else {
					background[row][cols] = ' ';
				}
			}

		}

		// 再在图中画一条蛇
		for (int i = 3; i <= 15; i++) {
			if (i < 15) {
				background[3][i] = '#';
			} else {
				background[3][i] = '$';
			}

		}

		showMap(background);
	}

	/**
	 * 打印地图
	 * 
	 * @param background
	 *            数组
	 */
	private static void showMap(char[][] background) {
		for (int i = 0; i < background.length; i++) {
			for (int j = 0; j < background[i].length; j++) {
				System.out.print(background[i][j]);
			}
			System.out.println();
		}
	}
}
