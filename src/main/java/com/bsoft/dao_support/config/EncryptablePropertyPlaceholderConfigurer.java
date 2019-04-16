package com.bsoft.dao_support.config;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.bsoft.common.util.DESUtil;

public class EncryptablePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {  
  
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)  
        throws BeansException {  
            try {  
                DESUtil desUtil=new DESUtil();
                String username = props.getProperty("connection.username");  
                if (username != null) {  
                    System.out.println(username);
                	props.setProperty("connection.username", desUtil.decrypt(username));  
                }  
                  
                String password = props.getProperty("connection.password");  
                if (password != null) {  
                    props.setProperty("connection.password", desUtil.decrypt(password));  
                }  
                
                /*
                String url = props.getProperty("connection.url");  
                if (url != null) {  
                    props.setProperty("connection.url", desUtil.decrypt(url));  
                }  
                
                String driverClassName = props.getProperty("connection.driver_class");  
                if(driverClassName != null){  
                    props.setProperty("connection.driver_class", desUtil.decrypt(driverClassName));  
                }  
                */
                  
                super.processProperties(beanFactory, props);  
            } catch (Exception e) {  
                e.printStackTrace();  
                throw new BeanInitializationException(e.getMessage());  
            }  
        } 
}