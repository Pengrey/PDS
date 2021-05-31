public class Accountant implements Worker{
    
    private Boss boss;

    public Accountant(Boss b){
        this.boss = b;
    }

    @Override
    public void enterMeetingRoom() {
        if(boss.meetingRoomAvailable()){
            System.out.println("Accountant entered Meeting Room...");
            boss.setMeetingRoomStatus(false);
        }else{
            System.out.println("Accountant can't enter Meeting Room...");
        }
    }

    @Override
    public void leaveMeetingRoom() {
        System.out.println("Accountant leaving Meeting Room...");
        boss.setMeetingRoomStatus(true);        
    } 
}
