/*
 * This file was automatically generated by EvoSuite
 * Tue Oct 16 13:53:41 GMT 2018
 */

package com.cheops.candidatemanager.services.impl;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.cheops.candidatemanager.models.Skills;
import com.cheops.candidatemanager.repositories.SkillsRepository;
import com.cheops.candidatemanager.services.impl.SkillService;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.Vector;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.javaee.injection.Injector;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class SkillsService_ESTest extends SkillsService_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      ArrayList<Skills> arrayList0 = new ArrayList<Skills>();
      Skills skills0 = new Skills();
      arrayList0.add(skills0);
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(arrayList0).when(skillsRepository0).findAllById(anyIterable());
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      List<Integer> list0 = skillsService0.findAllByExperienceGreaterThan((-58), linkedList0);
      assertFalse(list0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      Skills skills0 = new Skills("#=@[j$:u^o", (-1459), "", "", false, false, true);
      Optional<Skills> optional0 = Optional.of(skills0);
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(optional0, (Optional) null).when(skillsRepository0).findById(anyInt());
      doReturn((Object) null).when(skillsRepository0).save(nullable(java.lang.Object.class));
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      Skills skills1 = skillsService0.getSkillsById(2126);
      skillsService0.updateSkills(skills1);
      // Undeclared exception!
      try { 
        skillsService0.getSkillsById(0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("SkillsService", e);
      }
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      Skills skills0 = new Skills((String) null, 2745, (String) null, ":R", true, true, true);
      Optional<Skills> optional0 = Optional.of(skills0);
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(optional0).when(skillsRepository0).findById(anyInt());
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      Skills skills1 = skillsService0.getSkillsById(0);
      assertNull(skills1.getPreferredLocation());
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      ArrayList<Skills> arrayList0 = new ArrayList<Skills>();
      Skills skills0 = new Skills("{v%,R3fY1@Z#w:SxIf=", (-1674), "{v%,R3fY1@Z#w:SxIf=", "{v%,R3fY1@Z#w:SxIf=", true, true, true);
      arrayList0.add(skills0);
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(arrayList0).when(skillsRepository0).findAll();
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      List<Skills> list0 = skillsService0.getAllSkills();
      assertEquals(1, list0.size());
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      Skills skills0 = new Skills("sj5zBtlm*YNy*", 0, "Exception thrown providing input to the parser", (String) null, true, true, true);
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(skills0).when(skillsRepository0).save(nullable(java.lang.Object.class));
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      Skills skills1 = skillsService0.addSkills(skills0);
      assertTrue(skills1.isDotnet());
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      Skills skills0 = new Skills("", 1923, "C;L<.*XF17``JtEY%p", "", true, true, false);
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(skills0).when(skillsRepository0).save(nullable(java.lang.Object.class));
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      Skills skills1 = skillsService0.addSkills(skills0);
      assertEquals("", skills1.getPreferredTechnologies());
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      Skills skills0 = new Skills("", (-1567), "", "", false, true, false);
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(skills0).when(skillsRepository0).save(nullable(java.lang.Object.class));
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      Skills skills1 = skillsService0.addSkills(skills0);
      assertEquals("", skills1.getPreferredLocation());
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      // Undeclared exception!
      try { 
        skillsService0.updateSkills((Skills) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("SkillsService", e);
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      Skills skills0 = new Skills((String) null, 0, (String) null, "C;MU:", true, true, false);
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(linkedList0).when(skillsRepository0).save(nullable(java.lang.Object.class));
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      // Undeclared exception!
      try { 
        skillsService0.updateSkills(skills0);
        fail("Expecting exception: ClassCastException");
      
      } catch(ClassCastException e) {
         //
         // java.util.LinkedList cannot be cast to Skill
         //
         verifyException("SkillsRepository$MockitoMock$1904746507", e);
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn((Optional) null).when(skillsRepository0).findById(anyInt());
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      int int0 = 2536;
      // Undeclared exception!
      try { 
        skillsService0.getSkillsById(2536);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("SkillsService", e);
      }
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      // Undeclared exception!
      try { 
        skillsService0.getAllSkills();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("SkillsService", e);
      }
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn((List<Object>) null).when(skillsRepository0).findAllByJava(anyBoolean());
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      // Undeclared exception!
      try { 
        skillsService0.findAllJava();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("SkillsService", e);
      }
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      Vector<Skills> vector0 = new Vector<Skills>();
      vector0.setSize(1);
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(vector0).when(skillsRepository0).findAllByFrontend(anyBoolean());
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      // Undeclared exception!
      try { 
        skillsService0.findAllFrontend();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("SkillsService", e);
      }
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn((List<Skills>) null).when(skillsRepository0).findAllByDotnet(anyBoolean());
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      // Undeclared exception!
      try { 
        skillsService0.findAllDotnet();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("SkillsService", e);
      }
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn((Iterable<Object>) null).when(skillsRepository0).findAllById(anyIterable());
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      Stack<Integer> stack0 = new Stack<Integer>();
      // Undeclared exception!
      try { 
        skillsService0.findAllByExperienceGreaterThan((-465), stack0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("SkillsService", e);
      }
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      LinkedList<Skills> linkedList0 = new LinkedList<Skills>();
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(linkedList0).when(skillsRepository0).findAllByJava(anyBoolean());
      doReturn(linkedList0).when(skillsRepository0).findAllById(anyIterable());
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      List<Integer> list0 = skillsService0.findAllJava();
      // Undeclared exception!
      try { 
        skillsService0.findAllByExperienceGreaterThan(0, list0);
        fail("Expecting exception: ClassCastException");
      
      } catch(ClassCastException e) {
         //
         // java.util.LinkedList cannot be cast to java.util.ArrayList
         //
         verifyException("SkillsService", e);
      }
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      // Undeclared exception!
      try { 
        skillsService0.addSkills((Skills) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("SkillsService", e);
      }
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      LinkedList<Skills> linkedList0 = new LinkedList<Skills>();
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(linkedList0).when(skillsRepository0).findAll();
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      List<Skills> list0 = skillsService0.getAllSkills();
      assertTrue(list0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      ArrayList<Skills> arrayList0 = new ArrayList<Skills>();
      Skills skills0 = new Skills("LT:", (-1674), "LT:", "LT:", false, true, false);
      arrayList0.add(skills0);
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(arrayList0).when(skillsRepository0).findAllById(anyIterable());
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      ArrayList<Integer> arrayList1 = new ArrayList<Integer>();
      List<Integer> list0 = skillsService0.findAllByExperienceGreaterThan(65535, arrayList1);
      assertTrue(list0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      ArrayList<Skills> arrayList0 = new ArrayList<Skills>();
      Skills skills0 = new Skills("LT:", (-1674), "LT:", "LT:", false, true, false);
      arrayList0.add(skills0);
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(arrayList0).when(skillsRepository0).findAllById(anyIterable());
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      ArrayList<Integer> arrayList1 = new ArrayList<Integer>();
      List<Integer> list0 = skillsService0.findAllByExperienceGreaterThan((-1674), arrayList1);
      assertFalse(list0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      ArrayList<Skills> arrayList0 = new ArrayList<Skills>();
      Skills skills0 = new Skills("LN(", (-645), "LN(", "LN(", true, true, true);
      arrayList0.add(skills0);
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(arrayList0).when(skillsRepository0).findAllByFrontend(anyBoolean());
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      List<Integer> list0 = skillsService0.findAllFrontend();
      assertEquals(1, list0.size());
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      ArrayList<Skills> arrayList0 = new ArrayList<Skills>();
      Skills skills0 = new Skills("LT:", (-1674), "LT:", "LT:", false, true, false);
      arrayList0.add(skills0);
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(arrayList0).when(skillsRepository0).findAllByFrontend(anyBoolean());
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      List<Integer> list0 = skillsService0.findAllFrontend();
      assertEquals(0, list0.size());
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      LinkedList<Skills> linkedList0 = new LinkedList<Skills>();
      Skills skills0 = new Skills();
      linkedList0.add(skills0);
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(linkedList0).when(skillsRepository0).findAllByJava(anyBoolean());
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      List<Integer> list0 = skillsService0.findAllJava();
      assertEquals(0, list0.size());
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      ArrayList<Skills> arrayList0 = new ArrayList<Skills>();
      Skills skills0 = new Skills("LT:", (-1674), "LT:", "LT:", false, true, false);
      arrayList0.add(skills0);
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(arrayList0).when(skillsRepository0).findAllByJava(anyBoolean());
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      List<Integer> list0 = skillsService0.findAllJava();
      assertEquals(1, list0.size());
  }

  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      LinkedList<Skills> linkedList0 = new LinkedList<Skills>();
      Skills skills0 = new Skills("lKZtHTy\"ho", (-1588), "lKZtHTy\"ho", "lKZtHTy\"ho", true, true, true);
      linkedList0.add(skills0);
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(linkedList0).when(skillsRepository0).findAllByDotnet(anyBoolean());
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      List<Integer> list0 = skillsService0.findAllDotnet();
      assertEquals(1, list0.size());
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      ArrayList<Skills> arrayList0 = new ArrayList<Skills>();
      Skills skills0 = new Skills("LT:", (-1674), "LT:", "LT:", false, true, false);
      arrayList0.add(skills0);
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn(arrayList0).when(skillsRepository0).findAllByDotnet(anyBoolean());
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      List<Integer> list0 = skillsService0.findAllDotnet();
      assertEquals(0, list0.size());
  }

  @Test(timeout = 4000)
  public void test26()  throws Throwable  {
      SkillsService skillsService0 = new SkillsService();
      SkillsRepository skillsRepository0 = mock(SkillsRepository.class, new ViolatedAssumptionAnswer());
      doReturn((Optional<Object>) null).when(skillsRepository0).findById(anyInt());
      Injector.inject(skillsService0, (Class<?>) SkillsService.class, "skillsRepository", (Object) skillsRepository0);
      Injector.validateBean(skillsService0, (Class<?>) SkillsService.class);
      // Undeclared exception!
      try { 
        skillsService0.deleteSkills(59);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("SkillsService", e);
      }
  }
}
