package com.leveluptor.opengoblin;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApplicationTests {

    private AuctionDumpParser parser = new AuctionDumpParser();

    @Test
    public void shouldParseNormalSortedEntries() throws Exception { //TODO better name plzzz...
        assertEquals(4, parser.parse("src/test/resources/auctions_excerpt.json"));
    }

    @Test
    public void shouldParseSwappedEntries() throws Exception {
        assertEquals(4, parser.parse("src/test/resources/auctions_excerpt_swapped.json"));
    }

}
