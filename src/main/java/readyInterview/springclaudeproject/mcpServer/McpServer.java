package readyInterview.springclaudeproject.mcpServer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import readyInterview.springclaudeproject.entity.Order;
import readyInterview.springclaudeproject.repository.OrderRepository;
import readyInterview.springclaudeproject.service.OrderService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class McpServer {
    static final ObjectMapper mapper = new ObjectMapper();
    static OrderService orderService;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String line;
        while ((line = reader.readLine()) != null) {
            JsonNode request = mapper.readTree(line);
            String method = request.get("method").asText();
            int id = request.get("id").asInt();

            ObjectNode response = handleRequest(method, request, id);
            System.out.println(mapper.writeValueAsString(response));
            System.out.flush();
        }
    }

    static ObjectNode handleRequest(String method, JsonNode request, int id) {
        ObjectNode response = mapper.createObjectNode();
        response.put("jsonrpc", "2.0");
        response.put("id", id);

        switch (method) {
            case "initialize":
                ObjectNode initResult = mapper.createObjectNode();
                initResult.put("protocolVersion", "2024-11-05");
                response.set("result", initResult);
                break;

            case "tools/list":
                ObjectNode listResult = mapper.createObjectNode();
                ArrayNode tools = mapper.createArrayNode();

                ObjectNode tool = mapper.createObjectNode();
                tool.put("name", "getOrderStatus");
                tool.put("description", "Returns the status of an order by ID");

                ObjectNode inputSchema = mapper.createObjectNode();
                inputSchema.put("type", "object");
                ObjectNode properties = mapper.createObjectNode();
                ObjectNode orderId = mapper.createObjectNode();
                orderId.put("type", "string");
                properties.set("orderId", orderId);
                inputSchema.set("properties", properties);

                tool.set("inputSchema", inputSchema);
                tools.add(tool);
                listResult.set("tools", tools);
                response.set("result", listResult);
                break;
            case "tools/call":
                JsonNode params = request.get("params");
                String toolName = params.get("name").asText();
                JsonNode arguments = params.get("arguments");

                ObjectNode callResult = mapper.createObjectNode();
                ArrayNode content = mapper.createArrayNode();
                ObjectNode textContent = mapper.createObjectNode();
                textContent.put("type", "text");

                if (toolName.equals("getOrderStatus")) {
                    String ordersId = arguments.get("orderId").asText();
                    // اینجا در آینده به‌جای رشته‌ی ثابت، یک کوئری واقعی به دیتابیس می‌زنیم
                    String status = "";
                    textContent.put("text", "Order " + ordersId + " status: " + status);
                } else {
                    textContent.put("text", "Unknown tool: " + toolName);
                }

                content.add(textContent);
                callResult.set("content", content);
                response.set("result", callResult);
                break;

            default:
                ObjectNode error = mapper.createObjectNode();
                error.put("code", -32601);
                error.put("message", "Method not found: " + method);
                response.set("error", error);
        }
        return response;
    }
}