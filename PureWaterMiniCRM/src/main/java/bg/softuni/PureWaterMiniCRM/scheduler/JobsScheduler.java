package bg.softuni.PureWaterMiniCRM.scheduler;

import bg.softuni.PureWaterMiniCRM.services.OrderService;
import bg.softuni.PureWaterMiniCRM.services.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class JobsScheduler {

    private final OrderService orderService;
    private UserService userService;

    public JobsScheduler(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedDelay = 60, initialDelay = 30)
    public void checkExpiryDateOfOrdersAndDelete() {

        this.orderService.deleteOverDueOrders();

    }

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedDelay = 60, initialDelay = 30)
    public void checkOrdersAndComplete() {

        this.orderService.completeOrders();

    }

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedDelay = 1, initialDelay = 0)
    public void deleteObsoleteTestUsers() {

        this.userService.deleteObsoleteTestUsers();

    }
}
