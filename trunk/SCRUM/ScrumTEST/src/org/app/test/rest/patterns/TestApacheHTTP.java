package org.app.test.rest.patterns;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * This example demonstrates how the HttpClient fluent API can be used to handle HTTP responses
 * without buffering content body in memory.
 */
public class TestApacheHTTP {
	private static String BASE_URL = "http://localhost:8080/ScrumREST/service";
    
    @Test
	public void test() throws Exception{
        System.out.println(
        		Request.Get(BASE_URL).execute().returnContent().asString());   
    }
}
