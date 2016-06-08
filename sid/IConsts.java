// ***********************************************************************
// Assembly         : 
// Author           : Alberto-PC
// Created          : 05-12-2016
//
// Last Modified By : Alberto-PC
// Last Modified On : 06-08-2016
// ***********************************************************************
// <copyright file="IConst.java" company="Military Technical Academy">
//     Copyright (c) . All rights reserved.
// </copyright>
// <summary></summary>
// ***********************************************************************
package sid;

import javacard.framework.JCSystem;
// TODO: Auto-generated Javadoc

/**
 * This class stores all the constants from the biometric application.
 *
 * @author Alberto-PC
 */
public interface IConsts 
{
	
	/** System variables. */
	public static final byte  UNTOUCHED_VALUE = 0x02;
	
	/** The Constant TRUE. */
	public static final byte  TRUE = 0x01;
	
	/** The Constant FALSE. */
	public static final byte  FALSE = 0x03;
	
	/** The Constant OFFSET_START. */
	public static final byte  OFFSET_START=0x00;
	
	/** The Constant INVALID_DATA_LENGTH. */
	public static final short INVALID_DATA_LENGTH=-1;
	
	/** CMD_CLA variables. */
	public static final byte OFFSET_CLA_APPLICATION = (byte) 0x00;
	
	/** These offsets stores the INS offset of the APDU command. */
	public static final byte OFFSET_INS_LIGHT =  (byte) 0x11;
	
	/** The Constant OFFSET_INS_SYSTEM. */
	public static final byte OFFSET_INS_SYSTEM = (byte) 0x21;
	
	/** The Constant OFFSET_INS_UPROVE. */
	public static final byte OFFSET_INS_UPROVE = (byte) 0x22;
	
	/** The Constant OFFSET_INS_TEST. */
	public static final byte OFFSET_INS_TEST   = (byte) 0x23; 
	
	/** The Constant OFFSET_INS_HASH. */
	public static final byte OFFSET_INS_HASH   = (byte) 0x24;
	
	/** The Constant OFFSET_INS_BIOMETRIC. */
	public static final byte OFFSET_INS_BIOMETRIC = (byte)0x25;

	/** These variables stores the P1 byte from APDU command. */
	public static final byte OFFSET_P1_ENC 	 = (byte) 0x21;
	
	/** The Constant OFFSET_P1_DEC. */
	public static final byte OFFSET_P1_DEC	 = (byte) 0x22;
	
	/** The Constant OFFSET_P1_GEN. */
	public static final byte OFFSET_P1_GEN 	 = (byte) 0x23;
	
	/** The Constant OFFSET_P1_CHAIN_BIO. */
	public static final byte OFFSET_P1_CHAIN_BIO  = (byte) 0x24;
	
	/** The Constant OFFSET_P1_SET_PASSWORD. */
	public static final byte OFFSET_P1_SET_PASSWORD =  (byte)0x25;
	
	/** The Constant OFFSET_P1_SET_IDENTITY. */
	public static final byte OFFSET_P1_SET_IDENTITY =  (byte)0x26;
	
	/** The Constant OFFSET_P1_REGISTRATION. */
	public static final byte OFFSET_P1_REGISTRATION = (byte)0x27;
	
	/** The Constant OFFSET_PI_ADDITIONAL_DATA. */
	public static final byte OFFSET_PI_ADDITIONAL_DATA  = (byte)0x28;
	
	/** The Constant OFFSET_PI_SECOND_MESSAGE. */
	public static final byte OFFSET_PI_SECOND_MESSAGE = 0x29;
	
	/** The Constant OFFSET_P1_REGISTRATION_MESSAGE. */
	public static final byte OFFSET_P1_REGISTRATION_MESSAGE = 0x33;
	
	/** The Constant OFFSET_P1_AUTH_PASSWORD. */
	public static final byte OFFSET_P1_AUTH_PASSWORD   =   (byte)0x30;
	
	/** The Constant OFFSET_P1_AUTH_IDENTITY. */
	public static final byte OFFSET_P1_AUTH_IDENTITY   =   (byte)0x31;
	
	/** The Constant OFFSET_P1_CHAIN_AUTH. */
	public static final byte OFFSET_P1_CHAIN_AUTH 	   =   (byte)0x32;
	
	/** The Constant OFFSET_P1_AUTH_FIRST_STEP. */
	public static final byte OFFSET_P1_AUTH_FIRST_STEP =   (byte)0x34;
	
	/** The Constant OFFSET_P1_AUTH_SECOND_STEP. */
	public static final byte OFFSET_P1_AUTH_SECOND_STEP =  (byte)0x35;
	
	/** The Constant OFFSET_P1_GET_PASSWORD. */
	public static final byte OFFSET_P1_GET_PASSWORD = (byte)0x40;
	
	/** The Constant OFFSET_P1_GET_IDENTITY. */
	public static final byte OFFSET_P1_GET_IDENTITY = (byte)0x41;
	
	/** The Constant OFFSET_P1_GET_ADITIONAL_FIELDS. */
	public static final byte OFFSET_P1_GET_ADITIONAL_FIELDS   = (byte)0x42;
	
	/** The Constant OFFSET_P1_GET_BIO. */
	public static final byte OFFSET_P1_GET_BIO   = (byte)0x43;
	
	/** The Constant OFFSET_P1_GET_P. */
	public static final byte OFFSET_P1_GET_P   = (byte)0x44;
	
	/** The Constant OFFSET_P1_GET_Q. */
	public static final byte OFFSET_P1_GET_Q   = (byte)0x45;
	
	/** The Constant OFFSET_P1_GET_S3. */
	public static final byte OFFSET_P1_GET_S3   = (byte)0x46;
	
	/** The Constant OFFSET_P1_GET_S1. */
	public static final byte OFFSET_P1_GET_S1   = (byte)0x47;
	
	/** The Constant OFFSET_P1_GET_SK. */
	public static final byte OFFSET_P1_GET_SK =  (byte)0x48;
	
	/** The Constant OFFSET_P1_GET_M2_N1. */
	public static final byte OFFSET_P1_GET_M2_N1 =  (byte)0x49;
	
	/** These variables stores the P2 byte from APDU command. */
	public static final byte OFFSET_P2_LAST_FRAGMENT = (byte)0x01;
	
	/** The Constant OFFSET_P2_MORE_FRAGMENTS. */
	public static final byte OFFSET_P2_MORE_FRAGMENTS = (byte)0x00;
	
	/** The Constant OFFSET_P2_CONFIG_DATA. */
	public static final byte OFFSET_P2_CONFIG_DATA = (byte)0x27;
	
	/** The Constant OFFSET_P2_GALOIS_FIELD. */
	public static final byte OFFSET_P2_GALOIS_FIELD = (byte)0x28;
	
	/** The Constant OFFSET_P2_COMPUTE. */
	public static final byte OFFSET_P2_COMPUTE = (byte)0x29;
	
	/** The Constant BIOMETRIC_INFO. */
	public static final byte BIOMETRIC_INFO=0x75;
	

	
}
