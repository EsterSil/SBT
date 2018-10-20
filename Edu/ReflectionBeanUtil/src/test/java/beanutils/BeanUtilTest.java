package beanutils;
import org.junit.Assert;
import org.junit.Test;
import testclasses.*;

public class BeanUtilTest {

    @Test
    public void assignWithGoodClassesTest() {
        From from  = new FromWithGetters(12, "hello");
        To to = new ToWithSetters();
        BeanUtils.assign(to, from );
        Assert.assertEquals(12, ((ToWithSetters) to).getIntParameter());
        Assert.assertEquals("hello", ((ToWithSetters) to).getStringParameter() );

    }

    @Test
    public void assignWithBadFromTest() {
        From fromWithWrongGetters = new FromWithWrongGetters(15, "bay");
        To to = new ToWithSetters();
        BeanUtils.assign(to, fromWithWrongGetters);
        Assert.assertEquals(0, ((ToWithSetters) to).getIntParameter());
        Assert.assertTrue(null ==((ToWithSetters) to).getStringParameter());
    }
    @Test
    public void assignWithBadToTest() {
        From from = new FromWithGetters(22, "cat");
        To toWithWrongSetters = new ToWithWrongSetters();
        BeanUtils.assign(toWithWrongSetters, from);
        Assert.assertEquals(0, ((ToWithWrongSetters) toWithWrongSetters).getIntParameter());
        Assert.assertEquals("cat", ((ToWithWrongSetters) toWithWrongSetters).getStringParameter());
    }
    @Test
    public void assignWithoutFromTest() {
        From from = new FromWithoutGetters(35, "dog");
        To to = new ToWithSetters();
        BeanUtils.assign(to, from);
        Assert.assertEquals(0, ((ToWithSetters) to).getIntParameter());
        Assert.assertTrue(null == ((ToWithSetters) to).getStringParameter());
    }
    @Test
    public void assignNullTest() {
        From from = new FromWithGetters(2, "bird");
        To to = new ToWithSetters();
        BeanUtils.assign(null, from);
        Assert.assertEquals(0, ((ToWithSetters) to).getIntParameter());
        Assert.assertTrue(null == ((ToWithSetters) to).getStringParameter());
    }
}
