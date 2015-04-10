package au.com.stephen.drools.rules.sample1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import au.com.stephen.drools.helper.Helper;
import au.com.stephen.drools.rules.model.Message;

public class KieTest {

	@Test
    public void testKie() throws Exception {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession1");
        Message msg = new Message();
        msg.setCode("Test");
	kSession.setGlobal("helper", new Helper());
        kSession.insert(msg);
        int rules = kSession.fireAllRules();
        assertEquals(1, rules);
        assertEquals("Done", msg.getResult());
    }
	
}
