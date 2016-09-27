package solitaire;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

public abstract class CardPile {
	protected List pile;//�ƵĴ洢�ṹ
	protected int x;//�ƶѵ�x����
	protected int y;//�ƶѵ�y����

	public CardPile(int x, int y) {
		pile = new LinkedList();
		this.x = x;
		this.y = y;
	}

	public int size() {//�����ƶѵ�����
		return pile.size();
	}

	public void setLocation(int x, int y) {//�����ƶѵ�λ��
		this.x = x;
		this.y = y;
	}

	public boolean empty() {//�����ƶ��Ƿ�Ϊ��
		return pile.isEmpty();
	}

	public Card topCard() {//�õ��ƶ�������һ����
		if (!empty())
			return (Card) pile.get(pile.size() - 1);
		else
			return null;
	}

	public Card pop() {//�õ��ƶ��������һ����
		if (!empty())
			return (Card) pile.remove(pile.size() - 1);
		else
			return null;
	}

	public boolean includes(int tx, int ty) {//�ж�����Ƿ�ͣ���ڸ��ƶ���
		return x <= tx && tx <= x + Card.width && y <= ty
				&& ty <= y + Card.height;
	}

	public void addCard(Card aCard) {//���ƶ��м���һ����
		pile.add(aCard);
	}

	public void draw(Graphics g) {//�������ƶ�
		if (empty()) {//����ƶ�Ϊ�գ���һ�����ƴ�С�ľ���
			g.drawRect(x, y, Card.width, Card.height);
		} else
			//����������һ����
			topCard().draw(g, x, y);
	}

	public int getX() {//�õ��ƶѵ�x����
		return x;
	}

	public int getY() {//�õ��ƶѵ�y����
		return y;
	}

	public abstract boolean canTake(Card aCard);//�ж��ƶ��Ƿ���Լ���aCard������

	public abstract void select(int x, int y);//ѡ���ƶѺ�Ĳ���
}
