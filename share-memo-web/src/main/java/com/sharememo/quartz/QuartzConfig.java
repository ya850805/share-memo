package com.sharememo.quartz;

import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class QuartzConfig {
  @Autowired private JobFactory jobFactory;

  @Bean
  public Properties quartzProperties() throws IOException {
    PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
    propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
    propertiesFactoryBean.afterPropertiesSet();
    return propertiesFactoryBean.getObject();
  }

  @Bean
  public SchedulerFactoryBeanWithWait schedulerFactoryBeanWithWait() throws IOException {
    SchedulerFactoryBeanWithWait factory = new SchedulerFactoryBeanWithWait();
    factory.setJobFactory(jobFactory);
    factory.setWaitForJobsToCompleteOnShutdown(true);
    factory.setQuartzProperties(quartzProperties());
    return factory;
  }

  @Bean
  public QuartzInitializerListener executorListener() {
    return new QuartzInitializerListener();
  }

  @Bean
  public Scheduler scheduler() throws IOException {
    return schedulerFactoryBeanWithWait().getScheduler();
  }
}
