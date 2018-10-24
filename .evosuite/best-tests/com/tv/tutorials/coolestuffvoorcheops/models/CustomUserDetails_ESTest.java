/*
 * This file was automatically generated by EvoSuite
 * Tue Oct 16 13:05:15 GMT 2018
 */

package com.cheops.candidatemanager.models;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.cheops.candidatemanager.models.CustomUserDetails;
import com.cheops.candidatemanager.models.User;
import java.util.Set;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class CustomUserDetails_ESTest extends CustomUserDetails_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      User user0 = mock(User.class, new ViolatedAssumptionAnswer());
      doReturn(0).when(user0).getActive();
      doReturn((String) null).when(user0).getEmail();
      doReturn(0).when(user0).getId();
      doReturn((String) null).when(user0).getLastName();
      doReturn((String) null).when(user0).getName();
      doReturn((String) null).when(user0).getPassword();
      doReturn((Set) null).when(user0).getRoles();
      CustomUserDetails customUserDetails0 = new CustomUserDetails(user0);
      String string0 = customUserDetails0.getPassword();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      User user0 = mock(User.class, new ViolatedAssumptionAnswer());
      doReturn(0).when(user0).getActive();
      doReturn((String) null).when(user0).getEmail();
      doReturn(0).when(user0).getId();
      doReturn((String) null).when(user0).getLastName();
      doReturn((String) null).when(user0).getName();
      doReturn((String) null).when(user0).getPassword();
      doReturn((Set) null).when(user0).getRoles();
      CustomUserDetails customUserDetails0 = new CustomUserDetails(user0);
      customUserDetails0.setPassword("");
      String string0 = customUserDetails0.getPassword();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      User user0 = mock(User.class, new ViolatedAssumptionAnswer());
      doReturn(0).when(user0).getActive();
      doReturn((String) null).when(user0).getEmail();
      doReturn(0).when(user0).getId();
      doReturn((String) null).when(user0).getLastName();
      doReturn((String) null).when(user0).getName();
      doReturn((String) null).when(user0).getPassword();
      doReturn((Set) null).when(user0).getRoles();
      CustomUserDetails customUserDetails0 = new CustomUserDetails(user0);
      String string0 = customUserDetails0.getUsername();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      User user0 = mock(User.class, new ViolatedAssumptionAnswer());
      doReturn(0).when(user0).getActive();
      doReturn((String) null).when(user0).getEmail();
      doReturn(0).when(user0).getId();
      doReturn((String) null).when(user0).getLastName();
      doReturn((String) null).when(user0).getName();
      doReturn((String) null).when(user0).getPassword();
      doReturn((Set) null).when(user0).getRoles();
      CustomUserDetails customUserDetails0 = new CustomUserDetails(user0);
      // Undeclared exception!
      try { 
        customUserDetails0.getAuthorities();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("CustomUserDetails", e);
      }
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      User user0 = mock(User.class, new ViolatedAssumptionAnswer());
      doReturn(0).when(user0).getActive();
      doReturn((String) null).when(user0).getEmail();
      doReturn(0).when(user0).getId();
      doReturn((String) null).when(user0).getLastName();
      doReturn((String) null).when(user0).getName();
      doReturn((String) null).when(user0).getPassword();
      doReturn((Set) null).when(user0).getRoles();
      CustomUserDetails customUserDetails0 = new CustomUserDetails(user0);
      boolean boolean0 = customUserDetails0.isEnabled();
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      User user0 = mock(User.class, new ViolatedAssumptionAnswer());
      doReturn(0).when(user0).getActive();
      doReturn((String) null).when(user0).getEmail();
      doReturn(0).when(user0).getId();
      doReturn((String) null).when(user0).getLastName();
      doReturn((String) null).when(user0).getName();
      doReturn((String) null).when(user0).getPassword();
      doReturn((Set) null).when(user0).getRoles();
      CustomUserDetails customUserDetails0 = new CustomUserDetails(user0);
      boolean boolean0 = customUserDetails0.isAccountNonLocked();
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      User user0 = mock(User.class, new ViolatedAssumptionAnswer());
      doReturn(0).when(user0).getActive();
      doReturn((String) null).when(user0).getEmail();
      doReturn(0).when(user0).getId();
      doReturn((String) null).when(user0).getLastName();
      doReturn((String) null).when(user0).getName();
      doReturn((String) null).when(user0).getPassword();
      doReturn((Set) null).when(user0).getRoles();
      CustomUserDetails customUserDetails0 = new CustomUserDetails(user0);
      boolean boolean0 = customUserDetails0.isCredentialsNonExpired();
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test7()  throws Throwable  {
      User user0 = mock(User.class, new ViolatedAssumptionAnswer());
      doReturn(0).when(user0).getActive();
      doReturn((String) null).when(user0).getEmail();
      doReturn(0).when(user0).getId();
      doReturn((String) null).when(user0).getLastName();
      doReturn((String) null).when(user0).getName();
      doReturn((String) null).when(user0).getPassword();
      doReturn((Set) null).when(user0).getRoles();
      CustomUserDetails customUserDetails0 = new CustomUserDetails(user0);
      boolean boolean0 = customUserDetails0.isAccountNonExpired();
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test8()  throws Throwable  {
      CustomUserDetails customUserDetails0 = null;
      try {
        customUserDetails0 = new CustomUserDetails((User) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("User", e);
      }
  }
}
