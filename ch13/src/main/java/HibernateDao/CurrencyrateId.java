package HibernateDao;

/**
 * CurrencyrateId generated by MyEclipse Persistence Tools
 */

public class CurrencyrateId implements java.io.Serializable {

    // Fields

    private Currency currency;

    private Currency currency_1;

    private Short mouth1;

    private Double curRate;

    // Constructors

    /**
     * default constructor
     */
    public CurrencyrateId() {
    }

    /**
     * full constructor
     */
    public CurrencyrateId(Currency currency, Currency currency_1, Short mouth1,
                          Double curRate) {
        this.currency = currency;
        this.currency_1 = currency_1;
        this.mouth1 = mouth1;
        this.curRate = curRate;
    }

    // Property accessors

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency_1() {
        return this.currency_1;
    }

    public void setCurrency_1(Currency currency_1) {
        this.currency_1 = currency_1;
    }

    public Short getMouth1() {
        return this.mouth1;
    }

    public void setMouth1(Short mouth1) {
        this.mouth1 = mouth1;
    }

    public Double getCurRate() {
        return this.curRate;
    }

    public void setCurRate(Double curRate) {
        this.curRate = curRate;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof CurrencyrateId))
            return false;
        CurrencyrateId castOther = (CurrencyrateId) other;

        return ((this.getCurrency() == castOther.getCurrency()) || (this
                .getCurrency() != null
                && castOther.getCurrency() != null && this.getCurrency()
                .equals(castOther.getCurrency())))
                && ((this.getCurrency_1() == castOther.getCurrency_1()) || (this
                .getCurrency_1() != null
                && castOther.getCurrency_1() != null && this
                .getCurrency_1().equals(castOther.getCurrency_1())))
                && ((this.getMouth1() == castOther.getMouth1()) || (this
                .getMouth1() != null
                && castOther.getMouth1() != null && this.getMouth1()
                .equals(castOther.getMouth1())))
                && ((this.getCurRate() == castOther.getCurRate()) || (this
                .getCurRate() != null
                && castOther.getCurRate() != null && this.getCurRate()
                .equals(castOther.getCurRate())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getCurrency() == null ? 0 : this.getCurrency().hashCode());
        result = 37
                * result
                + (getCurrency_1() == null ? 0 : this.getCurrency_1()
                .hashCode());
        result = 37 * result
                + (getMouth1() == null ? 0 : this.getMouth1().hashCode());
        result = 37 * result
                + (getCurRate() == null ? 0 : this.getCurRate().hashCode());
        return result;
    }

}