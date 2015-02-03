package controllers;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service("slowService")
@EnableAsync
public class SlowService{
	private Logger log = Logger.getLogger(this.getClass());
	
	@Async
	public void subscribe(final DeferredResult<String> result, final LinkedBlockingQueue<String> messages) {
		log.info("working");
		
		Thread tr = new Thread(new Runnable() {
			public void run() {
				try {
					result.setResult(messages.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		tr.run();
		
		log.info("working - exit");
	}
}