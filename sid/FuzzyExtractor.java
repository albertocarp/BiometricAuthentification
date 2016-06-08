// ***********************************************************************
// Assembly         : 
// Author           : Alberto-PC
// Created          : 05-12-2016
//
// Last Modified By : Alberto-PC
// Last Modified On : 06-08-2016
// ***********************************************************************
// <copyright file="FuzzyExtractor.java" company="Military Technical Academy">
//     Copyright (c) . All rights reserved.
// </copyright>
// <summary></summary>
// ***********************************************************************
package sid;

import javacard.framework.ISO7816;
import javacard.framework.ISOException;
import javacard.framework.JCSystem;
import javacard.framework.Util;
import javacard.security.RandomData;

// TODO: Auto-generated Javadoc
/**
 * The Class FuzzyExtractor.
 */
public class FuzzyExtractor  implements IConsts{

	/** The m. */
	public  short  M=9;
	
	/** The t. */
	public  short  T=52;
	
	/** The d. */
	public  short  D=(short) (2*T+1);
	
	/** The length. */
	public  short  LENGTH=384;
	
	/** The p. */
	public short[] P = new short[15];
	
	/** The alpha to. */
	public short[] ALPHA_TO = new short[512];
	
	/** The encoded data. */
	public byte[]  ENCODED_DATA = new byte[512];
	
	/** The index of. */
	public short[] INDEX_OF = new short[512];
	
	/** The elp. */
	public short[]  elp = new short[128*128]; // 128x128
	
	/** The l. */
	public byte[]   l = new byte[256];
	
	/** The u_lu. */
	public short[]  u_lu = new short[256];
	
	/** The s. */
	public short[]  s = new short[256];
	
	/** The root. */
	public short[]  root = new short[256];
	
	/** The loc. */
	public short[]  loc = new short[256];
	
	/** The error. */
	public short[]  error = new short[256];
	
	/** The reg. */
	public short[]  reg = new short[256];
	
	/** The temp. */
	public byte[]   temp = new byte[512];
	
	/** The k. */
	public short    n=511,k=23;
	
	/** The p_ instance. */
	private static FuzzyExtractor p_Instance=null;
	
	/** The p_ memory. */
	private static MemoryManager p_Memory=null;
	
