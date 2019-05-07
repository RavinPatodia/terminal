
package com.langmy.terminal.modules.cometd;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.cometd.annotation.Service;
import org.cometd.annotation.Session;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ConfigurableServerChannel;
import org.cometd.bayeux.server.ServerChannel;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.server.authorizer.GrantAuthorizer;

import com.alibaba.fastjson.JSON;
import com.langmy.terminal.modules.monitor.model.EnterModel;
import com.langmy.terminal.modules.monitor.model.ExitModel;

/**
 * comted推送
 * 
 * @author lxj
 *
 */
@javax.inject.Named // Tells Spring that this is a bean
@Service("echoService")
public class EchoService
{
    @Inject
    private BayeuxServer bayeuxServer;
    @Session
    private ServerSession serverSession;

 /*   @PostConstruct
    public void init()
    {
    }*/
    
    // Channel Configuration Support
   /* @Configure("/echo")
    public void configure(ConfigurableServerChannel channel)
    {
        channel.setLazy(true);
        //channel.addAuthorizer(GrantAuthorizer.GRANT_PUBLISH);
    }*/
    
    /**
     * 推送入场信息
     * 
     * @param model
     */
    public void enterProcess(EnterModel model){
    	//取得卡口对应的终端机ip,得到该终端机专用的通道名称
    	String channelName = "/enterChannel"; 

        bayeuxServer.createChannelIfAbsent(channelName, new ConfigurableServerChannel.Initializer()
        {
            public void configureChannel(ConfigurableServerChannel channel)
            {
                channel.setPersistent(true);
                channel.addAuthorizer(GrantAuthorizer.GRANT_SUBSCRIBE_PUBLISH);
                //channel.setLazy(true);
            }
        });
        
        Map<String, Object> data = new HashMap<String, Object>(4);
        data.put("jsonObj", JSON.toJSON(model));
        
        ServerChannel channel = bayeuxServer.getChannel(channelName);
        channel.publish(serverSession, data);
    }
    
    /**
     * 推送出场信息
     * 
     * @param model
     */
    public void exitProcess(ExitModel model){

    	//取得卡口对应的终端机ip,得到该终端机专用的通道名称
    	String channelName = "/exitChannel";     	
    	
        bayeuxServer.createChannelIfAbsent(channelName, new ConfigurableServerChannel.Initializer()
        {
            public void configureChannel(ConfigurableServerChannel channel)
            {
                channel.setPersistent(true);
                channel.addAuthorizer(GrantAuthorizer.GRANT_SUBSCRIBE_PUBLISH);
                //channel.setLazy(true);
            }
        });
        Object jsonObj = JSON.toJSON(model);
       // String jsonStr = JSON.toJSONString(model);
        
        // Convert the Update business object to a CometD-friendly format
        Map<String, Object> data = new HashMap<String, Object>(4);
        data.put("jsonObj", jsonObj);
        
        // Publish to all subscribers
        ServerChannel channel = bayeuxServer.getChannel(channelName);
        channel.publish(serverSession, data);
    }
    
    /*@Listener("/echo")
    public void echo(ServerSession remote, ServerMessage.Mutable message)
    {
    	String channel = message.getChannel();
        Object data = message.getData();
        remote.deliver(serverSession, channel, data, null);
    	
        Map<String, Object> input = message.getDataAsMap();
        String name = (String)input.get("name");

        Map<String, Object> output = new HashMap<String, Object>();
        output.put("greeting", "Hello, " + name);
        remote.deliver(serverSession, "/hello", output, null);
    }*/
}
