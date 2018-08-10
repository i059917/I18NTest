package javatest.i18n.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class TranslateUtil {
	
	@Autowired
	private MessageSource caasMessageSource;
	
	public void translate(Object translatableObject) {
		if(translatableObject == null) {
			return;
		}
		
		if(translatableObject.getClass().isAnnotationPresent(I18NEnabled.class) == false) {
			return;
		}
		
		Field[] fields = translatableObject.getClass().getDeclaredFields();
		for(Field propertyField : fields) {
			if(propertyField.isAnnotationPresent(Translation.class)) {
				Translation annotation = propertyField.getAnnotation(Translation.class);
				String textKeyName = annotation.textKeyField();

				String text = getTranslatedTextByKeyField(translatableObject, textKeyName);
				
				String textPropertyName = propertyField.getName();
				String textSetterName = "set" + textPropertyName.substring(0, 1).toUpperCase() + 
						textPropertyName.substring(1, textPropertyName.length());
				try {
					Method textSetterMethod = translatableObject.getClass().getDeclaredMethod(textSetterName, String.class);
					textSetterMethod.invoke(translatableObject, text);
				} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private String getTranslatedTextByKeyField(Object translatableObject, String textKeyName) {
		try {
			Field textKeyField = translatableObject.getClass().getDeclaredField(textKeyName);
			if(!textKeyField.getType().equals(String.class)) {
				return null;
			}
			String getterName = "get" + textKeyName.substring(0, 1).toUpperCase() + textKeyName.substring(1, textKeyName.length());
			Method getterMethod = translatableObject.getClass().getDeclaredMethod(getterName);
			
			if(!getterMethod.getReturnType().equals(String.class)) {
				return null;
			}
			String textKey = (String)getterMethod.invoke(translatableObject);			
			String text = caasMessageSource.getMessage(textKey, null, null, Locale.getDefault());
			
			return text;
		} catch (NoSuchFieldException | IllegalAccessException 
				| IllegalArgumentException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}
}