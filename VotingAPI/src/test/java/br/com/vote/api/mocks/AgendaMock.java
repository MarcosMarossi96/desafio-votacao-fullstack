package br.com.vote.api.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.vote.api.form.AgendaForm;
import br.com.vote.api.model.Agenda;

public class AgendaMock {

	public Agenda mockEntity() {
		return mockEntity(0);
	}

	public List<Agenda> mockEntityList() {
		List<Agenda> books = new ArrayList<>();
		
        for (int i = 0; i < 14; i++) {
        	books.add(mockEntity(i));
        }
        
        return books;
	}	

	private Agenda mockEntity(Integer number) {
		Agenda agenda = new Agenda();
        agenda.setTitle("Test" + number);
        agenda.setDescription("Description" + number);
        agenda.setId(Long.valueOf(number));
        
        return agenda;
	}
	
	public AgendaForm mockForm() {
		return mockForm(0);
	}
	
	public AgendaForm mockForm(Integer number) {
		AgendaForm form = new AgendaForm();
        form.setTitle("Title1" + number);
        form.setDescription("Desc" + number);
        
		return form;
	}
}
