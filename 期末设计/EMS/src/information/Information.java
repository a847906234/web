package information;

public class Information {
	private String orderNum;			
	private String EMSTrackNum;			
	private double pureWeight;			
	private double roughWeight;			
	private double number;				
	private String mainGoods;			
	private String receiName;			
	private String receiProvinCode;		
	private String receiAddr;			
	private String receiTel;			
	private String consignerName;		
	private String consignerProvinCode;
	private String consignerAddr;		
	private String consignerTel;		
	private String note;				
	

	
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getEMSTrackNum() {
		return EMSTrackNum;
	}
	public void setEMSTrackNum(String eMSTrackNum) {
		EMSTrackNum = eMSTrackNum;
	}
	public double getPureWeight() {
		return pureWeight;
	}
	public void setPureWeight(double pureWeight) {
		this.pureWeight = pureWeight;
	}
	public double getRoughWeight() {
		return roughWeight;
	}
	public void setRoughWeight(double roughWeight) {
		this.roughWeight = roughWeight;
	}
	public double getNumber() {
		return number;
	}
	public void setNumber(double number) {
		this.number = number;
	}
	public String getMainGoods() {
		return mainGoods;
	}
	public void setMainGoods(String mainGoods) {
		this.mainGoods = mainGoods;
	}
	public String getReceiName() {
		return receiName;
	}
	public void setReceiName(String receiName) {
		this.receiName = receiName;
	}
	public String getReceiProvinCode() {
		return receiProvinCode;
	}
	public void setReceiProvinCode(String receiProvinCode) {
		this.receiProvinCode = receiProvinCode;
	}
	public String getReceiAddr() {
		return receiAddr;
	}
	public void setReceiAddr(String receiAddr) {
		this.receiAddr = receiAddr;
	}
	public String getReceiTel() {
		return receiTel;
	}
	public void setReceiTel(String receiTel) {
		this.receiTel = receiTel;
	}
	public String getConsignerName() {
		return consignerName;
	}
	public void setConsignerName(String consignerName) {
		this.consignerName = consignerName;
	}
	public String getConsignerProvinCode() {
		return consignerProvinCode;
	}
	public void setConsignerProvinCode(String consignerProvinCode) {
		this.consignerProvinCode = consignerProvinCode;
	}
	public String getConsignerAddr() {
		return consignerAddr;
	}
	public void setConsignerAddr(String consignerAddr) {
		this.consignerAddr = consignerAddr;
	}
	public String getConsignerTel() {
		return consignerTel;
	}
	public void setConsignerTel(String consignerTel) {
		this.consignerTel = consignerTel;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}


	public Information(String orderNum, String eMSTrackNum, double pureWeight, double roughWeight, double number,
			String mainGoods, String receiName, String receiProvinCode, String receiAddr, String receiTel,
			String consignerName, String consignerProvinCode, String consignerAddr, String consignerTel, String note) {
		super();
		this.orderNum = orderNum;
		EMSTrackNum = eMSTrackNum;
		this.pureWeight = pureWeight;
		this.roughWeight = roughWeight;
		this.number = number;
		this.mainGoods = mainGoods;
		this.receiName = receiName;
		this.receiProvinCode = receiProvinCode;
		this.receiAddr = receiAddr;
		this.receiTel = receiTel;
		this.consignerName = consignerName;
		this.consignerProvinCode = consignerProvinCode;
		this.consignerAddr = consignerAddr;
		this.consignerTel = consignerTel;
		this.note = note;
	}
	public Information() {
		super();
	}
	public Information(double roughWeight,double number,String mainGoods) {
		this.roughWeight=roughWeight;
		this.number=number;
		this.mainGoods=mainGoods;
	}

	@Override
	public String toString() {
		return "Information [orderNum=" + orderNum + ", EMSTrackNum=" + EMSTrackNum + ", pureWeight=" + pureWeight
				+ ", roughWeight=" + roughWeight + ", number=" + number + ", mainGoods=" + mainGoods + ", receiName="
				+ receiName + ", receiProvinCode=" + receiProvinCode + ", receiAddr=" + receiAddr + ", receiTel="
				+ receiTel + ", consignerName=" + consignerName + ", consignerProvinCode=" + consignerProvinCode
				+ ", consignerAddr=" + consignerAddr + ", consignerTel=" + consignerTel + ", note=" + note + "]\n";
	}
}
