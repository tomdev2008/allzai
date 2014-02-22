package com.allzai.isp;

import java.util.ArrayList;
import java.util.List;

public class AclTree {

	public AclTree() {
		root = null;
		codelist = new ArrayList<String>();
	}

	public String getStrIpBy32Int(Long k) {
		String ip = "";
		Long p1 = Long.valueOf((k.longValue() & -16777216L) >> 24);
		Long p2 = Long.valueOf((k.longValue() & 16711680L) >> 16);
		Long p3 = Long.valueOf((k.longValue() & 65280L) >> 8);
		Long p4 = Long.valueOf(k.longValue() & 255L);
		ip = (new StringBuilder(String.valueOf(p1.toString())))
			.append(".").append(p2.toString())
			.append(".").append(p3.toString())
			.append(".").append(p4.toString()).toString();
		return ip;
	}

	public Long get32IntIpByIp(String ip) {
		Long ipInt = new Long(0L);
		String ps[] = ip.split("\\.");
		if (4 != ps.length) {
			return ipInt;
		} else {
			Long p1 = new Long(ps[0]);
			Long p2 = new Long(ps[1]);
			Long p3 = new Long(ps[2]);
			Long p4 = new Long(ps[3]);
			ipInt = Long.valueOf(p1.longValue() 
					* 256L * 256L * 256L + p2.longValue() 
					* 256L * 256L + p3.longValue() 
					* 256L + p4.longValue());
			return ipInt;
		}
	}

	private int getIndex(String obj) {
		int index = codelist.indexOf(obj);
		if (index < 0) {
			index = codelist.size();
			codelist.add(obj);
		}
		if ("HuaShu" == obj)
			System.out.println(index);
		return index;
	}

	private Node insertNewNode(byte k, short v, Node n) {
		if (k == 0) {
			if (n.left == null)
				n.left = new Node();
			if (n.codeIndex != null) {
				n.right = new Node();
				n.right.codeIndex = n.codeIndex;
				n.left.codeIndex = n.codeIndex;
				n.codeIndex = null;
			}
			return n.left;
		}
		if (1 == k) {
			if (n.right == null)
				n.right = new Node();
			if (n.codeIndex != null) {
				n.left = new Node();
				n.left.codeIndex = n.codeIndex;
				n.right.codeIndex = n.codeIndex;
				n.codeIndex = null;
			}
			return n.right;
		} else {
			return null;
		}
	}

	private void merge(Node node) {
		if (node == null)
			return;
		if (node.codeIndex != null)
			return;
		if (node.left != null)
			merge(node.left);
		if (node.right != null)
			merge(node.right);
		if (node.left != null 
				&& node.right != null 
				&& node.left.codeIndex != null 
				&& node.right.codeIndex != null) {
			int l = node.left.codeIndex.intValue();
			int r = node.right.codeIndex.intValue();
			if (l == r 
					&& node.left.codeIndex != null) {
				node.codeIndex = node.left.codeIndex;
				node.left = null;
				node.right = null;
			}
		}
	}

	public String searchISP(String ip) {
		String ret = "unknown";
		Long ipint = get32IntIpByIp(ip);
		ret = selfSearchIpInfo(root, ipint.longValue(), 31);
		return ret;
	}

	private String selfSearchIpInfo(Node node, long ipint, int level) {
		String ret = "unknown";
		if (level < 0)
			return ret;
		if (node != null) {
			if (node.codeIndex != null)
				return (String) codelist.get(node.codeIndex.shortValue());
		} else {
			return ret;
		}
		byte k = (byte) (int) (ipint >> level & (long) MASK.intValue());
		int next_level = level - 1;
		if (k == 0)
			return selfSearchIpInfo(node.left, ipint, next_level);
		else
			return selfSearchIpInfo(node.right, ipint, next_level);
	}

	public void insert(String ipInt, String areaCode, Integer mask) {
		if (root == null)
			root = new Node();
		Node node = root;
		short index = (short) getIndex(areaCode);
		long x = (new Long(ipInt)).longValue();
		x >>= 32 - mask.intValue();
		for (int i = mask.intValue() - 1; i >= 0; i--) {
			byte k = (byte) (int) (x >> i & (long) MASK.intValue());
			if (node.codeIndex != null 
					&& index == node.codeIndex.shortValue())
				break;
			node = insertNewNode(k, index, node);
		}
		node.codeIndex = Short.valueOf(index);
	}

	public void doMerge() {
		merge(root);
	}

	private static final Integer MASK = Integer.valueOf(1);
	private Node root = null;
	private List<String> codelist = null;

	class Node {
		Node() {
			codeIndex = null;
			right = null;
			left = null;
		}
		Short codeIndex;
		Node right;
		Node left;
	}

}
