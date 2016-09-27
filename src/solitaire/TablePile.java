package solitaire;

import java.awt.Graphics;

public class TablePile extends CardPile {
	public int cnum;//���ƶѵĳ�ʼ��Ŀ

	public TablePile(int x, int y, int num) {
		super(x, y);
		cnum = num;
	}

	public Card get(int i) {
		return (Card) pile.get(i);//�õ����ƶѵĵ�i����
	}

	@Override
	public boolean canTake(Card aCard) {
		//���ƶѵĽ��ܹ���ΪaCard�Ļ�ɫ�뵱ǰ�ƶ�������һ�����෴����ڼ��෴��������С1
		if (empty() && aCard.number == 13) {
			return true;
		} else if (empty() && aCard.number != 13) {
			return false;
		} else if ((topCard().suit == 1 || topCard().suit == 4)
				&& (aCard.suit == 2 || aCard.suit == 3)
				&& topCard().number == aCard.number + 1) {
			return true;
		} else if ((topCard().suit == 2 || topCard().suit == 3)
				&& (aCard.suit == 1 || aCard.suit == 4)
				&& topCard().number == aCard.number + 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void select(int x, int y) {
		//ѡ�и��ƶѺ��ĳЩ�Ƽ����ƶ��ƶ�
		if (!empty()) {
			int n = locate(x, y);//ȷ�����ͣ����������
			if (get(pile.size() - n).faceup != true) {//�ж������ͣ�������Ƿ����泯�ϣ������泯�ϣ�������ƶ����Ƽ����������
			} else {
				Solitaire.movePile.setLocation(this.x, this.y
						+ (pile.size() - n) * 30);
				for (int i = 0; i < n; i++) {
					if (topCard().faceup) {
						Card aCard = pop();
						Solitaire.movePile.addCard(aCard);
					}
				}
			}
		}
	}

	//����������ڵ�λ���ж�ѡ�е�������
	private int locate(int tx, int ty) {
		int n = 1;
		int temp = y;
		for (int i = 0; i < pile.size() - 1; i++) {
			if (ty > temp + 30) {
				n++;
				temp = temp + 30;
			} else {
				return (pile.size() - n + 1);
			}
		}
		return (pile.size() - n + 1);
	}

	public boolean includes(int tx, int ty) {//��д�˸���ķ�������Ϊ�����ƶѻ���������ӵ�е�������
		return x <= tx && tx <= x + Card.width && y <= ty
				&& ty <= y + 30 * (pile.size() - 1) + Card.height;
	}

	public void draw(Graphics g) {
		//����Ϊ�գ��򻭳�������
		if (empty()) {
			g.drawRect(x, y, Card.width, Card.height);
		} else {
			for (int i = 0; i < pile.size(); i++) {
				((Card) pile.get(i)).draw(g, x, y + 30 * i);
			}
		}
	}

}
