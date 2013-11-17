package logAnalyzer;

import logAnalyzer.model.Ip;
import logAnalyzer.model.Operation;
import logAnalyzer.model.Time;
import logAnalyzer.model.TotalBytes;

public class Entry {
	private Ip ip;
	private Operation operation;
	private Time time;
	private TotalBytes totalbytes;
	
	Entry(Ip ip, Operation operation, Time time, TotalBytes totalbytes)
	{
		setIp(ip);
		setOperation(operation);
		setTime(time);
		setTotalbytes(totalbytes);
	}
	

	public Ip getIp() {
		return ip;
	}
	public void setIp(Ip ip) {
		this.ip = ip;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public TotalBytes getTotalbytes() {
		return totalbytes;
	}
	public void setTotalbytes(TotalBytes totalbytes) {
		this.totalbytes = totalbytes;
	}
	
}
