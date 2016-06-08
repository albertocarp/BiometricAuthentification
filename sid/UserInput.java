// ***********************************************************************
// Assembly         : 
// Author           : Alberto-PC
// Created          : 05-12-2016
//
// Last Modified By : Alberto-PC
// Last Modified On : 06-08-2016
// ***********************************************************************
// <copyright file="UserInput.java" company="Military Technical Academy">
//     Copyright (c) . All rights reserved.
// </copyright>
// <summary></summary>
// ***********************************************************************
package sid;

import javacard.framework.Util;
import sid.User.Biometrics;

// TODO: Auto-generated Javadoc
/**
 * The Class UserInput.
 */
public class UserInput implements IConsts 
{
	
	/** The Password. */
	public byte[] Password = new byte[128];
	
	/** The Password size. */
	public short PasswordSize;
	
	/** The Identity. */
	public byte[] Identity = new byte[128];
	
	/** The Identity size. */
	public short IdentitySize;
	
	/** The r prim. */
	public byte[] R_PRIM = new byte[512];
	
	/** The p_ instance. */
	static UserInput p_Instance = null;
	
	/**
	 * Gets the single instance of UserInput.
	 *
	 * @return single instance of UserInput
	 */
	public static UserInput getInstance()
	{
		if(p_Instance == null)
			p_Instance = new UserInput();
		return p_Instance;
	}
	
	/**
	 * The Class Biometrics.
	 */
	class Biometrics {
		
		/** The Biometrics. */
		byte[] Biometrics = new byte[512];
		
		/** The index. */
		short index=0;
		
		/** The size. */
		short size=0;
	}
	
	/** The challange_biometric. */
	public Biometrics challange_biometric = new Biometrics(); 

	/**
	 * Auth_update_ bio.
	 *
	 * @param arrayInfo the array info
	 * @param offsetArray the offset array
	 * @param sizeBio the size bio
	 */
	public void auth_update_Bio(byte[] arrayInfo,short offsetArray,short sizeBio)
	{
		Util.arrayCopy(arrayInfo,offsetArray,challange_biometric.Biometrics,challange_biometric.index,sizeBio);
		challange_biometric.index += sizeBio;
		challange_biometric.size += sizeBio;
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
}
