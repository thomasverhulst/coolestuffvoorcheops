/*
 * This file was automatically generated by EvoSuite
 * Thu Oct 25 12:50:12 GMT 2018
 */

package com.cheops.candidatemanager.models;

import org.junit.Test;
import static org.junit.Assert.*;
import com.cheops.candidatemanager.models.CandaidateSearchModel;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class CandaidateSearchModel_ESTest extends CandaidateSearchModel_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      CandaidateSearchModel candaidateSearchModel0 = new CandaidateSearchModel();
      candaidateSearchModel0.setLink("KNtVg|e");
      String string0 = candaidateSearchModel0.getLink();
      assertEquals("KNtVg|e", string0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      CandaidateSearchModel candaidateSearchModel0 = new CandaidateSearchModel();
      candaidateSearchModel0.setLink("");
      String string0 = candaidateSearchModel0.getLink();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      CandaidateSearchModel candaidateSearchModel0 = new CandaidateSearchModel();
      candaidateSearchModel0.setId("com.cheops.candidatemanager.models.CandidateSearchModel");
      String string0 = candaidateSearchModel0.getId();
      assertEquals("com.cheops.candidatemanager.models.CandidateSearchModel", string0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      CandaidateSearchModel candaidateSearchModel0 = new CandaidateSearchModel();
      candaidateSearchModel0.setId("");
      String string0 = candaidateSearchModel0.getId();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      CandaidateSearchModel candaidateSearchModel0 = new CandaidateSearchModel();
      String string0 = candaidateSearchModel0.getLink();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      CandaidateSearchModel candaidateSearchModel0 = new CandaidateSearchModel("X*", "");
      assertEquals("X*", candaidateSearchModel0.getId());
      assertEquals("", candaidateSearchModel0.getLink());
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      CandaidateSearchModel candaidateSearchModel0 = new CandaidateSearchModel();
      String string0 = candaidateSearchModel0.getId();
      assertNull(string0);
  }
}
