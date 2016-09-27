package solitaire;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Card {
	public static int width = 90;//�ƵĿ��
	public static int height = 128;//�Ƶĳ���
	public boolean faceup = false;//�����Ƿ���
	public int num;//�Ƶĵ���
	public int suit;//�ƵĻ�ɫ
	public int number;//���ڳ����е����

	public Card(int n) {
		//�����ڳ����е���ż����Ƶĵ����ͻ�ɫ
		//1-13Ϊ��Ƭ��14-26Ϊ���ң�27-39Ϊ÷����40-52Ϊ����
		num = n;
		suit = num / 13 + 1;
		if (suit == 5) {
			suit = 4;
		}
		number = num % 13;
		if (number == 0) {
			number = 13;
		}
	}

	public void draw(Graphics g, int x, int y) {
		//�������ƣ�������泯�ϣ�����ݻ�ɫ�͵���������Ӧ����
		if (faceup == true) {
			ImageIcon pic = null;

			switch (suit) {
			case 1:
				switch (number) {
				case 1:
					pic = new ImageIcon("res/f1.jpg");
					break;
				case 2:
					pic = new ImageIcon("res/f2.jpg");
					break;
				case 3:
					pic = new ImageIcon("res/f3.jpg");
					break;
				case 4:
					pic = new ImageIcon("res/f4.jpg");
					break;
				case 5:
					pic = new ImageIcon("res/f5.jpg");
					break;
				case 6:
					pic = new ImageIcon("res/f6.jpg");
					break;
				case 7:
					pic = new ImageIcon("res/f7.jpg");
					break;
				case 8:
					pic = new ImageIcon("res/f8.jpg");
					break;
				case 9:
					pic = new ImageIcon("res/f9.jpg");
					break;
				case 10:
					pic = new ImageIcon("res/f10.jpg");
					break;
				case 11:
					pic = new ImageIcon("res/f11.jpg");
					break;
				case 12:
					pic = new ImageIcon("res/f12.jpg");
					break;
				case 13:
					pic = new ImageIcon("res/f13.jpg");
					break;
				default:
					pic = null;
					break;
				}
				break;
			case 2:
				switch (number) {
				case 1:
					pic = new ImageIcon("res/h1.jpg");
					break;
				case 2:
					pic = new ImageIcon("res/h2.jpg");
					break;
				case 3:
					pic = new ImageIcon("res/h3.jpg");
					break;
				case 4:
					pic = new ImageIcon("res/h4.jpg");
					break;
				case 5:
					pic = new ImageIcon("res/h5.jpg");
					break;
				case 6:
					pic = new ImageIcon("res/h6.jpg");
					break;
				case 7:
					pic = new ImageIcon("res/h7.jpg");
					break;
				case 8:
					pic = new ImageIcon("res/h8.jpg");
					break;
				case 9:
					pic = new ImageIcon("res/h9.jpg");
					break;
				case 10:
					pic = new ImageIcon("res/h10.jpg");
					break;
				case 11:
					pic = new ImageIcon("res/h11.jpg");
					break;
				case 12:
					pic = new ImageIcon("res/h12.jpg");
					break;
				case 13:
					pic = new ImageIcon("res/h13.jpg");
					break;
				default:
					pic = null;
					break;
				}
				break;
			case 3:
				switch (number) {
				case 1:
					pic = new ImageIcon("res/m1.jpg");
					break;
				case 2:
					pic = new ImageIcon("res/m2.jpg");
					break;
				case 3:
					pic = new ImageIcon("res/m3.jpg");
					break;
				case 4:
					pic = new ImageIcon("res/m4.jpg");
					break;
				case 5:
					pic = new ImageIcon("res/m5.jpg");
					break;
				case 6:
					pic = new ImageIcon("res/m6.jpg");
					break;
				case 7:
					pic = new ImageIcon("res/m7.jpg");
					break;
				case 8:
					pic = new ImageIcon("res/m8.jpg");
					break;
				case 9:
					pic = new ImageIcon("res/m9.jpg");
					break;
				case 10:
					pic = new ImageIcon("res/m10.jpg");
					break;
				case 11:
					pic = new ImageIcon("res/m11.jpg");
					break;
				case 12:
					pic = new ImageIcon("res/m12.jpg");
					break;
				case 13:
					pic = new ImageIcon("res/m13.jpg");
					break;
				default:
					pic = null;
					break;
				}
				break;
			case 4:
				switch (number) {
				case 1:
					pic = new ImageIcon("res/t1.jpg");
					break;
				case 2:
					pic = new ImageIcon("res/t2.jpg");
					break;
				case 3:
					pic = new ImageIcon("res/t3.jpg");
					break;
				case 4:
					pic = new ImageIcon("res/t4.jpg");
					break;
				case 5:
					pic = new ImageIcon("res/t5.jpg");
					break;
				case 6:
					pic = new ImageIcon("res/t6.jpg");
					break;
				case 7:
					pic = new ImageIcon("res/t7.jpg");
					break;
				case 8:
					pic = new ImageIcon("res/t8.jpg");
					break;
				case 9:
					pic = new ImageIcon("res/t9.jpg");
					break;
				case 10:
					pic = new ImageIcon("res/t10.jpg");
					break;
				case 11:
					pic = new ImageIcon("res/t11.jpg");
					break;
				case 12:
					pic = new ImageIcon("res/t12.jpg");
					break;
				case 13:
					pic = new ImageIcon("res/t13.jpg");
					break;
				default:
					pic = null;
					break;
				}
				break;
			default:
				pic = null;
				break;
			}
			Image img = pic.getImage();
			g.drawImage(img, x, y, width, height, null);
		} else {
			//�����泯���򻭳��Ƶı���
			ImageIcon pic = new ImageIcon("res/back.jpg");
			Image img = pic.getImage();
			g.drawImage(img, x, y, width, height, null);
		}
	}
}
