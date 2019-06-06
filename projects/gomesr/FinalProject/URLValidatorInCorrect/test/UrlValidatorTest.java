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

import junit.framework.TestCase;
import java.util.Random;

/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision$
 */
public class UrlValidatorTest extends TestCase {

   private final boolean printStatus = false;
   private final boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   @Override
protected void setUp() {
      for (int index = 0; index < testPartsIndex.length - 1; index++) {
         testPartsIndex[index] = 0;
      }
   }

   public void testIsValidRandomTests() {
//        testIsValid(testUrlParts, UrlValidator.ALLOW_ALL_SCHEMES);
//        setUp();
//        long options =
//            UrlValidator.ALLOW_2_SLASHES
//                + UrlValidator.ALLOW_ALL_SCHEMES
//                + UrlValidator.NO_FRAGMENTS;
//
//        testIsValid(testUrlPartsOptions, options);
	   System.out.printf("\n------ Begin testIsValid() Random Testing ------\n");
	   /* Referenced: https://www.geeksforgeeks.org/generating-random-numbers-in-java/ */
	   /* Create instance of Random class. */
	   Random rand = new Random();
	   int randomNumTests = (rand.nextInt(10000) + 1);	// Generate a random number of tests between 1 and 10,000.
	   int validPassedTests = 0;	// Stores the number of passed tests for valid URLs.
	   int invalidPassedTests = 0;	// Stores the number of passed tests for invalid URLs.
	   int validFailedTests = 0;	// Stores the number of failed tests for valid URLs.
	   int invalidFailedTests = 0;	// Stores the number of failed tests for invalid URLs.
	   String validURL = "";	// Stores a randomly generated valid URL.
	   String invalidURL = "";	// Stores a randomly generated invalid URL.
	   UrlValidator urlRandomTest1 = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);	// Instantiate test object.
	   UrlValidator urlRandomTest2 = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);	// Instantiate test object.
	   System.out.println();	// Newline

	   /* Valid Authorities Array (5) */
	   String validAuthorities[] = {
			   "google.com",	// Valid = True
			   "www.twitch.tv",	// Valid = True
			   "oregonstate.edu",	// Valid = True
			   "github.com",	// Valid = True
			   "255.255.255.255",	// Valid = True
	   };

	   /* Invalid Authorities Array (5) */
	   String invalidAuthorities[] = {
			   "256.256.256.256",	// Valid = False
			   "",	// Valid = False
			   "google.a",	// Valid = False
			   "1.2.3.4.5",	// Valid = False
			   "facebook.1aa"	// Valid = False
	   };

	   /* Valid Schemes Array (4) */
	   String validSchemes[] = {
			   "http://",	// Valid = True
			   "https://",	// Valid = True
			   "ftp://",	// Valid = True
			   "h3t://",	// Valid = True
	   };

	   /* Invalid Schemes Array (5) */
	   String invalidSchemes[] = {
			   "3ht://",	// Valid = False
			   "http:/",	// Valid = False
			   "http:",	// Valid = False
			   "http/",	// Valid = False
			   "://"	// Valid = False
	   };

	   /* Valid Paths Array (6) */
	   String validPaths[] = {
			   "/test1",	// Valid = True
			   "/t123",	// Valid = True
			   "/$23",	// Valid = True
			   "/test1/",	// Valid = True
			   "",	// Valid = True
			   "/test1/file",	// Valid = True
	   };

	   /* Invalid Paths Array (4) */
	   String invalidPaths[] = {
			   "/..",	// Valid = False
			   "/../",	// Valid = False
			   "/..//file",	// Valid = False
			   "/test1//file"	// Valid = False
	   };

	   /* Valid Queries Array (3) */
	   String validQueries[] = {
		   "?action=view",	// Valid = True
		   "?action=edit&mode=up", // Valid = True
		   "",	// Valid = True
	   };

	   /* Invalid Queries Array (3) */
	   String invalidQueries[] = {
			   "@action=edit",	// Valid = False
			   "&action=edit&mode=up",	// Valid = False
			   "!action=view",	// Valid = False
	   };

	   /* Begin Test Loop: */
	   for (int i = 0; i < randomNumTests; i++) {
		   int validAuthority = rand.nextInt(5);	// Generate random valid authority.
//		   System.out.print("validAuthority: " + validAuthority);
//		   System.out.println();
		   int invalidAuthority = rand.nextInt(5);	// Generate random invalid authority.
//		   System.out.print("invalidAuthority: " + invalidAuthority);
//		   System.out.println();
		   int validScheme = rand.nextInt(4);		// Generate random valid scheme.
//		   System.out.print("validScheme: " + validScheme);
//		   System.out.println();
		   int invalidScheme = rand.nextInt(5);		// Generate random invalid scheme.
//		   System.out.print("invalidScheme: " + invalidScheme);
//		   System.out.println();
		   int validPath = rand.nextInt(6);		// Generate random valid path.
//		   System.out.print("validPath: " + validPath);
//		   System.out.println();
		   int invalidPath = rand.nextInt(4);		// Generate random invalid path.
//		   System.out.print("invalidPath: " + invalidPath);
//		   System.out.println();
		   int validQuery = rand.nextInt(3);		// Generate random valid query.
//		   System.out.print("validQuery: " + validQuery);
//		   System.out.println();
		   int invalidQuery = rand.nextInt(3);		// Generate random invalid query.
//		   System.out.print("invalidQuery: " + invalidQuery);
//		   System.out.println();

		   /* Combine randomly generated parts to create a valid URL. */
		   validURL = validSchemes[validScheme] + validAuthorities[validAuthority] + validPaths[validPath] + validQueries[validQuery];

		   /* Combine randomly generated parts to create an invalid URL. */
		   invalidURL = invalidSchemes[invalidScheme] + invalidAuthorities[invalidAuthority] + invalidPaths[invalidPath] + invalidQueries[invalidQuery];

		   /* Assign the results of the isValid tests to variables. */
		   Boolean result1 = urlRandomTest1.isValid(validURL);
		   Boolean result2 = urlRandomTest2.isValid(invalidURL);

		   /* Test the resulting valid URL. */
		   System.out.print(" Valid URL Generated = " + validURL + "\n");
		   System.out.print("Result = " + result1);
		   if(result1) {	// If generated URL is true, it is a valid URL, which is expected:
			   System.out.print(" -- URL is valid. (TEST PASSED!)\n");
			   validPassedTests++;	// Increment the number of passed tests for valid URLs.
		   }
		   else {	// Otherwise, the generated URL is invalid.
			   System.out.print(" -- URL is invalid. (TEST FAILED!)\n");
			   validFailedTests++;	// Increment the number of failed tests for valid URLs.
		   }

		   /* Test the resulting invalid URL. */
		   System.out.print(" Invalid URL Generated = " + invalidURL + "\n");
		   System.out.print("Result = " + result2);
		   if(!result2) {	// If generated URL is false, it is an invalid URL, which is expected:
			   System.out.print(" -- URL is invalid. (TEST PASSED!)\n");
			   invalidPassedTests++;	// Increment the number of passed tests for invalid URLs.
		   }
		   else {	// Otherwise, the generated URL is valid.
			   System.out.print(" -- URL is valid. (TEST FAILED!\n");
			   invalidFailedTests++;	// Increment the number of failed tests for invalid URLs.
		   }
	   }

	   /* Print Test Results */
	   System.out.printf("\n------ testIsValid() Random Test Results ------\n");
	   System.out.printf("Number of valid URL tests passed: %d\n", validPassedTests);
	   System.out.printf("Number of valid URL tests failed: %d\n", invalidFailedTests);
	   System.out.printf("Number of invalid URL tests passed: %d\n", invalidPassedTests);
	   System.out.printf("Number of invalid URL tests failed: %d", invalidFailedTests);
	   System.out.printf("\n------ End of testIsValid() Random Test Results ------\n");
	   System.out.println();	// Newline
   }

   public void testIsValidScheme() {
      if (printStatus) {
         System.out.print("\n testIsValidScheme() ");
      }
      //UrlValidator urlVal = new UrlValidator(schemes,false,false,false);
      UrlValidator urlVal = new UrlValidator(schemes, 0);
      for (int sIndex = 0; sIndex < testScheme.length; sIndex++) {
         ResultPair testPair = testScheme[sIndex];
         boolean result = urlVal.isValidScheme(testPair.item);
         assertEquals(testPair.item, testPair.valid, result);
         if (printStatus) {
            if (result == testPair.valid) {
               System.out.print('.');
            } else {
               System.out.print('X');
            }
         }
      }
      if (printStatus) {
         System.out.println();
      }

   }

   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */
   public void testIsValid(Object[] testObjects, long options) {
      UrlValidator urlVal = new UrlValidator(null, null, options);
      assertTrue(urlVal.isValid("http://www.google.com"));
      assertTrue(urlVal.isValid("http://www.google.com/"));
      int statusPerLine = 60;
      int printed = 0;
      if (printIndex)  {
         statusPerLine = 6;
      }
      do {
          StringBuilder testBuffer = new StringBuilder();
         boolean expected = true;

//         for (int testPartsIndexIndex = 0; testPartsIndexIndex < 0; ++testPartsIndexIndex) {	// Original Bug
         for (int testPartsIndexIndex = 0; testPartsIndexIndex < testPartsIndex.length; ++testPartsIndexIndex) {	// Fixed Bug
        	 int index = testPartsIndex[testPartsIndexIndex];

//            ResultPair[] part = (ResultPair[]) testObjects[-1];	// Original Bug
            ResultPair[] part = (ResultPair[]) testObjects[testPartsIndexIndex];	// Fixed Bug
            testBuffer.append(part[index].item);
            expected &= part[index].valid;
         }
         String url = testBuffer.toString();

//         boolean result = !urlVal.isValid(url);	// Original Bug
         boolean result = urlVal.isValid(url);	// Fixed Bug
         assertEquals(url, expected, result);
         if (printStatus) {
            if (printIndex) {
               System.out.print(testPartsIndextoString());
            } else {
               if (result == expected) {
                  System.out.print('.');
               } else {
                  System.out.print('X');
               }
            }
            printed++;
            if (printed == statusPerLine) {
               System.out.println();
               printed = 0;
            }
         }
      } while (incrementTestPartsIndex(testPartsIndex, testObjects));
      if (printStatus) {
         System.out.println();
      }
   }

   /** Random Test For Allowing Custom Schemes
    *  Randomly generates 2000 strings to be used as custom schemes between
    *  the length of 1 and 10 characters
    *  Initializes two UrlValidator objects, one with the schemes and one
    *  without.
    *  Attaches randomly generated schemes to valid authority, port, path
    *  and query
    *  source for random string generation: https://www.baeldung.com/java-random-string
    */
   public void testSchemesRandom() {

	   //randomly generate a scheme between 1 and 10 characters
	   for (int i = 0; i < 2000; i++) {
		   int leftLimit = 97; //letter 'A'
		   int rightLimit = 122; //letter 'z'
		   Random random = new Random();
		   int num = random.nextInt(10) + 1;
		   StringBuilder test = new StringBuilder();
		   for (int x = 0; x < num; x++) {
			   int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			   test.append((char) randomLimitedInt);
		   }
		   String[] schemes = {test.toString()};
		   test.append("://");
		   String schemeValid = test.toString();

		   //randomly select a valid authority, port, path, and query
	       String[] authorityValid = {"www.google.com", "www.twitch.tv", "stackoverflow.com", "0.0.0.0", "go.au"};
	       String[] portValid = {":0", ":8080", ":80", ":65535"};
	       String[] pathsValid = {"/tests", "/123", "/$"};
	       String[] queriesValid = {"?action=view","?action=edit&mode=up", ""};

	       int authority = random.nextInt(5);
	       int port = random.nextInt(4);
	       int path = random.nextInt(3);
	       int query = random.nextInt(3);

	       test.append(authorityValid[authority]);
	       test.append(portValid[port]);
	       test.append(pathsValid[path]);
	       test.append(queriesValid[query]);

	       UrlValidator urlValidator1 = new UrlValidator(schemes);			//custom schemes should be allowed
	       UrlValidator urlValidator2 = new UrlValidator();					//custom schemes not allowed

	       String testUrl = test.toString();
		   assertTrue(urlValidator1.isValid(testUrl));
		   assertFalse(urlValidator2.isValid(testUrl));

		   //randomly test invalid authority port, path
		   authority = random.nextInt(5);
		   port = random.nextInt(3);
		   path = random.nextInt(2);
	       String[] authorityInvalid = {"256.256.256.256", "go.a1a", "toast", "1.2.3.4", "go."};
	       String[] portInvalid = {":999999999999999999", ":8080a", ":-1"};
	       String[] pathsInvalid = {"/..", "//file"};

	       StringBuilder newTest = new StringBuilder();
	       newTest.append(schemeValid);
	       newTest.append(authorityInvalid[authority]);
	       newTest.append(portInvalid[port]);
	       newTest.append(pathsInvalid[path]);

	       testUrl = newTest.toString();
		   assertFalse(urlValidator1.isValid(testUrl));
		   assertFalse(urlValidator2.isValid(testUrl));
	   }

   }



   public void testValidator202() {
       String[] schemes = {"http","https"};
       UrlValidator urlValidator = new UrlValidator(schemes, UrlValidator.NO_FRAGMENTS);
       assertTrue(urlValidator.isValid("http://l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.l.org"));
   }

   public void testValidator204() {
       String[] schemes = {"http","https"};
       UrlValidator urlValidator = new UrlValidator(schemes);
       assertTrue(urlValidator.isValid("http://tech.yahoo.com/rc/desktops/102;_ylt=Ao8yevQHlZ4On0O3ZJGXLEQFLZA5"));
   }

   public void testValidator218() {
       UrlValidator validator = new UrlValidator(UrlValidator.ALLOW_2_SLASHES);
       assertTrue("parentheses should be valid in URLs",
               validator.isValid("http://somewhere.com/pathxyz/file(1).html"));
   }

   public void testValidator235() {
       String version = System.getProperty("java.version");
       if (version.compareTo("1.6") < 0) {
           System.out.println("Cannot run Unicode IDN tests");
           return; // Cannot run the test
       }
       UrlValidator validator = new UrlValidator();
       assertTrue("xn--d1abbgf6aiiy.xn--p1ai should validate", validator.isValid("http://xn--d1abbgf6aiiy.xn--p1ai"));
       assertTrue("президент.рф should validate", validator.isValid("http://президент.рф"));
       assertTrue("www.b\u00fccher.ch should validate", validator.isValid("http://www.b\u00fccher.ch"));
       assertFalse("www.\uFFFD.ch FFFD should fail", validator.isValid("http://www.\uFFFD.ch"));
       assertTrue("www.b\u00fccher.ch should validate", validator.isValid("ftp://www.b\u00fccher.ch"));
       assertFalse("www.\uFFFD.ch FFFD should fail", validator.isValid("ftp://www.\uFFFD.ch"));
   }

    public void testValidator248() {
        RegexValidator regex = new RegexValidator(new String[] {"localhost", ".*\\.my-testing"});
        UrlValidator validator = new UrlValidator(regex, 0);

        assertTrue("localhost URL should validate",
                validator.isValid("http://localhost/test/index.html"));
        assertTrue("first.my-testing should validate",
                validator.isValid("http://first.my-testing/test/index.html"));
        assertTrue("sup3r.my-testing should validate",
                validator.isValid("http://sup3r.my-testing/test/index.html"));

        assertFalse("broke.my-test should not validate",
                validator.isValid("http://broke.my-test/test/index.html"));

        assertTrue("www.apache.org should still validate",
                validator.isValid("http://www.apache.org/test/index.html"));

        // Now check using options
        validator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);

        assertTrue("localhost URL should validate",
              validator.isValid("http://localhost/test/index.html"));

        assertTrue("machinename URL should validate",
              validator.isValid("http://machinename/test/index.html"));

        assertTrue("www.apache.org should still validate",
              validator.isValid("http://www.apache.org/test/index.html"));
    }

    public void testValidator288() {
        UrlValidator validator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);

        assertTrue("hostname should validate",
                validator.isValid("http://hostname"));

        assertTrue("hostname with path should validate",
                validator.isValid("http://hostname/test/index.html"));

        assertTrue("localhost URL should validate",
                validator.isValid("http://localhost/test/index.html"));

        assertFalse("first.my-testing should not validate",
                validator.isValid("http://first.my-testing/test/index.html"));

        assertFalse("broke.hostname should not validate",
                validator.isValid("http://broke.hostname/test/index.html"));

        assertTrue("www.apache.org should still validate",
                validator.isValid("http://www.apache.org/test/index.html"));

        // Turn it off, and check
        validator = new UrlValidator(0);

        assertFalse("hostname should no longer validate",
                validator.isValid("http://hostname"));

        assertFalse("localhost URL should no longer validate",
                validator.isValid("http://localhost/test/index.html"));

        assertTrue("www.apache.org should still validate",
                validator.isValid("http://www.apache.org/test/index.html"));
    }

    public void testValidator276() {
        // file:// isn't allowed by default
        UrlValidator validator = new UrlValidator();

        assertTrue("http://apache.org/ should be allowed by default",
                 validator.isValid("http://www.apache.org/test/index.html"));

        assertFalse("file:///c:/ shouldn't be allowed by default",
                 validator.isValid("file:///C:/some.file"));

        assertFalse("file:///c:\\ shouldn't be allowed by default",
              validator.isValid("file:///C:\\some.file"));

        assertFalse("file:///etc/ shouldn't be allowed by default",
              validator.isValid("file:///etc/hosts"));

        assertFalse("file://localhost/etc/ shouldn't be allowed by default",
              validator.isValid("file://localhost/etc/hosts"));

        assertFalse("file://localhost/c:/ shouldn't be allowed by default",
              validator.isValid("file://localhost/c:/some.file"));

        // Turn it on, and check
        // Note - we need to enable local urls when working with file:
        validator = new UrlValidator(new String[] {"http","file"}, UrlValidator.ALLOW_LOCAL_URLS);

        assertTrue("http://apache.org/ should be allowed by default",
                 validator.isValid("http://www.apache.org/test/index.html"));

        assertTrue("file:///c:/ should now be allowed",
                 validator.isValid("file:///C:/some.file"));

        // Currently, we don't support the c:\ form
        assertFalse("file:///c:\\ shouldn't be allowed",
              validator.isValid("file:///C:\\some.file"));

        assertTrue("file:///etc/ should now be allowed",
              validator.isValid("file:///etc/hosts"));

        assertTrue("file://localhost/etc/ should now be allowed",
              validator.isValid("file://localhost/etc/hosts"));

        assertTrue("file://localhost/c:/ should now be allowed",
              validator.isValid("file://localhost/c:/some.file"));

        // These are never valid
        assertFalse("file://c:/ shouldn't ever be allowed, needs file:///c:/",
              validator.isValid("file://C:/some.file"));

        assertFalse("file://c:\\ shouldn't ever be allowed, needs file:///c:/",
              validator.isValid("file://C:\\some.file"));
    }

    public void testValidator391OK() {
        String[] schemes = {"file"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        assertTrue(urlValidator.isValid("file:///C:/path/to/dir/"));
    }

    public void testValidator391FAILS() {
        String[] schemes = {"file"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        assertTrue(urlValidator.isValid("file:/C:/path/to/dir/"));
    }

    public void testValidator309() {
        UrlValidator urlValidator = new UrlValidator();
        assertTrue(urlValidator.isValid("http://sample.ondemand.com/"));
        assertTrue(urlValidator.isValid("hTtP://sample.ondemand.CoM/"));
        assertTrue(urlValidator.isValid("httpS://SAMPLE.ONEMAND.COM/"));
        urlValidator = new UrlValidator(new String[] {"HTTP","HTTPS"});
        assertTrue(urlValidator.isValid("http://sample.ondemand.com/"));
        assertTrue(urlValidator.isValid("hTtP://sample.ondemand.CoM/"));
        assertTrue(urlValidator.isValid("httpS://SAMPLE.ONEMAND.COM/"));
    }

    public void testValidator339(){
        UrlValidator urlValidator = new UrlValidator();
        assertTrue(urlValidator.isValid("http://www.cnn.com/WORLD/?hpt=sitenav")); // without
        assertTrue(urlValidator.isValid("http://www.cnn.com./WORLD/?hpt=sitenav")); // with
        assertFalse(urlValidator.isValid("http://www.cnn.com../")); // doubly dotty
        assertFalse(urlValidator.isValid("http://www.cnn.invalid/"));
        assertFalse(urlValidator.isValid("http://www.cnn.invalid./")); // check . does not affect invalid domains
    }

    public void testValidator339IDN(){
        UrlValidator urlValidator = new UrlValidator();
        assertTrue(urlValidator.isValid("http://президент.рф/WORLD/?hpt=sitenav")); // without
        assertTrue(urlValidator.isValid("http://президент.рф./WORLD/?hpt=sitenav")); // with
        assertFalse(urlValidator.isValid("http://президент.рф..../")); // very dotty
        assertFalse(urlValidator.isValid("http://президент.рф.../")); // triply dotty
        assertFalse(urlValidator.isValid("http://президент.рф../")); // doubly dotty
    }

    public void testValidator342(){
        UrlValidator urlValidator = new UrlValidator();
        assertTrue(urlValidator.isValid("http://example.rocks/"));
        assertTrue(urlValidator.isValid("http://example.rocks"));
    }

    public void testValidator411(){
        UrlValidator urlValidator = new UrlValidator();
        assertTrue(urlValidator.isValid("http://example.rocks:/"));
        assertTrue(urlValidator.isValid("http://example.rocks:0/"));
        assertTrue(urlValidator.isValid("http://example.rocks:65535/"));
        assertFalse(urlValidator.isValid("http://example.rocks:65536/"));
        assertFalse(urlValidator.isValid("http://example.rocks:100000/"));
    }

    static boolean incrementTestPartsIndex(int[] testPartsIndex, Object[] testParts) {
        boolean carry = true;  //add 1 to lowest order part.
        boolean maxIndex = true;
        //previously incorrect with testPartsIndexIndex = testPartsIndex.length
        for (int testPartsIndexIndex = testPartsIndex.length - 1; testPartsIndexIndex >= 0; --testPartsIndexIndex) {
            int index = testPartsIndex[testPartsIndexIndex];
            ResultPair[] part = (ResultPair[]) testParts[testPartsIndexIndex];
            maxIndex &= (index == (part.length - 1));

            if (carry) {
                if (index < part.length - 1) {
                    index++;//previously incorrect as index--
                    testPartsIndex[testPartsIndexIndex] = index;
                    carry = false;
                } else {
                    testPartsIndex[testPartsIndexIndex] = 0;
                    carry = true;
                }
            }
        }

        return (!maxIndex);
    }

    //randomly generate ResultPairs and test that the incrementTestPartsIndex method
    //will iterate over them properly
    public void testRandomIncrementPartsIndex(){
        Random random = new Random();

        ResultPair[] randomTestUrlScheme = new ResultPair[random.nextInt(50) + 1];
        ResultPair[] randomTestUrlAuthority = new ResultPair[random.nextInt(50) + 1];
        ResultPair[] randomTestUrlPort = new ResultPair[random.nextInt(50) + 1];
        ResultPair[] randomTestPath = new ResultPair[random.nextInt(50) + 1];
        ResultPair[] randomTestUrlQuery = new ResultPair[random.nextInt(50) + 1];

        int leftLimit = 97; //letter 'A'
        int rightLimit = 122; //letter 'z'
        int numChars;
        StringBuilder randomString;

        for (int i=0; i<randomTestUrlScheme.length; i++){
            //random string of n characters
            numChars = random.nextInt(10) + 1;
            randomString = new StringBuilder();
            for (int x = 0; x < numChars; x++) {
                int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
                randomString.append((char) randomLimitedInt);
            }

            randomTestUrlScheme[i]=new ResultPair(randomString.toString(), random.nextBoolean());

        }


        for (int i=0; i<randomTestUrlAuthority.length; i++){
            //random string of n characters
            numChars = random.nextInt(10) + 1;
            randomString = new StringBuilder();
            for (int x = 0; x < numChars; x++) {
                int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
                randomString.append((char) randomLimitedInt);
            }

            randomTestUrlAuthority[i]=new ResultPair(randomString.toString(), random.nextBoolean());

        }


        for (int i=0; i<randomTestUrlPort.length; i++){
            //random string of n characters
            numChars = random.nextInt(10) + 1;
            randomString = new StringBuilder();
            for (int x = 0; x < numChars; x++) {
                int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
                randomString.append((char) randomLimitedInt);
            }

            randomTestUrlPort[i]=new ResultPair(randomString.toString(), random.nextBoolean());

        }



        for (int i=0; i<randomTestPath.length; i++){
            //random string of n characters
            numChars = random.nextInt(10) + 1;
            randomString = new StringBuilder();
            for (int x = 0; x < numChars; x++) {
                int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
                randomString.append((char) randomLimitedInt);
            }

            randomTestPath[i]=new ResultPair(randomString.toString(), random.nextBoolean());

        }



        for (int i=0; i<randomTestUrlQuery.length; i++){
            //random string of n characters
            numChars = random.nextInt(10) + 1;
            randomString = new StringBuilder();
            for (int x = 0; x < numChars; x++) {
                int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
                randomString.append((char) randomLimitedInt);
            }
            randomTestUrlQuery[i]=new ResultPair(randomString.toString(), random.nextBoolean());

        }

        Object[] sampleTestUrlParts = {randomTestUrlScheme, randomTestUrlAuthority, randomTestUrlPort, randomTestPath, randomTestUrlQuery};
        int[] sampleTestPartsIndex = {0, 0, 0, 0, 0};

        int totUrlsGenerated =0;
        int expectedNumUrls = randomTestUrlScheme.length*randomTestUrlAuthority.length*
                randomTestUrlPort.length*randomTestPath.length*randomTestUrlQuery.length;

        do {
            totUrlsGenerated++;

        }while (incrementTestPartsIndex(sampleTestPartsIndex,sampleTestUrlParts));

        System.out.println("total of " + expectedNumUrls + " expected");
        System.out.println("total of " + totUrlsGenerated + " generated");

        assertTrue(totUrlsGenerated==expectedNumUrls);

    }

   private String testPartsIndextoString() {
       StringBuilder carryMsg = new StringBuilder("{");
      for (int testPartsIndexIndex = 0; testPartsIndexIndex < testPartsIndex.length; ++testPartsIndexIndex) {
         carryMsg.append(testPartsIndex[testPartsIndexIndex]);
         if (testPartsIndexIndex < testPartsIndex.length - 1) {
            carryMsg.append(',');
         } else {
            carryMsg.append('}');
         }
      }
      return carryMsg.toString();

   }

   public void testValidateUrl() {
      assertTrue(true);
   }

   public void testValidator290() {
        UrlValidator validator = new UrlValidator();
        assertTrue(validator.isValid("http://xn--h1acbxfam.idn.icann.org/"));
//        assertTrue(validator.isValid("http://xn--e1afmkfd.xn--80akhbyknj4f"));
        // Internationalized country code top-level domains
        assertTrue(validator.isValid("http://test.xn--lgbbat1ad8j")); //Algeria
        assertTrue(validator.isValid("http://test.xn--fiqs8s")); // China
        assertTrue(validator.isValid("http://test.xn--fiqz9s")); // China
        assertTrue(validator.isValid("http://test.xn--wgbh1c")); // Egypt
        assertTrue(validator.isValid("http://test.xn--j6w193g")); // Hong Kong
        assertTrue(validator.isValid("http://test.xn--h2brj9c")); // India
        assertTrue(validator.isValid("http://test.xn--mgbbh1a71e")); // India
        assertTrue(validator.isValid("http://test.xn--fpcrj9c3d")); // India
        assertTrue(validator.isValid("http://test.xn--gecrj9c")); // India
        assertTrue(validator.isValid("http://test.xn--s9brj9c")); // India
        assertTrue(validator.isValid("http://test.xn--xkc2dl3a5ee0h")); // India
        assertTrue(validator.isValid("http://test.xn--45brj9c")); // India
        assertTrue(validator.isValid("http://test.xn--mgba3a4f16a")); // Iran
        assertTrue(validator.isValid("http://test.xn--mgbayh7gpa")); // Jordan
        assertTrue(validator.isValid("http://test.xn--mgbc0a9azcg")); // Morocco
        assertTrue(validator.isValid("http://test.xn--ygbi2ammx")); // Palestinian Territory
        assertTrue(validator.isValid("http://test.xn--wgbl6a")); // Qatar
        assertTrue(validator.isValid("http://test.xn--p1ai")); // Russia
        assertTrue(validator.isValid("http://test.xn--mgberp4a5d4ar")); //  Saudi Arabia
        assertTrue(validator.isValid("http://test.xn--90a3ac")); // Serbia
        assertTrue(validator.isValid("http://test.xn--yfro4i67o")); // Singapore
        assertTrue(validator.isValid("http://test.xn--clchc0ea0b2g2a9gcd")); // Singapore
        assertTrue(validator.isValid("http://test.xn--3e0b707e")); // South Korea
        assertTrue(validator.isValid("http://test.xn--fzc2c9e2c")); // Sri Lanka
        assertTrue(validator.isValid("http://test.xn--xkc2al3hye2a")); // Sri Lanka
        assertTrue(validator.isValid("http://test.xn--ogbpf8fl")); // Syria
        assertTrue(validator.isValid("http://test.xn--kprw13d")); // Taiwan
        assertTrue(validator.isValid("http://test.xn--kpry57d")); // Taiwan
        assertTrue(validator.isValid("http://test.xn--o3cw4h")); // Thailand
        assertTrue(validator.isValid("http://test.xn--pgbs0dh")); // Tunisia
        assertTrue(validator.isValid("http://test.xn--mgbaam7a8h")); // United Arab Emirates
        // Proposed internationalized ccTLDs
//        assertTrue(validator.isValid("http://test.xn--54b7fta0cc")); // Bangladesh
//        assertTrue(validator.isValid("http://test.xn--90ae")); // Bulgaria
//        assertTrue(validator.isValid("http://test.xn--node")); // Georgia
//        assertTrue(validator.isValid("http://test.xn--4dbrk0ce")); // Israel
//        assertTrue(validator.isValid("http://test.xn--mgb9awbf")); // Oman
//        assertTrue(validator.isValid("http://test.xn--j1amh")); // Ukraine
//        assertTrue(validator.isValid("http://test.xn--mgb2ddes")); // Yemen
        // Test TLDs
//        assertTrue(validator.isValid("http://test.xn--kgbechtv")); // Arabic
//        assertTrue(validator.isValid("http://test.xn--hgbk6aj7f53bba")); // Persian
//        assertTrue(validator.isValid("http://test.xn--0zwm56d")); // Chinese
//        assertTrue(validator.isValid("http://test.xn--g6w251d")); // Chinese
//        assertTrue(validator.isValid("http://test.xn--80akhbyknj4f")); // Russian
//        assertTrue(validator.isValid("http://test.xn--11b5bs3a9aj6g")); // Hindi
//        assertTrue(validator.isValid("http://test.xn--jxalpdlp")); // Greek
//        assertTrue(validator.isValid("http://test.xn--9t4b11yi5a")); // Korean
//        assertTrue(validator.isValid("http://test.xn--deba0ad")); // Yiddish
//        assertTrue(validator.isValid("http://test.xn--zckzah")); // Japanese
//        assertTrue(validator.isValid("http://test.xn--hlcj6aya9esc7a")); // Tamil
    }

   public void testValidator361() {
       UrlValidator validator = new UrlValidator();
       assertTrue(validator.isValid("http://hello.tokyo/"));
    }

   public void testValidator363(){
        UrlValidator urlValidator = new UrlValidator();
        assertTrue(urlValidator.isValid("http://www.example.org/a/b/hello..world"));
        assertTrue(urlValidator.isValid("http://www.example.org/a/hello..world"));
        assertTrue(urlValidator.isValid("http://www.example.org/hello.world/"));
        assertTrue(urlValidator.isValid("http://www.example.org/hello..world/"));
        assertTrue(urlValidator.isValid("http://www.example.org/hello.world"));
        assertTrue(urlValidator.isValid("http://www.example.org/hello..world"));
        assertTrue(urlValidator.isValid("http://www.example.org/..world"));
        assertTrue(urlValidator.isValid("http://www.example.org/.../world"));
        assertFalse(urlValidator.isValid("http://www.example.org/../world"));
        assertFalse(urlValidator.isValid("http://www.example.org/.."));
        assertFalse(urlValidator.isValid("http://www.example.org/../"));
        assertFalse(urlValidator.isValid("http://www.example.org/./.."));
        assertFalse(urlValidator.isValid("http://www.example.org/././.."));
        assertTrue(urlValidator.isValid("http://www.example.org/..."));
        assertTrue(urlValidator.isValid("http://www.example.org/.../"));
        assertTrue(urlValidator.isValid("http://www.example.org/.../.."));
    }

   public void testValidator375() {
       UrlValidator validator = new UrlValidator();
       String url = "http://[FEDC:BA98:7654:3210:FEDC:BA98:7654:3210]:80/index.html";
       assertTrue("IPv6 address URL should validate: " + url, validator.isValid(url));
       url = "http://[::1]:80/index.html";
       assertTrue("IPv6 address URL should validate: " + url, validator.isValid(url));
       url = "http://FEDC:BA98:7654:3210:FEDC:BA98:7654:3210:80/index.html";
       assertFalse("IPv6 address without [] should not validate: " + url, validator.isValid(url));
    }


   public void testValidator353() { // userinfo
       UrlValidator validator = new UrlValidator();
       assertTrue(validator.isValid("http://www.apache.org:80/path"));
       assertTrue(validator.isValid("http://user:pass@www.apache.org:80/path"));
       assertTrue(validator.isValid("http://user:@www.apache.org:80/path"));
       assertTrue(validator.isValid("http://user@www.apache.org:80/path"));
       assertTrue(validator.isValid("http://us%00er:-._~!$&'()*+,;=@www.apache.org:80/path"));
       assertFalse(validator.isValid("http://:pass@www.apache.org:80/path"));
       assertFalse(validator.isValid("http://:@www.apache.org:80/path"));
       assertFalse(validator.isValid("http://user:pa:ss@www.apache.org/path"));
       assertFalse(validator.isValid("http://user:pa@ss@www.apache.org/path"));
   }

   public void testValidator382() {
       UrlValidator validator = new UrlValidator();
       assertTrue(validator.isValid("ftp://username:password@example.com:8042/over/there/index.dtb?type=animal&name=narwhal#nose"));
   }

   public void testValidator380() {
       UrlValidator validator = new UrlValidator();
       assertTrue(validator.isValid("http://www.apache.org:80/path"));
       assertTrue(validator.isValid("http://www.apache.org:8/path"));
       assertTrue(validator.isValid("http://www.apache.org:/path"));
   }

   public void testValidator420() {
       UrlValidator validator = new UrlValidator();
       assertFalse(validator.isValid("http://example.com/serach?address=Main Avenue"));
       assertTrue(validator.isValid("http://example.com/serach?address=Main%20Avenue"));
       assertTrue(validator.isValid("http://example.com/serach?address=Main+Avenue"));
   }



    /** Unit Test For Allowing Custom Schemes
     * Two sets of arrays for authority, ports, and paths
     * One set contains all valid parts other all invalid
     * Schemes array contains schemes that are not valid by default
     * and should only pass if schemes array is passed to UrlValidator constructor
     *  **/
    public void testSchemes() {
        //custom schemes
        String[] schemes = {"file", "rtsp", "session", "tag", "tel", "cid", "info"};

        // valid parts
        String[] schemesParts = {"rtsp://", "session://", "tag://", "tel://", "cid://", "info://"};
        String[] authorityValid = {"www.google.com", "www.twitch.tv", "stackoverflow.com", "0.0.0.0", "go.au"};
        String[] portValid = {":0", ":8080", ":80", ":65535"};
        String[] pathsValid = {"/tests", "/123", "/$"};
        String[] queriesValid = {"?action=view","?action=edit&mode=up", ""};

        UrlValidator urlValidator1 = new UrlValidator(schemes);			//custom schemes should be allowed
        UrlValidator urlValidator2 = new UrlValidator();					//custom schemes not allowed

        // build URLs and test
        for (int sIndex = 0; sIndex < schemesParts.length; sIndex++) {
            StringBuilder test = new StringBuilder();
            test.append(schemesParts[sIndex]);
            for (int aIndex = 0; aIndex < authorityValid.length; aIndex++) {
                test.append(authorityValid[aIndex]);
                for (int pIndex = 0; pIndex < portValid.length; pIndex++) {
                    test.append(portValid[pIndex]);
                    for (int paIndex = 0; paIndex < pathsValid.length; paIndex++) {
                        test.append(pathsValid[paIndex]);
                        for (int qIndex = 0; qIndex < queriesValid.length; qIndex++) {
                            test.append(queriesValid[qIndex]);
                            String testUrl = test.toString();
                            assertTrue(urlValidator1.isValid(testUrl));
                            assertFalse(urlValidator2.isValid(testUrl));
                        }
                    }
                }
            }
        }

        // invalid parts (with exception of queries)
        String[] authorityInvalid = {"256.256.256.256", "go.a1a", "toast", "1.2.3.4", "go."};
        String[] portInvalid = {":999999999999999999", ":8080a", ":-1"};
        String[] pathsInvalid = {"/..", "//file"};


        // build URLs and test
        for (int sIndex = 0; sIndex < schemesParts.length; sIndex++) {
            StringBuilder test = new StringBuilder();
            test.append(schemesParts[sIndex]);
            for (int aIndex = 0; aIndex < authorityInvalid.length; aIndex++) {
                test.append(authorityInvalid[aIndex]);
                for (int pIndex = 0; pIndex < portInvalid.length; pIndex++) {
                    test.append(portInvalid[pIndex]);
                    for (int paIndex = 0; paIndex < pathsInvalid.length; paIndex++) {
                        test.append(pathsInvalid[paIndex]);
                        for (int qIndex = 0; qIndex < queriesValid.length; qIndex++) {
                            test.append(queriesValid[qIndex]);
                            String testUrl = test.toString();
                            assertFalse(urlValidator1.isValid(testUrl));
                            assertFalse(urlValidator2.isValid(testUrl));
                        }
                    }
                }
            }
        }
    }



    //Hardcode the different parts of a url, pass it to incrementTestPartsIndex(int[] testPartsIndex, Object[] testParts)
//This should loop through and generate all the possibilities of the URLs -  all permutaitons of the individiual
//parts. Finally we keep track of how many times incrementTestPartsIndex allowed you to loop through. This is how
//many URLs were  generated which should match the product of each individual component array's
    public void testIncrementTestPartsIndex(){

        ResultPair[] sampleTestUrlScheme = {new ResultPair("http://", true),
                new ResultPair("ftp://", true),
                new ResultPair("h3t://", true),
                new ResultPair("3ht://", false),
                new ResultPair("http:/", false),
                new ResultPair("http:", false),
                new ResultPair("http/", false),
                new ResultPair("://", false)};

        ResultPair[] sampleTestUrlAuthority = {new ResultPair("www.google.com", true),
                new ResultPair("www.google.com.", true),
                new ResultPair("go.com", true),
                new ResultPair("go.au", true),
                new ResultPair("0.0.0.0", true),
                new ResultPair("255.255.255.255", true),
                new ResultPair("256.256.256.256", false),
                new ResultPair("255.com", true),
                new ResultPair("1.2.3.4.5", false),
                new ResultPair("1.2.3.4.", false),
                new ResultPair("1.2.3", false),
                new ResultPair(".1.2.3.4", false),
                new ResultPair("go.a", false),
                new ResultPair("go.a1a", false),
                new ResultPair("go.cc", true),
                new ResultPair("go.1aa", false),
                new ResultPair("aaa.", false),
                new ResultPair(".aaa", false),
                new ResultPair("aaa", false),
                new ResultPair("", false)};

        ResultPair[] sampleTestUrlPort = {new ResultPair(":80", true),
                new ResultPair(":65535", true), // max possible
                new ResultPair(":65536", false), // max possible +1
                new ResultPair(":0", true),
                new ResultPair("", true),
                new ResultPair(":-1", false),
                new ResultPair(":65636", false),
                new ResultPair(":999999999999999999", false),
                new ResultPair(":65a", false)};

        ResultPair[] sampleTestPath = {new ResultPair("/test1", true),
                new ResultPair("/t123", true),
                new ResultPair("/$23", true),
                new ResultPair("/..", false),
                new ResultPair("/../", false),
                new ResultPair("/test1/", true),
                new ResultPair("", true),
                new ResultPair("/test1/file", true),
                new ResultPair("/..//file", false),
                new ResultPair("/test1//file", false)};

        ResultPair[] sampleTestUrlQuery = {new ResultPair("?action=view", true),
                new ResultPair("?action=edit&mode=up", true),
                new ResultPair("", true)};

        Object[] sampleTestUrlParts = {sampleTestUrlScheme, sampleTestUrlAuthority, sampleTestUrlPort, sampleTestPath, sampleTestUrlQuery};

        int[] sampleTestPartsIndex = {0, 0, 0, 0, 0};

        int totUrlsGenerated =0;
        int expectedNumUrls = sampleTestUrlScheme.length*sampleTestUrlAuthority.length*
                sampleTestUrlPort.length*sampleTestPath.length*sampleTestUrlQuery.length;

        do {
            totUrlsGenerated++;

        }while (incrementTestPartsIndex(sampleTestPartsIndex,sampleTestUrlParts));

//       System.out.println(totUrlsGenerated + "tested");
//       System.out.println(expectedNumUrls  + "were expected");

        assertTrue(totUrlsGenerated==expectedNumUrls);

    }









    public void testIsValidUnitTest() {
//        testIsValid(testUrlParts, UrlValidator.ALLOW_ALL_SCHEMES);
//        setUp();
//        long options =
//            UrlValidator.ALLOW_2_SLASHES
//                + UrlValidator.ALLOW_ALL_SCHEMES
//                + UrlValidator.NO_FRAGMENTS;
//
//        testIsValid(testUrlPartsOptions, options);
        System.out.printf("\n------ Begin testIsValid() testing ------\n");
        int passedTests =0;
        int failedTests = 0;
        UrlValidator urlTest1 = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        System.out.println();	// Newline

        /* Array of URL authorities: 5 Valid, 5 Invalid. */
        String authorities[] = {
                "google.com",	// Valid = True
                "www.twitch.tv",	// Valid = True
                "oregonstate.edu",	// Valid = True
                "github.com",	// Valid = True
                "255.255.255.255",	// Valid = True
                "256.256.256.256",	// Valid = False
                "",	// Valid = False
                "google.a",	// Valid = False
                "1.2.3.4.5",	// Valid = False
                "facebook.1aa"	// Valid = False
        };

        /* Boolean array corresponding with the validity of the authorities from the array listed above. */
        boolean validAuthorities[] = {
                true, true, true, true, true, false, false, false, false, false
        };

        /* Array of URL schemes: 3 Valid, 5 Invalid. */
        String schemes[] = {
                "http://",	// Valid = True
                "ftp://",	// Valid = True
                "h3t://",	// Valid = True
                "3ht://",	// Valid = False
                "http:/",	// Valid = False
                "http:",	// Valid = False
                "http/",	// Valid = False
                "://"	// Valid = False
        };

        /* Boolean array corresponding with the validity of the schemes from the array listed above. */
        Boolean validSchemes[] = {
                true, true, true, false, false, false, false, false
        };

        /* Array of URL paths: 6 Valid, 4 Invalid. */
        String paths[] = {
                "/test1",	// Valid = True
                "/t123",	// Valid = True
                "/$23",	// Valid = True
                "/..",	// Valid = False
                "/../",	// Valid = False
                "/test1/",	// Valid = True
                "",	// Valid = True
                "/test1/file",	// Valid = True
                "/..//file",	// Valid = False
                "/test1//file"	// Valid = False
        };

        /* Boolean array corresponding with the validity of the paths from the array listed above. */
        Boolean validPaths[] = {
                true, true, true, false, false, true, true, true, false, false
        };

        String ports[] = {
                ":80",	// Valid = True
                ":65535",	// Valid = True
                ":65536",	// Valid = False
                ":0",	// Valid = True
                "",	// Valid = True
                ":-1",	// Valid = False
                ":65636",	// Valid = False
                ":999999999999999999",	// Valid = False
                ":65a",	// Valid = False
        };

        Boolean validPorts[] = {
                true, true, false, true, true, false, false, false, false
        };

        String queries[] = {
                "?action=view",	// Valid = True
                "?action=edit&mode=up", // Valid = True
                "",	// Valid = True
                "@action=edit",	// Valid = False
                "&action=edit&mode=up",	// Valid = False
                "!action=view",	// Valid = False
        };

        Boolean validQueries[] = {
                true, true, true, false, false, false
        };

    /* A complete URL is composed of a scheme+authority+port+path+query, all
    of which must be individually valid for the entire URL to be considered valid. */
        /* Automate the construction of URL based on the parameters above. */
        for(int i = 0; i < schemes.length; i++) {
            for(int j = 0; j < authorities.length; j++) {
                for(int k = 0; k < ports.length; k++) {
                    for(int l = 0; l < paths.length; l++) {
                        for(int m = 0; m < queries.length; m++) {
                            String testUrl = schemes[i] + authorities[j] + ports[k] + paths[l] + queries[m];	// Construct a URL.
                            boolean validUrl = validSchemes[i] && validAuthorities[j] && validPorts[k] && validPaths[l] && validQueries[m];
                            System.out.printf("\"%s\" is ", testUrl);
                            /* If all parameters are true and match its corresponding boolean, a valid URL has been constructed: */
                            if (urlTest1.isValid(testUrl) && validUrl) {
                                System.out.print("a valid URL (Test: PASSED)\n");
                                passedTests++;
                            }
                            else {
                                System.out.print("an invalid URL (Test: FAILED)\n");
                                failedTests++;
                            }
                        }
                    }
                }
            }
        }
        System.out.printf("\n------ testIsValid() Test Results ------\n");
        System.out.printf("Number of tests passed: %d\n", passedTests);
        System.out.printf("Number of tests failed: %d\n", failedTests);
    }

   //-------------------- Test data for creating a composite URL
   /**
    * The data given below approximates the 4 parts of a URL
    * <scheme>://<authority><path>?<query> except that the port number
    * is broken out of authority to increase the number of permutations.
    * A complete URL is composed of a scheme+authority+port+path+query,
    * all of which must be individually valid for the entire URL to be considered
    * valid.
    */
   ResultPair[] testUrlScheme = {new ResultPair("http://", true),
                               new ResultPair("ftp://", true),
                               new ResultPair("h3t://", true),
                               new ResultPair("3ht://", false),
                               new ResultPair("http:/", false),
                               new ResultPair("http:", false),
                               new ResultPair("http/", false),
                               new ResultPair("://", false)};

   ResultPair[] testUrlAuthority = {new ResultPair("www.google.com", true),
                                  new ResultPair("www.google.com.", true),
                                  new ResultPair("go.com", true),
                                  new ResultPair("go.au", true),
                                  new ResultPair("0.0.0.0", true),
                                  new ResultPair("255.255.255.255", true),
                                  new ResultPair("256.256.256.256", false),
                                  new ResultPair("255.com", true),
                                  new ResultPair("1.2.3.4.5", false),
                                  new ResultPair("1.2.3.4.", false),
                                  new ResultPair("1.2.3", false),
                                  new ResultPair(".1.2.3.4", false),
                                  new ResultPair("go.a", false),
                                  new ResultPair("go.a1a", false),
                                  new ResultPair("go.cc", true),
                                  new ResultPair("go.1aa", false),
                                  new ResultPair("aaa.", false),
                                  new ResultPair(".aaa", false),
                                  new ResultPair("aaa", false),
                                  new ResultPair("", false)
   };
   ResultPair[] testUrlPort = {new ResultPair(":80", true),
                             new ResultPair(":65535", true), // max possible
                             new ResultPair(":65536", false), // max possible +1
                             new ResultPair(":0", true),
                             new ResultPair("", true),
                             new ResultPair(":-1", false),
                             new ResultPair(":65636", false),
                             new ResultPair(":999999999999999999", false),
                             new ResultPair(":65a", false)
   };
   ResultPair[] testPath = {new ResultPair("/test1", true),
                          new ResultPair("/t123", true),
                          new ResultPair("/$23", true),
                          new ResultPair("/..", false),
                          new ResultPair("/../", false),
                          new ResultPair("/test1/", true),
                          new ResultPair("", true),
                          new ResultPair("/test1/file", true),
                          new ResultPair("/..//file", false),
                          new ResultPair("/test1//file", false)
   };
   //Test allow2slash, noFragment
   ResultPair[] testUrlPathOptions = {new ResultPair("/test1", true),
                                    new ResultPair("/t123", true),
                                    new ResultPair("/$23", true),
                                    new ResultPair("/..", false),
                                    new ResultPair("/../", false),
                                    new ResultPair("/test1/", true),
                                    new ResultPair("/#", false),
                                    new ResultPair("", true),
                                    new ResultPair("/test1/file", true),
                                    new ResultPair("/t123/file", true),
                                    new ResultPair("/$23/file", true),
                                    new ResultPair("/../file", false),
                                    new ResultPair("/..//file", false),
                                    new ResultPair("/test1//file", true),
                                    new ResultPair("/#/file", false)
   };

   ResultPair[] testUrlQuery = {new ResultPair("?action=view", true),
                              new ResultPair("?action=edit&mode=up", true),
                              new ResultPair("", true)
   };

   Object[] testUrlParts = {testUrlScheme, testUrlAuthority, testUrlPort, testPath, testUrlQuery};
   Object[] testUrlPartsOptions = {testUrlScheme, testUrlAuthority, testUrlPort, testUrlPathOptions, testUrlQuery};
   int[] testPartsIndex = {0, 0, 0, 0, 0};

   //---------------- Test data for individual url parts ----------------
   private final String[] schemes = {"http", "gopher", "g0-To+.",
                                      "not_valid" // TODO this will need to be dropped if the ctor validates schemes
                                    };

   ResultPair[] testScheme = {new ResultPair("http", true),
                            new ResultPair("ftp", false),
                            new ResultPair("httpd", false),
                            new ResultPair("gopher", true),
                            new ResultPair("g0-to+.", true),
                            new ResultPair("not_valid", false), // underscore not allowed
                            new ResultPair("HtTp", true),
                            new ResultPair("telnet", false)};


}
