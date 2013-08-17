
public interface Account {
	public boolean addAccountListinfo(String i_account,String i_intro);
	public boolean deleteAccountListinfo(String i_account);
	public boolean updateAccountListinfo(String old_account, String new_account,String new_intro);
	public boolean SearchAccountInDB(String i_account);

}
