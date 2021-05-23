package TheGlidedRosePackage;

class GildedRose {
	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (int i = 0; i < items.length; i++) {
			handleQuality(items[i]);
			updateSellIn(items[i]);

		}
	}

	private void handleQuality(Item item) {
		if (!item.name.equals("Aged Brie")
				&& !item.name
						.equals("Backstage passes to a TAFKAL80ETC concert")) {
			decreaseIQualityIfHasQuality(item);
		} else {
			increaseQualityInclusebackstage(item);
		}

	}

	private void decreaseIQualityIfHasQuality(Item item) {
		if (item.quality > 0) {
			decreaseQualityNotSulfuras(item);
		}
	}

	// decreases Quality
	private void decreaseQualityNotSulfuras(Item item) {
		if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
			item.quality--;
		}
	}

	// increases some items Quality also if its Backstage passes
	private void increaseQualityInclusebackstage(Item item) {
		if (item.quality < 50) {
			item.quality++;
			increasesBackstageQualityIfCloseToConcert(item);
		}
	}

	// increases the Backstage passes Quality when it gets near to concert.
	private void increasesBackstageQualityIfCloseToConcert(Item item) {
		if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
			increaseBackstageQualityIfLeft10DaysTillConcert(item);
			increaseBackstageQualityIfLeft5DaysTillConcert(item);
		}
	}

	// increases the Backstage passes Quality when its 10 days to concert.
	private void increaseBackstageQualityIfLeft10DaysTillConcert(Item item) {
		if (item.sellIn < 11) {
			increaseQuality(item);
		}
	}

	// increases the Backstage passes Quality again when its 5 days to concert.
	private void increaseBackstageQualityIfLeft5DaysTillConcert(Item item) {
		if (item.sellIn < 6) {
			increaseQuality(item);
		}
	}

	// increases Quality
	private void increaseQuality(Item item) {
		if (item.quality < 50) {
			item.quality++;
		}
	}

	// updates the sellIn
	private void updateSellIn(Item item) {
		if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
			item.sellIn--;
		}
		ifItemExpired(item);
	}

	// checks if the sellIin is negative - the Date Expired
	private void ifItemExpired(Item item) {
		if (item.sellIn < 0) {
			handleExpiredItem(item);
		}
	}

	// handles the quality of items when they are expired
	private void handleExpiredItem(Item item) {
		if (!item.name.equals("Aged Brie")) {
			if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
				decreaseIQualityIfHasQuality(item);
			} else {
				item.quality = 0;
			}
		} else {
			increaseQuality(item);
		}
	}
}