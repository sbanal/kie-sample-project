package au.com.stephen.drools.sample2;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import au.com.stephen.drools.helper.Helper;
import au.com.stephen.drools.rules.model.CustomMessage;
import au.com.stephen.drools.rules.model.Message;

public class RuleService implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public int process(String ruleId, Message msg) {
		KieSession kSession = applicationContext.getBean("ksession" + ruleId,
				KieSession.class);
		kSession.setGlobal("helper", new Helper());
		kSession.insert(msg);
		return kSession.fireAllRules();
	}

	public int process(String ruleId, CustomMessage msg) {
		KieSession kSession = applicationContext.getBean("ksession" + ruleId,
				KieSession.class);
		kSession.setGlobal("helper", new Helper());
		kSession.insert(msg);
		return kSession.fireAllRules();
	}

	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.applicationContext = ctx;
	}

}
