package br.com.vote.api.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.vote.api.form.AssociateForm;
import br.com.vote.api.model.Associate;

public class AssociateMock {

	public Associate mockEntity() {
		return mockEntity(0);
	}

	public List<Associate> mockEntityList() {
		List<Associate> associates = new ArrayList<>();
		
        for (int i = 0; i < 14; i++) {
        	associates.add(mockEntity(i));
        }
        
        return associates;
	}	

	public Associate mockEntity(Integer number) {
		Associate associate = new Associate();
		associate.setCpf("2121212121" + number);
        associate.setName("Associate" + number);
        
        return associate;
	}
	
	public AssociateForm mockForm() {
		return mockForm(0);
	}
	
	public AssociateForm mockForm(Integer number) {
		AssociateForm form = new AssociateForm();
		form.setCpf("2121212121" + number);
		form.setName("Associate" + number);
        
		return form;
	}
}
