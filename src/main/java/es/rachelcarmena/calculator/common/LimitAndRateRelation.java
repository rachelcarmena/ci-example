package es.rachelcarmena.calculator.common;

import es.rachelcarmena.domain.Amount;

public class LimitAndRateRelation {

    protected Amount limitFrom;
    protected int rate;

    public LimitAndRateRelation(Amount limitFrom, int rate) {
        this.limitFrom = limitFrom;
        this.rate = rate;
    }
}
