package demo.pattern.template;

/**
 * @ClassName RoomForAmericanSinger
 * @Description 必须实现点歌，但是不消费其他
 * @Author ma.kangkang
 * @Date 2020/11/6 16:21
 **/
public class RoomForAmericanSinger extends KTVRoom{

    @Override
    protected void orderSong() {
        System.out.println("来首节奏感强的英文歌");
    }
}
