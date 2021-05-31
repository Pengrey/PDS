public class Engineer implements Worker{

    private Boss boss;

    public Engineer(Boss b){
        this.boss = b;
    }

    @Override
    public void enterMeetingRoom() {
        if(boss.meetingRoomAvailable()){
            System.out.println("Engineer entered Meeting Room...");
            boss.setMeetingRoomStatus(false);
        }else{
            System.out.println("Engineer can't enter Meeting Room...");
        }
    }

    @Override
    public void leaveMeetingRoom() {
        System.out.println("Engineer leaving Meeting Room...");
        boss.setMeetingRoomStatus(true);        
    } 
}