	/** The Galois. */
	public final short[] Galois = {(short)1,(short)406,(short)440,(short)119,(short)250,(short)474,(short)302,(short)37,(short)125,(short)31,(short)370,(short)503,(short)443,(short)492,(short)385,(short)302,(short)168,(short)28,(short)178,(short)108,(short)403,(short)164,(short)367,(short)438,(short)46,(short)344,(short)64,(short)38,(short)370,(short)206,(short)257,(short)101,(short)153,(short)96,(short)144,(short)372,(short)153,(short)219,(short)223,(short)141,(short)462,(short)3,(short)164,(short)12,(short)481,(short)181,(short)399,(short)167,(short)342,(short)340,(short)503,
			(short)286,(short)26,(short)373,(short)26,(short)164,(short)88,(short)209,(short)43,(short)40,(short)69,(short)435,(short)224,(short)211,(short)438,(short)125,(short)34,(short)380,(short)233,(short)325,(short)129,(short)445,(short)153,(short)271,(short)437,(short)443,(short)479,(short)435,(short)152,(short)75,(short)38,(short)325,(short)421,(short)402,(short)253,(short)35,(short)269,(short)101,(short)44,(short)247,(short)32,(short)172,(short)244,(short)142,(short)84,(short)213,(short)129,(short)436,(short)315,(short)237,(short)201,
			(short)156,(short)217,(short)345,(short)291,(short)280,(short)5,(short)143,(short)118,(short)205,(short)333,(short)474,(short)200,(short)410,(short)36,(short)478,(short)503,(short)346,(short)440,(short)105,(short)484,(short)166,(short)231,(short)36,(short)24,(short)51,(short)336,(short)360,(short)185,(short)511,(short)118,(short)342,(short)361,(short)428,(short)452,(short)351,(short)115,(short)234,(short)191,(short)266,(short)474,(short)228,(short)354,(short)106,(short)249,(short)263,(short)308,(short)341,(short)281,(short)146,(short)43,
			(short)497,(short)216,(short)410,(short)108,(short)72,(short)477,(short)67,(short)441,(short)250,(short)89,(short)427,(short)30,(short)491,(short)228,(short)251,(short)79,(short)13,(short)77,(short)342,(short)101,(short)26,(short)450,(short)16,(short)336,(short)393,(short)174,(short)230,(short)334,(short)403,(short)168,(short)60,(short)398,(short)297,(short)156,(short)362,(short)468,(short)201,(short)185,(short)234,(short)150,(short)476,(short)408,(short)232,(short)125,(short)408,(short)150,(short)486,(short)140,(short)445,(short)367,
			(short)46,(short)311,(short)458,(short)200,(short)382,(short)435,(short)314,(short)186,(short)194,(short)429,(short)156,(short)159,(short)444,(short)262,(short)436,(short)276,(short)257,(short)264,(short)425,(short)353,(short)366,(short)301,(short)435,(short)126,(short)445,(short)181,(short)19,(short)379,(short)341,(short)338,(short)136,(short)268,(short)206,(short)397,(short)393,(short)511,(short)170,(short)486,(short)342,(short)479,(short)104,(short)423,(short)20,(short)429,(short)111,(short)342,(short)193,(short)94,(short)251,(short)318,
			(short)2,(short)127,(short)337,(short)125,(short)472,(short)106,(short)83,(short)204,(short)378,(short)191,(short)2,(short)154,(short)38,(short)129,(short)82,(short)391,(short)391,(short)1,(short)335,(short)305,(short)233,(short)378,(short)145,(short)339,(short)61,(short)58,(short)125,(short)45,(short)387,(short)157,(short)306,(short)434,(short)51,(short)3,(short)452,(short)169,(short)142,(short)460,(short)351,(short)405,(short)501,(short)165,(short)251,(short)364,(short)71,(short)409,(short)342,(short)411,(short)359,(short)471,
			(short)457,(short)210,(short)92,(short)167,(short)316,(short)293,(short)447,(short)95,(short)322,(short)317,(short)239,(short)149,(short)289,(short)72,(short)313,(short)404,(short)210,(short)439,(short)6,(short)509,(short)129,(short)40,(short)286,(short)135,(short)68,(short)461,(short)225,(short)436,(short)511,(short)432,(short)270,(short)164,(short)204,(short)285,(short)5,(short)175,(short)411,(short)497,(short)199,(short)141,(short)201,(short)469,(short)270,(short)349,(short)191,(short)465,(short)303,(short)494,(short)343,(short)338,
			(short)101,(short)131,(short)266,(short)160,(short)107,(short)30,(short)77,(short)390,(short)474,(short)154,(short)155,(short)0,(short)1};
	
	/**
	 * Instantiates a new fuzzy extractor.
	 */
	private FuzzyExtractor() 
	{
		
	}
	
	/**
	 * Creates the instance.
	 */
	public void createInstance()
	{
		p_Memory = MemoryManager.getInstance();
		precomputeInput();
		generate_gf();
	}
	
	/**
	 * Precompute input.
	 */
	public void precomputeInput()
	{
		short i;
		for (i = 1; i < M; i++)
			P[i] = 0;
		P[0] = P[M] = 1;
		P[1] = 1;
		n = 1;
		for (i = 0; i <= M; ++i) {
			n *= 2;
		}
		n = (short) (n / 2 - 1);
	}
	
