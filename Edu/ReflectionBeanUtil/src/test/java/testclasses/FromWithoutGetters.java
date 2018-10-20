package testclasses;

public class FromWithoutGetters implements From {
    private int intParameter;
    private String stringParameter;

    public FromWithoutGetters(int intParameter, String stringParameter) {
        this.intParameter = intParameter;
        this.stringParameter = stringParameter;
    }

    @Override
    public String toString() {
        return "FromWithoutGetters{" +
                "intParameter=" + intParameter +
                ", stringParameter='" + stringParameter + '\'' +
                '}';
    }
}
