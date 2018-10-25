/*
 * This file was automatically generated by EvoSuite
 * Thu Oct 25 09:32:25 GMT 2018
 */

package com.cheops.candidatemanager.controllers;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.cheops.candidatemanager.controllers.MessageOfTheDayController;
import java.security.Principal;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class MessageOfTheDayController_ESTest extends MessageOfTheDayController_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      MessageOfTheDayController messageOfTheDayController0 = new MessageOfTheDayController();
      // Undeclared exception!
      try { 
        messageOfTheDayController0.getMessageOfTheDay((Principal) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.cheops.candidatemanager.controllers.MessageOfTheDayController", e);
      }
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      MessageOfTheDayController messageOfTheDayController0 = new MessageOfTheDayController();
      Principal principal0 = mock(Principal.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(principal0).getName();
      String string0 = messageOfTheDayController0.getMessageOfTheDay(principal0);
      assertEquals("The message of the day is boring for user: null", string0);
  }
}
