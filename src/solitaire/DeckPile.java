package solitaire;

import java.awt.Graphics;
import java.util.Collections;

public class DeckPile extends CardPile {

	public DeckPile(int x, int y) {
		super(x, y);
		for (int i = 1; i <= 52; i++) {//向初始牌堆中加入52张牌
			Card aCard = new Card(i);
			pile.add(aCard);
		}
		Collections.shuffle(pile);//洗牌
	}

	@Override
	public boolean canTake(Card aCard) {//不接受来自其他牌堆的任何牌
		return false;
	}

	@Override
	public void select(int x, int y) {
		if (!empty()) {//若不为空，则向暂置牌堆加一张牌
			Card aCard = pop();
			Solitaire.discardPile.addCard(aCard);
		} else {
			//若为空，则把暂置牌堆中的牌全部加到该牌堆
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
			topCard().faceup = false;//初始牌堆中的所有牌均是背面朝上
			topCard().draw(g, x, y);
		}

	}

}
