package Tests;

public class ddk {
    private final String pan;
    private final String exp;
    private final String holder;
    private final String cvv;

    public ddk(String pan, String exp, String holder, String cvv) {
        this.pan = pan;
        this.exp = exp;
        this.holder = holder;
        this.cvv = cvv;
    }

    public String getPan() {
        return pan;
    }

    public String getExp() {
        return exp;
    }

    public String getHolder() {
        return holder;
    }

    public String getCvv() {
        return cvv;
    }
}
