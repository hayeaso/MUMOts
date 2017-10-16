package helpers;

import java.util.ArrayList;
import java.util.List;

import com.pm.onlinetest.domain.Assignment;

public class AssignmentSelectDto {
	List<Assignment> selectedAssignments = new ArrayList<Assignment>();

	public List<Assignment> getSelectedAssignments() {
		return selectedAssignments;
	}

	public void setSelectedAssignments(List<Assignment> selectedAssignments) {
		this.selectedAssignments = selectedAssignments;
	}
	
	@Override
	public String toString() {
		return "AssignmentSelectDto selected index =" + selectedAssignments+ "";
	}
}
