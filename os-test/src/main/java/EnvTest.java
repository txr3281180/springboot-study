import java.util.Map;

/**
 * Created by xinrui.tian on 2019/6/17
 */
public class EnvTest {


    /*
     *   idea 打包一个可以运行jar包
     *
     *   File -> Project Structure -> Artifacts
     *
     *      >点“+” -> 选 JAR -> 选 From Modules with dependencies..
     *
     *      >Main class
     *      >extract to the target JAR  提取到 target
     *      >选择resources: META-INF  D:\MyProject\My\springboot-study\os-test\src\main\resources
     *      ok
     *
     *   Build -> Build Artifacts
     *      > action  -> Build
     *
     */


    public static void main(String[] args) {
        Map<String, String> getenv = System.getenv();
        for (String s : getenv.keySet()) {
            System.out.println(s + "\t" + getenv.get(s));
        }
    }
}
