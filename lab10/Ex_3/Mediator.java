public interface Mediator {
    void registerWorkers(Worker eWorker, Worker aWorker);
    void setMeetingRoomStatus(boolean isAvailable);
    boolean meetingRoomAvailable();
    void enterMeetingRoom(Worker worker);
    void leaveMeetingRoom(Worker worker);
}
