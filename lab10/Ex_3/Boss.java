public class Boss implements Mediator{

    Worker engineer, accountant;
    boolean meetingRoomAvailable = true;
    @Override
    public void registerWorkers(Worker eWorker, Worker aWorker) {
        this.engineer = eWorker;
        this.accountant = aWorker;
    }

    @Override
    public void setMeetingRoomStatus(boolean inUse) {
        this.meetingRoomAvailable = inUse;
    }

    @Override
    public boolean meetingRoomAvailable() {
        return this.meetingRoomAvailable;
    }

    @Override
    public void enterMeetingRoom(Worker worker) {
        worker.enterMeetingRoom();
    }

    @Override
    public void leaveMeetingRoom(Worker worker) {
        worker.leaveMeetingRoom();
    }
}
