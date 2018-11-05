package com.hool.app.service;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service("miscService")
public class MiscServiceImpl implements  MiscService{

	@Override
	public String generateUniqueId() {
		String ts = String.valueOf(System.currentTimeMillis());
	    String rand = UUID.randomUUID().toString();
	    return DigestUtils.sha1Hex(ts + rand);
	}

}
