/*
 * This file was automatically generated by EvoSuite
 * Tue Oct 16 13:56:31 GMT 2018
 */

package com.tv.tutorials.coolestuffvoorcheops;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.tv.tutorials.coolestuffvoorcheops.CoolestuffvoorcheopsApplication;
import com.tv.tutorials.coolestuffvoorcheops.services.IStorageService;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class CoolestuffvoorcheopsApplication_ESTest extends CoolestuffvoorcheopsApplication_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      CoolestuffvoorcheopsApplication coolestuffvoorcheopsApplication0 = new CoolestuffvoorcheopsApplication();
      IStorageService iStorageService0 = mock(IStorageService.class, new ViolatedAssumptionAnswer());
      CommandLineRunner commandLineRunner0 = coolestuffvoorcheopsApplication0.init(iStorageService0);
      assertNotNull(commandLineRunner0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      String[] stringArray0 = new String[9];
      // Undeclared exception!
      try { 
        CoolestuffvoorcheopsApplication.main(stringArray0);
        fail("Expecting exception: NoClassDefFoundError");
      
      } catch(NoClassDefFoundError e) {
         //
         // Could not initialize class org.springframework.boot.SpringApplication
         //
         verifyException("com.tv.tutorials.coolestuffvoorcheops.CoolestuffvoorcheopsApplication", e);
      }
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      CoolestuffvoorcheopsApplication coolestuffvoorcheopsApplication0 = new CoolestuffvoorcheopsApplication();
      ResourceServerConfigurerAdapter resourceServerConfigurerAdapter0 = coolestuffvoorcheopsApplication0.resourceServerConfigurerAdapter();
      assertNotNull(resourceServerConfigurerAdapter0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      CoolestuffvoorcheopsApplication.GlobalSecurityConfiguration coolestuffvoorcheopsApplication_GlobalSecurityConfiguration0 = null;
      try {
        coolestuffvoorcheopsApplication_GlobalSecurityConfiguration0 = new CoolestuffvoorcheopsApplication.GlobalSecurityConfiguration();
        fail("Expecting exception: NoClassDefFoundError");
      
      } catch(NoClassDefFoundError e) {
      }
  }
}
