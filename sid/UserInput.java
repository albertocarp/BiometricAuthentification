package sid;

import javacard.framework.Util;
import sid.User.Biometrics;

public class UserInput implements IConsts 
{
	public byte[] Password = new byte[128];
	public short PasswordSize;
	public byte[] Identity = new byte[128];
	public short IdentitySize;
	public byte[] R_PRIM = new byte[512];
	static UserInput p_Instance = null;
	public static UserInput getInstance()
	{
		if(p_Instance == null)
			p_Instance = new UserInput();
		return p_Instance;
	}
	class Biometrics {
		byte[] Biometrics = new byte[512];
		short index=0;
		short size=0;
	}
	public Biometrics challange_biometric = new Biometrics(); 

	public void auth_update_Bio(byte[] arrayInfo,short offsetArray,short sizeBio)
	{
		Util.arrayCopy(arrayInfo,offsetArray,challange_biometric.Biometrics,challange_biometric.index,sizeBio);
		challange_biometric.index += sizeBio;
		challange_biometric.size += sizeBio;
	}
	
	public void setPassword(byte[] password,short offsetPassword,short sizePassword)
	{
		Util.arrayCopy(password, offsetPassword, this.Password, OFFSET_START, sizePassword);
		PasswordSize = sizePassword;
	}
	public void setIdentity(byte[] identity,short offsetIdentity,short sizeIdentity)
	{
		Util.arrayCopy(identity, offsetIdentity, Identity, OFFSET_START, sizeIdentity);
		IdentitySize = sizeIdentity;
	}
}
