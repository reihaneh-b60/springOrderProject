package readyInterview.springclaudeproject.mcpServer;

import java.io.*;

public class McpTestClient {
    public static void main(String[] args) throws Exception {

        String classpath = System.getProperty("java.class.path");
        String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";

        ProcessBuilder pb = new ProcessBuilder(
                javaBin, "-cp", classpath,
                "readyInterview.springclaudeproject.mcpServer.McpServer"
        );
        pb.redirectErrorStream(true); // خطاها را هم توی همون stream ببینیم، برای دیباگ

        Process process = pb.start();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        // پیام اول: initialize
        writer.write("{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"initialize\",\"params\":{}}");
        writer.newLine();
        writer.flush(); // چرا flush؟ چون بدون این، خط توی بافر جاوا می‌مونه و هرگز به پروسه‌ی سرور نمی‌رسه

        System.out.println("Response 1: " + reader.readLine());

        // پیام دوم: tools/list
        writer.write("{\"jsonrpc\":\"2.0\",\"id\":2,\"method\":\"tools/list\",\"params\":{}}");
        writer.newLine();
        writer.flush();

        System.out.println("Response 2: " + reader.readLine());

        // پیام سوم: tools/call
        writer.write("{\"jsonrpc\":\"2.0\",\"id\":3,\"method\":\"tools/call\",\"params\":{\"name\":\"getOrderStatus\",\"arguments\":{\"orderId\":\"999\"}}}");
        writer.newLine();
        writer.flush();

        System.out.println("Response 3: " + reader.readLine());

        process.destroy();
    }
}