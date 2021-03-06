package com.cn.nj.putian.newodnclient.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * crc校验
 * @author zhaol
 *
 */
public final class CrcUtils {

	public static  int crc16_ccitt_table[] ={
			0x0000, 0x1189, 0x2312, 0x329b, 0x4624, 0x57ad, 0x6536, 0x74bf,
			0x8c48, 0x9dc1, 0xaf5a, 0xbed3, 0xca6c, 0xdbe5, 0xe97e, 0xf8f7,
			0x1081, 0x0108, 0x3393, 0x221a, 0x56a5, 0x472c, 0x75b7, 0x643e,
			0x9cc9, 0x8d40, 0xbfdb, 0xae52, 0xdaed, 0xcb64, 0xf9ff, 0xe876,
			0x2102, 0x308b, 0x0210, 0x1399, 0x6726, 0x76af, 0x4434, 0x55bd,
			0xad4a, 0xbcc3, 0x8e58, 0x9fd1, 0xeb6e, 0xfae7, 0xc87c, 0xd9f5,
			0x3183, 0x200a, 0x1291, 0x0318, 0x77a7, 0x662e, 0x54b5, 0x453c,
			0xbdcb, 0xac42, 0x9ed9, 0x8f50, 0xfbef, 0xea66, 0xd8fd, 0xc974,
			0x4204, 0x538d, 0x6116, 0x709f, 0x0420, 0x15a9, 0x2732, 0x36bb,
			0xce4c, 0xdfc5, 0xed5e, 0xfcd7, 0x8868, 0x99e1, 0xab7a, 0xbaf3,
			0x5285, 0x430c, 0x7197, 0x601e, 0x14a1, 0x0528, 0x37b3, 0x263a,
			0xdecd, 0xcf44, 0xfddf, 0xec56, 0x98e9, 0x8960, 0xbbfb, 0xaa72,
			0x6306, 0x728f, 0x4014, 0x519d, 0x2522, 0x34ab, 0x0630, 0x17b9,
			0xef4e, 0xfec7, 0xcc5c, 0xddd5, 0xa96a, 0xb8e3, 0x8a78, 0x9bf1,
			0x7387, 0x620e, 0x5095, 0x411c, 0x35a3, 0x242a, 0x16b1, 0x0738,
			0xffcf, 0xee46, 0xdcdd, 0xcd54, 0xb9eb, 0xa862, 0x9af9, 0x8b70,
			0x8408, 0x9581, 0xa71a, 0xb693, 0xc22c, 0xd3a5, 0xe13e, 0xf0b7,
			0x0840, 0x19c9, 0x2b52, 0x3adb, 0x4e64, 0x5fed, 0x6d76, 0x7cff,
			0x9489, 0x8500, 0xb79b, 0xa612, 0xd2ad, 0xc324, 0xf1bf, 0xe036,
			0x18c1, 0x0948, 0x3bd3, 0x2a5a, 0x5ee5, 0x4f6c, 0x7df7, 0x6c7e,
			0xa50a, 0xb483, 0x8618, 0x9791, 0xe32e, 0xf2a7, 0xc03c, 0xd1b5,
			0x2942, 0x38cb, 0x0a50, 0x1bd9, 0x6f66, 0x7eef, 0x4c74, 0x5dfd,
			0xb58b, 0xa402, 0x9699, 0x8710, 0xf3af, 0xe226, 0xd0bd, 0xc134,
			0x39c3, 0x284a, 0x1ad1, 0x0b58, 0x7fe7, 0x6e6e, 0x5cf5, 0x4d7c,
			0xc60c, 0xd785, 0xe51e, 0xf497, 0x8028, 0x91a1, 0xa33a, 0xb2b3,
			0x4a44, 0x5bcd, 0x6956, 0x78df, 0x0c60, 0x1de9, 0x2f72, 0x3efb,
			0xd68d, 0xc704, 0xf59f, 0xe416, 0x90a9, 0x8120, 0xb3bb, 0xa232,
			0x5ac5, 0x4b4c, 0x79d7, 0x685e, 0x1ce1, 0x0d68, 0x3ff3, 0x2e7a,
			0xe70e, 0xf687, 0xc41c, 0xd595, 0xa12a, 0xb0a3, 0x8238, 0x93b1,
			0x6b46, 0x7acf, 0x4854, 0x59dd, 0x2d62, 0x3ceb, 0x0e70, 0x1ff9,
			0xf78f, 0xe606, 0xd49d, 0xc514, 0xb1ab, 0xa022, 0x92b9, 0x8330,
			0x7bc7, 0x6a4e, 0x58d5, 0x495c, 0x3de3, 0x2c6a, 0x1ef1, 0x0f78
	};


