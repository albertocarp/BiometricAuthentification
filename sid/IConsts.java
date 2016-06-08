package sid;

import javacard.framework.JCSystem;

public interface IConsts 
{
	/**
	 * System variables
	 */
	public static final byte  UNTOUCHED_VALUE = 0x02;
	public static final byte  TRUE = 0x01;
	public static final byte  FALSE = 0x03;
	public static final byte  OFFSET_START=0x00;
	public static final short INVALID_DATA_LENGTH=-1;
	
	/**
	 * CMD_CLA variables
	 */
	public static final byte OFFSET_CLA_APPLICATION = (byte) 0x00;
	
	public static final byte OFFSET_INS_LIGHT =  (byte) 0x11;
	public static final byte OFFSET_INS_SYSTEM = (byte) 0x21;
	public static final byte OFFSET_INS_UPROVE = (byte) 0x22;
	public static final byte OFFSET_INS_TEST   = (byte) 0x23; 
	public static final byte OFFSET_INS_HASH   = (byte) 0x24;
	public static final byte OFFSET_INS_BIOMETRIC = (byte)0x25;

	public static final byte OFFSET_P1_ENC 	 = (byte) 0x21;
	public static final byte OFFSET_P1_DEC	 = (byte) 0x22;
	public static final byte OFFSET_P1_GEN 	 = (byte) 0x23;
	
	public static final byte OFFSET_P1_CHAIN_BIO  = (byte) 0x24;
	public static final byte OFFSET_P1_SET_PASSWORD =  (byte)0x25;
	public static final byte OFFSET_P1_SET_IDENTITY =  (byte)0x26;
	public static final byte OFFSET_P1_REGISTRATION = (byte)0x27;
	public static final byte OFFSET_PI_ADDITIONAL_DATA  = (byte)0x28;
	public static final byte OFFSET_PI_SECOND_MESSAGE = 0x29;
	public static final byte OFFSET_P1_REGISTRATION_MESSAGE = 0x33;
 	
	public static final byte OFFSET_P1_AUTH_PASSWORD   =   (byte)0x30;
	public static final byte OFFSET_P1_AUTH_IDENTITY   =   (byte)0x31;
	public static final byte OFFSET_P1_CHAIN_AUTH 	   =   (byte)0x32;
	public static final byte OFFSET_P1_AUTH_FIRST_STEP =   (byte)0x34;
	public static final byte OFFSET_P1_AUTH_SECOND_STEP =  (byte)0x35;
	
	
	public static final byte OFFSET_P1_GET_PASSWORD = (byte)0x40;
	public static final byte OFFSET_P1_GET_IDENTITY = (byte)0x41;
	public static final byte OFFSET_P1_GET_ADITIONAL_FIELDS   = (byte)0x42;
	public static final byte OFFSET_P1_GET_BIO   = (byte)0x43;
	public static final byte OFFSET_P1_GET_P   = (byte)0x44;
	public static final byte OFFSET_P1_GET_Q   = (byte)0x45;
	public static final byte OFFSET_P1_GET_S3   = (byte)0x46;
	public static final byte OFFSET_P1_GET_S1   = (byte)0x47;
	public static final byte OFFSET_P1_GET_SK =  (byte)0x48;
	public static final byte OFFSET_P1_GET_M2_N1 =  (byte)0x49;
	
	public static final byte OFFSET_P2_LAST_FRAGMENT = (byte)0x01;
	public static final byte OFFSET_P2_MORE_FRAGMENTS = (byte)0x00;
	
	public static final byte OFFSET_P2_CONFIG_DATA = (byte)0x27;
	public static final byte OFFSET_P2_GALOIS_FIELD = (byte)0x28;
	public static final byte OFFSET_P2_COMPUTE = (byte)0x29;
	

	public static final byte BIOMETRIC_INFO=0x75;
	

	
}
