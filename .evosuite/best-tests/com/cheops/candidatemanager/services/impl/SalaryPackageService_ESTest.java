/*
 * This file was automatically generated by EvoSuite
 * Thu Oct 25 09:32:50 GMT 2018
 */

package com.cheops.candidatemanager.services.impl;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.cheops.candidatemanager.models.SalaryPackage;
import com.cheops.candidatemanager.repositories.SalaryPackageRepository;
import com.cheops.candidatemanager.services.impl.SalaryPackageService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.javaee.injection.Injector;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class SalaryPackageService_ESTest extends SalaryPackageService_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      SalaryPackageService salaryPackageService0 = new SalaryPackageService();
      ArrayList<SalaryPackage> arrayList0 = new ArrayList<SalaryPackage>();
      arrayList0.add((SalaryPackage) null);
      SalaryPackageRepository salaryPackageRepository0 = mock(SalaryPackageRepository.class, new ViolatedAssumptionAnswer());
      doReturn(arrayList0).when(salaryPackageRepository0).findAll();
      Injector.inject(salaryPackageService0, (Class<?>) SalaryPackageService.class, "salaryPackageRepository", (Object) salaryPackageRepository0);
      Injector.validateBean(salaryPackageService0, (Class<?>) SalaryPackageService.class);
      List<SalaryPackage> list0 = salaryPackageService0.getAllSalaryPackages();
      assertFalse(list0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      SalaryPackageService salaryPackageService0 = new SalaryPackageService();
      Double double0 = Double.valueOf((-71.2060714));
      SalaryPackage salaryPackage0 = new SalaryPackage((-2320.0), "", double0, double0, true, true);
      SalaryPackageRepository salaryPackageRepository0 = mock(SalaryPackageRepository.class, new ViolatedAssumptionAnswer());
      doReturn(salaryPackage0).when(salaryPackageRepository0).save(nullable(java.lang.Object.class));
      Injector.inject(salaryPackageService0, (Class<?>) SalaryPackageService.class, "salaryPackageRepository", (Object) salaryPackageRepository0);
      Injector.validateBean(salaryPackageService0, (Class<?>) SalaryPackageService.class);
      SalaryPackage salaryPackage1 = salaryPackageService0.addSalaryPackage(salaryPackage0);
      assertEquals((-71.2060714), salaryPackage1.getDailyAllowance(), 0.01);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      SalaryPackageService salaryPackageService0 = new SalaryPackageService();
      SalaryPackage salaryPackage0 = new SalaryPackage();
      SalaryPackageRepository salaryPackageRepository0 = mock(SalaryPackageRepository.class, new ViolatedAssumptionAnswer());
      doReturn(salaryPackage0).when(salaryPackageRepository0).save(nullable(java.lang.Object.class));
      Injector.inject(salaryPackageService0, (Class<?>) SalaryPackageService.class, "salaryPackageRepository", (Object) salaryPackageRepository0);
      Injector.validateBean(salaryPackageService0, (Class<?>) SalaryPackageService.class);
      SalaryPackage salaryPackage1 = salaryPackageService0.addSalaryPackage(salaryPackage0);
      assertSame(salaryPackage0, salaryPackage1);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      SalaryPackageService salaryPackageService0 = new SalaryPackageService();
      Double double0 = new Double(0.0);
      SalaryPackage salaryPackage0 = new SalaryPackage(1.0, "", double0, double0, false, false);
      SalaryPackageRepository salaryPackageRepository0 = mock(SalaryPackageRepository.class, new ViolatedAssumptionAnswer());
      doReturn(salaryPackage0).when(salaryPackageRepository0).save(nullable(java.lang.Object.class));
      Injector.inject(salaryPackageService0, (Class<?>) SalaryPackageService.class, "salaryPackageRepository", (Object) salaryPackageRepository0);
      Injector.validateBean(salaryPackageService0, (Class<?>) SalaryPackageService.class);
      SalaryPackage salaryPackage1 = salaryPackageService0.addSalaryPackage(salaryPackage0);
      assertEquals(1.0, salaryPackage1.getGrossSalary(), 0.01);
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      SalaryPackageService salaryPackageService0 = new SalaryPackageService();
      Double double0 = new Double((-1.0));
      SalaryPackage salaryPackage0 = new SalaryPackage((-1.0), "", double0, double0, true, false);
      SalaryPackageRepository salaryPackageRepository0 = mock(SalaryPackageRepository.class, new ViolatedAssumptionAnswer());
      doReturn(salaryPackage0, "").when(salaryPackageRepository0).save(nullable(java.lang.Object.class));
      Injector.inject(salaryPackageService0, (Class<?>) SalaryPackageService.class, "salaryPackageRepository", (Object) salaryPackageRepository0);
      Injector.validateBean(salaryPackageService0, (Class<?>) SalaryPackageService.class);
      SalaryPackage salaryPackage1 = salaryPackageService0.addSalaryPackage(salaryPackage0);
      // Undeclared exception!
      try { 
        salaryPackageService0.updateSalaryPackage(salaryPackage1);
        fail("Expecting exception: ClassCastException");
      
      } catch(ClassCastException e) {
         //
         // java.lang.String cannot be cast to com.cheops.candidatemanager.models.SalaryPackage
         //
         verifyException("com.cheops.candidatemanager.repositories.SalaryPackageRepository$MockitoMock$64053919", e);
      }
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      SalaryPackageService salaryPackageService0 = new SalaryPackageService();
      SalaryPackageRepository salaryPackageRepository0 = mock(SalaryPackageRepository.class, new ViolatedAssumptionAnswer());
      doReturn((Optional) null).when(salaryPackageRepository0).findById(anyInt());
      Injector.inject(salaryPackageService0, (Class<?>) SalaryPackageService.class, "salaryPackageRepository", (Object) salaryPackageRepository0);
      Injector.validateBean(salaryPackageService0, (Class<?>) SalaryPackageService.class);
      // Undeclared exception!
      try { 
        salaryPackageService0.getSalaryPackageById(2420);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.cheops.candidatemanager.services.impl.SalaryPackageService", e);
      }
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      SalaryPackageService salaryPackageService0 = new SalaryPackageService();
      SalaryPackageRepository salaryPackageRepository0 = mock(SalaryPackageRepository.class, new ViolatedAssumptionAnswer());
      doReturn((Optional) null).when(salaryPackageRepository0).findById(anyInt());
      Injector.inject(salaryPackageService0, (Class<?>) SalaryPackageService.class, "salaryPackageRepository", (Object) salaryPackageRepository0);
      Injector.validateBean(salaryPackageService0, (Class<?>) SalaryPackageService.class);
      // Undeclared exception!
      try { 
        salaryPackageService0.deleteSalaryPackage((-318));
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.cheops.candidatemanager.services.impl.SalaryPackageService", e);
      }
  }

  @Test(timeout = 4000)
  public void test7()  throws Throwable  {
      SalaryPackageService salaryPackageService0 = new SalaryPackageService();
      SalaryPackageRepository salaryPackageRepository0 = mock(SalaryPackageRepository.class, new ViolatedAssumptionAnswer());
      doReturn("RY})n8A Zl,n{(8x4n").when(salaryPackageRepository0).save(nullable(java.lang.Object.class));
      Injector.inject(salaryPackageService0, (Class<?>) SalaryPackageService.class, "salaryPackageRepository", (Object) salaryPackageRepository0);
      Injector.validateBean(salaryPackageService0, (Class<?>) SalaryPackageService.class);
      // Undeclared exception!
      try { 
        salaryPackageService0.addSalaryPackage((SalaryPackage) null);
        fail("Expecting exception: ClassCastException");
      
      } catch(ClassCastException e) {
         //
         // java.lang.String cannot be cast to com.cheops.candidatemanager.models.SalaryPackage
         //
         verifyException("com.cheops.candidatemanager.repositories.SalaryPackageRepository$MockitoMock$64053919", e);
      }
  }

  @Test(timeout = 4000)
  public void test8()  throws Throwable  {
      SalaryPackageService salaryPackageService0 = new SalaryPackageService();
      SalaryPackageRepository salaryPackageRepository0 = mock(SalaryPackageRepository.class, new ViolatedAssumptionAnswer());
      doReturn((Object) null).when(salaryPackageRepository0).save(nullable(java.lang.Object.class));
      Injector.inject(salaryPackageService0, (Class<?>) SalaryPackageService.class, "salaryPackageRepository", (Object) salaryPackageRepository0);
      Injector.validateBean(salaryPackageService0, (Class<?>) SalaryPackageService.class);
      salaryPackageService0.updateSalaryPackage((SalaryPackage) null);
  }

  @Test(timeout = 4000)
  public void test9()  throws Throwable  {
      SalaryPackageService salaryPackageService0 = new SalaryPackageService();
      SalaryPackageRepository salaryPackageRepository0 = mock(SalaryPackageRepository.class, new ViolatedAssumptionAnswer());
      doReturn((Iterable) null).when(salaryPackageRepository0).findAll();
      Injector.inject(salaryPackageService0, (Class<?>) SalaryPackageService.class, "salaryPackageRepository", (Object) salaryPackageRepository0);
      Injector.validateBean(salaryPackageService0, (Class<?>) SalaryPackageService.class);
      // Undeclared exception!
      try { 
        salaryPackageService0.getAllSalaryPackages();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.cheops.candidatemanager.services.impl.SalaryPackageService", e);
      }
  }
}
