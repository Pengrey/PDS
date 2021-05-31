public class Demo {
    public static void main(String[] args) {
        Boss boss = new Boss();
        Accountant accountant = new Accountant(boss);
        Engineer engineer = new Engineer(boss);
        boss.registerWorkers(engineer, accountant);
        boss.enterMeetingRoom(engineer);
        boss.enterMeetingRoom(accountant);
        boss.leaveMeetingRoom(engineer);
        boss.enterMeetingRoom(accountant);
    }
}
