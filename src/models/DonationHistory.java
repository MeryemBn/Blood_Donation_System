package models;

import java.util.Date;

public class DonationHistory {
	private String bloodGroup;
	private int donorId;
	private Date date;
	private String time;
	private int AmountDonated;

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public int getDonorId() {
		return donorId;
	}

	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}

	public int getAmountDonated() {
		return AmountDonated;
	}

	public void setAmountDonated(int amountDonated) {
		AmountDonated = amountDonated;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
