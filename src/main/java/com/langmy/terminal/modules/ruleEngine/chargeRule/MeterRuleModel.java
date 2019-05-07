package com.langmy.terminal.modules.ruleEngine.chargeRule;

public class MeterRuleModel extends Rule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2671801653710606765L;
	private double carMetering;//小车计次收费
	private double truckMetering;//大车计次收费

	public MeterRuleModel(){}

	public double getCarMetering() {
		return carMetering;
	}

	public void setCarMetering(double carMetering) {
		this.carMetering = carMetering;
	}

	public double getTruckMetering() {
		return truckMetering;
	}

	public void setTruckMetering(double truckMetering) {
		this.truckMetering = truckMetering;
	}

	public MeterRuleModel(int id,String name,int meteringFree, double carMetering, double truckMetering) {
		super();
		this.id=id;
		this.name=name;
		this.freeTime = meteringFree;
		this.carMetering = carMetering;
		this.truckMetering = truckMetering;
	}

	public double countCarMoney() {
		return carMetering;
	}
	
	public double countTruckMoney() {
		return truckMetering;
	}

	@Override
	public String toString() {
		return "规则引擎计次收费模型 [小车计次收费=" + carMetering
				+ ", 大车计次收费=" + truckMetering + ", 免费时间=" + freeTime
				+ ", 主键=" + id + ", 名称=" + name + "]";
	}


}
