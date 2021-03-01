package io.qiot.covid19.datahub.importer.domain.dto;

/**
 * The Enum HistoricalDataPeriod.
 *
 * @author andreabattaglia
 */
public enum HistoricalDataPeriod {

    /** The p2020q1. */
    LATEST(""),

    /** The p2020q1. */
    P2020Q1("2020Q1"),

    /** The p2020q2. */
    P2020Q2("2020Q2"),

    /** The p2020q3. */
    P2020Q3("2020Q3"),

    /** The p2020q4. */
    P2020Q4("2020Q4"),

    /** The p2019q1. */
    P2019Q1("2019Q1"),

    /** The p2019q2. */
    P2019Q2("2019Q2"),

    /** The p2019q3. */
    P2019Q3("2019Q3"),

    /** The p2019q4. */
    P2019Q4("2019Q4"),

    /** The p2018h1. */
    P2018H1("2018H1"),

    /** The p2017h1. */
    P2017H1("2017H1"),

    /** The p2016h1. */
    P2016H1("2016H1"),

    /** The p2015h1. */
    P2015H1("2015H1");

    /** The period. */
    private final String period;

    /**
     * Instantiates a new historical data period.
     *
     * @param period
     *            the period
     */
    HistoricalDataPeriod(String period) {
        this.period = period;
    }
    
    public String getPeriod() {
        return period;
    }

}