	/**
	 * Generate_gf.
	 */
	public void generate_gf()
	{
		short[] pShort = p_Memory.getShortRam();
		short i, mask;
		mask = 1;
		pShort[M] = 0;
		mask = 1;
		pShort[M] = 0;
		for (i = 0; i < M; i++) {
			pShort[i] = (short) mask;
			INDEX_OF[pShort[i]] = i;
			if (P[i] != 0)
				pShort[M] ^= mask;
			mask <<= 1;
		}
		INDEX_OF[pShort[M]] = M;
		mask >>= 1;
		for (i = (short) (M + 1); i < n; ++i) {
			if (pShort[(short)(i - 1)] >= mask)
				pShort[i] = (short) (pShort[M] ^ ((pShort[(short)(i - 1)] ^ mask) << 1));
			else
				pShort[i] = (short) (pShort[(short)(i - 1)] << 1);
			INDEX_OF[(short)pShort[i]] = i;
		}
		INDEX_OF[0] = -1;
		for( i = 0 ; i < n ; i++)
			ALPHA_TO[i]=pShort[i];
	}

/**
 * Xor.
 *
 * @param src1 the src1
 * @param offset the offset
 * @param src2 the src2
 * @param offset2 the offset2
 * @param size the size
 */
/*	
	void generate_poly()
	{
		short	ii, jj, ll, kaux;
		short	test, aux, nocycles, root, noterms, rdncy;

		cycle[0] = 0;
		size[0] = 1;
		cycle[10] = 1;
		size[1] = 1;
		jj = 1;
		do 
		{
			ii = 0;
			do {
				ii++;
				cycle[(short)(jj*10+ii)] = (short) ((short)(cycle[(short)((short)(jj*10)+ii - (short)1)] * 2) % n);
				size[jj]++;
				aux = (short) ((short)(cycle[(short)(jj*10+ii)] * 2) % n);
			} while (aux != cycle[(short)(jj*10)]);
			ll = 0;

			do 
			{
				ll++;
				test = 0;
				for (ii = 1; ((ii <= jj) && (test ==0)); ii++)
					for (kaux = 0; ((kaux < size[ii]) && (test == 0)); kaux++)
						if (ll == cycle[(short)(ii*10+kaux)])
							test = 1;
			} 
			while ((test != 0) && (ll < ((short)(n - 1))));
			if (test == 0) {
				jj++;
				cycle[(short)(jj*10)] = ll;
				size[jj] = 1;
			}
		} 
		while (ll < ((short)(n - 1)));
		nocycles = jj;
		kaux = 0;
		rdncy = 0;
		for (ii = 1; ii <= nocycles; ii++) {
			min[kaux] = 0;
			test = 0;
			for (jj = 0; ((jj < size[ii]) && (test == 0)); jj++)
				for (root = 1; ((root < D) && (test == 0)); root++)
					if (root == cycle[(short)(ii*10+jj)]) {
						test = 1;
						min[kaux] = ii;
					}
			if (min[kaux] != 0) {
				rdncy += size[min[kaux]];
				kaux++;
			}
		}
		noterms = kaux;
		kaux = 1;
		for (ii = 0; ii < noterms; ii++)
			for (jj = 0; jj < size[min[ii]]; jj++) {
				zeros[kaux] = cycle[(short)(min[ii]*10 + jj)];
				kaux++;
			}

		k = (short) (LENGTH - rdncy);
		G.G[0] = ALPHA_TO[zeros[1]];
		G.G[1] = 1;
		for (ii = 2; ii <= rdncy; ii++) {
			G.G[ii] = 1;
			for (jj = (short) (ii - 1); jj > 0; jj--)
				if (G.G[jj] != 0)
					G.G[jj] =(short) (G.G[(short)(jj - 1)] ^ ALPHA_TO[(short)((short)((INDEX_OF[G.G[jj]] + zeros[ii])) % n)]);
				else
					G.G[jj] = G.G[(short)(jj - 1)];
			G.G[0] = ALPHA_TO[(short)((short)((short)((INDEX_OF[(short)(G.G[(short)(0)])] + zeros[ii])) % n))];
		}
	}
	*/
	void xor(byte[] src1,short offset,byte[] src2,short offset2,short size)
	{
		byte[] pByte = p_Memory.geByteRam();
		for (short i = 0; i < size; i++)
			pByte[i] = (byte) (src1[(short)(i+offset)] ^ src2[(short)(i+offset2)]);
		Util.arrayCopyNonAtomic(pByte, OFFSET_START, src1, OFFSET_START, size);
	}
	
