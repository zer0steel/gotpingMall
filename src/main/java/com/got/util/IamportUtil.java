package com.got.util;

import java.util.Objects;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.Payment;

public class IamportUtil {
	
	private static final String KEY = "6804401102834731";
	private static final String API = "T5NM4DtLvvg0zq8CGZV8MhiJzvdUAjAuoogt1JexevxOWFqCFlLsC3bbzp6aIGE75UA6EOETkcHgR7Tx";
	private static final IamportClient CLIENT = new IamportClient(KEY, API);
	
	public static Payment getCheckoutData(String impt_id) {
		return Objects.nonNull(impt_id) ? 
				CLIENT.paymentByImpUid(impt_id).getResponse() :
				new Payment();
	}
}
