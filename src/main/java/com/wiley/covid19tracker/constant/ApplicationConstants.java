package com.wiley.covid19tracker.constant;

public class ApplicationConstants {

	/*
	 * making the class unable to instantiate
	 */
	private ApplicationConstants() {
	}

	/*
	 * Super type of sent objects
	 */
	private interface AppConstants {
	}
	
	private static AppHelperConstants appHelperConstants = new AppHelperConstants();

	public static class AppHelperConstants implements AppConstants {
		private AppHelperConstants() { }

		public String DEV_ENV = "dev";
		public String STAGING_ENV = "staging";
		public String PRODUCTION_ENV = "production";
		
		public String DEV_PROPS_FILE = "dev.properties";
		public String STAGING_PROPS_FILE = "staging.properties";
		public String PRODUCTION_PROPS_FILE = "production.properties";
	}
	
	public static AppConstants instance(String type) {
		if (type.equals("AppHelper"))
			return appHelperConstants;
		else
			return null;
	}
}
