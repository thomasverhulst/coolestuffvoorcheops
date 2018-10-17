/*
 * This file was automatically generated by EvoSuite
 * Tue Oct 16 13:56:59 GMT 2018
 */

package com.tv.tutorials.coolestuffvoorcheops.models;

import org.junit.Test;
import static org.junit.Assert.*;
import com.tv.tutorials.coolestuffvoorcheops.models.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.models.CandidateSearchResolver;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class CandidateSearchResolver_ESTest extends CandidateSearchResolver_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      CandidateSearchResolver candidateSearchResolver0 = new CandidateSearchResolver((Candidate) null, "", "");
      String string0 = candidateSearchResolver0.getExpertise();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      CandidateSearchResolver candidateSearchResolver0 = new CandidateSearchResolver();
      candidateSearchResolver0.setApplicationStatus("");
      String string0 = candidateSearchResolver0.getApplicationStatus();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      CandidateSearchResolver candidateSearchResolver0 = new CandidateSearchResolver();
      Candidate candidate0 = candidateSearchResolver0.getCandidate();
      assertNull(candidate0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      CandidateSearchResolver candidateSearchResolver0 = new CandidateSearchResolver((Candidate) null, "", "GFV|<a,:DwS%z^/");
      candidateSearchResolver0.setCandidate((Candidate) null);
      assertEquals("GFV|<a,:DwS%z^/", candidateSearchResolver0.getApplicationStatus());
      assertEquals("", candidateSearchResolver0.getExpertise());
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      CandidateSearchResolver candidateSearchResolver0 = new CandidateSearchResolver();
      candidateSearchResolver0.setApplicationStatus("com.tv.tutorials.coolestuffvoorcheops.models.Candidate");
      String string0 = candidateSearchResolver0.getApplicationStatus();
      assertEquals("com.tv.tutorials.coolestuffvoorcheops.models.Candidate", string0);
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      CandidateSearchResolver candidateSearchResolver0 = new CandidateSearchResolver();
      String string0 = candidateSearchResolver0.getExpertise();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      CandidateSearchResolver candidateSearchResolver0 = new CandidateSearchResolver();
      candidateSearchResolver0.setExpertise("6dQ`y'}/=F");
      String string0 = candidateSearchResolver0.getExpertise();
      assertEquals("6dQ`y'}/=F", string0);
  }

  @Test(timeout = 4000)
  public void test7()  throws Throwable  {
      CandidateSearchResolver candidateSearchResolver0 = new CandidateSearchResolver();
      String string0 = candidateSearchResolver0.getApplicationStatus();
      assertNull(string0);
  }
}
