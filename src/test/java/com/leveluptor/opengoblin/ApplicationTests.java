package com.leveluptor.opengoblin;

import org.junit.Test;

public class ApplicationTests {

    private AuctionDumpParser parser = new AuctionDumpParser();

    @Test
    public void shouldParseNormalSortedEntries() throws Exception { //TODO better name plzzz...
        parser.parse("src/test/resources/auctions_excerpt.json");
    }

    @Test
    public void shouldParseSwappedEntries() throws Exception {
        parser.parse("src/test/resources/auctions_excerpt_swapped.json");
    }

}
