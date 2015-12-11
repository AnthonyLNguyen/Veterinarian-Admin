/**
 * 
 */
package edu.cpp.cs.cs141.extracredit;

import java.io.Serializable;

/**
 * @author antho
 *
 */
public class MedicalRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5974930004576293497L;
	private String info;
	private boolean current;
	
	public MedicalRecord(String info, boolean current) {
		super();
		this.info = info;
		this.current = current;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}
	
	
}
