package MenaxhimiRezervimeveHotele;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;


public class HashSHA512 
{
	static Connection conn = null;
	static ResultSet res = null;
	static PreparedStatement pst = null;
	public static void main(String[] args) 
	{
		String salt = gjeneroSalt();
		String pass = "admin1";
		String hashedValue = hash(pass,salt);
		System.out.println("Salt: "+salt+"\nPass: "+pass+"\nHashedValue: "+hashedValue);
		
	}
	public static void updatePass(String username, String pass)
	{
		
		try
		{
			conn = dbConnect.connectDb("MenaxhimiRezervimeveHotele","root","1234");
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			String sql = "SELECT salt FROM administratoret WHERE username = '" + username + "' AND password = '"+pass+"'";
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			res.next();
			String salt = res.getString("salt");
			md.update((pass+salt).getBytes("UTF-8"));
			byte[] digest = md.digest();
			String saveHashedPass  = String.format("%064x", new java.math.BigInteger(1, digest));
			sql = "UPDATE administratoret SET password='"+saveHashedPass+"' WHERE Fjalekalimi='"+pass+"'";
			pst = conn.prepareStatement(sql);
			pst.execute();
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	public static String hash(String pass, String salt)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update((pass+salt).getBytes("UTF-8"));
			byte[] digest = md.digest();
			String saveHashedPass = String.format("%064x", new java.math.BigInteger(1, digest));
			return saveHashedPass;
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			return null;
		}
	}
	
	public static String gjeneroSalt()
	{
		String bashkesiaChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		char[] karakteret = bashkesiaChar.toCharArray();
		String salt="";
		Random rn = new Random();
		for(int i=0;i<20;i++)
		{
			salt += karakteret[rn.nextInt(karakteret.length)];
		}
		
		return salt;
	}
}