package cc.hubailmn.ap.lab7;

public class StudentController {

    private Student student;
    private StudentView view;

    public StudentController(Student student, StudentView view) {
        this.student = student;
        this.view = view;
        view.getSubmitButton().setOnAction(e -> handleSubmitButton());
    }

    public void handleSubmitButton() {
        String name = view.getNameInput();
        String id = view.getIdInput();

        if (name.isBlank() || id.isBlank()) {
            view.setOutputArea("Please fill in all fields.");
            return;
        }

        long parsedId;
        try {
            parsedId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            view.setOutputArea("Please enter a valid number.");
            return;
        }

        student.setName(name);
        student.setId(parsedId);

        view.setOutputArea(
                String.format("""
                        Name: %s
                        ID: %s
                        """, student.getName(), student.getId()
                )
        );

    }
}