	/**
	 * Xor2.
	 *
	 * @param src1 the src1
	 * @param offset the offset
	 * @param src2 the src2
	 * @param offset2 the offset2
	 * @param size the size
	 * @param offset_out the offset_out
	 */
	void xor2(byte[] src1,short offset,byte[] src2,short offset2,short size,short offset_out)
	{
		byte[] pByte = p_Memory.geByteRam();
		for (short i = 0; i < size; i++)
			pByte[i] = (byte) (src1[(short)(i+offset)] ^ src2[(short)(i+offset2)]);
		Util.arrayCopyNonAtomic(pByte, OFFSET_START, temp, OFFSET_START, size);
	}
	
	/**
	 * Encode.
	 *
	 * @param data the data
	 * @return the byte[]
	 */
	byte[] encode(byte[] data)
	{
		byte[] temp_ram = p_Memory.geByteRam();
		short    i, j;
		short    feedback;
		short index = (short) (LENGTH - k);
		short index_minus_one = (short) (index - 1);
		short k_minus_one = (short) (k - 1);
		Util.arrayFillNonAtomic(temp_ram, OFFSET_START, index, (byte) 0x00);
		Util.arrayFillNonAtomic(ENCODED_DATA, OFFSET_START, (short)512, (byte) 0x00);
		for (i = k_minus_one; i >= 0; i--) {
			feedback = (short) (data[i] ^ temp_ram[index_minus_one]);
			if (feedback != 0) {
				for (j = index_minus_one; j > 0; j--)
					if (Galois[j] != 0)
						temp_ram[j] =  (byte) (temp_ram[(short)(j - 1)] ^ feedback);
					else
						temp_ram[j] = temp_ram[(short)(j - 1)];
				temp_ram[0] = (byte) (Galois[0] & feedback);
			}
			else {
				for (j = index_minus_one; j > 0; j--)
					temp_ram[j] = temp_ram[(short)(j - 1)];
				temp_ram[0] = 0;
			}
		}
		short bb_size = (short)(LENGTH - k);
		Util.arrayCopy(temp_ram, OFFSET_START, ENCODED_DATA, OFFSET_START, bb_size);
		Util.arrayCopy(data, OFFSET_START, ENCODED_DATA, bb_size, k);
		return ENCODED_DATA;
	}

