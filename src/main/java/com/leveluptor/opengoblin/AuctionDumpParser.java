package com.leveluptor.opengoblin;

import java.io.File;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * TODO add description
 */
@Component
public class AuctionDumpParser {

    private static final Logger logger = LoggerFactory.getLogger(AuctionDumpParser.class);

    @Scheduled(fixedRate = 10000)
    public void scheduledParse() {
        parse("src/main/resources/auctions.json");
    }

    public long parse(String path) {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory jsonFactory = mapper.getFactory();

        long counter = 0;

        try (JsonParser jp = jsonFactory.createParser(new File(path))) {

            while (jp.nextToken() != JsonToken.END_OBJECT) {

                jp.nextValue();

                if ("realm".equals(jp.getCurrentName())) {
                    jp.skipChildren();
                } else if ("auctions".equals(jp.getCurrentName())) {

                    jp.nextValue();

                    while (jp.nextToken() != JsonToken.END_OBJECT) {
                        JsonToken token = jp.nextToken();

                        Iterator<Auction> auctionIterator = jp.readValuesAs(Auction.class);

                        if (token == JsonToken.FIELD_NAME && auctionIterator.hasNext()) {
                            Auction auction = auctionIterator.next();
                            logger.info(auction.toString());
                            counter++;
                        }
                    }
                    break; //TODO get rid of it

                } else {
                    logger.warn("Ouch: " + jp.getCurrentName());
                }
            }

        } catch (Exception e) {
            logger.error("Error while parsing JSON: ", e);
        }
        return counter;
    }
}
