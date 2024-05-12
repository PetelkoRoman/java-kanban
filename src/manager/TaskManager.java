package manager;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {
    // объявление хранения задач, подзадач, епиков
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private int idGenerator = 0;

    // функции создания задач, подзадач, епиков с присвоением id

    public Integer createTask(Task task) {
        int id = idGenerator++;
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    public Integer createEpic(Epic epic) {
        int id = idGenerator++;
        epic.setId(id);
        epics.put(id, epic);
        return id;
    }

    public Integer createSubtask(Subtask subtask) {
        Epic epic = epics.get(subtask.getEpicId());
        if (epic == null) {
            return null;
        }
        int id = idGenerator++;
        subtask.setId(id);
        subtasks.put(id, subtask);
        epic.addSubtasksId(id);
        return id;
    }

    //функции получения задач, подзадач, епиков

    public List<Task> getAllTasks(){
        return new ArrayList<>(tasks.values());
    }

    public List<Subtask> getAllSubtasks(){
        return new ArrayList<>(subtasks.values());
    }

    public List<Epic> getAllEpics(){
        return new ArrayList<>(epics.values());
    }

    //функции удаления задач, подзадач, епиков

    public void deleteTasks(){
        tasks.clear();
    }

    public void deleteSubtasks(){
        subtasks.clear();
    }

    public void deleteEpics(){
        subtasks.clear();
        epics.clear();
    }


    // функции получения задач, подзадач, епиков по определенному id

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public List<Subtask> getSubtaskByEpicId(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return null;
        } else {
            List<Subtask> subtasksList = new ArrayList<>();
            List<Integer> subtasksId = epic.getSubtasksId(); // возвращает список id подзадач
            for (int i = 0; i < subtasksId.size(); i++) { // проходим по списку подзадач
                int subtaskId = subtasksId.get(i); // перебираем каждую подзадачу
                Subtask subtask = subtasks.get(subtaskId); // из Map получаем саму подзадачу по id
                subtasksList.add(subtask);

            }
            return subtasksList;
        }
    }


    public Epic getEpicById(int id){
        return epics.get(id);
    }

    // функции удаления задач, подзадач, епиков по определенному id

    public void deleteTaskById(int id){
        tasks.remove(id);
    }

    public void deleteSubtaskById(int id) {

        int epicId = subtasks.get(id).getEpicId();
        List<Integer> subtasksId = epics.get(epicId).getSubtasksId();
        subtasksId.remove((Integer) id);
        subtasks.remove(id);

    }

    public void deleteEpicById(int id){
        epics.remove(id);
    }

    //функции обновления задач, подзадач, епиков

    public void updateTask(Task task){
        if (tasks.get(task.getId()) != null) { // проверяю на наличие в Map задачи с корректным id на неравенство null
            tasks.put(task.getId(),task); // перезаписываю задачу с новыми данными
        }
    }

    public void updateSubtask(Subtask subtask) {
        if (subtasks.get(subtask.getId()).getStatus() != subtask.getStatus()){
            subtasks.get(subtask.getId()).setStatus(subtask.getStatus());
        }
    }

    public void updateEpic(Epic epic) {
        List<Subtask> subtasks = getSubtaskByEpicId(epic.getId());

            boolean allSubtasksNEW = true;
            boolean allSubtasksDONE = true;

            for (Subtask subtask : subtasks) {
                if (subtask != null) {
                    if (subtask.getStatus() != Status.NEW) {
                        allSubtasksNEW = false;
                    }
                    if (subtask.getStatus() != Status.DONE) {
                        allSubtasksDONE = false;
                    }
                    if ((!allSubtasksNEW) && (!allSubtasksDONE)) {
                        subtask.setStatus(Status.IN_PROGRESS);
                    }
                    if (allSubtasksNEW) {
                        epic.setStatus(Status.NEW);
                    } else if (allSubtasksDONE) {
                        epic.setStatus(Status.DONE);
                    } else {
                        epic.setStatus(Status.IN_PROGRESS);
                    }
                }
            }
        }
    }


