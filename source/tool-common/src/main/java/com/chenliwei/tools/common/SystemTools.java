package com.chenliwei.tools.common;

import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class SystemTools {

	/**
	 * 
	 * @return 获取当前环境的cpu数
	 */
	public int cpuAmount() {
		return Runtime.getRuntime().availableProcessors();
	}

	/**
	 * Registers a new virtual-machine shutdown hook 注册一个在jvm关闭时触发运行的钩子
	 */
	public void hookJvmShutdown(Runnable hook) {
		Runtime.getRuntime().addShutdownHook(new Thread(hook));
	}

	/**
	 * 
	 * @return 操作系统名称
	 */
	public String operatingSystem() {
		return System.getProperty("os.name");
	}

	/**
	 * 
	 * @return 机器名
	 */
	public static String hostname() {
		String hostname = null;
		try {
			hostname = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			String host = e.getMessage();
			if (host != null) {
				int colon = host.indexOf(':');
				if (colon > 0) {
					hostname = host.substring(0, colon);
				}
			}
		}

		if (hostname == null)
			hostname = "UnknownHost";

		return hostname;
	}
	

	/**
	 * 获取本机ip地址
	 * @return
	 */
	public static List<String> ips() {
//		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		List<String> netips = new ArrayList<String>();// 外网IP
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
//			boolean finded = false;// 是否找到外网IP
			while (netInterfaces.hasMoreElements() /**&& !finded**/) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> address = ni.getInetAddresses();
				while (address.hasMoreElements()) {
					ip = address.nextElement();
					if (ip instanceof Inet4Address){
						System.out.println(ip.getHostName() +":" +ip.getHostAddress());
						netips.add(ip.getHostAddress());
					}
					
//					if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
//						netips.add(ip.getHostAddress());
////						finded = true;
////						break;
//					} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
//						localip = ip.getHostAddress();
//					}
				}
			}

			return netips;
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 
	 * @return 当前进程id
	 */
	public static String processId() {
		String name = ManagementFactory.getRuntimeMXBean().getName();
		String pid = name.split("@")[0];
		return pid;
	}

	public static void main(String[] args) {
//		System.out.println(JsonTools.toJsonString(netIps()));
	}
}
