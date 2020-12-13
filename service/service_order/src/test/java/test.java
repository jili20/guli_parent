import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * @author bing  @create 2020/12/12-9:30 下午
 */
public class test {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        System.out.println(newDate + result);
        // 20201212213437219
    }
}
