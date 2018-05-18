package com.luxmart.store.service;

import java.io.InputStream;

public interface AmazonS3Services {
	
	
	public String uploadFile(String keyName, InputStream s3Image);
	
	
	void deleteImage(String keyName);

}
