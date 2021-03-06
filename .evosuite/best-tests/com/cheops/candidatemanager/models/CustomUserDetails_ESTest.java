/*
 * This file was automatically generated by EvoSuite
 * Thu Oct 25 12:51:18 GMT 2018
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
  public void test00()  throws Throwable  {
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
  public void test01()  throws Throwable  {
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
  public void test02()  throws Throwable  {
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
  public void test03()  throws Throwable  {
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
  public void test04()  throws Throwable  {
      User user0 = mock(User.class, new ViolatedAssumptionAnswer());
      doReturn(0).when(user0).getActive();
      doReturn((String) null).when(user0).getEmail();
      doReturn(0).when(user0).getId();
      doReturn((String) null).when(user0).getLastName();
      doReturn((String) null).when(user0).getName();
      doReturn((String) null).when(user0).getPassword();
      doReturn((Set) null).when(user0).getRoles();
      CustomUserDetails customUserDetails0 = new CustomUserDetails(user0);
      customUserDetails0.setName("jxu5E<:y^DL`6");
      String string0 = customUserDetails0.getUsername();
      assertEquals("jxu5E<:y^DL`6", string0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      User user0 = mock(User.class, new ViolatedAssumptionAnswer());
      doReturn(0).when(user0).getActive();
      doReturn((String) null).when(user0).getEmail();
      doReturn(0).when(user0).getId();
      doReturn((String) null).when(user0).getLastName();
      doReturn((String) null).when(user0).getName();
      doReturn((String) null).when(user0).getPassword();
      doReturn((Set) null).when(user0).getRoles();
      CustomUserDetails customUserDetails0 = new CustomUserDetails(user0);
      customUserDetails0.setName("");
      String string0 = customUserDetails0.getUsername();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
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
  public void test07()  throws Throwable  {
      User user0 = mock(User.class, new ViolatedAssumptionAnswer());
      doReturn(0).when(user0).getActive();
      doReturn((String) null).when(user0).getEmail();
      doReturn(0).when(user0).getId();
      doReturn((String) null).when(user0).getLastName();
      doReturn((String) null).when(user0).getName();
      doReturn((String) null).when(user0).getPassword();
      doReturn((Set) null).when(user0).getRoles();
      CustomUserDetails customUserDetails0 = new CustomUserDetails(user0);
      customUserDetails0.setPassword("q>J9A 6-Qbbc$");
      String string0 = customUserDetails0.getPassword();
      assertEquals("q>J9A 6-Qbbc$", string0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
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
  public void test09()  throws Throwable  {
      CustomUserDetails customUserDetails0 = null;
      try {
        customUserDetails0 = new CustomUserDetails((User) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.cheops.candidatemanager.models.User", e);
      }
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
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
  public void test11()  throws Throwable  {
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
         verifyException("com.cheops.candidatemanager.models.CustomUserDetails", e);
      }
  }
}
