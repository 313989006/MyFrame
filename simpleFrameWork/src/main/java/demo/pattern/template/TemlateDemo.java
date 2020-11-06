package demo.pattern.template;

/**
 * @ClassName TemlateDemo
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/6 16:55
 **/
public class TemlateDemo {
    public static void main(String[] args) {
        RoomForChineseSinger singer = new RoomForChineseSinger();
        singer.procedure();

        RoomForAmericanSinger americanSinger = new RoomForAmericanSinger();
        americanSinger.procedure();
    }
}
