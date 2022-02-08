package com.sharememo.quartz;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

@DisallowConcurrentExecution
public class QuartzNotificationJob extends QuartzJobBean {
  @Autowired
  private LineMessagingClient lineMessagingClient;

  @Override
  protected void executeInternal(JobExecutionContext jobExecutionContext)
      throws JobExecutionException {
    //TODO Execute real job.
    System.out.println("test!!!!");
    String text = (String) jobExecutionContext.getMergedJobDataMap().get("content");

    lineMessagingClient.pushMessage(
            new PushMessage(
                    "Uf6d1c75291a25f4f8c0599cb5b33fd4b", new TextMessage(text), true));
  }
}
