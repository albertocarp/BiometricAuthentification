// ***********************************************************************
// Assembly         : 
// Author           : Alberto-PC
// Created          : 05-12-2016
//
// Last Modified By : Alberto-PC
// Last Modified On : 06-08-2016
// ***********************************************************************
// <copyright file="MemoryManager.java" company="Military Technical Academy">
//     Copyright (c) . All rights reserved.
// </copyright>
// <summary></summary>
// ***********************************************************************
package sid;

import javacard.framework.JCSystem;
/**
 * This class handles the management of the memory
 * @author Alberto-PC
 *
 */
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
	/**
	 * The class constructor
	 */
	private MemoryManager()
	{
		transientMemory = JCSystem.makeTransientByteArray((short) 386, JCSystem.CLEAR_ON_DESELECT);
		temp_ram = JCSystem.makeTransientShortArray((short) 512, JCSystem.CLEAR_ON_RESET);
	}
	/**
	 * Function that gets the ram_array
	 * @return the ram_array
	 */
	public short[] getShortRam()
	{
		return temp_ram;
	}
	
	/**
	 * Function that gets the ram_byte_array
	 * @return
	 */
	public byte[] geByteRam()
	{
		return transientMemory;
	}
	
	
}
