package com.chat.client;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * mina测试客户端
 */
public class MinaClient {

	public static void main(String[] args) {

		// Create TCP/IP connector.
		IoConnector connector = new NioSocketConnector();
		// 创建接收数据的过滤器
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		// 设定这个过滤器将以对象为单位读取数
		ProtocolCodecFilter filter = new ProtocolCodecFilter(new ObjectSerializationCodecFactory());
		chain.addLast("objectFilter", filter);
		// 设定客户端的消息处理器:一个ObjectMinaClientHandler对象,
		connector.setHandler(new ObjectMinaClientHandler());

		// 连结到服务器:
		ConnectFuture cf = connector.connect(new InetSocketAddress("localhost", 9988));
		// 等待连接创建完成
		cf.awaitUninterruptibly();
		// 等待连接断开
		cf.getSession().getCloseFuture().awaitUninterruptibly();

		// 客户端断开链接，释放资源
		connector.dispose();
	}

}
