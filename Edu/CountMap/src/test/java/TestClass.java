import java.util.Objects;

public class TestClass {
    public int getParameter() {
        return parameter;
    }

    private  int parameter;

    public TestClass(int parameter) {
        this.parameter = parameter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestClass testClass = (TestClass) o;
        return parameter == testClass.parameter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameter);
    }
}
