package solitaire;

import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;

public class Solitaire extends JPanel implements MouseInputListener {
	static DeckPile deckPile;//��ʼ�ƶ�
	static DiscardPile discardPile;//�����ƶ�
	static TablePile tableau[];//�����ƶ�
	static SuitPile suitPile[];//Ŀ���ƶ�
	static CardPile allPiles[];//�����ƶ�
	static MovePile movePile;//�ƶ��ƶ�
	private Point point = new Point(0, 0);//�����ƶ��ƶѵ���ʱ��
	private CardPile home = null;//��¼�ƶ��ƶѵ���Դ

	public Solitaire() {//������
		setBackground(new Color(14, 85, 7));//���ñ���
		addMouseListener(this);//��Ӽ�����
		addMouseMotionListener(this);
		allPiles = new CardPile[14];
		suitPile = new SuitPile[4];
		tableau = new TablePile[7];
		int deckPos = 20;//��ʼ�ƶѵ�λ��
		int suitPos = 320;//Ŀ���ƶѵ�λ��
		//�½��ƶ�
		allPiles[0] = deckPile = new DeckPile(deckPos, 5);
		allPiles[1] = discardPile = new DiscardPile(deckPos + Card.width + 20,
				5);
		allPiles[13] = movePile = new MovePile(0, 0);
		for (int i = 0; i < 4; i++)
			allPiles[2 + i] = suitPile[i] = new SuitPile(suitPos
					+ (Card.width + 30) * i, 5);
		for (int i = 0; i < 7; i++)
			allPiles[6 + i] = tableau[i] = new TablePile(30 + (Card.width + 20)
					* i, 5 + Card.height + 15, i + 1);
		//Ϊ�����ƶ���ӿ���
		for (int i = 0; i < 7; i++) {
			for (int j = 1; j <= i + 1; j++) {
				Card aCard = deckPile.pop();
				tableau[i].addCard(aCard);
			}
			//�������ƶѵĵ�һ������������
			tableau[i].topCard().faceup = true;
		}
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//����ÿ����
		for (int i = 0; i < 14; i++)
			allPiles[i].draw(g);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(800, 600);
		frame.setTitle(" S o l i t a i r e ");
		Solitaire s = new Solitaire();
		frame.add(s);
		frame.validate();
		s.repaint();
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		point.setLocation(x, y);//����㣬Ϊ֮��movePile���ƶ��ռ�λ����Ϣ
		//�ж��ĸ��ƶѱ������������ѡ�е��Ƽ���movePile������movePile���Ƶ���Դ
		for (int i = 0; i < 13; i++) {
			if (allPiles[i].includes(x, y)) {//�ж��ĸ��ƶѱ����
				allPiles[i].select(x, y);//����ѡ�е��Ƽ���movePile
				home = allPiles[i];//����movePile���Ƶ���Դ
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		//��movePile�����ƣ����ͷ����ʱ���������Ӧ���ƶѣ����ܷ���������ڵ��ƶѣ����ܷŻ�ԭ�ƶ�
		if (!movePile.empty()) {
			for (int i = 0; i < 13; i++) {
				if (allPiles[i].includes(x, y)) {//�ж����ͣ�����ĸ��ƶ�
					if (allPiles[i].canTake(movePile.topCard())) {//�жϸ��ƶ��Ƿ���Խ�������movePile����
						int t = movePile.size();//��¼��ǰmovePile�Ĵ�С
						//���ƶ���ӿ���
						for (int j = 0; j < t; j++) {
							Card aCard = movePile.pop();
							allPiles[i].addCard(aCard);
						}
						//��Դ�ƶѵĵ�һ������Ϊ���泯��
						if (!home.empty()) {
							home.topCard().faceup = true;
						}
						home = null;
					} else {
						//�����ƶѲ��ܽ�������movePile���ƣ������ǷŻ�Դ�ƶ�
						int t = movePile.size();
						for (int j = 0; j < t; j++) {
							Card aCard = movePile.pop();
							home.addCard(aCard);
							home = null;
						}
					}
				}
			}
			//���û���ƶѱ�ѡ�У����movePile�е��ƷŻ�Դ�ƶ�
			if (home != null) {
				int t = movePile.size();
				for (int j = 0; j < t; j++) {
					Card aCard = movePile.pop();
					home.addCard(aCard);
					home = null;
				}
			}
		}
		repaint();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (!movePile.empty()) {
			int x = e.getX();
			int y = e.getY();
			Point newPoint = new Point(x, y);//��¼��ǰ��
			//����movePile��λ�ã�ע�ⲻ�ǵ�ǰ������ڵ�λ�ã�������ԭλ�ü����ƶ��ľ���
			movePile.setLocation(movePile.getX() + (newPoint.x - point.x),
					movePile.getY() + (newPoint.y - point.y));
			point = newPoint;
			repaint();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
