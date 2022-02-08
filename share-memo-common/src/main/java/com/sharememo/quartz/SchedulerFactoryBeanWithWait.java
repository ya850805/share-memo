package com.sharememo.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Slf4j
public class SchedulerFactoryBeanWithWait extends SchedulerFactoryBean {
  @Override
  public void destroy() throws SchedulerException {
    super.destroy();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      log.error(e.getMessage());
    }
  }
}
