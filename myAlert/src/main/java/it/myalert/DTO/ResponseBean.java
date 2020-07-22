package it.myalert.DTO;

public class ResponseBean {

	private String codeString;
	private String message;
	private Object payload;
	
	public String getCodeString() {
		return codeString;
	}
	public void setCodeString(String codeString) {
		this.codeString = codeString;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}
	
	public static ResponseBean okResponse(Object obj) {
		ResponseBean rBean = new ResponseBean();
		rBean.setCodeString("OK");
		rBean.setMessage("OK");
		rBean.setPayload(obj);
		return rBean;
	}
	
	public static ResponseBean koResponseBean(Object obj, String message) {
		ResponseBean rBean = new ResponseBean();
		rBean.setCodeString("KO");
		rBean.setMessage(message);
		rBean.setPayload(obj);
		return rBean;
	}
}
