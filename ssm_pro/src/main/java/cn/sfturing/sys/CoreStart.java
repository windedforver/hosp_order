package cn.sfturing.sys;


import java.io.File;
import java.util.Date;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CoreStart {
	private static ConfigurableApplicationContext ctx;
	private static volatile boolean running = true;
	
	public static void main(String[] args) {
		
		
		// 初始化Spring容器
		String[] configLocations = new String[] { "spring/spring-dao.xml", "spring/spring-service.xml","spring/spring-web.xml","mybatis-config.xml" };
		ctx = new ClassPathXmlApplicationContext(configLocations);
		ctx.registerShutdownHook();
		ctx.start();

		System.out.println("[" + new Date().toString() + "] PartnerCenterService Core system start ok !~");
		
		synchronized (CoreStart.class) { 
            while (running) { 
                try { 
                	CoreStart.class.wait(); 
                } catch (Throwable e) { 
                } 
            } 
        }

//		try {
//			ServerSocket ss = new ServerSocket(51818);
//			while (true) {
//				Socket socket = ss.accept();
//				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//				String line = in.readLine();
//				if ("stop".trim().equalsIgnoreCase(line)) {
//					if (ctx != null) {
//						ctx.stop();
//						ctx.close();
//						System.out.println("[" + new Date().toString() + "] exit!~");
//						System.exit(0);
//					}
//				} else {
//					System.out.println("错误的命令参数，需要关闭参数stop");
//				}
//				in.close();
//				socket.close();
//				ss.close();
//			}
//		} catch (Exception e) {
//		}
	}
}
