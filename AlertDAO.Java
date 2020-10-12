package org.ubique.alerts;

private Interface AlertDAO{
	public UUID addAlert(Date time);
	public Date getAlert(UUID id);
}

public class MapAlertDAO implements AlertDAO
{	
	private final Map<UUID, Date> alerts = new HashMap<UUID, Date>();

	public UUID addAlert(Date time) {
		UUID id = UUID.randomUUID();
		this.alerts.put(id, time);
		return id;
	}

	public Date getAlert(UUID id) {
	return this.alerts.get(id);
	}
}

class AlertService {
	private AlertDAO alertDAO;
	
	public AlertService(AlertDAO alertDAO){
		this.alertDAO = alertDAO;
	}

	public UUID raiseAlert() {
		return this. alertDAO.addAlert(new Date());
	}

	public Date getAlertTime(UUID id) {
		return this. alertDAO.getAlert(id);
	} 
}


