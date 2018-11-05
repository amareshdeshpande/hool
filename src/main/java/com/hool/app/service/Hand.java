package com.hool.app.service;

public class Hand {
	private String direction;
	private boolean[] cards = new boolean[52];

	public Hand() {
		for (int i = 0; i < 52; i++)
			cards[i] = false;
	}

	public Hand(Hand ahand) {
		for (int i = 0; i < 52; i++)
			cards[i] = ahand.getCard(i);
	}

	public Hand(String direction) {
		this.direction = direction;
		for (int i = 0; i < 52; i++)
			cards[i] = false;
	}

	public boolean getCard(int card) {
		return cards[card];

	}

	public void resetHand() {
		for (int i = 0; i < 52; i++)
			cards[i] = false;
	}

	public void setCard(int card) {
		cards[card] = true;
	}

	public void addCards(Hand hand) {
		for (int i = 0; i < 52; i++)
			if (hand.getCard(i))
				cards[i] = true;
	}

	public void setCards(Hand hand) {
		for (int i = 0; i < 52; i++)
			cards[i] = hand.getCard(i);
	}

	public int getNumberOfCards() {
		int card_numbers = 0;
		for (int i = 0; i < 52; i++)
			if (cards[i])
				card_numbers++;
		return card_numbers;
	}

	public int getNumberOfCardsBySuit(int offset) {
		int card_numbers = 0;
		for (int i = offset; i < offset + 13; i++)
			if (cards[i])
				card_numbers++;
		return card_numbers;
	}

	public void printHand() {
		String[] suits = { "C", "D", "H", "S" };
		String[] cards_print = { "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A" };

		for (int j = 3; j > -1; j--) {
			System.out.print(suits[j] + " ");
			for (int i = 12; i > -1; i--)
				if (cards[j * 13 + i])
					System.out.print(cards_print[i] + " ");
			System.out.println();

		}
		System.out.println();
		System.out.println();

	}

}
