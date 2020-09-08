package it.myalert.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import it.myalert.service.NotificationService;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

	@Override
	public boolean sendNotification(String receiver, String message) {
		System.out.print("httpRequest"+ getPostsPlainJSON());
		return false;
	}
	
	
	private RestTemplate restTemplate = new RestTemplate();

    public NotificationServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getPostsPlainJSON() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        return this.restTemplate.getForObject(url, String.class);
    }

}
