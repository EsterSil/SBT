package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.sbt.mipt.oop.alarm.Activated;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.Disabled;
import ru.sbt.mipt.oop.alarm.Signaling;

public class SignalingTest {

    private static Signaling signaling= new Signaling();


    @Test
    public void activateFromDisabledTest() {
        //Signaling signaling = new Signaling();
        signaling.activate( "145236");
        Assert.assertTrue(signaling.getState() instanceof Activated);
    }

    @Test
    public void activateFromActivatedTest() {
        //Signaling signaling = new Signaling();
        signaling.activate( "145236");
        Assert.assertTrue(signaling.getState() instanceof Activated);
        signaling.activate( "31");
        Assert.assertTrue(signaling.getState() instanceof Alarm);
        Assert.assertTrue(!signaling.checkCodeConcept("31"));

    }

    @Test
    public void activateFromAlarmTest() {
        //Signaling signaling = new Signaling();
        signaling.activate( "145236");
        Assert.assertTrue(signaling.getState() instanceof Activated);
        signaling.activate( "31");
        Assert.assertTrue(signaling.getState() instanceof Alarm);
        signaling.activate( "1");
        Assert.assertTrue(signaling.getState() instanceof Alarm);
        Assert.assertTrue(!signaling.checkCodeConcept("1"));

    }

    @Test
    public void deactivateFromActivatedWithRightCodeTest() {
        //Signaling signaling = new Signaling();
        signaling.activate( "145236");
        signaling.deactivate( "145236");

        Assert.assertTrue(signaling.getState() instanceof Disabled);
        Assert.assertTrue(signaling.checkCodeConcept("0000"));
    }
    @Test
    public void deactivateFromActivatedWithWrongCodeTest() {
        //Signaling signaling = new Signaling();
        signaling.activate( "145236");
        signaling.deactivate( "31");

        Assert.assertTrue(signaling.getState() instanceof Alarm);
        //Assert.assertTrue(signaling.checkCodeConcept("0000"));
    }

    @Test
    public void deactivateFromDeactivatedTest() {
        //Signaling signaling = new Signaling();
        signaling.deactivate( "31");
        Assert.assertTrue(signaling.getState() instanceof Disabled);
        Assert.assertTrue(signaling.checkCodeConcept("0000"));
    }

    @Test
    public void deactivateFromAlarmTest() {
        //Signaling signaling = new Signaling();
        signaling.activate( "145236");
        signaling.deactivate( "31");
        Assert.assertTrue(signaling.getState() instanceof Alarm);
        signaling.deactivate( "145236");
        Assert.assertTrue(signaling.getState() instanceof Disabled);
        Assert.assertTrue(signaling.checkCodeConcept("0000"));
    }

}
