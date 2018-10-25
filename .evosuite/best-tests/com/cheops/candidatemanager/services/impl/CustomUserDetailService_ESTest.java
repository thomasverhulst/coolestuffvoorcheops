/*
 * This file was automatically generated by EvoSuite
 * Thu Oct 25 09:29:26 GMT 2018
 */

package com.cheops.candidatemanager.services.impl;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.cheops.candidatemanager.models.User;
import com.cheops.candidatemanager.repositories.UserRepository;
import com.cheops.candidatemanager.services.impl.CustomUserDetailService;
import java.util.Optional;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.javaee.injection.Injector;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class CustomUserDetailService_ESTest extends CustomUserDetailService_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      CustomUserDetailService customUserDetailService0 = new CustomUserDetailService();
      UserRepository userRepository0 = mock(UserRepository.class, new ViolatedAssumptionAnswer());
      doReturn((Optional) null).when(userRepository0).findByName(anyString());
      Injector.inject(customUserDetailService0, (Class<?>) CustomUserDetailService.class, "userRepository", (Object) userRepository0);
      Injector.validateBean(customUserDetailService0, (Class<?>) CustomUserDetailService.class);
      // Undeclared exception!
      try { 
        customUserDetailService0.loadUserByUsername(", password=");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.cheops.candidatemanager.services.impl.CustomUserDetailService", e);
      }
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      CustomUserDetailService customUserDetailService0 = new CustomUserDetailService();
      UserRepository userRepository0 = mock(UserRepository.class, new ViolatedAssumptionAnswer());
      Injector.inject(customUserDetailService0, (Class<?>) CustomUserDetailService.class, "userRepository", (Object) userRepository0);
      Injector.validateBean(customUserDetailService0, (Class<?>) CustomUserDetailService.class);
      customUserDetailService0.addUser((User) null);
  }
}
