package demo.generic;

import java.util.Random;

/**
 * @ClassName RobotFactory
 * @Description GenericFactory 的具体实现
 * @Author ma.kangkang
 * @Date 2020/11/1 11:07
 **/
public class RobotFactory implements GenericFactory<String,Integer> {

    private String[] stringRobot = new String[]{"Hello","Hi"};

    private Integer[] integerRobot = new Integer[]{111,222};
    @Override
    public String nextObject() {
        Random random = new Random();
        return stringRobot[random.nextInt(2)];
    }

    @Override
    public Integer nextNumber() {
        Random random = new Random();
        return integerRobot[random.nextInt(2)];
    }

    public static void main(String[] args) {
        GenericFactory<String,Integer> genericFactory = new RobotFactory();
        System.out.println(genericFactory.nextObject());
        System.out.println(genericFactory.nextNumber());
    }
}
