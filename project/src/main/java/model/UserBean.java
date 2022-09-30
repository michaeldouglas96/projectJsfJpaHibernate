package model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import dao.DaoGeneric;
import entitys.UserRegister;
import repository.IDaoImpl;
import repository.IDaoUser;

@ViewScoped
@ManagedBean(name = "userBean")
public class UserBean {

	private UserRegister userRegister = new UserRegister();

	private DaoGeneric<UserRegister> daoGeneric = new DaoGeneric<UserRegister>();
	
	private List<UserRegister> userRegisters = new ArrayList<UserRegister>(); 
	
	private IDaoUser iDaoUser = new IDaoImpl();
	
	

	public String saveUser() {

		daoGeneric.save(userRegister);

		userRegister = new UserRegister();

		listUsers();

		return "";
	}

	public String updateUser() {

		userRegister = daoGeneric.update(userRegister);
		
		listUsers();

		return "";
	}

	public String newUser() {

		userRegister = new UserRegister();
		
		listUsers();

		return "";
	}

	public String deleteUser() {

		daoGeneric.delete(userRegister);
		
		userRegister = new UserRegister();
		
		listUsers();
		
		return "";
	}
	
	@PostConstruct
	public void listUsers() {
		
		userRegisters = daoGeneric.listUser(UserRegister.class);
		
	}
	
	public String acess() {
		
		 UserRegister userLog = iDaoUser.consultUser(userRegister.getUserName(), userRegister.getSenha());
		
		if(userRegister != null) {
			
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext external = context.getExternalContext();
			external.getSessionMap().put("userLogin", userRegister.getUserName());
			
			return "index.xhtml";
		}
		
		return "home.xhtml";
	}
	
	/*========================================== GET AND SET ====================================================== */

	public UserRegister getUserRegister() {
		return userRegister;
	}

	public void setUserRegister(UserRegister userRegister) {
		this.userRegister = userRegister;
	}

	public DaoGeneric<UserRegister> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<UserRegister> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}
	
	public List<UserRegister> getUserRegisters() {
		return userRegisters;
	}

}
