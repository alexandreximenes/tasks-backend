import br.ce.wcaquino.taskbackend.controller.TaskController;
import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.DateUtils;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TaskControllerTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskController taskController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() {
        Task task = new Task();
        task.setDueDate(LocalDate.now());
        try {
            taskController.save(task);
            fail("Não deveria chegar nesse ponto");
        } catch (ValidationException e) {
            assertEquals("Fill the task description", e.getMessage());
        }
    }


    @Test(expected = ValidationException.class)
    public void naoDeveSalvarTarefaSemDescricaoException() throws ValidationException {
        Task task = new Task();
        task.setDueDate(LocalDate.now());
        taskController.save(task);
    }


    @Test
    public void naoDeveSalvarTarefaSemData() {
        Task task = new Task();
        task.setTask("Tarefa 1");
        try {
            taskController.save(task);
            fail("Não deveria chegar nesse ponto");
        } catch (ValidationException e) {
            assertEquals("Fill the due date", e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemDataException() throws ValidationException {
        Task task = new Task();
        task.setTask("Tarefa 1");
        taskController.save(task);
    }


    @Test
    public void naoDeveSalvarTarefaComDataPassada() {
        Task task = new Task();
        task.setTask("Tarefa 1");
        task.setDueDate(LocalDate.now().minusYears(1));
        try {
            taskController.save(task);
            fail("Não deveria chegar nesse ponto");
        } catch (ValidationException e) {
            assertEquals("Due date must not be in past", e.getMessage());
        }
    }

    @Test(expected = ValidationException.class)
    public void naoDeveSalvarTarefaComDataPassadaException() throws ValidationException {
        Task task = new Task();
        task.setTask("Tarefa 1");
        task.setDueDate(LocalDate.now().minusYears(1));
        taskController.save(task);
    }


    @Test
    public void deveSalvarTarefaComSucesso() throws ValidationException {
        Task task = new Task();
        task.setTask("Tarefa 1");
        task.setDueDate(LocalDate.now());

        taskController.save(task);
        Mockito.verify(taskRepo).save(task);

    }
}
