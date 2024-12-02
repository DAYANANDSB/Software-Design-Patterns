import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

interface Command {
    void execute();   //execute command
    void undo();      //undo command
}

// handleing various order actions
class OrderProcessor {
    private static final Logger logger = Logger.getLogger(OrderProcessor.class.getName());
    private List<String> history = new ArrayList<>();

    // Prepare the order
    public void prepareOrder() {
        System.out.println("Preparing order.");
        logger.info("Order prepared.");
        history.add("Order prepared.");
    }

    // Pack the order
    public void packOrder() {
        System.out.println("Packing order.");
        logger.info("Order packed.");
        history.add("Order packed.");
    }

    // Ship the order
    public void shipOrder() {
        System.out.println("Shipping order.");
        logger.info("Order shipped.");
        history.add("Order shipped.");
    }

    // Complete the order
    public void completeOrder() {
        System.out.println("Order completed.");
        logger.info("Order completed.");
        history.add("Order completed.");
    }

    // Cancel the order
    public void cancelOrder() {
        System.out.println("Order canceled.");
        logger.info("Order canceled.");
        history.add("Order canceled.");
    }

    // Print the history of actions
    public void printHistory() {
        System.out.println("Order History:");
        for (String action : history) {
            System.out.println(action);
        }
    }
}

// prepare the order
class PrepareOrderCommand implements Command {
    private OrderProcessor orderProcessor;

    public PrepareOrderCommand(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    @Override
    public void execute() {
        orderProcessor.prepareOrder();
    }

    @Override
    public void undo() {
        System.out.println("Undoing preparation.");
    }
}

// pack the order
class PackOrderCommand implements Command {
    private OrderProcessor orderProcessor;

    public PackOrderCommand(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    @Override
    public void execute() {
        orderProcessor.packOrder();
    }

    @Override
    public void undo() {
        System.out.println("Undoing packing.");
        // Code to undo packing
    }
}

// ship the order
class ShipOrderCommand implements Command {
    private OrderProcessor orderProcessor;

    public ShipOrderCommand(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    @Override
    public void execute() {
        orderProcessor.shipOrder();
    }

    @Override
    public void undo() {
        System.out.println("Undoing shipping.");
        
    }
}

// complete the order
class CompleteOrderCommand implements Command {
    private OrderProcessor orderProcessor;

    public CompleteOrderCommand(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    @Override
    public void execute() {
        orderProcessor.completeOrder();
    }

    @Override
    public void undo() {
        System.out.println("Undoing completion.");
        // Code to undo completion
    }
}

// cancel the order
class CancelOrderCommand implements Command {
    private OrderProcessor orderProcessor;

    public CancelOrderCommand(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    @Override
    public void execute() {
        orderProcessor.cancelOrder();
    }

    @Override
    public void undo() {
        System.out.println("Undoing cancellation.");
      
    }
}

class OrderManager {
    private List<Command> commands = new ArrayList<>();

    public void executeCommand(Command command) {
        try {
            command.execute();
            commands.add(command);
        } catch (Exception e) {
            System.out.println("Error executing command: " + e.getMessage());
        }
    }

    public void undoLastCommand() {
        if (!commands.isEmpty()) {
            Command lastCommand = commands.remove(commands.size() - 1);
            lastCommand.undo();
        } else {
            System.out.println("No commands to undo.");
        }
    }
}

public class ECommerceOrderProcessing {
    public static void main(String[] args) {
        OrderProcessor orderProcessor = new OrderProcessor();
        OrderManager orderManager = new OrderManager();
        Command prepareOrder = new PrepareOrderCommand(orderProcessor);
        Command packOrder = new PackOrderCommand(orderProcessor);
        Command shipOrder = new ShipOrderCommand(orderProcessor);
        Command completeOrder = new CompleteOrderCommand(orderProcessor);
        Command cancelOrder = new CancelOrderCommand(orderProcessor);

        orderManager.executeCommand(prepareOrder);
        orderManager.executeCommand(packOrder);
        orderManager.executeCommand(shipOrder);
        orderManager.executeCommand(completeOrder);

        orderManager.undoLastCommand();

        orderManager.executeCommand(cancelOrder);

        orderProcessor.printHistory();
    }
}
