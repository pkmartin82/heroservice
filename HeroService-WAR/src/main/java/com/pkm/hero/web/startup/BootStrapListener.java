/**
 * 
 */
package com.pkm.hero.web.startup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * @author pmartin
 *
 */
public class BootStrapListener implements ServletContextListener {

	public BootStrapListener() {
		
	}
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		this.initializeLogging();
	}

	private void initializeLogging() {
		
	}
}
