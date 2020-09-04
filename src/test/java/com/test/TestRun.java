package com.test;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.response.Response;
import com.test.calls.TriggerCalls;
import com.test.util.Configure;

public class TestRun extends BaseTest {
	Response response;

	public TestRun() {
		log = Logger.getLogger(this.getClass());
	}

	TriggerCalls trigger = new TriggerCalls();

	@Before
	public void setup() {
		TriggerCalls.setup();
	}
	
	@Test
	public void fetchAllRecords() {
		try {
			int record = Integer.parseInt(Configure.getValue("ALL_RECORD"));
			response = trigger.getRecord(record);
			Assert.assertEquals("Response code miss-match", 200, response.statusCode());
			log.info("GET response code :" + response.statusCode());
			Assert.assertTrue("Record number miss-match",trigger.validateRecord(response, record));
			if(record != 0) {
				Assert.assertTrue("Response validation failed.",trigger.validateSchema(response));				
			}
			
			log.info("Record validated :" + Integer.parseInt(Configure.getValue("ALL_RECORD")));
			
		}catch (Exception e) {
			log.error("GET call failed", e);
			Assert.fail("GET call failed");
		}
	}

	@Test
	public void fetchSingleRecord() {
		try {
			int record = Integer.parseInt(Configure.getValue("RECORD"));
			response = trigger.getRecord(record);
			Assert.assertEquals("Response code miss-match", 200, response.statusCode());
			log.info("GET response code :" + response.statusCode());
			Assert.assertTrue("Record number miss-match",trigger.validateRecord(response, record));
			if(record != 0) {
				Assert.assertTrue("Response validation failed.",trigger.validateSchema(response));
				Assert.assertTrue("Record validation failed",trigger.validateResponseRecords(response,Configure.getValue("FETCH_BODY")));
				
			}
			
			log.info("Record validated :" + Integer.parseInt(Configure.getValue("RECORD")));

		} catch (Exception e) {
			log.error("GET call failed", e);
			Assert.fail("GET call failed");
		}
	}

	@Test
	public void postRecord() {
		try {
			response = trigger.postRecord();
			Assert.assertEquals("Response code miss-match", 201, response.statusCode());
			log.info("POST response code :" + response.statusCode());
			Assert.assertTrue("Response schema validation failed.",trigger.validateSchema(response));
			log.info("Record validated");
			Assert.assertTrue("Record validation failed",trigger.validateResponseRecords(response,Configure.getValue("BODY")));
		} catch (Exception e) {
			log.error("POST call failed", e);
			Assert.fail("POST call failed");
		}
	}
	
	@Test
	public void putRecord() {
		try {
			response = trigger.putRecord(Configure.getValue("RECORD"));
			Assert.assertEquals("Response code miss-match", 200, response.statusCode());
			log.info("PUT response code :" + response.statusCode());
			Assert.assertTrue("Response schema validation failed.",trigger.validateSchema(response));
			log.info("Record validated");
			Assert.assertTrue("Record validation failed",trigger.validatePutResponseRecords(response,Integer.parseInt(Configure.getValue("RECORD"))));
		} catch (Exception e) {
			log.error("PUT call failed", e);
			Assert.fail("PUT call failed");
		}	
	}
	
	@Test
	public void deleteRecord() {
		try {
			response = trigger.deleteGivenRecord(Configure.getValue("RECORD"));
			Assert.assertEquals("Response code miss-match", 200, response.statusCode());
			log.info("DELETE response code :" + response.statusCode());	
			Assert.assertTrue("Response is not empty",trigger.validateEmptyResponse(response));
		} catch (Exception e) {
			log.error("DELETE call failed", e);
			Assert.fail("DELETE call failed");
		}
	}
	
	@Test
	public void makeInvalidCall() {
		try {
			response = trigger.makeInvalidCall();
			Assert.assertEquals("Response code miss-match", 404, response.statusCode());
			log.info("GET response code :" + response.statusCode());
			log.info(response.headers().toString());
			log.info(response.body().asString());
		} catch (Exception e) {
			log.error("GET call failed", e);
			Assert.fail("GET call failed");
		}
	}
}
