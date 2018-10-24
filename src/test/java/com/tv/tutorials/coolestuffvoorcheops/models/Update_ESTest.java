/*
 * This file was automatically generated by EvoSuite
 * Tue Oct 16 13:57:47 GMT 2018
 */

package com.cheops.candidatemanager.models;

import org.junit.Test;
import static org.junit.Assert.*;
import com.cheops.candidatemanager.models.Update;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class Update_ESTest extends Update_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      Update update0 = new Update(true);
      update0.setCurrentSalaryPackage0OrProposedSalarypackage1((-579));
      int int0 = update0.getCurrentSalaryPackage0OrProposedSalarypackage1();
      assertEquals((-579), int0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      Update update0 = new Update();
      update0.setCurrentSalaryPackage0OrProposedSalarypackage1(1);
      int int0 = update0.getCurrentSalaryPackage0OrProposedSalarypackage1();
      assertEquals(1, int0);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      Update update0 = new Update();
      int int0 = update0.getCurrentSalaryPackage0OrProposedSalarypackage1();
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      Update update0 = new Update();
      boolean boolean0 = update0.isUpdate();
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      Update update0 = new Update();
      update0.setUpdate(true);
      boolean boolean0 = update0.isUpdate();
      assertTrue(boolean0);
  }
}
