package br.com.vote.api.mocks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.vote.api.form.SessionForm;
import br.com.vote.api.model.Session;

public class SessionMock {

	public Session mockEntity() {
		return mockEntity(0);
	}

	public List<Session> mockEntityList() {
		List<Session> sessions = new ArrayList<>();

		for (int i = 0; i < 14; i++) {
			sessions.add(mockEntity(i));
		}

		return sessions;
	}

	public Session mockEntity(Integer number) {
		AgendaMock agendaMock = new AgendaMock();

		Session session = new Session();
		session.setStart(new Date());
		session.setEnd(getEndDate());
		session.setId(Long.valueOf(number));
		
		session.setAgenda(agendaMock.mockEntity());

		return session;
	}

	public SessionForm mockForm() {
		return mockForm(0);
	}

	public SessionForm mockForm(Integer number) {
		SessionForm form = new SessionForm();
		form.setStart(new Date());
		form.setEnd(getEndDate());
		form.setAgendaId(Long.valueOf(number));

		return form;
	}

	private Date getEndDate() {
		Date date = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.add(Calendar.MINUTE, 2);
		return calendar.getTime();
	}

}
