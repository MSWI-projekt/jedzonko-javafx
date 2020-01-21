package jedzonko.utils;

import jedzonko.model.Account;

public class Login
{
	public static String validateCredentials(String login, String password)
	{
		Account account = DBManager.selectOneWhere("Account", "login", login, 0);
		if (account == null)
		{
			return "wrong login";
		}
		if (account.getPassword() != password.hashCode())
		{
			return "wrong password";
		}
		return account.getType();
	}
}
