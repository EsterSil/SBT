package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.alarm.Activated;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.Disabled;
import ru.sbt.mipt.oop.alarm.Signaling;
 class SignalingTest {

    private Signaling signaling= new Signaling();


    @Test
     void activateFromDisabledTest() {
        //Signaling signaling = new Signaling();
        signaling.activate( "145236");
        Assertions.assertTrue(signaling.getState() instanceof Activated);
    }

    @Test
     void activateFromActivatedTest() {
        //Signaling signaling = new Signaling();
        signaling.activate( "145236");
        Assertions.assertTrue(signaling.getState() instanceof Activated);
        signaling.activate( "31");
        Assertions.assertTrue(signaling.getState() instanceof Alarm);
        Assertions.assertTrue(!signaling.checkCodeConcept("31"));

    }

    @Test
     void activateFromAlarmTest() {
        //Signaling signaling = new Signaling();
        signaling.activate( "145236");
        Assertions.assertTrue(signaling.getState() instanceof Activated);
        signaling.activate( "31");
        Assertions.assertTrue(signaling.getState() instanceof Alarm);
        signaling.activate( "1");
        Assertions.assertTrue(signaling.getState() instanceof Alarm);
        Assertions.assertTrue(!signaling.checkCodeConcept("1"));

    }

    @Test
     void deactivateFromActivatedWithRightCodeTest() {
        //Signaling signaling = new Signaling();
        signaling.activate( "145236");
        signaling.deactivate( "145236");

        Assertions.assertTrue(signaling.getState() instanceof Disabled);
        Assertions.assertTrue(signaling.checkCodeConcept("0000"));
    }
    @Test
     void deactivateFromActivatedWithWrongCodeTest() {
        //Signaling signaling = new Signaling();
        signaling.activate( "145236");
        signaling.deactivate( "31");

        Assertions.assertTrue(signaling.getState() instanceof Alarm);
        //Assert.assertTrue(signaling.checkCodeConcept("0000"));
    }

    @Test
     void deactivateFromDeactivatedTest() {
        //Signaling signaling = new Signaling();
        signaling.deactivate( "31");
        Assertions.assertTrue(signaling.getState() instanceof Disabled);
        Assertions.assertTrue(signaling.checkCodeConcept("0000"));
    }

    @Test
     void deactivateFromAlarmTest() {
        //Signaling signaling = new Signaling();
        signaling.activate( "145236");
        signaling.deactivate( "31");
        Assertions.assertTrue(signaling.getState() instanceof Alarm);
        signaling.deactivate( "145236");
        Assertions.assertTrue(signaling.getState() instanceof Disabled);
        Assertions.assertTrue(signaling.checkCodeConcept("0000"));
    }

    @Test
     void serToAlarmFromActivatedTest() {
        signaling.activate( "145236");
        signaling.setToAlarm();
        Assertions.assertTrue(signaling.getState() instanceof Alarm);

    }

}
