package com.leveluptor.opengoblin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TODO add description
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Auction {

    private final long auc;
    private final int item;
    private final String owner;
    private final long bid;
    private final long buyout;
    private final int quantity;

    @JsonCreator
    public Auction(@JsonProperty("auc") long auc,
                   @JsonProperty("item") int item,
                   @JsonProperty("owner") String owner,
                   @JsonProperty("bid") long bid,
                   @JsonProperty("buyout") long buyout,
                   @JsonProperty("quantity") int quantity) {
        this.auc = auc;
        this.item = item;
        this.owner = owner;
        this.bid = bid;
        this.buyout = buyout;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "auc=" + auc +
                ", item=" + item +
                ", owner='" + owner + '\'' +
                ", bid=" + bid +
                ", buyout=" + buyout +
                ", quantity=" + quantity +
                '}';
    }
}
