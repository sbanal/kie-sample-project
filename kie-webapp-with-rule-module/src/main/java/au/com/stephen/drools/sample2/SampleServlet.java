package au.com.stephen.drools.sample2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import au.com.stephen.drools.helper.Helper;
import au.com.stephen.drools.rules.model.CustomMessage;
import au.com.stephen.drools.rules.model.Message;

/**
 * Servlet implementation class SampleServlet
 */
public class SampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public SampleServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String ruleId = request.getParameter("rule");
		String code = request.getParameter("code");

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession" + ruleId);
		int rules = 0;
		String result = null;
		if(ruleId.equals("3")) {
			CustomMessage msg = new CustomMessage();
			msg.setCode(code);
			kSession.setGlobal("helper", new Helper());
			kSession.insert(msg);
			rules = kSession.fireAllRules();
			result = msg.getResult();
		} else {
			Message msg = new Message();
			msg.setCode(code);
			kSession.setGlobal("helper", new Helper());
			kSession.insert(msg);
			rules = kSession.fireAllRules();
			result = msg.getResult();
		}
		String responseMsg = String.format("Rules run %d, result=%s%n",  rules, result);
		response.getWriter().write(responseMsg);
		response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
