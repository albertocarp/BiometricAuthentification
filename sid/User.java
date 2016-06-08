/*
 * 
 */

// ***********************************************************************
// Assembly         : 
// Author           : Alberto-PC
// Created          : 05-12-2016
//
// Last Modified By : Alberto-PC
// Last Modified On : 06-08-2016
// ***********************************************************************
// <copyright file="User.java" company="Military Technical Academy">
//     Copyright (c) . All rights reserved.
// </copyright>
// <summary></summary>
// ***********************************************************************
package sid;

import com.sun.javacard.crypto.al;

import javacard.framework.ISO7816;
import javacard.framework.ISOException;
import javacard.framework.JCSystem;
import javacard.framework.Util;
import javacard.security.KeyBuilder;
import javacard.security.MessageDigest;
import javacard.security.RSAPrivateKey;
import javacard.security.RSAPublicKey;
import javacard.security.RandomData;
import javacardx.crypto.Cipher;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User  implements IConsts 
{
	
	/** The Password. */
	byte[] Password = new byte[128];
	
	/** The Password size. */
	short PasswordSize;
	
	/** The Identity. */
	byte[] Identity = new byte[128];
	
	/** The Identity size. */
	short IdentitySize;
	
	/** The message_registration. */
	byte[] message_registration = new byte[128];
	
	/** The p number. */
	byte[] P_NUMBER = new byte[128];
	
	/** The q number. */
	byte[] Q_NUMBER = new byte[128];
	
	/** The p. */
	byte[] P = new byte[512];
	
	/** The hash r. */
	byte[] HASH_R = new byte[20];
	
	/** The S3. */
	byte[] S3 = new byte[20];
	
	/** The M1. */
	byte[] M1 = new byte[128];
	
	/** The buffer. */
	byte[] BUFFER = new byte[512];
	
	/** The BUFFE r2. */
	byte[] BUFFER2 = new byte[400];
	
	/** The S2_ prim. */
	byte[] S2_PRIM = new byte[128];
	
	/** The N1. */
	byte[] N1 = new byte[128];
	
	/** The sk. */
	byte[] SK = new byte[20];
	
	/** The M2_ n1. */
	byte[] M2_N1 = new byte[128];
	
	/** The index_buffer. */
	short index_buffer = 0;
	
	/** The rsa. */
	Cipher rsa;
	
	/** The m rsa public key mod pow. */
	RSAPrivateKey mRsaPublicKeyModPow;
	
	 /** The group p. */
 	public  byte[] GROUP_P = {(byte) 0xD2, (byte) 0x1A, (byte) 0xE8, (byte) 0xD6, (byte) 0x6E, (byte) 0x6C, (byte) 0x6B, (byte) 0x3C, (byte) 0xED, (byte) 0x0E, 
			 (byte) 0xB3, (byte) 0xDF, (byte) 0x1A, (byte) 0x26, (byte) 0xC9, (byte) 0x1B, (byte) 0xDE, (byte) 0xED, (byte) 0x01, (byte) 0x3C, (byte) 0x17,
			 (byte) 0xD8, (byte) 0x49, (byte) 0xD3, (byte) 0x0E, (byte) 0xC3, (byte) 0x09, (byte) 0x81, (byte) 0x3E, (byte) 0x4D, (byte) 0x37, (byte) 0x99, (byte) 0xF2, 
			 (byte) 0x6D, (byte) 0xB0, (byte) 0xD4, (byte) 0x94, (byte) 0xE8, (byte) 0x2E, (byte) 0xC6, (byte) 0x1E, (byte) 0xA9, (byte) 0xFD, (byte) 0xC7, (byte) 0x0B, 
			 (byte) 0xB5, (byte) 0xCB, (byte) 0xCA, (byte) 0xF2, (byte) 0xE5, (byte) 0xF1, (byte) 0x8A, (byte) 0x83, (byte) 0x64, (byte) 0x94, (byte) 0xF5, (byte) 0x8E,
			 (byte) 0x67, (byte) 0xC6, (byte) 0xD6, (byte) 0x16, (byte) 0x48, (byte) 0x0C, (byte) 0x37, (byte) 0xA7, (byte) 0xF2, (byte) 0x30, (byte) 0x61, (byte) 0x01, 
			 (byte) 0xFC, (byte) 0x9F, (byte) 0x0F, (byte) 0x47, (byte) 0x68, (byte) 0xF9, (byte) 0xC9, (byte) 0x79, (byte) 0x3C, (byte) 0x2B, (byte) 0xE1, (byte) 0x76, 
			 (byte) 0xB0, (byte) 0xB7, (byte) 0xC9, (byte) 0x79, (byte) 0xB4, (byte) 0x06, (byte) 0x5D, (byte) 0x3E, (byte) 0x83, (byte) 0x56, (byte) 0x86, (byte) 0xA3,
			 (byte) 0xF0, (byte) 0xB8, (byte) 0x42, (byte) 0x0C, (byte) 0x68, (byte) 0x34, (byte) 0xCB, (byte) 0x17, (byte) 0x93, (byte) 0x03, (byte) 0x86, (byte) 0xDE, 
			 (byte) 0xDA, (byte) 0xB2, (byte) 0xB0, (byte) 0x7D, (byte) 0xD4, (byte) 0x73, (byte) 0x44, (byte) 0x9A, (byte) 0x48, (byte) 0xBA, (byte) 0xAB, (byte) 0x31,
			 (byte) 0x62, (byte) 0x86, (byte) 0xB4, (byte) 0x21, (byte) 0x05, (byte) 0x24, (byte) 0x75, (byte) 0xD1, (byte) 0x34, (byte) 0xCD, (byte) 0x3B};
	 
 	/** The manager. */
 	MemoryManager manager;
	
	/**
	 * The Class Biometrics.
	 */
	class Biometrics {
		
		/** The Biometrics. */
		byte[] Biometrics = new byte[1024];
		
		/** The index. */
		short index=0;
		
		/** The size. */
		short size=0;
	}

	/** The biometric. */
	Biometrics biometric = new Biometrics(); 
	
	/** The p_ instance. */
	static User p_Instance;
	
	/** The p_ instance fuzzy. */
	static FuzzyExtractor p_InstanceFuzzy;
	
	/**
	 * Instantiates a new user.
	 */
	private User()
	{
		p_InstanceFuzzy =  FuzzyExtractor.getInstance();
		manager = MemoryManager.getInstance();
		rsa = Cipher.getInstance(Cipher.ALG_RSA_NOPAD,false);
		mRsaPublicKeyModPow = (RSAPrivateKey) KeyBuilder.buildKey(
				KeyBuilder.TYPE_RSA_PRIVATE, KeyBuilder.LENGTH_RSA_1024, false);
	}
	
	/**
	 * Gets the single instance of User.
	 *
	 * @return single instance of User
	 */
	public static User getInstance()
	{
		if(p_Instance == null)
			p_Instance = new User();
		return p_Instance;
	}
	
	/**
	 * Creates the instance fuzzy.
	 */
	public void createInstanceFuzzy()
	{
		FuzzyExtractor.getInstance().createInstance();	
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the password
	 * @param offsetPassword the offset password
	 * @param sizePassword the size password
	 */
	public void setPassword(byte[] password,short offsetPassword,short sizePassword)
	{
		Util.arrayCopy(password, offsetPassword, this.Password, OFFSET_START, sizePassword);
		PasswordSize = sizePassword;
	}
	
	/**
	 * Sets the identity.
	 *
	 * @param identity the identity
	 * @param offsetIdentity the offset identity
	 * @param sizeIdentity the size identity
	 */
	public void setIdentity(byte[] identity,short offsetIdentity,short sizeIdentity)
	{
		Util.arrayCopy(identity, offsetIdentity, Identity, OFFSET_START, sizeIdentity);
		IdentitySize = sizeIdentity;
	}

	/**
	 * Update bio.
	 *
	 * @param arrayInfo the array info
	 * @param offsetArray the offset array
	 * @param sizeBio the size bio
	 */
	public void updateBio(byte[] arrayInfo,short offsetArray,short sizeBio)
	{
		Util.arrayCopy(arrayInfo,offsetArray,biometric.Biometrics,biometric.index,sizeBio);
		biometric.index += sizeBio;
		biometric.size += sizeBio;
	}
	
	/**
	 * Update galois field.
	 *
	 * @param arrayInfo the array info
	 * @param offsetArray the offset array
	 * @param size the size
	 */
	public void updateGaloisField(byte[] arrayInfo,short offsetArray,short size)
	{
		
	}
	
	/**
	 * Registration.
	 *
	 * @return the byte[]
	 */
	public byte[] registration()
	{
		byte[] transientMemory = manager.geByteRam();
		short offset = OFFSET_START;
		// generates 
		RandomData rng = RandomData.getInstance(RandomData.ALG_PSEUDO_RANDOM);
		MessageDigest SHA = MessageDigest.getInstance(MessageDigest.ALG_SHA, false);
		short k = FuzzyExtractor.getInstance().k;
		rng.generateData(this.P, OFFSET_START,k);
		for(short i = 0 ; i < k ; i ++)
			this.P[i] = (byte) (this.P[i] >> 16 & (short)0x0001);
		FuzzyExtractor.getInstance().encode(P);
		MessageDigest.getInstance(MessageDigest.ALG_SHA, false).doFinal(biometric.Biometrics, OFFSET_START, p_InstanceFuzzy.LENGTH, HASH_R, OFFSET_START);
		Util.arrayCopy(FuzzyExtractor.getInstance().ENCODED_DATA, OFFSET_START, P, OFFSET_START, FuzzyExtractor.getInstance().LENGTH);
		FuzzyExtractor.getInstance().xor(P, OFFSET_START, biometric.Biometrics, OFFSET_START, FuzzyExtractor.getInstance().LENGTH);
		
		
		Util.arrayCopy(Identity, OFFSET_START, message_registration, offset,IdentitySize);
		offset += IdentitySize;
		
		MessageDigest.getInstance(MessageDigest.ALG_SHA, false).doFinal(biometric.Biometrics, OFFSET_START,biometric.index, transientMemory,OFFSET_START);
		
		Util.arrayCopy(transientMemory, OFFSET_START,message_registration, offset, MessageDigest.LENGTH_SHA);
		offset += MessageDigest.LENGTH_SHA;
		
		Util.arrayCopy(biometric.Biometrics, OFFSET_START,BUFFER, OFFSET_START, biometric.index);
		Util.arrayCopy(Password, OFFSET_START,BUFFER, (short) (biometric.index), PasswordSize);
		
		MessageDigest.getInstance(MessageDigest.ALG_SHA, false).doFinal(BUFFER,OFFSET_START,(short)(biometric.index +PasswordSize),
				BUFFER,OFFSET_START );
		
		Util.arrayCopy( BUFFER, OFFSET_START, message_registration, (short)(offset),MessageDigest.LENGTH_SHA);
		
		return message_registration;
		
	}
	
	/**
	 * Receive registration response.
	 *
	 * @param data the data
	 * @param offset the offset
	 * @param size the size
	 * @param more_data the more_data
	 */
	public void receiveRegistrationResponse(byte[] data,short offset,short size,boolean more_data)
	{
		if(more_data)
		{
			Util.arrayCopy(data, offset, BUFFER, index_buffer, size);
			index_buffer += size;
		}
		else
		{
			Util.arrayCopy(data, offset, BUFFER, index_buffer, size);
			index_buffer += size;
			
			// copy the results
			Util.arrayCopy(BUFFER, OFFSET_START, S3, OFFSET_START, (short) 20);
			Util.arrayCopy(BUFFER, (short) 20, P_NUMBER, OFFSET_START, (short) 128);
			Util.arrayCopy(BUFFER, (short) (20+128), Q_NUMBER, OFFSET_START, (short) 128);
		}
	}


	/**
	 * Authetificate.
	 *
	 * @param input the input
	 * @return the byte[]
	 */
	public byte[] authetificate(UserInput input)
	{
		byte[] r_prim = p_InstanceFuzzy.makeFuzzy(input.challange_biometric.Biometrics, P);
		Util.arrayCopy(r_prim, OFFSET_START, input.R_PRIM, OFFSET_START, p_InstanceFuzzy.LENGTH);
		MessageDigest.getInstance(MessageDigest.ALG_SHA, false).doFinal(r_prim, OFFSET_START, p_InstanceFuzzy.LENGTH, r_prim, OFFSET_START);
		
		byte result = Util.arrayCompare(r_prim, OFFSET_START, HASH_R, OFFSET_START,MessageDigest.LENGTH_SHA);
		if(result != 0)
			ISOException.throwIt(ISO7816.SW_CONDITIONS_NOT_SATISFIED);
		byte[] memory = MemoryManager.getInstance().geByteRam();
		short offset  = OFFSET_START;
		Util.arrayCopyNonAtomic(input.R_PRIM, OFFSET_START, BUFFER, offset, p_InstanceFuzzy.LENGTH);
		offset += p_InstanceFuzzy.LENGTH;
		Util.arrayCopyNonAtomic(input.Password, OFFSET_START, BUFFER, offset, input.PasswordSize);
		offset += input.PasswordSize;
		MessageDigest.getInstance(MessageDigest.ALG_SHA, false).doFinal(BUFFER, OFFSET_START,offset,BUFFER2,OFFSET_START);
		Util.arrayCopy(r_prim, OFFSET_START, BUFFER, OFFSET_START, MessageDigest.LENGTH_SHA);
		Util.arrayCopy(BUFFER2, OFFSET_START, BUFFER,  MessageDigest.LENGTH_SHA, MessageDigest.LENGTH_SHA);
	    MessageDigest.getInstance(MessageDigest.ALG_SHA, false).doFinal(BUFFER, OFFSET_START,(short) (2*MessageDigest.LENGTH_SHA),S2_PRIM,OFFSET_START);
		
		p_InstanceFuzzy.xor(S2_PRIM,(short)OFFSET_START,S3,(short)OFFSET_START, MessageDigest.LENGTH_SHA); // s2_prim stores  s1	
		
		Util.arrayCopy(GROUP_P, OFFSET_START, memory, OFFSET_START, (short)128);
		RandomData.getInstance(RandomData.ALG_PSEUDO_RANDOM).generateData(N1, OFFSET_START, MessageDigest.LENGTH_SHA);
		Util.arrayCopy(N1, OFFSET_START, memory, (short)128, MessageDigest.LENGTH_SHA);
		
		
		Util.arrayFillNonAtomic(memory,  (short)(148),(short) 128, (byte)0x00);
		Util.arrayCopy(S2_PRIM, OFFSET_START, memory, (short)(256) , MessageDigest.LENGTH_SHA);
		
		
		
		offset = 0;
		mRsaPublicKeyModPow.setModulus(memory,OFFSET_START,(short)128);
		offset += 128;
		mRsaPublicKeyModPow.setExponent(memory,(short)128,MessageDigest.LENGTH_SHA);	
		offset += MessageDigest.LENGTH_SHA;
	
		rsa.init(mRsaPublicKeyModPow,Cipher.MODE_DECRYPT);
		rsa.doFinal(memory, (short)(148), (short) 128, memory, input.IdentitySize); // this is the M1
		
		Util.arrayCopyNonAtomic(memory, input.IdentitySize, M1, OFFSET_START, (short) 128);
		Util.arrayCopyNonAtomic(input.Identity, OFFSET_START, memory, OFFSET_START,  input.IdentitySize);
		Util.arrayCopyNonAtomic(S2_PRIM, OFFSET_START, memory,(short) (128+input.IdentitySize) , MessageDigest.LENGTH_SHA);
		
		MessageDigest.getInstance(MessageDigest.ALG_SHA, false).doFinal(memory,OFFSET_START,(short) (148+input.IdentitySize),BUFFER,IdentitySize);
		
		Util.arrayCopyNonAtomic(input.Identity, OFFSET_START, BUFFER, OFFSET_START,  input.IdentitySize); // insert the identity
		Util.arrayCopyNonAtomic(memory, input.IdentitySize, BUFFER, (short)(input.IdentitySize+MessageDigest.LENGTH_SHA) ,(short)128); //insert the message
		
		// copy all data to buffer and send it to transient memory and send out
	 
		return BUFFER;
		//return memory;	
		
	}
	
	/**
	 * Authetificate2.
	 *
	 * @param data the data
	 * @param offsetData the offset data
	 * @param length the length
	 * @return the byte[]
	 */
	public byte[] authetificate2(byte[] data,short offsetData,short length)
	{
		short offset  = OFFSET_START;
		short offsetM2 = (short) (offsetData + MessageDigest.LENGTH_SHA);
		Util.arrayCopyNonAtomic(Identity, OFFSET_START, BUFFER, OFFSET_START,IdentitySize);
		offset += IdentitySize;
		Util.arrayCopyNonAtomic(S2_PRIM, OFFSET_START, BUFFER, offset,MessageDigest.LENGTH_SHA);
		offset += MessageDigest.LENGTH_SHA;
		Util.arrayCopyNonAtomic(data,offsetM2,BUFFER,offset,(short) 128);
		offset += 128;
		MessageDigest.getInstance(MessageDigest.ALG_SHA, false).doFinal(BUFFER,OFFSET_START,offset,BUFFER,OFFSET_START);
		
		byte val = Util.arrayCompare(BUFFER, OFFSET_START, data,offsetData, MessageDigest.LENGTH_SHA);
		
		if(val != 0)
			ISOException.throwIt(ISO7816.SW_CONDITIONS_NOT_SATISFIED);
		
		 offset  = OFFSET_START;
		 Util.arrayCopyNonAtomic(M1,OFFSET_START,BUFFER,offset,(short) 128);
	     offset += 128;
		 Util.arrayCopyNonAtomic(Identity, OFFSET_START, BUFFER, offset,IdentitySize);
		 offset += IdentitySize;
		 Util.arrayCopyNonAtomic(S2_PRIM, OFFSET_START, BUFFER, offset,MessageDigest.LENGTH_SHA);
		 offset += MessageDigest.LENGTH_SHA;
		 Util.arrayCopyNonAtomic(data,offsetM2,BUFFER,offset,(short) 128);
		 offset += 128;
		 
		 MessageDigest.getInstance(MessageDigest.ALG_SHA, false).doFinal(BUFFER,OFFSET_START,offset,BUFFER,OFFSET_START);
		 // compute keys
		 byte[] memory = MemoryManager.getInstance().geByteRam();
		 
		 Util.arrayCopy(S2_PRIM,OFFSET_START,BUFFER2,OFFSET_START,MessageDigest.LENGTH_SHA);
		 Util.arrayCopy(Identity,OFFSET_START,BUFFER2,(short)(128+MessageDigest.LENGTH_SHA),IdentitySize);
		 
		 Util.arrayCopy(GROUP_P, OFFSET_START, memory, OFFSET_START, (short)128);
		 Util.arrayCopy(N1, OFFSET_START, memory, (short)128, MessageDigest.LENGTH_SHA);
		 
		 mRsaPublicKeyModPow.clearKey();
		 Util.arrayFillNonAtomic(memory,  (short)(148),(short) 128, (byte)0x00);
		 offset = 0;
		 mRsaPublicKeyModPow.setModulus(memory,OFFSET_START,(short)128);
		 offset += 128;
		 mRsaPublicKeyModPow.setExponent(memory,(short)128,MessageDigest.LENGTH_SHA);	
		 offset += MessageDigest.LENGTH_SHA;
		 Util.arrayCopyNonAtomic(data,offsetM2,memory,offset,(short) 128);
		 rsa.init(mRsaPublicKeyModPow,Cipher.MODE_DECRYPT);
		 rsa.doFinal(memory, (short)(148), (short) 128, BUFFER2, MessageDigest.LENGTH_SHA); // this is the M1
		 Util.arrayCopy(BUFFER2, MessageDigest.LENGTH_SHA,  M2_N1,OFFSET_START,(short) 128);
		 MessageDigest.getInstance(MessageDigest.ALG_SHA, false).doFinal(BUFFER2,OFFSET_START,(short)(128+MessageDigest.LENGTH_SHA+IdentitySize),SK,OFFSET_START);
		
		return BUFFER;
	}

}
