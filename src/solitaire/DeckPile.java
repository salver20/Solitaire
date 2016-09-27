package solitaire;

import java.awt.Graphics;
import java.util.Collections;

public class DeckPile extends CardPile {

	public DeckPile(int x, int y) {
		super(x, y);
		for (int i = 1; i <= 52; i++) {//���ʼ�ƶ��м���52����
			Card aCard = new Card(i);
			pile.add(aCard);
		}
		Collections.shuffle(pile);//ϴ��
	}

	@Override
	public boolean canTake(Card aCard) {//���������������ƶѵ��κ���
		return false;
	}

	@Override
	public void select(int x, int y) {
		if (!empty()) {//����Ϊ�գ����������ƶѼ�һ����
			Card aCard = pop();
			Solitaire.discardPile.addCard(aCard);
		} else {
			//��Ϊ�գ���������ƶ��е���ȫ���ӵ����ƶ�
			int t = Solitaire.discardPile.size();
			for (int i = 0; i < t; i++) {
				Card aCard = Solitaire.discardPile.pop();
				addCard(aCard);
			}
		}

	}

	public void draw(Graphics g) {
		if (empty()) {
			g.drawRect(x, y, Card.width, Card.height);
		} else {
			topCard().faceup = false;//��ʼ�ƶ��е������ƾ��Ǳ��泯��
			topCard().draw(g, x, y);
		}

	}

}
