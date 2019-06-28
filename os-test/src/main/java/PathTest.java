import java.io.File;
import java.nio.file.Path;

/**
 * Created by xinrui.tian on 2019/6/26
 */
public class PathTest {

    public static void main(String[] args) {

        String property = System.getProperty("user.dir");
        System.out.println(property);

        File file = new File(property);
        Path path = file.toPath();
        System.out.println(path);


        System.out.println(File.separator);


        //File,Path, System.getProperty 自动根据系统使用分正确的隔符

        /*
        * windows:
        * D:\MyProject\My\springboot-study\os-test
        * D:\MyProject\My\springboot-study\os-test
        * \
        */
        /*
        * linux:
        * /opt/sumscope/xinrui.tian
        * /opt/sumscope/xinrui.tian
        * /
        */
    }
}
