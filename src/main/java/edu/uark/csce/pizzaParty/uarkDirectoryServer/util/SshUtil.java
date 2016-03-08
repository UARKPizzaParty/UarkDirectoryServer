package edu.uark.csce.pizzaParty.uarkDirectoryServer.util;

import com.jcabi.ssh.SSHByPassword;
import com.jcabi.ssh.Shell;

import java.io.IOException;

public class SshUtil
{
	private static final String ADDRESS = "turing.csce.uark.edu";

	public static Boolean isValidLogin(String username, String password)
	{
		try
		{
			new Shell.Empty(new SSHByPassword(ADDRESS, 22, username, password)).exec("echo 'validating turing user'");
			return true;
		}
		catch (IOException e)
		{
			return false;
		}
	}
}