	/**
	 * Decode.
	 *
	 * @param data the data
	 */
	public  void decode(byte[] data)
	{
		byte[] transientMemory = p_Memory.geByteRam();
		short[] temp_ram = p_Memory.getShortRam();
		short index = 0;
		for(; index < n ; index++)
			temp_ram[index]=ALPHA_TO[index];
		Util.arrayCopyNonAtomic(data, OFFSET_START, transientMemory, OFFSET_START,LENGTH);
		short i, j, u, q, t2, count = 0, syn_error = 0,s_local=0;
		t2 = (short) (2 * T);
		for (i = 1; i <= t2; i++)
		{
			s_local = s[i];
			for (j = 0; j < LENGTH; j++)
				if (transientMemory[j] != 0)
				{
					short t = (short)(i*j);
					if(t < 0) // (i*j > 32767 )
					{
						t &= (short)0x7FFF;
						t += 64;  // hack because n is always 511
					}
					t %= n;
					s_local ^= temp_ram[t]; 
				}	
			if (s_local != 0)
				syn_error = 1; 
			s[i] = (short) INDEX_OF[s_local];
	   }
		for(; index < n ; index++)
			ALPHA_TO[index]=temp_ram[index];
		Util.arrayCopyNonAtomic(l, OFFSET_START, transientMemory, OFFSET_START,(short)255);
		if (syn_error != 0) 
		{	
			temp_ram[0] = 0;	
			temp_ram[1] = s[1];
			elp[0] = 0;	
			elp[128] = 1;	
			for (i = 1; i < t2; i++) {
				elp[i] = -1;
				elp[(short)(128+i)] = 0;
			}
			transientMemory[0] = 0;
			transientMemory[1] = 0;
			u_lu[0] = -1;
			u_lu[1] = 0;
			u = 0;

			do {
				u++;
				if (temp_ram[u] == -1) {
					transientMemory[(short)(u + 1)] = transientMemory[u];
					for (i = 0; i <= transientMemory[u]; i++) {
						elp[(short)((u + 1)*128+i)] = elp[(short)(u*128+i)];
						elp[(short)(u*128+i)] =  INDEX_OF[(short)elp[(short)(u*128+i)]];
					}
				}
				else
				{
					q = (short) (u - 1);
					while ((temp_ram[q] == -1) && (q > 0))
						q--;
					if (q > 0) {
						j = q;
						do {
							j--;
							if ((temp_ram[j] != -1) && (u_lu[q] < u_lu[j]))
								q = j;
						} while (j > 0);
					}
					if (transientMemory[u] > (short)(transientMemory[q] + u - q))
						transientMemory[(short)(u + 1)] = transientMemory[u];
					else
						transientMemory[(short)(u + 1)] = (byte) (transientMemory[q] + u - q);

					for (i = 0; i < t2; i++)
						elp[(short)((u + 1)*128+i)] = 0;
					for (i = 0; i <= transientMemory[q]; i++)
						if (elp[(short)(q*128+i)] != -1)
							elp[(short)((short)(u + 1)*128+(short)(i + u - q))] =
							ALPHA_TO[(short)((short)(((short)(temp_ram[u] + n - temp_ram[q]) + elp[(short)(q*128+i)])) % n)];
					for (i = 0; i <= transientMemory[u]; i++) {
						elp[(short)((u + 1)*128+i)] ^= elp[(short)(u*128+i)];
						elp[(short)(u*128+i)] = INDEX_OF[(short)(elp[(short)(u*128+i)])];
					}
				}
				u_lu[(short)(u + 1)] = (short) (u - transientMemory[(short)(u + 1)]);
				if(u == 104)
					u = 104;
				if (u < t2) {
					if (s[(short)(u + 1)] != -1)
						temp_ram[(short)(u + 1)] = ALPHA_TO[(short)(s[(short)(u + 1)])];
					else
						temp_ram[(short)(u + 1)] = 0;
					for (i = 1; i <= transientMemory[(short)(u + 1)]; i++)
						if ((s[(short)(u + 1 - i)] != -1) && (elp[(short)((u + 1)*128+i)] != 0))
							temp_ram[(short)(u + 1)] ^= (short)(ALPHA_TO[(short)(s[(short)(u + 1 - i)] + INDEX_OF[(short)(elp[(short)((short)((u + 1)*128)+i)])]) % n]);
					temp_ram[(short)(u + 1)] =  INDEX_OF[(short)(temp_ram[(short)(u + 1)])];
				}
			}
			while ((u < t2) && (transientMemory[(short)(u + 1)] <= T));
			u++;	
			
		}
		Util.arrayCopyNonAtomic(data, (short)(LENGTH - k), data, OFFSET_START, k);
	}
	
	/**
	 * Gets the single instance of FuzzyExtractor.
	 *
	 * @return single instance of FuzzyExtractor
	 */
	public static FuzzyExtractor getInstance()
	{
		if(p_Instance == null)
			p_Instance = new FuzzyExtractor();
		return p_Instance;
	}
	
	/**
	 * Make fuzzy.
	 *
	 * @param B the b
	 * @param P the p
	 * @return the byte[]
	 */
	byte[] makeFuzzy(byte[] B,byte[] P)
	{
		xor2(B,OFFSET_START,P,OFFSET_START,LENGTH,OFFSET_START);
		decode(temp);
		encode(temp);
		xor(ENCODED_DATA,OFFSET_START,P,OFFSET_START,LENGTH);
		return ENCODED_DATA;
	}
}
