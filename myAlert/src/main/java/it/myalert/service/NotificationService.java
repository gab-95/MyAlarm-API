package it.myalert.service;

import com.sun.xml.bind.v2.runtime.unmarshaller.Receiver;

@Deprecated
public interface NotificationService {

	public boolean sendNotification(String receiver, String message);
}
