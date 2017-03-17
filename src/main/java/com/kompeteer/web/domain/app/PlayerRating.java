package com.kompeteer.web.domain.app;

import com.kompeteer.web.domain.Player;

public class PlayerRating {
	private final Player player;
	private int rating;

	public PlayerRating(Player player, int initialRating) {
		this.player = player;
		rating = initialRating;
	}

	public int getRating() {
		return rating;
	}

	public Player getPlayer() {
		return player;
	}

	public PlayerRating adjust(int adjustment) {
		rating += adjustment;

		return this;
	}
}
