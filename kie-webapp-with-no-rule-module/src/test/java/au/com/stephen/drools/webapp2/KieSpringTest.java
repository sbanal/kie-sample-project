package au.com.stephen.drools.webapp2;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import au.com.stephen.drools.rules.model.Message;

public class KieSpringTest {

	static ApplicationContext context = null;

    @BeforeClass
    public static void setup() {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/classes/applicationContext.xml");
    }

	@Test
	public void test() {
		Assert.assertNotNull(context);
		KieSession ksession = context.getBean("ksession1", KieSession.class);
		Assert.assertNotNull(ksession);
		Message msg = context.getBean("testMessage", Message.class);
		FactHandle handle = ksession.insert(msg);
		Assert.assertNotNull(handle);
		ksession.fireAllRules();
		Assert.assertEquals("Done", msg.getResult());
		ksession.dispose();
	}

}
