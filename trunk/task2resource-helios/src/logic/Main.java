package logic;

import java.util.HashSet;
import java.util.Set;

import uiclasses.*;
import ORM.*;

public class Main {
	protected Set<Request> requests = new HashSet<Request>();
	protected RequestMapper reqMap = new RequestMapper();
	protected UserMapper userMapper = new UserMapper();
	User user = new User();
	
	public void createUser(UIRequest uirequest) {
		Request request = new Request(uirequest);
		
		reqMap.setRequest(request);
	}
	
	public void acceptRequest(){
		requests = reqMap.getAllRequests();
		while(requests.iterator().hasNext()){
			Request tmpReq = requests.iterator().next();
			/*
			 * TODO
			 * Make some user checking
			 */
			if(true){
				user.setFirstName(tmpReq.getFirstName());
				user.setLastName(tmpReq.getLastName());
				user.setEmail(tmpReq.getEmail());
				//user.setOid(tmpReq.getOid());
				userMapper.setUser(user);
			} else{
				reqMap.deleteRequest(tmpReq);
			}
		}
	}
	
	
}
