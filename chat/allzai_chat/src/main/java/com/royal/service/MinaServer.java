package com.royal.service;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * mina服务端
 * @author Royal
 *
 */
public class MinaServer {

	public static void main(String[] args) throws IOException {
		// 创建一个非阻塞的Server端 Socket,用NIO
		IoAcceptor acceptor = new NioSocketAcceptor();
		// 创建接收数据的过滤器
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		// 设定这个过滤器将以对象为单位读取数
		ProtocolCodecFilter filter = new ProtocolCodecFilter(new ObjectSerializationCodecFactory());
		chain.addLast("objectFilter", filter);

		// 设定服务器端的消息处理器:一个ObjectMinaServerHandler对象,
		acceptor.setHandler(new ObjectMinaServerHandler());

		// 服务器端绑定的端口
		int bindPort = 9988;
		// 绑定端口,启动服务器
		acceptor.bind(new InetSocketAddress(bindPort));
		System.out.println("Mina Server is Listing on:= " + bindPort);
	}

}
