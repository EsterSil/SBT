package testclasses;

public class ToWithSetters implements To{
    private int intParameter;
    private String stringParameter;

    public void setIntParameter(int intParameter) {
        this.intParameter = intParameter;
    }

    public void setStringParameter(String stringParameter) {
        this.stringParameter = stringParameter;
    }

    public int getIntParameter() {
        return intParameter;
    }

    public String getStringParameter() {
        return stringParameter;
    }
}