	/**
	 * 计算crc16
	 * @param b 任何一个命令
	 * @param start 去掉头 0x7e
	 * @param end   去掉尾3个字节 [crc1 crc2 0x7e]
	 * @return  的到crc两个字节，填入原来的[crc1 crc2]里 (低字节,高字节)
	 */
	public byte[] crcMethod(byte[]b, int start,int end){
		byte[] data = new byte[end-start];
		int j = 0;
		for (int i = start; i < end; i++) {
			data[j] = b[i];
			j++;
		}
		int result = crc_calByByte(data);
		byte[] arr = new byte[2];
		arr[0] = (byte)(result & 0xff);//低字节
		arr[1]= (byte) ((result>>8 )& 0xff);//高字节
		return arr;
	}

	/**
	 *
	 * @param b 去掉头尾，真正要计算的数据
	 * @return
	 */
	public int crc_calByByte(byte[] b){
		int crc_reg = 0x0000;//数据反转 lsb first
		return  do_crc(crc_reg, b);
	}

	/**
	 * crc16 ccit欧版  查表法 多项式1021
	 * @param reg_init
	 * @param message
	 * @return
	 */
	private static int do_crc(int reg_init, byte[] message) {
		int crc_reg = reg_init;
		for (int i = 0; i < message.length; i++) {
			crc_reg = (crc_reg >> 8) ^ crc16_ccitt_table[(crc_reg ^ message[i]) & 0xff];
		}
		return crc_reg;
	}

	/**
	 * 多项式计算法----crc的另外一种算法
	 * @param b
	 * @param len
	 * @return
	 */
	public  static int crc16_calc(byte[] b, int len){
		int i, j;
		int crc_reg = 0x0000;
		int to_xor  = 0x0000;
		int  index   = 0x0;

		for (i = 0; i < len; i++) {
			index = (crc_reg ^ b[i]) & 0xff;
			to_xor = index;
			for (j = 0; j < 8; j++) {
				if ((to_xor & 0x0001) ==1){
					to_xor = (to_xor >> 1) ^ 0x8408;
				}else{
					to_xor >>= 1;
				}
			}
			crc_reg = (crc_reg >> 8) ^ to_xor;
		}
		return crc_reg;
	}


	/**
	 * 按照华为的规则转换数组(华为格式==>真实数据)
	 * @param b 接收的华为数据
	 * @return 真实数据
	 */
	public List<Byte> transformByRealData(byte[] b) {
		List<Byte> list = new ArrayList<Byte>();
		list.add(b[0]);
		//第0位不转换，和最后位不转换
		for(int i = 1; i < b.length -1; i++) {
			if(b[i] == 0x7d && b[i+1] == 0x5e) {
				//0x7d,0x5e ==> 0x7e
				list.add((byte)0x7e);
				i++;
			} else if(b[i] == 0x7d && b[i+1] == 0x5d) {
				//0x7d,0x5d ==> 0x7d
				list.add((byte)0x7d);
				i++;
			} else {
				list.add((byte)b[i]);
			}
		}
		list.add(b[b.length-1]);
		return list;
	}

	/**
	 * 转换为华为需要的格式(真实数据==>华为格式)
	 * @param b 真实数据
	 * @return 华为格式数据
	 */
	public byte[] transformByHuaWei(byte[] b) {
		List<Byte> list_result = new ArrayList<Byte>();
		list_result.add(b[0]);
		for (int i = 1; i < b.length -1; i++) {
			if (b[i] == 0x7e) {
				list_result.add((byte) 0x7d);
				list_result.add((byte) 0x5e);
			} else if (b[i] == 0x7d) {
				list_result.add((byte) 0x7d);
				list_result.add((byte) 0x5d);
			} else {
				list_result.add(b[i]);
			}
		}
		list_result.add(b[b.length-1]);

		byte [] arr = new byte[list_result.size()];
		for(int i = 0; i < list_result.size(); i++) {
			arr[i] = list_result.get(i);
		}
		return arr;
	}


