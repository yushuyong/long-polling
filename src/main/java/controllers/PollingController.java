package controllers;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

@Controller
@EnableAsync
public class PollingController {

	private Logger log = Logger.getLogger(PollingController.class);
	private final LinkedBlockingQueue<String> messages = new LinkedBlockingQueue<String>();;
	
	@Autowired
	private SlowService service;
	
	public PollingController() {
		log.info("registered controller");
	}

	@RequestMapping(value = "/publish", method=RequestMethod.GET)
	public ModelAndView showPublish() {
		log.info("publish - GET");
		
		return new ModelAndView("publish");
	}
	
	@RequestMapping(value = "/publish", method=RequestMethod.POST)
	public ModelAndView doPublish(String publish) {
		log.info("publish - POST");
		
		messages.add(publish);
		
		return new ModelAndView("publish");
	}
	
	
	@RequestMapping(value = "/subscribe", method=RequestMethod.GET)
	public ModelAndView showSubscribe() {
		log.info("subscibe - GET");
		
		return new ModelAndView("subscribe");
	}

	@RequestMapping(value = "/subscribe", method=RequestMethod.POST)
	@ResponseBody
	public DeferredResult<String> doSubscribe() {
		log.info("subscribe - POST");
		final DeferredResult<String> result = new DeferredResult<String>(33000);
		
		service.subscribe(result, messages);
		
		log.info("subscribe - exit");
		return result;
	}
}
