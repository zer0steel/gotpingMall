package com.got.service.deal;

import org.springframework.stereotype.Service;

import com.siot.IamportRestClient.IamportClient;

@Service
public class ImportService {
	
	private static final String KEY = "6804401102834731";
	private static final String API = "T5NM4DtLvvg0zq8CGZV8MhiJzvdUAjAuoogt1JexevxOWFqCFlLsC3bbzp6aIGE75UA6EOETkcHgR7Tx";
	private static final IamportClient CLIENT = new IamportClient(KEY, API);
	
	
}
