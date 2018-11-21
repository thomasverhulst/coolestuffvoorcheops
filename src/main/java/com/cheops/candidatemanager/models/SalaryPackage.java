package com.cheops.candidatemanager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "salarypackage")
public class SalaryPackage implements Serializable {

	@Id
	@Column(name = "idsalarypackage")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "grosssalary")
	private double grossSalary;

	@Column(name = "car")
	@Size(max = 45, message = "{car.size}")
	private String car;

	@Column(name = "dailyallowance")
	private double dailyAllowance;

	@Column(name = "mealvouchers")
	private double mealVouchers;

	@Column(name = "hospitalization")
	private boolean hospitalization;

	@Column(name = "groupinsurance")
	private boolean groupInsurance;

	public SalaryPackage() {
	}

  public SalaryPackage(double grossSalary, @Size(max = 45, message = "{car.size}") String car, double dailyAllowance, double mealVouchers, boolean hospitalization, boolean groupInsurance) {
    this.grossSalary = grossSalary;
    this.car = car;
    this.dailyAllowance = dailyAllowance;
    this.mealVouchers = mealVouchers;
    this.hospitalization = hospitalization;
    this.groupInsurance = groupInsurance;
  }

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
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

	public double getDailyAllowance() {
		return dailyAllowance;
	}

	public void setDailyAllowance(double dailyAllowance) {
		this.dailyAllowance = dailyAllowance;
	}

	public double getMealVouchers() {
		return mealVouchers;
	}

	public void setMealVouchers(double mealVouchers) {
		this.mealVouchers = mealVouchers;
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

}
