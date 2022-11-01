package de.hska.lat.robot.connection.view;

import java.util.Timer;
import java.util.TimerTask;

import de.hska.lat.robot.abstractRobot.AbstractRobot;
import de.hska.lat.robot.abstractRobot.RobotConnectionListener;
import de.hska.lat.robot.connection.Connection;
import de.hska.lat.robot.displayFrame.DisplayFrame;

public class ConnectionView extends DisplayFrame implements RobotConnectionListener {

	private static final long serialVersionUID = -7180743603886059090L;

	ConnectionStatisticInfo connectionStatistic;
	ConnectionInfo connectionInfo;
	protected AbstractRobot<?, ?, ?> robot;

	private Connection connection;

	private Timer sheduler;

	public ConnectionView() {
		super("Connection view", false, true, false, false);

		this.setLayout(null);

		this.connectionInfo = new ConnectionInfo();
		this.connectionInfo.setLocation(0, 0);
		this.add(this.connectionInfo);

		this.connectionStatistic = new ConnectionStatisticInfo();
		this.connectionStatistic.setLocation(0, 100);
		this.add(this.connectionStatistic);

		this.setSize(210, 250);
		this.show();

		this.sheduler = new Timer();
		this.sheduler.scheduleAtFixedRate(new ConnectionViewRefreshTask(), 1000, 1000);

	}

	@Override
	public boolean setRobot(AbstractRobot<?, ?, ?> robot) {
		this.robot = robot;
		this.connection = robot.getConnection();

		this.robot.addConnectionListener(this);

		return (true);
	}

	/*
	 * public void setRobot(Robot robot) { this.robot = robot; this.connection =
	 * robot.getConnection();
	 * 
	 * this.robot.addConnectionListener(this); }
	 * 
	 * public void setRobot(RobotEmulator robotEmulator) { //this.robot = robot;
	 * this.connection = robotEmulator.getConnection();
	 * 
	 * // robotEmulator.addConnectionListener(this);
	 * 
	 * // this.connectionPanel.setRobot(robot); }
	 */

	class ConnectionViewRefreshTask extends TimerTask {

		public void run() {
			ConnectionView.this.connectionInfo
					.update(ConnectionView.this.connection);
			ConnectionView.this.connectionStatistic
					.update(ConnectionView.this.connection);
		}

	}

	@Override
	public void connected(AbstractRobot<?, ?, ?> robot) {
		this.connection = robot.getConnection();

		if (this.connection != null) {
			this.connectionInfo.update(ConnectionView.this.connection);
			this.connectionStatistic.update(this.connection);
		}
	}

	@Override
	public void disconnected(AbstractRobot<?, ?, ?> Robot) {

	}

	protected void onClosing() {
		super.onClosing();
		this.sheduler.cancel();
		this.robot.removeConnectionListener(this);
	}

}
