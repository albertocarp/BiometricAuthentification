// ***********************************************************************
// Assembly         : 
// Author           : Alberto-PC
// Created          : 05-12-2016
//
// Last Modified By : Alberto-PC
// Last Modified On : 06-08-2016
// ***********************************************************************
// <copyright file="ProiectLicentaBio.cpp" company="Military Technical Academy">
//     Copyright (c) . All rights reserved.
// </copyright>
// <summary></summary>
// ***********************************************************************
package sid;

import javacard.framework.APDU;
import javacard.framework.Applet;
import javacard.framework.ISO7816;
import javacard.framework.ISOException;
import javacard.framework.JCSystem;
import javacard.framework.Util;
import javacard.security.MessageDigest;
import javacard.security.RandomData;

// TODO: Auto-generated Javadoc
/**
 * The Class ProiectLicentaBio.
 */
public class ProiectLicentaBio extends Applet implements IConsts{

	
	/**
	 * Instantiates a new proiect licenta bio.
	 */
	private ProiectLicentaBio() 
	{
		
	}

	/**
	 * Install.
	 *
	 * @param bArray the b array
	 * @param bOffset the b offset
	 * @param bLength the b length
	 * @throws ISOException the ISO exception
	 */
	public static void install(byte bArray[], short bOffset, byte bLength) throws ISOException {
		new ProiectLicentaBio().register();
	}
	
	/* (non-Javadoc)
	 * @see javacard.framework.Applet#process(javacard.framework.APDU)
	 */
	public void process(APDU apdu) throws ISOException {
		byte[] buf = apdu.getBuffer();
		if(buf[ISO7816.OFFSET_CLA] != IConsts.OFFSET_CLA_APPLICATION)
			ISOException.throwIt(ISO7816.SW_CLA_NOT_SUPPORTED);
		short lc = (short) (buf[ISO7816.OFFSET_LC] & 0xff);
		short read = apdu.setIncomingAndReceive();
		while(read < lc) {
		  read += apdu.receiveBytes(read);
		}
		
		switch (buf[ISO7816.OFFSET_INS]) 
		{
			case IConsts.OFFSET_INS_BIOMETRIC:
				processBiometrics(apdu);
				return;
			case OFFSET_INS_TEST:
				testMode(apdu);
				break;
			default:
				break;
		}
	
	}
	
