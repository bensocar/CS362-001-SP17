/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package finalprojectB;

import java.util.Random;

import junit.framework.TestCase;





/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.
   private static final int NUM_TESTS=10000;
   private static final long TestTimeout = 60*500*1;

   private String[] validUrlSchemes = {"http://", "ftp://", "h3t://", ""};
   private String[] invalidUrlSchemes = {"3ht://", "http:/", "http:", "http/", "://"};
   private String[] validUrlAuthorities = {"www.amazon.com", "www.google.com", "go.com", "go.au", "0.0.0.0", "255.255.255.255", "255.com", "go.cc"};
   private String[] invalidUrlAuthorities = {"256.256.256.256", "1.2.3.4.5", "1.2.3.4.", "1.2.3", ".1.2.3.4", "go.a", "go.a1a", "go.1aa", "aaa.", ".aaa", ""};
   private String[] validUrlPorts = {":80", ":65535", ":0", ""};
   private String[] invalidUrlPorts = {":-1", ":65636", "65a"};
   private String[] validUrlPaths = {"/test1", "/t123", "/$23", "/test1/", "", "/test1/file"};
   private String[] invalidUrlPaths = {"/..", "/../", "/..//file", "/test1//file"};
   private String[] urlQueries = {"?action=view", "?action=edit&mode=up"};
   
   public UrlValidatorTest(String testName) {
      super(testName);
   }
   
   public void testManualTest()
   {
       System.out.println(System.lineSeparator()+"**** MANUAL TESTS *****");
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
       System.out.println(urlVal.isValid("http://www.amazon.com")+", Expected: true for http://www.amazon.com");
       System.out.println(urlVal.isValid("3ht://www.amazon.com")+", Expected: false for 3ht://www.amazon.com");
       System.out.println(urlVal.isValid("ftp://www.amazon.com")+", Expected: true for ftp://www.amazon.com");
       System.out.println(urlVal.isValid("192.168.1.512")+", Expected: false for 192.168.1.512");
       System.out.println(urlVal.isValid("255.255.255.255")+", Expected: true for 255.255.255.255");
       System.out.println(urlVal.isValid("http://www.amazon.com:-1")+", Expected: false for http://www.amazon.com:-1");
       System.out.println(urlVal.isValid("http://www.amazon.com:8080")+", Expected: true for http://www.amazon.com:8080");
       System.out.println(urlVal.isValid("http://www.amazon.com/../")+", Expected: false for http://www.amazon.com/../");
       System.out.println(urlVal.isValid("http://www.amazon.com/test1")+", Expected: true for http://www.amazon.com/test1");
       System.out.println(urlVal.isValid("http://www.amazon.com?action=view")+", Expected: true for http://www.amazon.com?action=view");
       
       String address = "";
       System.out.println(address + ": " + urlVal.isValid(address));
	   
       address = "http://www.amazon.com";
       System.out.println(address + ": " + urlVal.isValid(address));
   
       address = "https://www.amazon.com";
       System.out.println(address + ": " + urlVal.isValid(address));
	   
       address = "http://amazon.com";
       System.out.println(address + ": " + urlVal.isValid(address));

       address = "https://ww.amazon.com";
       System.out.println(address + ": " + urlVal.isValid(address));
	   
       address = "https://www.!!.com";
       System.out.println(address + ": " + urlVal.isValid(address));
	   
       address = "http://www..com";
       System.out.println(address + ": " + urlVal.isValid(address));

       address = "5://www.amazon.com";
       System.out.println(address + ": " + urlVal.isValid(address));

       address = "http://www.amazon.com:123";
       System.out.println(address + ": " + urlVal.isValid(address));
	   
       address = "http://www.amazon.com:12356"; //Bug
       System.out.println(address + ": " + urlVal.isValid(address));
	   
       address = "http://192.168.0.512"; //Bug
       System.out.println(address + ": " + urlVal.isValid(address));
	   
       address = "http://192.168.0.1";
       System.out.println(address + ": " + urlVal.isValid(address));

       address = "http://www.example.com/some-page.asp?articleid=232"; //Bug
       System.out.println(address + ": " + urlVal.isValid(address));
   }
   
   public void testYourFirstPartition()
   {
       /*
        * First partition just checks valid schemes
        */
       System.out.println(System.lineSeparator()+"**** FIRST PARTITION TESTS *****");
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
       System.out.println(urlVal.isValid("http://www.amazon.com")+", Expected: true for http://www.amazon.com");
       System.out.println(urlVal.isValid("ftp://www.amazon.com")+", Expected: true for ftp://www.amazon.com");
       System.out.println(urlVal.isValid("h3t://www.amazon.com")+", Expected: true for h3t://www.amazon.com");
       System.out.println(urlVal.isValid("www.amazon.com")+", Expected: true for www.amazon.com");
       System.out.println(urlVal.isValid("3ht://www.amazon.com")+", Expected: false for 3ht://www.amazon.com");
       System.out.println(urlVal.isValid("http:/www.amazon.com")+", Expected: false for http:/www.amazon.com");
       System.out.println(urlVal.isValid("http:www.amazon.com")+", Expected: false for http:www.amazon.com");
       System.out.println(urlVal.isValid("http/www.amazon.com")+", Expected: false for http/www.amazon.com");
       System.out.println(urlVal.isValid("://www.amazon.com")+", Expected: false for ://www.amazon.com");
       
       String address = "http://www.amazon.com";
       System.out.println(address + ": " + urlVal.isValid(address));
	   
       address = "invalid://www.amazon.com";
       System.out.println(address + ": " + urlVal.isValid(address));
   }
   
   public void testYourSecondPartition(){
       /*
        * Second partition just checks valid ports
        */
       System.out.println(System.lineSeparator()+"**** SECOND PARTITION TESTS *****");
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
       System.out.println(urlVal.isValid("http://www.amazon.com")+", Expected: true for http://www.amazon.com");
       System.out.println(urlVal.isValid("http://www.amazon.com:80")+", Expected: true for http://www.amazon.com:80");
       System.out.println(urlVal.isValid("http://www.amazon.com:65535")+", Expected: true for http://www.amazon.com:65535");
       System.out.println(urlVal.isValid("http://www.amazon.com:0")+", Expected: true for http://www.amazon.com:0");
       System.out.println(urlVal.isValid("http://www.amazon.com:65636")+", Expected: false for http://www.amazon.com:65636");
       System.out.println(urlVal.isValid("http://www.amazon.com:-1")+", Expected: false for http://www.amazon.com:-1");
       System.out.println(urlVal.isValid("http://www.amazon.com:65a")+", Expected: false for http://www.amazon.com:65a");
       String address = "http://www.amazon.com:80";
       System.out.println(address + ": " + urlVal.isValid(address));
	   
       address = "http://www.amazon.com:8080";
       System.out.println(address + ": " + urlVal.isValid(address));
	   
       address = "http://www.amazon.com:0";
       System.out.println(address + ": " + urlVal.isValid(address));
   }
   
   public void testYourThirdPartition(){
       /*
        * Second partition just checks valid authorities
        */
       System.out.println(System.lineSeparator()+"**** THIRD PARTITION TESTS *****");
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
       System.out.println(urlVal.isValid("http://www.amazon.com")+", Expected: true for http://www.amazon.com");
       System.out.println(urlVal.isValid("http://go.com")+", Expected: true for http://go.com");
       System.out.println(urlVal.isValid("http://go.au")+", Expected: true for http://go.au");
       System.out.println(urlVal.isValid("http://255.255.255.255")+", Expected: true for http://255.255.255.255");
       System.out.println(urlVal.isValid("http://255.com")+", Expected: true for http://255.com");
       System.out.println(urlVal.isValid("http://go.cc")+", Expected: true for http://go.cc");
       System.out.println(urlVal.isValid("http://0.0.0.0")+", Expected: true for http://0.0.0.0");
       System.out.println(urlVal.isValid("http://256.256.256.256")+", Expected: false for http://256.256.256.256");
       System.out.println(urlVal.isValid("http://1.2.3.4.5")+", Expected: false for http://1.2.3.4.5");
       System.out.println(urlVal.isValid("http://1.2.3.4.")+", Expected: false for http://1.2.3.4.");
       System.out.println(urlVal.isValid("http://1.2.3")+", Expected: false for http://1.2.3");
       System.out.println(urlVal.isValid("http://.1.2.3.4")+", Expected: false for http://.1.2.3.4");
       System.out.println(urlVal.isValid("http://go.a")+", Expected: false for http://go.a");
       System.out.println(urlVal.isValid("http://go.a1a")+", Expected: false for http://go.a1a");
       System.out.println(urlVal.isValid("http://go.1aa")+", Expected: false for http://go.1aa");
       System.out.println(urlVal.isValid("http://aaa.")+", Expected: false for http://aaa.");
       System.out.println(urlVal.isValid("http://.aaa")+", Expected: false for http://.aaa");
       System.out.println(urlVal.isValid("http://aaa")+", Expected: false for http://aaa");
       System.out.println(urlVal.isValid("http://")+", Expected: false for http://");
       
       String address = "http://www..com";
       System.out.println(address + ": " + urlVal.isValid(address));
	   
       address = "http://";
       System.out.println(address + ": " + urlVal.isValid(address));
   }
   
   public void testYourFourthPartition(){
       /*
        * Second partition just checks valid paths
        */
       System.out.println(System.lineSeparator()+"**** FOURTH PARTITION TESTS *****");
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
       System.out.println(urlVal.isValid("http://www.amazon.com")+", Expected: true for http://www.amazon.com");
       System.out.println(urlVal.isValid("http://www.amazon.com/test1")+", Expected: true for http://www.amazon.com/test1");
       System.out.println(urlVal.isValid("http://www.amazon.com/t123")+", Expected: true for http://www.amazon.comt123");
       System.out.println(urlVal.isValid("http://www.amazon.com/$23")+", Expected: true for http://www.amazon.com/$23");
       System.out.println(urlVal.isValid("http://www.amazon.com/test1/")+", Expected: true for http://www.amazon.com/test1/");
       System.out.println(urlVal.isValid("http://www.amazon.com/test1/file")+", Expected: true for http://www.amazon.comtest1/file");
       System.out.println(urlVal.isValid("http://www.amazon.com/..")+", Expected: false for http://www.amazon.com/..");
       System.out.println(urlVal.isValid("http://www.amazon.com/../")+", Expected: false for http://www.amazon.com/../");
       System.out.println(urlVal.isValid("http://www.amazon.com/..//file")+", Expected: false for http://www.amazon.com/..//file");
       System.out.println(urlVal.isValid("http://www.amazon.com/test1//file")+", Expected: false for http://www.amazon.com/test1//file");
   }
   
   public void testYourFifthPartition(){
       /*
        * Second partition just checks valid queries
        */
       System.out.println(System.lineSeparator()+"**** FIFTH PARTITION TESTS *****");
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
       System.out.println(urlVal.isValid("http://www.amazon.com")+", Expected: true for http://www.amazon.com");
       System.out.println(urlVal.isValid("http://www.amazon.com?action=view")+", Expected: true for http://www.amazon.com?action=view");
       System.out.println(urlVal.isValid("http://www.amazon.com?action=edit&mode=up")+", Expected: true for http://www.amazon.com?action=edit&mode=up");
       
       String address = "http://www.example.com/some-page.asp?articleid=232";
       System.out.println(address + ": " + urlVal.isValid(address));
	   
       address = "http://www.example.com/some-page.asp?";
       System.out.println(address + ": " + urlVal.isValid(address));
   }
   
   public void testIsValid()
   {
       long randomseed=10;
       Random random = new Random(randomseed);
       boolean expectedValue = true;
       
       UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
       String[] invalidUrlComponent = new String[] {"scheme", "authority", "port", "path", "query"};
       String testComponent = null;
       String urlScheme = null;
       String urlAuthority = null;
       String urlPort = null;
       String urlPath = null;
       String urlQuery = null;
       String urlConstruction = null;

       for(int i = 0;i<TestTimeout && i<NUM_TESTS;i++)
       {
	   expectedValue = true;
	   testComponent = "nothing";
	   
	   urlScheme = validUrlSchemes[random.nextInt(validUrlSchemes.length)];
	   urlAuthority = validUrlAuthorities[random.nextInt(validUrlAuthorities.length)];
	   urlPort = validUrlPorts[random.nextInt(validUrlPorts.length)];
	   urlPath = validUrlPaths[random.nextInt(validUrlPaths.length)];
	   urlQuery = "";
	   
	   if(random.nextBoolean()){
	       testComponent = invalidUrlComponent[random.nextInt(invalidUrlComponent.length)];
	       expectedValue = false;
	       
	       if(testComponent.equals("scheme")){
		   urlScheme = invalidUrlSchemes[random.nextInt(invalidUrlSchemes.length)];
	       }
	       else if(testComponent.equals("authority")){
		   urlAuthority = invalidUrlAuthorities[random.nextInt(invalidUrlAuthorities.length)];
	       }
	       else if(testComponent.equals("port")){
		   urlPort = invalidUrlPorts[random.nextInt(invalidUrlPorts.length)];
	       }
	       else if(testComponent.equals("path")){
		   urlPath = invalidUrlPaths[random.nextInt(invalidUrlPaths.length)];
	       }
	       else{
		   expectedValue = true;
		   urlQuery = urlQueries[random.nextInt(urlQueries.length)];
	       }
	   }
	   
	   urlConstruction = urlScheme+urlAuthority+urlPort+urlPath+urlQuery;
	   System.out.println("Received: "+urlVal.isValid(urlConstruction)+", Expected: "+expectedValue+", Testing: "+testComponent+", URL: "+urlConstruction);
	   
       }
   }
   
   public void testAnyOtherUnitTest()
   {
	   
   }
   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */
   

}
