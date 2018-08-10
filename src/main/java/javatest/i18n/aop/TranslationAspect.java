package javatest.i18n.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javatest.i18n.util.TranslateUtil;

@Aspect
@Component
public class TranslationAspect {
	
	@Autowired
	TranslateUtil transUtil;
	 
	 @AfterReturning(pointcut= "execution(* javatest.i18n.controller.*.*(..))", returning= "retValue")
	 public void afterReturningAdviceForAllMethods(JoinPoint jp, Object retValue) throws Throwable {
		 try {
			 transUtil.translate(retValue);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	 }
}