import manager.TaskManager;
import model.Subtask;
import model.Epic;
import model.Status;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        List<Integer> subtasksId = new ArrayList<>();
        Epic epic1 = new Epic("Языки программирования",
        "объектно-ориентированные", Status.NEW, subtasksId);
        int epicId1 = taskManager.createEpic(epic1);
        Subtask subtask1 = new Subtask("Java", "изучение ООП",
                Status.NEW, epicId1);
        int subtaskId1 = taskManager.createSubtask(subtask1);
        Subtask subtask2 = new Subtask("SQL", "изучение команд",
                Status.NEW, epicId1);
        int subtaskId2 = taskManager.createSubtask(subtask2);
        Subtask subtask3 = new Subtask("html", "изучение frontend",
                Status.NEW, epicId1);
        int subtaskId3 = taskManager.createSubtask(subtask3);
        Subtask subtask4 = new Subtask("javascript", "изучение frontend",
                Status.NEW, epicId1);
        int subtaskId4 = taskManager.createSubtask(subtask4);
        taskManager.getEpicById(subtaskId1);
        taskManager.getEpicById(subtaskId2);
        taskManager.getEpicById(subtaskId3);
        taskManager.getSubtaskByEpicId(epicId1);
        subtask1.setStatus(Status.DONE);
        subtask2.setStatus(Status.DONE);
        subtask3.setStatus(Status.IN_PROGRESS);
        subtask4.setStatus(Status.DONE);
        taskManager.updateSubtask(subtask1);
        taskManager.updateSubtask(subtask2);
        taskManager.updateSubtask(subtask3);
        taskManager.updateSubtask(subtask4);
        taskManager.updateEpic(epic1);
        System.out.println(epic1);
        taskManager.deleteSubtaskById(subtaskId2);
        taskManager.updateEpic(epic1);
        System.out.println(epic1);
    }
}
