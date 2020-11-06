package demo.pattern.template;

/**
 * @ClassName RoomForChineseSinger
 * @Description 必须实现点歌，选择性点了零食
 * @Author ma.kangkang
 * @Date 2020/11/6 16:20
 **/
public class RoomForChineseSinger extends KTVRoom {

    @Override
    protected void orderSong() {
        System.out.println("来首Jay Chou的歌");
    }

    // 零食可点可不点，这里点了
    @Override
    protected void orderExtra(){
        System.out.println("来点零食");
    }

}
