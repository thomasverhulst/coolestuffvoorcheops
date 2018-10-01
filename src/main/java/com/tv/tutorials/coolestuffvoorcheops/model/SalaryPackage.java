package com.tv.tutorials.coolestuffvoorcheops.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salarypackage")
public class SalaryPackage {

	@Id
	@Column(name = "idsalarypackage")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "grosssalary")
	private double grossSalary;

	@Column(name = "car")
	private String car;

	@Column(name = "dailyallowance")
	private Double dailyAllowance;

	@Column(name = "maaltijdchecques")
	private Double maaltijdchecques;

	@Column(name = "hospitalization")
	private boolean hospitalization;

	@Column(name = "groupinsurance")
	private boolean groupInsurance;

	public SalaryPackage() {

	}

	public SalaryPackage(double grossSalary, String car, Double dailyAllowance, Double maaltijdchecques,
			boolean hospitalization, boolean groupInsurance) {
		super();
		this.grossSalary = grossSalary;
		this.car = car;
		this.dailyAllowance = dailyAllowance;
		this.maaltijdchecques = maaltijdchecques;
		this.hospitalization = hospitalization;
		this.groupInsurance = groupInsurance;
	}

	public double getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(double grossSalary) {
		this.grossSalary = grossSalary;
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public Double getDailyAllowance() {
		return dailyAllowance;
	}

	public void setDailyAllowance(Double dailyAllowance) {
		this.dailyAllowance = dailyAllowance;
	}

	public Double getMaaltijdchecques() {
		return maaltijdchecques;
	}

	public void setMaaltijdchecques(Double maaltijdchecques) {
		this.maaltijdchecques = maaltijdchecques;
	}

	public boolean isHospitalization() {
		return hospitalization;
	}

	public void setHospitalization(boolean hospitalization) {
		this.hospitalization = hospitalization;
	}

	public boolean isGroupInsurance() {
		return groupInsurance;
	}

	public void setGroupInsurance(boolean groupInsurance) {
		this.groupInsurance = groupInsurance;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

}