	/**
	 * crc校验(crc8)
	 *
	 * @param ptr
	 *            需要校验的数组
	 * @param len
	 *            数组长度
	 * @return 结果
	 */
	public int crcCheck(byte[] ptr, int len) {
		int crc = 0;
		for (int j = 0; j < len; j++) {
			int i = getUnsignedByte((byte) 0x80);
			while (i != 0) {
				if (((crc & 0x80) != 0) && (((ptr[j]) & i) != 0))// CRC最高位为1且校验bit为1
				{
					crc <<= 1;
				} else if (((crc & 0x80) != 0) && (((ptr[j]) & i) == 0))// CRC最高位为1且校验bit为0
				{
					crc <<= 1;
					crc ^= 0x31;
				} else if (((crc & 0x80) == 0) && (((ptr[j]) & i) != 0))// CRC最高位为0且校验bit为1
				{
					crc <<= 1;
					crc ^= 0x31;
				} else if (((crc & 0x80) == 0) && (((ptr[j]) & i) == 0))// CRC最高位为0且校验bit为0
				{
					crc <<= 1;
				}
				i >>= 1;
			}
		}
		return crc;
	}

	/**
	 * 将data字节型数据转换为0~255 (0xFF 即BYTE)。
	 *
	 * @param data 字节
	 * @return 0~255的数据
	 */
	public int getUnsignedByte(byte data) {
		return data & 0x0FF;
	}

	/**
	 * 生成uuid
	 * @return
	 */
	private String getUUID(){
		UUID uuid=UUID.randomUUID();
		String str = uuid.toString();
		String uuidStr=str.replace("-", "");
		return uuidStr;
	}

	/**
	 * 生成双端标签
	 * @return 双数组
	 */
	public byte[][] buildEidCode1() {
		byte[][] data = new byte[2][32];
		byte[] eidCode = null;
		byte[] uuid = uuidToByteAry(getUUID());//eid中的uuid
		String company = "njpt";//厂商标识
		for(int i = 0 ;i < 2; i++) {
			eidCode = new byte[32];
			//版本号
			eidCode[0] = (byte) 0x0a;
			if(i==0){
				//产品类型
				eidCode[1] = (byte)0x41;
				//端口号
				eidCode[21] = (byte)0x01;
			}else{
				//产品类型
				eidCode[1] = (byte)0x81;
				//端口号
				eidCode[21] = (byte)0x02;
			}
			byte[] company_ary = company.getBytes();
			for (int j = 0; j <3; j++) {
				eidCode[2+j] = company_ary[j];
			}
			//序列号
			for (int j = 0; j < uuid.length; j++) {
				eidCode[5+j] = uuid[j];
			}
			//入端口数
			eidCode[22] = 0x01;
			//出端口数
			eidCode[23] = 0x01;
			//运营商标识
			eidCode[24] = 0x00;
			//扩展信息，预留信息
			for (int j = 0; j < 7; j++) {
				eidCode[25+j] = 0x00;
			}
			//crc
			byte rec_crc= (byte)crcCheck(eidCode, eidCode.length-2);
			eidCode[eidCode.length-1] = rec_crc;
			data[i] = eidCode;
		}
		return data;
	}

	/**
	 * 生成单端标签
	 * @return 数据
	 */
	public byte[] buildEidCode2() {
		byte[] eidCode = new byte[32];
		byte[] uuid = uuidToByteAry(getUUID());//eid中的uuid
		String company = "njpt";//厂商标识
		//版本号
		eidCode[0] = (byte) 0x0a;
		//产品类型
		eidCode[1] = (byte)0x42;
		//端口号
		eidCode[21] = (byte)0x01;
		byte[] company_ary = company.getBytes();
		for (int j = 0; j <3; j++) {
			eidCode[2+j] = company_ary[j];
		}
		//序列号
		for (int j = 0; j < uuid.length; j++) {
			eidCode[5+j] = uuid[j];
		}
		//入端口数
		eidCode[22] = 0x01;
		//出端口数
		eidCode[23] = 0x01;
		//运营商标识
		eidCode[24] = 0x00;
		//扩展信息，预留信息
		for (int j = 0; j < 7; j++) {
			eidCode[25+j] = 0x00;
		}
		//crc
		byte rec_crc= (byte)crcCheck(eidCode, eidCode.length-2);
		eidCode[eidCode.length-1] = rec_crc;
		return eidCode;
	}

	/**
	 * uuid转换成16位eid码
	 * @param uuid
	 * @return
	 */
	private byte[] uuidToByteAry(String uuid){
		byte [] b  = null;
		if(uuid.length()%2 == 0){
			b = new byte[uuid.length()/2];
			for(int i =0;i<uuid.length()/2;i++){
				String two = uuid.substring(i*2, (i+1)*2);
				int a = Integer.valueOf(two, 16);
				b[i] = (byte) a;
			}
		}
		return b;
	}
}
