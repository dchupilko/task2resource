package logic;

import java.util.HashSet;
import java.util.Set;

import uiclasses.*;

public class Main {
	protected Set<Request> requests = new HashSet<Request>();
	
	public void createUser(UIRequest uirequest) {
		Request request = new Request(uirequest);
	}
	
	
}
