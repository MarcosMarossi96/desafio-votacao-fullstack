package br.com.vote.api.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.vote.api.form.VoteForm;
import br.com.vote.api.model.Vote;

public class VoteMock {
	
	private AssociateMock associateMock = new AssociateMock();
    private SessionMock sessionMock = new SessionMock();

	public Vote mockEntity() {
		return mockEntity(0);
	}

	public List<Vote> mockEntityList() {
		List<Vote> votes = new ArrayList<>();

		for (int i = 0; i < 14; i++) {
			votes.add(mockEntity(i));
		}

		return votes;
	}

	private Vote mockEntity(Integer number) {		
		Vote vote = new Vote();
		vote.setAssociate(associateMock.mockEntity());
		vote.setSession(sessionMock.mockEntity());
		vote.setVote(false);

		return vote;
	}

	public VoteForm mockForm() {
		return mockForm(0);
	}

	public VoteForm mockForm(Integer number) {
		VoteForm form = new VoteForm();
		form.setCpf(associateMock.mockEntity().getCpf());
		form.setCurrentDate(new Date());
		form.setSessionId(sessionMock.mockEntity(1).getId());
		form.setVote(true);		

		return form;
	}

}
