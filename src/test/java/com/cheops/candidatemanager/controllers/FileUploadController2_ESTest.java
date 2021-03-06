///*
// * This file was automatically generated by EvoSuite
// * Thu Oct 25 12:44:02 GMT 2018
// */
//
//package com.cheops.candidatemanager.controllers;
//
//import org.junit.Test;
//import static org.junit.Assert.*;
//import static org.evosuite.shaded.org.mockito.Mockito.*;
//import static org.evosuite.runtime.EvoAssertions.*;
//import com.cheops.candidatemanager.controllers.FileUploadController2;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.PrintWriter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponseWrapper;
//import javax.servlet.http.Part;
//import org.evosuite.runtime.EvoRunner;
//import org.evosuite.runtime.EvoRunnerParameters;
//import org.evosuite.runtime.ViolatedAssumptionAnswer;
//import org.evosuite.runtime.testdata.EvoSuiteFile;
//import org.evosuite.runtime.testdata.FileSystemHandling;
//import org.junit.runner.RunWith;
//
//@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
//public class FileUploadController2_ESTest extends FileUploadController2_ESTest_scaffolding {
//
//  @Test(timeout = 4000)
//  public void test0()  throws Throwable  {
//      FileUploadController2 fileUploadController2_0 = new FileUploadController2();
//      Part part0 = mock(Part.class, new ViolatedAssumptionAnswer());
//      doReturn("ZUO&)gX!h69GHc?").when(part0).getHeader(anyString());
//      Part part1 = mock(Part.class, new ViolatedAssumptionAnswer());
//      doReturn("filename$)=:b+").when(part1).getHeader(anyString());
//      doReturn((InputStream) null).when(part1).getInputStream();
//      HttpServletRequest httpServletRequest0 = mock(HttpServletRequest.class, new ViolatedAssumptionAnswer());
//      doReturn("", "3Pof\"[fv_bCtJ.,").when(httpServletRequest0).getParameter(anyString());
//      doReturn(part0, part1).when(httpServletRequest0).getPart(anyString());
//      HttpServletRequestWrapper httpServletRequestWrapper0 = new HttpServletRequestWrapper(httpServletRequest0);
//      PrintWriter printWriter0 = mock(PrintWriter.class, new ViolatedAssumptionAnswer());
//      EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("null");
//      FileSystemHandling.createFolder(evoSuiteFile0);
//      HttpServletResponse httpServletResponse0 = mock(HttpServletResponse.class, new ViolatedAssumptionAnswer());
//      doReturn(printWriter0, (PrintWriter) null).when(httpServletResponse0).getWriter();
//      HttpServletResponseWrapper httpServletResponseWrapper0 = new HttpServletResponseWrapper(httpServletResponse0);
//      fileUploadController2_0.processRequest(httpServletRequestWrapper0, httpServletResponseWrapper0);
//      // Undeclared exception!
//      try {
//        fileUploadController2_0.processRequest(httpServletRequestWrapper0, httpServletResponseWrapper0);
//        fail("Expecting exception: NullPointerException");
//
//      } catch(NullPointerException e) {
//         //
//         // no message in exception (getMessage() returned null)
//         //
//         verifyException("com.cheops.candidatemanager.controllers.FileUploadController2", e);
//      }
//  }
//
//  @Test(timeout = 4000)
//  public void test1()  throws Throwable  {
//      FileUploadController2 fileUploadController2_0 = new FileUploadController2();
//      InputStream inputStream0 = mock(InputStream.class, new ViolatedAssumptionAnswer());
//      doReturn((-3572)).when(inputStream0).read(any(byte[].class));
//      Part part0 = mock(Part.class, new ViolatedAssumptionAnswer());
//      doReturn("BhUfiemeZ`0!").when(part0).getHeader(anyString());
//      doReturn(inputStream0).when(part0).getInputStream();
//      HttpServletRequest httpServletRequest0 = mock(HttpServletRequest.class, new ViolatedAssumptionAnswer());
//      doReturn("BhUfiemeZ`0!").when(httpServletRequest0).getParameter(anyString());
//      doReturn(part0).when(httpServletRequest0).getPart(anyString());
//      PrintWriter printWriter0 = mock(PrintWriter.class, new ViolatedAssumptionAnswer());
//      HttpServletResponse httpServletResponse0 = mock(HttpServletResponse.class, new ViolatedAssumptionAnswer());
//      doReturn(printWriter0).when(httpServletResponse0).getWriter();
//      try {
//        fileUploadController2_0.processRequest(httpServletRequest0, httpServletResponse0);
//        fail("Expecting exception: IOException");
//
//      } catch(IOException e) {
//         //
//         // Error in writing to file
//         //
//         verifyException("org.evosuite.runtime.mock.java.io.NativeMockedIO", e);
//      }
//  }
//
//  @Test(timeout = 4000)
//  public void test2()  throws Throwable  {
//      FileUploadController2 fileUploadController2_0 = new FileUploadController2();
//      HttpServletRequest httpServletRequest0 = mock(HttpServletRequest.class, new ViolatedAssumptionAnswer());
//      HttpServletRequestWrapper httpServletRequestWrapper0 = new HttpServletRequestWrapper(httpServletRequest0);
//      HttpServletResponse httpServletResponse0 = mock(HttpServletResponse.class, new ViolatedAssumptionAnswer());
//      HttpServletResponseWrapper httpServletResponseWrapper0 = new HttpServletResponseWrapper(httpServletResponse0);
//      httpServletResponseWrapper0.setResponse(httpServletResponseWrapper0);
//      // Undeclared exception!
//      try {
//        fileUploadController2_0.processRequest(httpServletRequestWrapper0, httpServletResponseWrapper0);
//        fail("Expecting exception: StackOverflowError");
//
//      } catch(StackOverflowError e) {
//         //
//         // no message in exception (getMessage() returned null)
//         //
//      }
//  }
//
//  @Test(timeout = 4000)
//  public void test3()  throws Throwable  {
//      FileUploadController2 fileUploadController2_0 = new FileUploadController2();
//      Part part0 = mock(Part.class, new ViolatedAssumptionAnswer());
//      doReturn("ZUO)gX!h69GHc?").when(part0).getHeader(anyString());
//      HttpServletRequest httpServletRequest0 = mock(HttpServletRequest.class, new ViolatedAssumptionAnswer());
//      doReturn("", (String) null).when(httpServletRequest0).getParameter(anyString());
//      doReturn(part0, (Part) null).when(httpServletRequest0).getPart(anyString());
//      HttpServletRequestWrapper httpServletRequestWrapper0 = new HttpServletRequestWrapper(httpServletRequest0);
//      PrintWriter printWriter0 = mock(PrintWriter.class, new ViolatedAssumptionAnswer());
//      EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("null");
//      FileSystemHandling.createFolder(evoSuiteFile0);
//      HttpServletResponse httpServletResponse0 = mock(HttpServletResponse.class, new ViolatedAssumptionAnswer());
//      doReturn(printWriter0).when(httpServletResponse0).getWriter();
//      HttpServletResponseWrapper httpServletResponseWrapper0 = new HttpServletResponseWrapper(httpServletResponse0);
//      fileUploadController2_0.processRequest(httpServletRequestWrapper0, httpServletResponseWrapper0);
//      // Undeclared exception!
//      try {
//        fileUploadController2_0.processRequest(httpServletRequestWrapper0, httpServletResponseWrapper0);
//        fail("Expecting exception: NullPointerException");
//
//      } catch(NullPointerException e) {
//         //
//         // no message in exception (getMessage() returned null)
//         //
//         verifyException("com.cheops.candidatemanager.controllers.FileUploadController2", e);
//      }
//  }
//
//  @Test(timeout = 4000)
//  public void test4()  throws Throwable  {
//      FileUploadController2 fileUploadController2_0 = new FileUploadController2();
//      InputStream inputStream0 = mock(InputStream.class, new ViolatedAssumptionAnswer());
//      doReturn(123, 0).when(inputStream0).read(any(byte[].class));
//      Part part0 = mock(Part.class, new ViolatedAssumptionAnswer());
//      doReturn("ZUO)gX!h69GHc?").when(part0).getHeader(anyString());
//      Part part1 = mock(Part.class, new ViolatedAssumptionAnswer());
//      doReturn("g*%BZm~I9\"QuP;7$0?").when(part1).getHeader(anyString());
//      doReturn(inputStream0).when(part1).getInputStream();
//      HttpServletRequest httpServletRequest0 = mock(HttpServletRequest.class, new ViolatedAssumptionAnswer());
//      doReturn("", "Sk82MgU").when(httpServletRequest0).getParameter(anyString());
//      doReturn(part0, part1).when(httpServletRequest0).getPart(anyString());
//      HttpServletRequestWrapper httpServletRequestWrapper0 = new HttpServletRequestWrapper(httpServletRequest0);
//      PrintWriter printWriter0 = mock(PrintWriter.class, new ViolatedAssumptionAnswer());
//      PrintWriter printWriter1 = mock(PrintWriter.class, new ViolatedAssumptionAnswer());
//      EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("null");
//      FileSystemHandling.createFolder(evoSuiteFile0);
//      HttpServletResponse httpServletResponse0 = mock(HttpServletResponse.class, new ViolatedAssumptionAnswer());
//      doReturn(printWriter0, printWriter1).when(httpServletResponse0).getWriter();
//      HttpServletResponseWrapper httpServletResponseWrapper0 = new HttpServletResponseWrapper(httpServletResponse0);
//      fileUploadController2_0.processRequest(httpServletRequestWrapper0, httpServletResponseWrapper0);
//      try {
//        fileUploadController2_0.processRequest(httpServletRequestWrapper0, httpServletResponseWrapper0);
//        fail("Expecting exception: IOException");
//
//      } catch(IOException e) {
//         //
//         // Error in writing to file
//         //
//         verifyException("org.evosuite.runtime.mock.java.io.NativeMockedIO", e);
//      }
//  }
//
//  @Test(timeout = 4000)
//  public void test5()  throws Throwable  {
//      FileUploadController2 fileUploadController2_0 = new FileUploadController2();
//      InputStream inputStream0 = mock(InputStream.class, new ViolatedAssumptionAnswer());
//      doReturn(421, 421, (-1)).when(inputStream0).read(any(byte[].class));
//      Part part0 = mock(Part.class, new ViolatedAssumptionAnswer());
//      doReturn("").when(part0).getHeader(anyString());
//      doReturn(inputStream0).when(part0).getInputStream();
//      HttpServletRequest httpServletRequest0 = mock(HttpServletRequest.class, new ViolatedAssumptionAnswer());
//      doReturn("").when(httpServletRequest0).getParameter(anyString());
//      doReturn(part0).when(httpServletRequest0).getPart(anyString());
//      HttpServletRequestWrapper httpServletRequestWrapper0 = new HttpServletRequestWrapper(httpServletRequest0);
//      PrintWriter printWriter0 = mock(PrintWriter.class, new ViolatedAssumptionAnswer());
//      HttpServletResponse httpServletResponse0 = mock(HttpServletResponse.class, new ViolatedAssumptionAnswer());
//      doReturn(printWriter0).when(httpServletResponse0).getWriter();
//      HttpServletResponseWrapper httpServletResponseWrapper0 = new HttpServletResponseWrapper(httpServletResponse0);
//      fileUploadController2_0.processRequest(httpServletRequestWrapper0, httpServletResponseWrapper0);
//  }
//}
