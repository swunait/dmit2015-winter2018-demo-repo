package dmit2015.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import dmit2015.model.ex02.BankAccount;
import dmit2015.model.ex02.Transaction;
import dmit2015.model.ex03.InsufficientFundsException;

@Named("currentBankAccountController")
@SessionScoped
public class BankAccountController implements Serializable {
	private static final long serialVersionUID = 1L;

	private BankAccount currentBankAccount = new BankAccount();	// +getter 
	private Double amount;										// +getter +setter
	private String description;									// +getter +setter
	private BarChartModel currentBarChartModel;				// +getter
	
	@PostConstruct
	public void init() {
		currentBarChartModel = new BarChartModel();
		currentBarChartModel.setTitle("Transaction Amount Chart");
		Axis xAxis  = currentBarChartModel.getAxis(AxisType.X);
		xAxis.setLabel("Description");
		Axis yAxis = currentBarChartModel.getAxis(AxisType.Y);
		yAxis.setLabel("Amount");
	}
	
	public void doWithdraw(ActionEvent event) {
		try {
			currentBankAccount.withdraw(amount, description);
			amount = null;
			description = null;		
			populateChart();
		} catch (InsufficientFundsException e) {
			Messages.addGlobalError(e.getMessage());
		}
	}

	public void doDeposit(ActionEvent event) {
		currentBankAccount.deposit(amount, description);
		amount = null;
		description = null;		
		populateChart();
	}

	public void populateChart() {
		// erase the previous data from the chart
		currentBarChartModel.clear();
		
		ChartSeries amountSeries = new ChartSeries();
		amountSeries.setLabel("Transaction Amount");
		for(Transaction currentTransaction : currentBankAccount.getTransactions() ) {
			amountSeries.set(currentTransaction.getDescription(), currentTransaction.getAmount());
		}
		currentBarChartModel.addSeries(amountSeries);
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BankAccount getCurrentBankAccount() {
		return currentBankAccount;
	}

	public BarChartModel getCurrentBarChartModel() {
		return currentBarChartModel;
	}
	
}
