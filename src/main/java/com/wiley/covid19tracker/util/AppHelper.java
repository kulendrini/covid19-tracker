package com.wiley.covid19tracker.util;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.wiley.covid19tracker.constant.ApplicationConstants;
import com.wiley.covid19tracker.constant.ApplicationConstants.AppHelperConstants;
import com.wiley.covid19tracker.constant.Status;
import com.wiley.covid19tracker.dto.ApplicationEnvironmentVariables;
import com.wiley.covid19tracker.dto.OperationData;
import com.wiley.covid19tracker.dto.OperationStatus;

@Component
public class AppHelper {

	static final Logger log = Logger.getLogger(AppHelper.class);
	
	@Autowired
	private PropertyFileReader propfileReader;
	
	@Autowired
	private Environment env;
	
	private static final AppHelperConstants CONSTANTS = (AppHelperConstants) ApplicationConstants.instance("AppHelper");
	
	public ApplicationEnvironmentVariables getEnvBasedProperties() {
		String environment = env.getProperty("covidtracker.environment");
		log.info("Environment = {}"+ environment);
		ApplicationEnvironmentVariables initProps;
		if (environment.equals(CONSTANTS.DEV_ENV))
			initProps = getPropsByEnvironmentStr(CONSTANTS.DEV_PROPS_FILE);
		else if (environment.equals(CONSTANTS.STAGING_ENV))
			initProps = getPropsByEnvironmentStr(CONSTANTS.STAGING_PROPS_FILE);
		else if (environment.equals(CONSTANTS.PRODUCTION_ENV))
			initProps = getPropsByEnvironmentStr(CONSTANTS.PRODUCTION_PROPS_FILE);
		else
			initProps = null;
		return initProps;
	}
	
	private ApplicationEnvironmentVariables getPropsByEnvironmentStr(String propfileName) {
		Properties props = propfileReader.getAppInitPropertiesByFile(propfileName);
//		ApplicationEnvironmentVariables initProps = new ApplicationEnvironmentVariablesBuilder()
		ApplicationEnvironmentVariables initProps = new ApplicationEnvironmentVariables();
		initProps.setHealthgovCoronavirusUrl(env.getProperty("healthgov.coronavirus.url"));
//				.toApplicationInitProperties();
		return initProps;
	}
	
	/**
	 * 
	 * @param status : status of the operation. will generate the status-code and status-description using this.
	 * @param successResponseData : data to return only if the operation is successfully ended. (optional) 
	 * @param failedResponseReason : reason for the operation to have failed. (optional)
	 * @return operation-data (with response-info wrapped in, if response-info exists)
	 */
	public <T> OperationData<T> generateOperationData(Status status, T successResponseData, String failedResponseReason) {
		OperationData<T> opData = new OperationData<>();
		OperationStatus opStatus = new OperationStatus(status.getStatus(), status.getStatusCode());
		if (successResponseData != null) opData.setData(successResponseData);
		if (failedResponseReason != null ? !failedResponseReason.trim().equals("") : false) opStatus.setReason(failedResponseReason);
		opData.setStatus(opStatus);
		return opData;
	}
}
