package javatest.i18n;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javatest.i18n.controller.I18NController;
import javatest.i18n.dto.StatusDTO;
import javatest.i18n.util.I18NEnabled;
import javatest.i18n.util.TranslateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=UnitTestConfig.class)
public class DTOTest {

//	@Autowired
//	private TranslateUtil transUtil;
//	
//	@Autowired
//	private MessageSource caasMessageSource;
	
	@Autowired
	I18NController i18nController;
	
//	@Test
//	public void dummyTest() {
//		Object dto = new StatusDTO();
//		System.out.println(dto.getClass());	
//		assertThat(dto.getClass().isAnnotationPresent(I18NEnabled.class), equalTo(true));
//	}
//	
//	@Test
//	public void transUtilTest() {
//		assertThat(this.transUtil, notNullValue());
//
//	}
//	
//	@Test
//	public void resourceBundleTest() {
//		assertThat(this.caasMessageSource, notNullValue());	
//		
//		Locale.setDefault(Locale.CHINESE);
//		StatusDTO dto = new StatusDTO();
//		transUtil.translate(dto);
//		assertThat(dto.getStatusText(), equalTo("未激活的"));
//		
//		Locale.setDefault(Locale.ENGLISH);
//		transUtil.translate(dto);
//		assertThat(dto.getStatusText(), equalTo("Inactive"));
//	}
	
	@Test
	public void withMatchedLocale() {
		System.out.println("With matched locale, translate to CHINESE: ");
		Locale.setDefault(Locale.CHINESE);
		StatusDTO dto = i18nController.getStatusDTO();
		
		System.out.println("After translation ---");
		System.out.println("Status Key: " + dto.getStatus());
		System.out.println("Status Text: " + dto.getStatusText());
		
		assertThat(dto.getStatusText(), equalTo("未激活的"));
		System.out.println();
	}
	
	@Test
	public void noMatchedLanguage() {
		System.out.println("With unmatched locale, use default properties:");
		Locale.setDefault(Locale.GERMAN);
		StatusDTO dto = i18nController.getStatusDTO();
		
		System.out.println("After translation ---");
		System.out.println("Status Key: " + dto.getStatus());
		System.out.println("Status Text: " + dto.getStatusText());
		
		assertThat(dto.getStatusText(), equalTo("Inactive"));
		System.out.println();
	}
}