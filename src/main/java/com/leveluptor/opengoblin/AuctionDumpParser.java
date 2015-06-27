package com.leveluptor.opengoblin;

import java.io.File;

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

    Logger logger = LoggerFactory.getLogger(AuctionDumpParser.class);

    @Scheduled(fixedRate=10000)
    public void parse() {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory jsonFactory = mapper.getFactory();
        try (JsonParser jp = jsonFactory.createParser(new File("src/main/resources/auctions_small.json"))) {

            while (jp.nextToken() != JsonToken.END_OBJECT) {

                jp.nextTextValue();

                if ("realm".equals(jp.getCurrentName())) {
                    jp.nextTextValue();
                    jp.skipChildren();
                } else if ("auctions".equals(jp.getCurrentName())) {
                    jp.nextTextValue();

                    while (jp.nextToken() != JsonToken.END_OBJECT) {
                        JsonToken token = jp.nextToken();
                        if (token != JsonToken.END_OBJECT && jp.readValuesAs(Auction.class).hasNext()) {
                            Auction auction = jp.readValuesAs(Auction.class).next();
                            logger.info(auction.toString());
                        }
                    }
                    break;
                } else {
                    logger.warn("Ouch: " + jp.getCurrentName());
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