	/**
	 * Process biometrics.
	 *
	 * @param apdu the apdu
	 */
	private void processBiometrics(APDU apdu) {
		byte[] buf = apdu.getBuffer();
		byte state = (buf[ISO7816.OFFSET_P1]);
		byte type = (buf[ISO7816.OFFSET_P2]);
		short count_data = (short) (buf[ISO7816.OFFSET_LC] & 0xff);
		if(count_data < 0)
			ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
		User instanceUser = User.getInstance();
		UserInput currentInput = UserInput.getInstance();
		
		switch(state)
		{
			case OFFSET_P1_SET_PASSWORD:
			{
				instanceUser.setPassword(buf,(short)(ISO7816.OFFSET_LC+1), count_data);
				break;
			}
			case OFFSET_P1_GET_PASSWORD:
			{
				Util.arrayCopy(instanceUser.Password, OFFSET_START, buf, ISO7816.OFFSET_CDATA, instanceUser.PasswordSize);
				apdu.setOutgoingAndSend(ISO7816.OFFSET_CDATA, instanceUser.PasswordSize);
				break;
			}
			case  OFFSET_P1_SET_IDENTITY:
			{
				instanceUser.setIdentity(buf,(short)(ISO7816.OFFSET_LC+1), count_data);
				break;
			}
			case OFFSET_P1_CHAIN_BIO:
			{
				instanceUser.updateBio(buf,(short)(ISO7816.OFFSET_LC+1), count_data);
				break;
			}
			case OFFSET_P1_GET_BIO:
			{
				Util.arrayCopy(instanceUser.biometric.Biometrics, (short) (OFFSET_START+180), buf, ISO7816.OFFSET_CDATA,(short) 204);
				apdu.setOutgoingAndSend(ISO7816.OFFSET_CDATA, (short)204);
				break;
			}
			case OFFSET_P1_GET_P:
			{				
				Util.arrayCopy(instanceUser.P_NUMBER,OFFSET_START, buf, ISO7816.OFFSET_CDATA,(short) 128);
				apdu.setOutgoingAndSend(ISO7816.OFFSET_CDATA, (short)128);
				break;
			}
			case OFFSET_P1_GET_Q:
			{				
				Util.arrayCopy(instanceUser.Q_NUMBER,OFFSET_START, buf, ISO7816.OFFSET_CDATA,(short) 128);
				apdu.setOutgoingAndSend(ISO7816.OFFSET_CDATA, (short)128);
				break;
			}
			case OFFSET_P1_GET_S3:
			{				
				Util.arrayCopy(instanceUser.S3,OFFSET_START, buf, ISO7816.OFFSET_CDATA,(short) 20);
				apdu.setOutgoingAndSend(ISO7816.OFFSET_CDATA, (short)20);
				break;
			}
			case OFFSET_P1_GET_S1:
			{	
				Util.arrayCopy(instanceUser.S2_PRIM,OFFSET_START, buf, ISO7816.OFFSET_CDATA,(short) 40);
				apdu.setOutgoingAndSend(ISO7816.OFFSET_CDATA, (short)40);
				break;
			}
			
			case OFFSET_PI_ADDITIONAL_DATA:
			{
				if(type == OFFSET_P2_CONFIG_DATA)
				{
					FuzzyExtractor.getInstance().LENGTH = Util.makeShort(buf[ISO7816.OFFSET_CDATA], buf[ISO7816.OFFSET_CDATA+1]);
					FuzzyExtractor.getInstance().n = FuzzyExtractor.getInstance().LENGTH; 
					FuzzyExtractor.getInstance().M = Util.makeShort(buf[ISO7816.OFFSET_CDATA+2], buf[ISO7816.OFFSET_CDATA+3]);
					FuzzyExtractor.getInstance().T = Util.makeShort(buf[ISO7816.OFFSET_CDATA+4], buf[ISO7816.OFFSET_CDATA+5]);
					FuzzyExtractor.getInstance().k = Util.makeShort(buf[ISO7816.OFFSET_CDATA+6], buf[ISO7816.OFFSET_CDATA+7]);
					FuzzyExtractor.getInstance().D = (short) (2 *  FuzzyExtractor.getInstance().T + 1);
					
				}
				if(type == OFFSET_P2_GALOIS_FIELD)
				{
					instanceUser.updateGaloisField(buf, ISO7816.OFFSET_CDATA, count_data);
				}
				
				if(type == OFFSET_P2_COMPUTE)
				{
					instanceUser.createInstanceFuzzy();
				}
				break;
			}
			case OFFSET_P1_GET_ADITIONAL_FIELDS:
			{
				byte sh1 = (byte) (FuzzyExtractor.getInstance().LENGTH >> 8 & 0xff);
				byte sh2 = (byte) (FuzzyExtractor.getInstance().LENGTH);
				buf[ISO7816.OFFSET_CDATA] = sh1;
				buf[ISO7816.OFFSET_CDATA+1] = sh2;
				
				sh1 = (byte) (FuzzyExtractor.getInstance().M >> 8 & 0xff);
				sh2 = (byte) (FuzzyExtractor.getInstance().M);
				
				buf[ISO7816.OFFSET_CDATA+2] = sh1;
				buf[ISO7816.OFFSET_CDATA+3] = sh2;
				
				sh1 = (byte) (FuzzyExtractor.getInstance().T >> 8 & 0xff);
				sh2 = (byte) (FuzzyExtractor.getInstance().T);
				
				buf[ISO7816.OFFSET_CDATA+4] = sh1;
				buf[ISO7816.OFFSET_CDATA+5] = sh2;
				
				apdu.setOutgoingAndSend(ISO7816.OFFSET_CDATA,(short) 6);
				break;
			}
			case OFFSET_P1_REGISTRATION:
			{
				short size_out = (short) (40 + instanceUser.IdentitySize);
				byte[] out_data = instanceUser.registration();
				Util.arrayCopy(out_data,(short)0,buf, (short)ISO7816.OFFSET_CDATA,(short) 48);
				apdu.setOutgoingAndSend(ISO7816.OFFSET_CDATA, (short)48);
				break;
			}
			case OFFSET_P1_REGISTRATION_MESSAGE: {
			    short data_length = (short) (instanceUser.IdentitySize +40);
				Util.arrayCopy(instanceUser.message_registration,(short)0,buf, (short)ISO7816.OFFSET_CDATA,(short) data_length);
				apdu.setOutgoingAndSend(ISO7816.OFFSET_CDATA, (short)48);
				break;
			}
			case OFFSET_PI_SECOND_MESSAGE:
			{
				if(type == OFFSET_P2_MORE_FRAGMENTS)
				{
					instanceUser.receiveRegistrationResponse(buf,ISO7816.OFFSET_CDATA,count_data,true);
				}
				if(type == OFFSET_P2_LAST_FRAGMENT)
				{
					instanceUser.receiveRegistrationResponse(buf,ISO7816.OFFSET_CDATA,count_data,false);
				}
				break;
			}
			/// autentification scope
			
			case OFFSET_P1_AUTH_IDENTITY:
			{
				currentInput.setIdentity(buf,(short)(ISO7816.OFFSET_LC+1), count_data);
				break;
			}
			case OFFSET_P1_AUTH_PASSWORD:
			{
				currentInput.setPassword(buf,(short)(ISO7816.OFFSET_LC+1), count_data);
				break;
			}
			case OFFSET_P1_CHAIN_AUTH:
			{
				currentInput.auth_update_Bio(buf,(short)(ISO7816.OFFSET_LC+1), count_data);
				break;
			}
			case OFFSET_P1_AUTH_FIRST_STEP:
			{
				byte[] result = instanceUser.authetificate(currentInput);
				short size_auth = (short) (instanceUser.IdentitySize + 128 + MessageDigest.LENGTH_SHA);
				Util.arrayCopy(result,(short)0,buf, (short) ((short)ISO7816.OFFSET_CDATA),(short)(size_auth));
				apdu.setOutgoingAndSend((short) (ISO7816.OFFSET_CDATA), (short) size_auth);
				break;
			}
			case OFFSET_P1_AUTH_SECOND_STEP:
			{
				// input the message from the 
				byte[] result = instanceUser.authetificate2(buf, ISO7816.OFFSET_CDATA, count_data);
				short result_len = (short) (MessageDigest.LENGTH_SHA);
				Util.arrayCopy(result,(short)0,buf, (short) ((short)ISO7816.OFFSET_CDATA),(short)(result_len));
				apdu.setOutgoingAndSend((short) (ISO7816.OFFSET_CDATA), (short) result_len);
				break;
			}
			case OFFSET_P1_GET_SK: {
				byte[] result = instanceUser.SK;
				short result_len = (short) (MessageDigest.LENGTH_SHA);
				Util.arrayCopy(result,(short)0,buf, (short) ((short)ISO7816.OFFSET_CDATA),(short)(result_len));
				apdu.setOutgoingAndSend((short) (ISO7816.OFFSET_CDATA), (short) result_len);
				break;
			}
			case OFFSET_P1_GET_M2_N1: {
				byte[] result = instanceUser.M2_N1;
				short result_len = (short)128;
				Util.arrayCopy(result,(short)0,buf, (short) ((short)ISO7816.OFFSET_CDATA),(short)(result_len));
				apdu.setOutgoingAndSend((short) (ISO7816.OFFSET_CDATA), (short) result_len);
				break;
			}
		}
	}

	/**
	 * Test mode.
	 *
	 * @param apdu the apdu
	 */
	private void testMode(APDU apdu)
	{
		FuzzyExtractor.getInstance().createInstance();
		RandomData rng = RandomData.getInstance(RandomData.ALG_PSEUDO_RANDOM);
		byte[] P = User.getInstance().P;
		rng.generateData(User.getInstance().P, OFFSET_START,(short) FuzzyExtractor.getInstance().LENGTH);
		for(short i = 0 ; i < FuzzyExtractor.getInstance().LENGTH ; i ++)
			P[i] = (byte) (P[i] >> 16 & (short)0x0001);
		byte[] result = FuzzyExtractor.getInstance().encode(P);
		FuzzyExtractor.getInstance().decode(P);
		result = FuzzyExtractor.getInstance().encode(P);
		Util.arrayCopy(result,(short)0,apdu.getBuffer(), (short) ((short)ISO7816.OFFSET_CDATA),(short)(200));
		apdu.setOutgoingAndSend((short) (ISO7816.OFFSET_CDATA), (short) 200);
	 
	}
}
