package com.langmy.terminal.modules.sys.service;

public class DoAutoPushServiceImpl {  
    //这个是总管理类  
//    private Scheduler scheduler;  
    public void init(){  
          
/*        try {  
        	TriggerKey triggerKey = TriggerKey.triggerKey("cronTrigger",
        			Scheduler.DEFAULT_GROUP);
        	//CronTriggerFactoryBean conTrigger = (CronTriggerFactoryBean) scheduler.getTrigger(triggerKey);  
           //String pushTime =  formatTime(time); 
        	CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
        	
        	
        	String pushTime =  "0 20 16 * * ? ";
        	System.out.println(pushTime);
            
        } catch (SchedulerException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  */
    }  
  
    public String formatTime(String time){
    	String[] strTime = time.split(":");
		String minutes = strTime[1].substring(0, 2);
		String amPm = strTime[1].substring(3, 5);
		if(amPm.endsWith("PM")){
			Integer t = Integer.parseInt(strTime[0])+12;
			strTime[0] = t.toString();
			
		}
		String pushTime =  "0 "+minutes+" "+strTime[0]+" * * ? ";  
		return pushTime;
    }
  
}  