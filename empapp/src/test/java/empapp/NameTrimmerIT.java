package empapp;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class NameTrimmerIT {

    @Inject
    private NameTrimmer nameTrimmer;

    @Deployment
    public static WebArchive createDeployment() {
        var deployment = ShrinkWrap
                .create(WebArchive.class)
                .addClass(NameTrimmer.class);
        System.out.println(deployment.toString(true));
        return deployment;
    }

    @Test
    public void testTrimName() {
        System.out.println("*****************************");
        assertEquals("John Doe", nameTrimmer.trimName("   John Doe    "));
    }
}
