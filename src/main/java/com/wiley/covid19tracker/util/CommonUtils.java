package com.wiley.covid19tracker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.wiley.covid19tracker.constant.Status;
import com.wiley.covid19tracker.dto.OperationData;
import com.wiley.covid19tracker.dto.OperationStatus;

@Component
public class CommonUtils {

	private static final Logger log = LoggerFactory.getLogger(CommonUtils.class);
	
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
