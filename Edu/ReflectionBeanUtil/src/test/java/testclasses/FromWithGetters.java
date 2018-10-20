package testclasses;

public class FromWithGetters implements From{
    private int intParameter;
    private String stringParameter;

    public FromWithGetters(int intParameter, String stringParameter) {
        this.intParameter = intParameter;
        this.stringParameter = stringParameter;
    }

    public int getIntParameter() {
        return intParameter;
    }

    public String getStringParameter() {
        return stringParameter;
    }

    @Override
    public String toString() {
        return "FromWithGetters{" +
                "intParameter=" + intParameter +
                ", stringParameter='" + stringParameter + '\'' +
                '}';
    }
}
