package au.com.stephen.drools.sample2;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import au.com.stephen.drools.rules.model.CustomMessage;
import au.com.stephen.drools.rules.model.Message;

/**
 * Servlet implementation class SampleServlet
 */
public class SampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private RuleService ruleService;
	
	/**
	 * Default constructor.
	 */
	public SampleServlet() {
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		String ruleId = request.getParameter("rule");
		String code = request.getParameter("code");

		System.out.println("Request recvd [ruleId=" + ruleId + ",code=" + code + "]");
		
		int rules = 0;
		String result = null;
		if (ruleId.equals("3")) {
			CustomMessage msg = new CustomMessage();
			msg.setCode(code);
			rules = ruleService.process(ruleId, msg);
			result = msg.getResult();
		} else {
			Message msg = new Message();
			msg.setCode(code);
			rules = ruleService.process(ruleId, msg);
			result = msg.getResult();
		}
		
		String responseMsg = String.format("Rules run %d, result=%s%n", rules, result);
		response.getWriter().write(responseMsg);
		response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
