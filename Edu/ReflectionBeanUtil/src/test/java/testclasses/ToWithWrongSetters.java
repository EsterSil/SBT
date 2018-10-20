package testclasses;

public class ToWithWrongSetters  implements To {
    private int intParameter;
    private String stringParameter;

    public void setIntParameter(Integer intParameter) {
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
