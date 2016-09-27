package solitaire;

public class SuitPile extends CardPile {

	public SuitPile(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean canTake(Card aCard) {
		//Ŀ���ƶѵĽ��ܹ���Ϊ��ɫ��ͬ���ҽ�Ҫ������Ʊȵ�ǰ������һ���Ƶĵ�����1
		if (empty() && aCard.number == 1) {
			return true;
		} else if (empty() && aCard.number != 1) {
			return false;
		} else if (topCard().suit == aCard.suit
				&& topCard().number == aCard.number - 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void select(int x, int y) {
		if (!empty()) {//������ƶѱ�ѡ�У����������һ���Ƽ����ƶ��ƶѣ������ƶ��ƶѵĳ�ʼλ����Ϊ���ƶ�����λ��
			Card aCard = pop();
			Solitaire.movePile.addCard(aCard);
			Solitaire.movePile.setLocation(this.x, this.y);
		}
	}

}
