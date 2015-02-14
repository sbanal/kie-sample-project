
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import au.com.stephen.drools.rules.model.Message;

public class KieTest {

    public static void main(String... args) throws Exception {
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession2");
        Message msg = new Message();
        msg.setCode("Test2");
        kSession.insert(msg);
        int rules = kSession.fireAllRules();
        System.out.printf("Rules run %d, result=%s%n",  rules, msg.getResult());
    }
	
}
