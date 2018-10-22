/*
 * This file was automatically generated by EvoSuite
 * Tue Oct 16 13:49:27 GMT 2018
 */

package com.tv.tutorials.coolestuffvoorcheops.controllers;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.tv.tutorials.coolestuffvoorcheops.controllers.FileUploadController2;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.Part;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class FileUploadController2_ESTest extends FileUploadController2_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      FileUploadController2 fileUploadController2_0 = new FileUploadController2();
      HttpServletRequest httpServletRequest0 = mock(HttpServletRequest.class, new ViolatedAssumptionAnswer());
      HttpServletRequestWrapper httpServletRequestWrapper0 = new HttpServletRequestWrapper(httpServletRequest0);
      HttpServletResponse httpServletResponse0 = mock(HttpServletResponse.class, new ViolatedAssumptionAnswer());
      HttpServletResponseWrapper httpServletResponseWrapper0 = new HttpServletResponseWrapper(httpServletResponse0);
      httpServletResponseWrapper0.setResponse(httpServletResponseWrapper0);
      // Undeclared exception!
      try { 
        fileUploadController2_0.processRequest(httpServletRequestWrapper0, httpServletResponseWrapper0);
        fail("Expecting exception: StackOverflowError");
      
      } catch(StackOverflowError e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      FileUploadController2 fileUploadController2_0 = new FileUploadController2();
      InputStream inputStream0 = mock(InputStream.class, new ViolatedAssumptionAnswer());
      doReturn(3, 3, 3, (-1)).when(inputStream0).read(any(byte[].class));
      Part part0 = mock(Part.class, new ViolatedAssumptionAnswer());
      doReturn("&pgradeHandler.unexpeXtedAck").when(part0).getHeader(anyString());
      doReturn(inputStream0).when(part0).getInputStream();
      HttpServletRequest httpServletRequest0 = mock(HttpServletRequest.class, new ViolatedAssumptionAnswer());
      doReturn("&pgradeHandler.unexpeXtedAck", (String) null).when(httpServletRequest0).getParameter(anyString());
      doReturn(part0, (Part) null).when(httpServletRequest0).getPart(anyString());
      HttpServletRequestWrapper httpServletRequestWrapper0 = new HttpServletRequestWrapper(httpServletRequest0);
      PrintWriter printWriter0 = mock(PrintWriter.class, new ViolatedAssumptionAnswer());
      HttpServletResponse httpServletResponse0 = mock(HttpServletResponse.class, new ViolatedAssumptionAnswer());
      doReturn(printWriter0).when(httpServletResponse0).getWriter();
      HttpServletResponseWrapper httpServletResponseWrapper0 = new HttpServletResponseWrapper(httpServletResponse0);
      fileUploadController2_0.processRequest(httpServletRequestWrapper0, httpServletResponseWrapper0);
      // Undeclared exception!
      try { 
        fileUploadController2_0.processRequest(httpServletRequestWrapper0, httpServletResponseWrapper0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.tv.tutorials.coolestuffvoorcheops.controllers.FileUploadController2", e);
      }
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      FileUploadController2 fileUploadController2_0 = new FileUploadController2();
      InputStream inputStream0 = mock(InputStream.class, new ViolatedAssumptionAnswer());
      doReturn((-145)).when(inputStream0).read(any(byte[].class));
      Part part0 = mock(Part.class, new ViolatedAssumptionAnswer());
      doReturn(",FL.bl'.2SGYFnO#F`").when(part0).getHeader(anyString());
      doReturn(inputStream0).when(part0).getInputStream();
      HttpServletRequest httpServletRequest0 = mock(HttpServletRequest.class, new ViolatedAssumptionAnswer());
      doReturn("New file ").when(httpServletRequest0).getParameter(anyString());
      doReturn(part0).when(httpServletRequest0).getPart(anyString());
      HttpServletRequestWrapper httpServletRequestWrapper0 = new HttpServletRequestWrapper(httpServletRequest0);
      HttpServletResponse httpServletResponse0 = mock(HttpServletResponse.class, new ViolatedAssumptionAnswer());
      doReturn((PrintWriter) null).when(httpServletResponse0).getWriter();
      HttpServletResponseWrapper httpServletResponseWrapper0 = new HttpServletResponseWrapper(httpServletResponse0);
      try { 
        fileUploadController2_0.processRequest(httpServletRequestWrapper0, httpServletResponseWrapper0);
        fail("Expecting exception: IOException");
      
      } catch(IOException e) {
         //
         // Error in writing to file
         //
         verifyException("org.evosuite.runtime.mock.java.io.NativeMockedIO", e);
      }
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      FileUploadController2 fileUploadController2_0 = new FileUploadController2();
      ServletInputStream servletInputStream0 = mock(ServletInputStream.class, new ViolatedAssumptionAnswer());
      doReturn(485, Integer.MAX_VALUE, 485, 485, Integer.MAX_VALUE).when(servletInputStream0).read(any(byte[].class));
      HttpServletRequest httpServletRequest0 = mock(HttpServletRequest.class, new ViolatedAssumptionAnswer());
      doReturn(servletInputStream0).when(httpServletRequest0).getInputStream();
      HttpServletRequestWrapper httpServletRequestWrapper0 = new HttpServletRequestWrapper(httpServletRequest0);
      ServletInputStream servletInputStream1 = httpServletRequestWrapper0.getInputStream();
      Part part0 = mock(Part.class, new ViolatedAssumptionAnswer());
      doReturn("flnameXc4N%=").when(part0).getHeader(anyString());
      doReturn(servletInputStream1).when(part0).getInputStream();
      HttpServletRequest httpServletRequest1 = mock(HttpServletRequest.class, new ViolatedAssumptionAnswer());
      doReturn("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n").when(httpServletRequest1).getParameter(anyString());
      doReturn(part0).when(httpServletRequest1).getPart(anyString());
      HttpServletRequestWrapper httpServletRequestWrapper1 = new HttpServletRequestWrapper(httpServletRequest1);
      PrintWriter printWriter0 = mock(PrintWriter.class, new ViolatedAssumptionAnswer());
      HttpServletResponse httpServletResponse0 = mock(HttpServletResponse.class, new ViolatedAssumptionAnswer());
      doReturn(printWriter0).when(httpServletResponse0).getWriter();
      // Undeclared exception!
      fileUploadController2_0.processRequest(httpServletRequestWrapper1, httpServletResponse0);
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      FileUploadController2 fileUploadController2_0 = new FileUploadController2();
      Part part0 = mock(Part.class, new ViolatedAssumptionAnswer());
      doReturn("filenameXc4N%=").when(part0).getHeader(anyString());
      HttpServletRequest httpServletRequest0 = mock(HttpServletRequest.class, new ViolatedAssumptionAnswer());
      doReturn("").when(httpServletRequest0).getParameter(anyString());
      doReturn(part0).when(httpServletRequest0).getPart(anyString());
      HttpServletResponse httpServletResponse0 = mock(HttpServletResponse.class, new ViolatedAssumptionAnswer());
      doReturn((PrintWriter) null).when(httpServletResponse0).getWriter();
      HttpServletResponseWrapper httpServletResponseWrapper0 = new HttpServletResponseWrapper(httpServletResponse0);
      // Undeclared exception!
      try { 
        fileUploadController2_0.processRequest(httpServletRequest0, httpServletResponseWrapper0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      FileUploadController2 fileUploadController2_0 = new FileUploadController2();
      Part part0 = mock(Part.class, new ViolatedAssumptionAnswer());
      doReturn("filenameXc4N%=").when(part0).getHeader(anyString());
      HttpServletRequest httpServletRequest0 = mock(HttpServletRequest.class, new ViolatedAssumptionAnswer());
      doReturn("").when(httpServletRequest0).getParameter(anyString());
      doReturn(part0).when(httpServletRequest0).getPart(anyString());
      HttpServletRequestWrapper httpServletRequestWrapper0 = new HttpServletRequestWrapper(httpServletRequest0);
      PrintWriter printWriter0 = mock(PrintWriter.class, new ViolatedAssumptionAnswer());
      HttpServletResponse httpServletResponse0 = mock(HttpServletResponse.class, new ViolatedAssumptionAnswer());
      doReturn(printWriter0).when(httpServletResponse0).getWriter();
      HttpServletResponseWrapper httpServletResponseWrapper0 = new HttpServletResponseWrapper(httpServletResponse0);
      fileUploadController2_0.processRequest(httpServletRequestWrapper0, httpServletResponseWrapper0);
  }
}
