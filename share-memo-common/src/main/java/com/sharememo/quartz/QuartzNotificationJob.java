package com.sharememo.quartz;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.sharememo.constant.ShareMemoConstant;
import com.sharememo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

@Slf4j
@DisallowConcurrentExecution
public class QuartzNotificationJob extends QuartzJobBean {
  @Autowired private LineMessagingClient lineMessagingClient;
  @Autowired private MemberService memberService;

  @Override
  protected void executeInternal(JobExecutionContext jobExecutionContext)
      throws JobExecutionException {
    // TODO Execute real job.
    log.info("Execute quartz job");
    String text =
        (String)
            jobExecutionContext
                .getMergedJobDataMap()
                .get(ShareMemoConstant.JOB_DATA_MAP_KEY_CONTENT);
    List<Integer> memberIds =
        (List<Integer>)
            jobExecutionContext.getMergedJobDataMap().get(ShareMemoConstant.JOB_DATA_MAP_KEY_IDS);
    String lineId = memberService.getById(memberIds.get(0)).getLineId();

    //TODO update "isSend" attribute

    lineMessagingClient.pushMessage(new PushMessage(lineId, new TextMessage(text), true));
  }
}
