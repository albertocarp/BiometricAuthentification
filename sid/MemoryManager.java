package sid;

import javacard.framework.JCSystem;

public class MemoryManager 
{
	private static MemoryManager p_Instance=null;
	public  static MemoryManager getInstance()
	{
		if(p_Instance == null)
			p_Instance = new MemoryManager();
		return p_Instance;
	}
	private   byte[] transientMemory;
	private  short[] temp_ram;
	
	private MemoryManager()
	{
		transientMemory = JCSystem.makeTransientByteArray((short) 386, JCSystem.CLEAR_ON_DESELECT);
		temp_ram = JCSystem.makeTransientShortArray((short) 512, JCSystem.CLEAR_ON_RESET);
	}
	public short[] getShortRam()
	{
		return temp_ram;
	}
	
	public byte[] geByteRam()
	{
		return transientMemory;
	}
	
	
}
