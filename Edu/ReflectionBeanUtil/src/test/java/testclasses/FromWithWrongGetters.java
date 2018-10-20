package testclasses;

public class FromWithWrongGetters implements From {
    private int intParameter;
    private String stringParameter;

    public FromWithWrongGetters(int intParameter, String stringParameter) {
        this.intParameter = intParameter;
        this.stringParameter = stringParameter;
    }

    public void getIntParameter() {
        return;
    }

    public void getStringParameter() {
        return ;
    }

    @Override
    public String toString() {
        return "FromWithWrongGetters{" +
                "intParameter=" + intParameter +
                ", stringParameter='" + stringParameter + '\'' +
                '}';
    }
}
